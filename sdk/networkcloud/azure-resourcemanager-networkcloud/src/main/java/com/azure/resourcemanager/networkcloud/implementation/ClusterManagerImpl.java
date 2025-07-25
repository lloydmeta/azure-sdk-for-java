// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.networkcloud.implementation;

import com.azure.core.management.Region;
import com.azure.core.management.SystemData;
import com.azure.core.util.Context;
import com.azure.resourcemanager.networkcloud.fluent.models.ClusterManagerInner;
import com.azure.resourcemanager.networkcloud.models.ClusterAvailableVersion;
import com.azure.resourcemanager.networkcloud.models.ClusterManager;
import com.azure.resourcemanager.networkcloud.models.ClusterManagerDetailedStatus;
import com.azure.resourcemanager.networkcloud.models.ClusterManagerPatchParameters;
import com.azure.resourcemanager.networkcloud.models.ClusterManagerProvisioningState;
import com.azure.resourcemanager.networkcloud.models.ExtendedLocation;
import com.azure.resourcemanager.networkcloud.models.ManagedResourceGroupConfiguration;
import com.azure.resourcemanager.networkcloud.models.ManagedServiceIdentity;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class ClusterManagerImpl implements ClusterManager, ClusterManager.Definition, ClusterManager.Update {
    private ClusterManagerInner innerObject;

    private final com.azure.resourcemanager.networkcloud.NetworkCloudManager serviceManager;

    public String id() {
        return this.innerModel().id();
    }

    public String name() {
        return this.innerModel().name();
    }

    public String type() {
        return this.innerModel().type();
    }

    public String location() {
        return this.innerModel().location();
    }

    public Map<String, String> tags() {
        Map<String, String> inner = this.innerModel().tags();
        if (inner != null) {
            return Collections.unmodifiableMap(inner);
        } else {
            return Collections.emptyMap();
        }
    }

    public String etag() {
        return this.innerModel().etag();
    }

    public ManagedServiceIdentity identity() {
        return this.innerModel().identity();
    }

    public SystemData systemData() {
        return this.innerModel().systemData();
    }

    public String analyticsWorkspaceId() {
        return this.innerModel().analyticsWorkspaceId();
    }

    public List<String> availabilityZones() {
        List<String> inner = this.innerModel().availabilityZones();
        if (inner != null) {
            return Collections.unmodifiableList(inner);
        } else {
            return Collections.emptyList();
        }
    }

    public List<ClusterAvailableVersion> clusterVersions() {
        List<ClusterAvailableVersion> inner = this.innerModel().clusterVersions();
        if (inner != null) {
            return Collections.unmodifiableList(inner);
        } else {
            return Collections.emptyList();
        }
    }

    public ClusterManagerDetailedStatus detailedStatus() {
        return this.innerModel().detailedStatus();
    }

    public String detailedStatusMessage() {
        return this.innerModel().detailedStatusMessage();
    }

    public String fabricControllerId() {
        return this.innerModel().fabricControllerId();
    }

    public ManagedResourceGroupConfiguration managedResourceGroupConfiguration() {
        return this.innerModel().managedResourceGroupConfiguration();
    }

    public ExtendedLocation managerExtendedLocation() {
        return this.innerModel().managerExtendedLocation();
    }

    public ClusterManagerProvisioningState provisioningState() {
        return this.innerModel().provisioningState();
    }

    public String vmSize() {
        return this.innerModel().vmSize();
    }

    public Region region() {
        return Region.fromName(this.regionName());
    }

    public String regionName() {
        return this.location();
    }

    public String resourceGroupName() {
        return resourceGroupName;
    }

    public ClusterManagerInner innerModel() {
        return this.innerObject;
    }

    private com.azure.resourcemanager.networkcloud.NetworkCloudManager manager() {
        return this.serviceManager;
    }

    private String resourceGroupName;

    private String clusterManagerName;

    private String createIfMatch;

    private String createIfNoneMatch;

    private String updateIfMatch;

    private String updateIfNoneMatch;

    private ClusterManagerPatchParameters updateClusterManagerUpdateParameters;

    public ClusterManagerImpl withExistingResourceGroup(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
        return this;
    }

    public ClusterManager create() {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .createOrUpdate(resourceGroupName, clusterManagerName, this.innerModel(), createIfMatch, createIfNoneMatch,
                Context.NONE);
        return this;
    }

    public ClusterManager create(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .createOrUpdate(resourceGroupName, clusterManagerName, this.innerModel(), createIfMatch, createIfNoneMatch,
                context);
        return this;
    }

    ClusterManagerImpl(String name, com.azure.resourcemanager.networkcloud.NetworkCloudManager serviceManager) {
        this.innerObject = new ClusterManagerInner();
        this.serviceManager = serviceManager;
        this.clusterManagerName = name;
        this.createIfMatch = null;
        this.createIfNoneMatch = null;
    }

    public ClusterManagerImpl update() {
        this.updateIfMatch = null;
        this.updateIfNoneMatch = null;
        this.updateClusterManagerUpdateParameters = new ClusterManagerPatchParameters();
        return this;
    }

    public ClusterManager apply() {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .updateWithResponse(resourceGroupName, clusterManagerName, updateIfMatch, updateIfNoneMatch,
                updateClusterManagerUpdateParameters, Context.NONE)
            .getValue();
        return this;
    }

    public ClusterManager apply(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .updateWithResponse(resourceGroupName, clusterManagerName, updateIfMatch, updateIfNoneMatch,
                updateClusterManagerUpdateParameters, context)
            .getValue();
        return this;
    }

    ClusterManagerImpl(ClusterManagerInner innerObject,
        com.azure.resourcemanager.networkcloud.NetworkCloudManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
        this.resourceGroupName = ResourceManagerUtils.getValueFromIdByName(innerObject.id(), "resourceGroups");
        this.clusterManagerName = ResourceManagerUtils.getValueFromIdByName(innerObject.id(), "clusterManagers");
    }

    public ClusterManager refresh() {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .getByResourceGroupWithResponse(resourceGroupName, clusterManagerName, Context.NONE)
            .getValue();
        return this;
    }

    public ClusterManager refresh(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getClusterManagers()
            .getByResourceGroupWithResponse(resourceGroupName, clusterManagerName, context)
            .getValue();
        return this;
    }

    public ClusterManagerImpl withRegion(Region location) {
        this.innerModel().withLocation(location.toString());
        return this;
    }

    public ClusterManagerImpl withRegion(String location) {
        this.innerModel().withLocation(location);
        return this;
    }

    public ClusterManagerImpl withFabricControllerId(String fabricControllerId) {
        this.innerModel().withFabricControllerId(fabricControllerId);
        return this;
    }

    public ClusterManagerImpl withTags(Map<String, String> tags) {
        if (isInCreateMode()) {
            this.innerModel().withTags(tags);
            return this;
        } else {
            this.updateClusterManagerUpdateParameters.withTags(tags);
            return this;
        }
    }

    public ClusterManagerImpl withIdentity(ManagedServiceIdentity identity) {
        if (isInCreateMode()) {
            this.innerModel().withIdentity(identity);
            return this;
        } else {
            this.updateClusterManagerUpdateParameters.withIdentity(identity);
            return this;
        }
    }

    public ClusterManagerImpl withAnalyticsWorkspaceId(String analyticsWorkspaceId) {
        this.innerModel().withAnalyticsWorkspaceId(analyticsWorkspaceId);
        return this;
    }

    public ClusterManagerImpl withAvailabilityZones(List<String> availabilityZones) {
        this.innerModel().withAvailabilityZones(availabilityZones);
        return this;
    }

    public ClusterManagerImpl
        withManagedResourceGroupConfiguration(ManagedResourceGroupConfiguration managedResourceGroupConfiguration) {
        this.innerModel().withManagedResourceGroupConfiguration(managedResourceGroupConfiguration);
        return this;
    }

    public ClusterManagerImpl withVmSize(String vmSize) {
        this.innerModel().withVmSize(vmSize);
        return this;
    }

    public ClusterManagerImpl withIfMatch(String ifMatch) {
        if (isInCreateMode()) {
            this.createIfMatch = ifMatch;
            return this;
        } else {
            this.updateIfMatch = ifMatch;
            return this;
        }
    }

    public ClusterManagerImpl withIfNoneMatch(String ifNoneMatch) {
        if (isInCreateMode()) {
            this.createIfNoneMatch = ifNoneMatch;
            return this;
        } else {
            this.updateIfNoneMatch = ifNoneMatch;
            return this;
        }
    }

    private boolean isInCreateMode() {
        return this.innerModel() == null || this.innerModel().id() == null;
    }
}
