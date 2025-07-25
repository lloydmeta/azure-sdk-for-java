// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package io.clientcore.http.netty4;

import io.clientcore.core.http.client.HttpClient;
import io.clientcore.core.http.models.ProxyOptions;
import io.clientcore.core.instrumentation.logging.ClientLogger;
import io.clientcore.core.utils.configuration.Configuration;
import io.clientcore.http.netty4.implementation.ChannelInitializationProxyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.concurrent.ThreadFactory;

/**
 * Builder for creating instances of NettyHttpClient.
 */
public class NettyHttpClientBuilder {
    private static final ClientLogger LOGGER = new ClientLogger(NettyHttpClientBuilder.class);

    private static final String EPOLL = "io.netty.channel.epoll.Epoll";
    private static final String EPOLL_CHANNEL = "io.netty.channel.epoll.EpollSocketChannel";
    private static final String EPOLL_EVENT_LOOP_GROUP = "io.netty.channel.epoll.EpollEventLoopGroup";
    private static final boolean IS_EPOLL_AVAILABLE;
    private static final Class<? extends SocketChannel> EPOLL_CHANNEL_CLASS;
    private static final Class<?> EPOLL_EVENT_LOOP_GROUP_CLASS;
    private static final MethodHandle EPOLL_EVENT_LOOP_GROUP_CREATOR;

    private static final String KQUEUE = "io.netty.channel.kqueue.KQueue";
    private static final String KQUEUE_CHANNEL = "io.netty.channel.kqueue.KQueueSocketChannel";
    private static final String KQUEUE_EVENT_LOOP_GROUP = "io.netty.channel.kqueue.KQueueEventLoopGroup";
    private static final boolean IS_KQUEUE_AVAILABLE;
    private static final Class<? extends SocketChannel> KQUEUE_CHANNEL_CLASS;
    private static final Class<?> KQUEUE_EVENT_LOOP_GROUP_CLASS;
    private static final MethodHandle KQUEUE_EVENT_LOOP_GROUP_CREATOR;

