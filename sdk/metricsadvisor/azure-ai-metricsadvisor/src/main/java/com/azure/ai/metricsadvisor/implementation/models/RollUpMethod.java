// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * roll up method.
 */
public final class RollUpMethod extends ExpandableStringEnum<RollUpMethod> {
    /**
     * Static value None for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod NONE = fromString("None");

    /**
     * Static value Sum for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod SUM = fromString("Sum");

    /**
     * Static value Max for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod MAX = fromString("Max");

    /**
     * Static value Min for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod MIN = fromString("Min");

    /**
     * Static value Avg for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod AVG = fromString("Avg");

    /**
     * Static value Count for RollUpMethod.
     */
    @Generated
    public static final RollUpMethod COUNT = fromString("Count");

    /**
     * Creates a new instance of RollUpMethod value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public RollUpMethod() {
    }

    /**
     * Creates or finds a RollUpMethod from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding RollUpMethod.
     */
    @Generated
    public static RollUpMethod fromString(String name) {
        return fromString(name, RollUpMethod.class);
    }

    /**
     * Gets known RollUpMethod values.
     * 
     * @return known RollUpMethod values.
     */
    @Generated
    public static Collection<RollUpMethod> values() {
        return values(RollUpMethod.class);
    }
}