    static {
        // Inspect the class path to determine is native transports are available.
        // If they are, this will determine runtime behaviors.
        boolean isEpollAvailable;
        Class<? extends SocketChannel> epollChannelClass;
        Class<?> epollEventLoopGroupClass;
        MethodHandle epollEventLoopGroupCreator;
        try {
            Class<?> epollClass = Class.forName(EPOLL);
            isEpollAvailable = (boolean) epollClass.getDeclaredMethod("isAvailable").invoke(null);

            epollChannelClass = getChannelClass(EPOLL_CHANNEL);
            epollEventLoopGroupClass = Class.forName(EPOLL_EVENT_LOOP_GROUP);
            epollEventLoopGroupCreator = MethodHandles.publicLookup()
                .unreflectConstructor(epollEventLoopGroupClass.getDeclaredConstructor(ThreadFactory.class));
            LOGGER.atVerbose()
                .addKeyValue("epollAvailable", isEpollAvailable)
                .log("Lookup for Epoll completed without error.");
            if (!isEpollAvailable) {
                LOGGER.atVerbose()
                    .setThrowable((Throwable) epollClass.getDeclaredMethod("unavailabilityCause").invoke(null))
                    .log("Epoll classes were available but can't be used.");
            }
        } catch (ReflectiveOperationException ignored) {
            LOGGER.atVerbose().log("Epoll is unavailable and won't be used.");
            isEpollAvailable = false;
            epollChannelClass = null;
            epollEventLoopGroupClass = null;
            epollEventLoopGroupCreator = null;
        }

        IS_EPOLL_AVAILABLE = isEpollAvailable;
        EPOLL_CHANNEL_CLASS = epollChannelClass;
        EPOLL_EVENT_LOOP_GROUP_CLASS = epollEventLoopGroupClass;
        EPOLL_EVENT_LOOP_GROUP_CREATOR = epollEventLoopGroupCreator;

        boolean isKqueueAvailable;
        Class<? extends SocketChannel> kqueueChannelClass;
        Class<?> kqueueEventLoopGroupClass;
        MethodHandle kqueueEventLoopGroupCreator;
        try {
            Class<?> kqueueClass = Class.forName(KQUEUE);
            isKqueueAvailable = (boolean) kqueueClass.getDeclaredMethod("isAvailable").invoke(null);

            kqueueChannelClass = getChannelClass(KQUEUE_CHANNEL);
            kqueueEventLoopGroupClass = Class.forName(KQUEUE_EVENT_LOOP_GROUP);
            kqueueEventLoopGroupCreator = MethodHandles.publicLookup()
                .unreflectConstructor(kqueueEventLoopGroupClass.getDeclaredConstructor(ThreadFactory.class));
            LOGGER.atVerbose()
                .addKeyValue("kqueueAvailable", isKqueueAvailable)
                .log("Lookup for KQueue completed without error.");
            if (!isKqueueAvailable) {
                LOGGER.atVerbose()
                    .setThrowable((Throwable) kqueueClass.getDeclaredMethod("unavailabilityCause").invoke(null))
                    .log("KQueue classes were available but can't be used.");
            }
        } catch (ReflectiveOperationException ignored) {
            LOGGER.atVerbose().log("KQueue is unavailable and won't be used.");
            isKqueueAvailable = false;
            kqueueChannelClass = null;
            kqueueEventLoopGroupClass = null;
            kqueueEventLoopGroupCreator = null;
        }

        IS_KQUEUE_AVAILABLE = isKqueueAvailable;
        KQUEUE_CHANNEL_CLASS = kqueueChannelClass;
        KQUEUE_EVENT_LOOP_GROUP_CLASS = kqueueEventLoopGroupClass;
        KQUEUE_EVENT_LOOP_GROUP_CREATOR = kqueueEventLoopGroupCreator;
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends SocketChannel> getChannelClass(String className) throws ClassNotFoundException {
        return (Class<? extends SocketChannel>) Class.forName(className);
    }

    private EventLoopGroup eventLoopGroup;
    private Class<? extends SocketChannel> channelClass;
    private SslContext sslContext;

    private Configuration configuration;
    private ProxyOptions proxyOptions;
    private Duration connectTimeout;
    private Duration readTimeout;
    private Duration responseTimeout;
    private Duration writeTimeout;

    /**
     * Creates a new instance of {@link NettyHttpClientBuilder}.
     */
    public NettyHttpClientBuilder() {
    }

    /**
     * Sets the event loop group for the Netty client.
     * <p>
     * By default, if no {@code eventLoopGroup} is configured and no native transports are available (Epoll KQueue)
     * {@link NioEventLoopGroup} will be used.
     * <p>
     * If native transports are available, the {@link EventLoopGroup} implementation for the native transport will be
     * chosen over {@link NioEventLoopGroup}.
     *
     * @param eventLoopGroup The event loop group.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder eventLoopGroup(EventLoopGroup eventLoopGroup) {
        this.eventLoopGroup = eventLoopGroup;
        return this;
    }

    /**
     * Sets the {@link SocketChannel} type that will be used to create channels of that type.
     * <p>
     * By default, if no {@code channelClass} is configured and no native transports are available (Epoll, KQueue)
     * {@link NioSocketChannel} will be used.
     * <p>
     * If native transports are available, the {@link SocketChannel} implementation for the native transport will be
     * chosen over {@link NioSocketChannel}.
     *
     * @param channelClass The {@link SocketChannel} class to use.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder channelClass(Class<? extends SocketChannel> channelClass) {
        this.channelClass = channelClass;
        return this;
    }

    /**
     * Sets the {@link SslContext} that will be used to configure SSL/TLS when establishing secure connections.
     * <p>
     * If this is left unset a default {@link SslContext} will be used to establish secure connections.
     *
     * @param sslContext The {@link SslContext} for SSL/TLS.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder sslContext(SslContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }

    /**
     * Sets the configuration store that is used during construction of the HTTP client.
     * <p>
     * The default configuration store is a clone of the {@link Configuration#getGlobalConfiguration() global
     * configuration store}.
     *
     * @param configuration The configuration store.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /**
     * Sets the connection timeout.
     *
     * @param connectTimeout The connection timeout.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder connectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * Sets the read timeout.
     *
     * @param readTimeout The read timeout.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder readTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Sets the response timeout.
     *
     * @param responseTimeout The response timeout.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder responseTimeout(Duration responseTimeout) {
        this.responseTimeout = responseTimeout;
        return this;
    }

    /**
     * Sets the write timeout.
     *
     * @param writeTimeout The write timeout.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder writeTimeout(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    /**
     * Sets the proxy options.
     *
     * @param proxyOptions The proxy options.
     * @return The updated builder.
     */
    public NettyHttpClientBuilder proxy(ProxyOptions proxyOptions) {
        this.proxyOptions = proxyOptions;
        return this;
    }

    /**
     * Builds the NettyHttpClient.
     *
     * @return A configured NettyHttpClient instance.
     */
    public HttpClient build() {
        EventLoopGroup group = getEventLoopGroupToUse(this.eventLoopGroup, this.channelClass, IS_EPOLL_AVAILABLE,
            EPOLL_EVENT_LOOP_GROUP_CREATOR, IS_KQUEUE_AVAILABLE, KQUEUE_EVENT_LOOP_GROUP_CREATOR);
        Class<? extends Channel> channelClass
            = getChannelClass(this.channelClass, group.getClass(), IS_EPOLL_AVAILABLE, IS_KQUEUE_AVAILABLE);

        // Leave breadcrumbs about the NettyHttpClient configuration, in case troubleshooting is needed.
        LOGGER.atVerbose()
            .addKeyValue("customEventLoopGroup", eventLoopGroup != null)
            .addKeyValue("eventLoopGroupClass", group.getClass())
            .addKeyValue("customChannelClass", this.channelClass != null)
            .addKeyValue("channelClass", channelClass)
            .log("NettyHttpClient was built with these configurations.");

        Bootstrap bootstrap = new Bootstrap().group(group)
            .channel(channelClass)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) getTimeoutMillis(connectTimeout, 10_000));
        // Disable auto-read as we want to control when and how data is read from the channel.
        bootstrap.option(ChannelOption.AUTO_READ, false);

        Configuration buildConfiguration
            = (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;

        ProxyOptions buildProxyOptions
            = (proxyOptions == null) ? ProxyOptions.fromConfiguration(buildConfiguration, true) : proxyOptions;

        return new NettyHttpClient(bootstrap, sslContext, new ChannelInitializationProxyHandler(buildProxyOptions),
            getTimeoutMillis(readTimeout), getTimeoutMillis(responseTimeout), getTimeoutMillis(writeTimeout));
    }

    static long getTimeoutMillis(Duration duration) {
        return getTimeoutMillis(duration, 60_000);
    }

    static long getTimeoutMillis(Duration duration, long defaultTimeout) {
        if (duration == null) {
            return defaultTimeout;
        }

        if (duration.isNegative() || duration.isZero()) {
            return 0;
        }

        return Math.max(1, duration.toMillis());
    }

    // For testing.
    ProxyOptions getProxyOptions() {
        Configuration buildConfiguration
            = (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;

        return (proxyOptions == null) ? ProxyOptions.fromConfiguration(buildConfiguration, true) : proxyOptions;
    }

    static EventLoopGroup getEventLoopGroupToUse(EventLoopGroup configuredGroup,
        Class<? extends SocketChannel> configuredChannelClass, boolean isEpollAvailable,
        MethodHandle epollEventLoopGroupCreator, boolean isKqueueAvailable, MethodHandle kqueueEventLoopGroupCreator) {
        if (configuredGroup != null) {
            return configuredGroup;
        }

        ThreadFactory threadFactory = new DefaultThreadFactory("clientcore-netty-client", true);

        // Use EpollEventLoopGroup if Epoll is available and 'channelClass' wasn't configured or was configured to
        // EpollSocketChannel.
        if (isEpollAvailable && (configuredChannelClass == null || configuredChannelClass == EPOLL_CHANNEL_CLASS)) {
            try {
                return (EventLoopGroup) epollEventLoopGroupCreator.invoke(threadFactory);
            } catch (Throwable ex) {
                LOGGER.atVerbose().setThrowable(ex).log("Failed to create an EpollEventLoopGroup.");
            }
        }

        // Use KQueueEventLoopGroup if KQueue is available and 'channelClass' wasn't configured or was configured to
        // KQueueSocketChannel.
        if (isKqueueAvailable && (configuredChannelClass == null || configuredChannelClass == KQUEUE_CHANNEL_CLASS)) {
            try {
                return (EventLoopGroup) kqueueEventLoopGroupCreator.invoke(threadFactory);
            } catch (Throwable ex) {
                LOGGER.atVerbose().setThrowable(ex).log("Failed to create a KQueueEventLoopGroup.");
            }
        }

        // Fallback to NioEventLoopGroup.
        return new NioEventLoopGroup(threadFactory);
    }

    static Class<? extends SocketChannel> getChannelClass(Class<? extends SocketChannel> configuredChannelClass,
        Class<? extends EventLoopGroup> configuredGroupClass, boolean isEpollAvailable, boolean isKqueueAvailable) {
        if (configuredChannelClass != null) {
            // If the Channel class was manually set, use it.
            return configuredChannelClass;
        } else if (isEpollAvailable && configuredGroupClass == EPOLL_EVENT_LOOP_GROUP_CLASS) {
            // If Epoll is available and the EventLoopGroup is EpollEventLoopGroup, use EpollSocketChannel.
            return EPOLL_CHANNEL_CLASS;
        } else if (isKqueueAvailable && configuredGroupClass == KQUEUE_EVENT_LOOP_GROUP_CLASS) {
            // If KQueue is available and the EventLoopGroup is KQueueEventLoopGroup, use KQueueSocketChannel.
            return KQUEUE_CHANNEL_CLASS;
        } else {
            // Fallback to NioSocketChannel.
            return NioSocketChannel.class;
        }
    }
}
