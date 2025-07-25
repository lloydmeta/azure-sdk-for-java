// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.resourcemanager.compute.models;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import com.azure.resourcemanager.compute.ComputeManager;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsBatchDeletion;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsDeletingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsGettingById;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsGettingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsListingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.models.HasManager;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsBatchCreation;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsCreating;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsDeletingById;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsListing;
import com.azure.resourcemanager.resources.fluentcore.model.Accepted;
import java.util.List;
import reactor.core.publisher.Mono;

/** Entry point to virtual machine management API. */
public interface VirtualMachines
    extends SupportsListing<VirtualMachine>, SupportsListingByResourceGroup<VirtualMachine>,
    SupportsGettingByResourceGroup<VirtualMachine>, SupportsGettingById<VirtualMachine>,
    SupportsCreating<VirtualMachine.DefinitionStages.Blank>, SupportsDeletingById, SupportsDeletingByResourceGroup,
    SupportsBatchCreation<VirtualMachine>, SupportsBatchDeletion, HasManager<ComputeManager> {

    /**
     * Gets available virtual machine sizes.
     *
     * @return available virtual machine sizes
     */
    VirtualMachineSizes sizes();

    /**
     * Shuts down the virtual machine and releases the compute resources.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void deallocate(String groupName, String name);

    /**
     * Shuts down the virtual machine and releases the compute resources asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> deallocateAsync(String groupName, String name);

    /**
     * Shuts down the virtual machine and releases the compute resources.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @param hibernate hibernate the virtual machine
     */
    void deallocate(String groupName, String name, boolean hibernate);

    /**
     * Shuts down the virtual machine and releases the compute resources asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @param hibernate hibernate the virtual machine
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> deallocateAsync(String groupName, String name, boolean hibernate);

    /**
     * Generalizes the virtual machine.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void generalize(String groupName, String name);

    /**
     * Generalizes the virtual machine asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> generalizeAsync(String groupName, String name);

    /**
     * Powers off (stops) a virtual machine.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void powerOff(String groupName, String name);

    /**
     * Powers off (stops) the virtual machine asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> powerOffAsync(String groupName, String name);

    /**
     * Restarts a virtual machine.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void restart(String groupName, String name);

    /**
     * Restarts the virtual machine asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> restartAsync(String groupName, String name);

    /**
     * Starts a virtual machine.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void start(String groupName, String name);

    /**
     * Starts the virtual machine asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> startAsync(String groupName, String name);

    /**
     * Redeploys a virtual machine.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     */
    void redeploy(String groupName, String name);

    /**
     * Redeploys the virtual machine asynchronously.
     *
     * @param groupName the name of the resource group the virtual machine is in
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> redeployAsync(String groupName, String name);

    /**
     * Captures the virtual machine by copying virtual hard disks of the VM and returns template as a JSON string that
     * can be used to create similar VMs.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param containerName destination container name to store the captured VHD
     * @param vhdPrefix the prefix for the VHD holding captured image
     * @param overwriteVhd whether to overwrites destination VHD if it exists
     * @return the template as JSON string
     */
    String capture(String groupName, String name, String containerName, String vhdPrefix, boolean overwriteVhd);

    /**
     * Captures the virtual machine by copying virtual hard disks of the VM asynchronously.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param containerName destination container name to store the captured VHD
     * @param vhdPrefix the prefix for the VHD holding captured image
     * @param overwriteVhd whether to overwrites destination VHD if it exists
     * @return a representation of the deferred computation of this call
     */
    Mono<String> captureAsync(String groupName, String name, String containerName, String vhdPrefix,
        boolean overwriteVhd);

    /**
     * Migrates the virtual machine with unmanaged disks to use managed disks.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     */
    void migrateToManaged(String groupName, String name);

    /**
     * Converts (migrates) the virtual machine with un-managed disks to use managed disk asynchronously.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> migrateToManagedAsync(String groupName, String name);

    /**
     * Run shell script in a virtual machine.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param scriptLines PowerShell script lines
     * @param scriptParameters script parameters
     * @return result of PowerShell script execution
     */
    RunCommandResult runPowerShellScript(String groupName, String name, List<String> scriptLines,
        List<RunCommandInputParameter> scriptParameters);

    /**
     * Run shell script in a virtual machine asynchronously.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param scriptLines PowerShell script lines
     * @param scriptParameters script parameters
     * @return handle to the asynchronous execution
     */
    Mono<RunCommandResult> runPowerShellScriptAsync(String groupName, String name, List<String> scriptLines,
        List<RunCommandInputParameter> scriptParameters);

    /**
     * Run shell script in a virtual machine.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param scriptLines shell script lines
     * @param scriptParameters script parameters
     * @return result of shell script execution
     */
    RunCommandResult runShellScript(String groupName, String name, List<String> scriptLines,
        List<RunCommandInputParameter> scriptParameters);

    /**
     * Run shell script in a virtual machine asynchronously.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param scriptLines shell script lines
     * @param scriptParameters script parameters
     * @return handle to the asynchronous execution
     */
    Mono<RunCommandResult> runShellScriptAsync(String groupName, String name, List<String> scriptLines,
        List<RunCommandInputParameter> scriptParameters);

    /**
     * Run commands in a virtual machine.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param inputCommand command input
     * @return result of execution
     */
    RunCommandResult runCommand(String groupName, String name, RunCommandInput inputCommand);

    /**
     * Run commands in a virtual machine asynchronously.
     *
     * @param groupName the resource group name
     * @param name the virtual machine name
     * @param inputCommand command input
     * @return handle to the asynchronous execution
     */
    Mono<RunCommandResult> runCommandAsync(String groupName, String name, RunCommandInput inputCommand);

    /**
     * Begins deleting a virtual machine from Azure, identifying it by its resource ID.
     *
     * @param id the resource ID of the virtual machine to delete
     * @return the accepted deleting operation
     */
    Accepted<Void> beginDeleteById(String id);

    /**
     * Begins deleting a virtual machine from Azure, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the resource group the resource is part of
     * @param name the virtual machine name
     * @return the accepted deleting operation
     */
    Accepted<Void> beginDeleteByResourceGroup(String resourceGroupName, String name);

    /**
     * Force delete a resource from Azure, identifying it by its resource ID.
     *
     * @param id the resource ID of the resource to delete
     * @param forceDeletion force delete without graceful shutdown
     */
    void deleteById(String id, boolean forceDeletion);

    /**
     * Asynchronously force delete a resource from Azure, identifying it by its resource ID.
     *
     * @param id the resource ID of the resource to delete
     * @param forceDeletion force delete without graceful shutdown
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> deleteByIdAsync(String id, boolean forceDeletion);

    /**
     * Force delete a resource from Azure, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the resource group the resource is part of
     * @param name the name of the resource
     * @param forceDeletion force delete without graceful shutdown
     */
    void deleteByResourceGroup(String resourceGroupName, String name, boolean forceDeletion);

    /**
     * Asynchronously force delete a resource from Azure, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the resource group the resource is part of
     * @param name the name of the resource
     * @param forceDeletion force delete without graceful shutdown
     * @return a representation of the deferred computation of this call
     */
    Mono<Void> deleteByResourceGroupAsync(String resourceGroupName, String name, boolean forceDeletion);

    /**
     * Begins force deleting a virtual machine from Azure, identifying it by its resource ID.
     *
     * @param id the resource ID of the virtual machine to delete
     * @param forceDeletion force delete without graceful shutdown
     * @return the accepted deleting operation
     */
    Accepted<Void> beginDeleteById(String id, boolean forceDeletion);

    /**
     * Begins force deleting a virtual machine from Azure, identifying it by its resource ID.
     *
     * @param id the resource ID of the virtual machine to delete
     * @param forceDeletion force delete without graceful shutdown
     * @param context the {@link Context} of the request
     * @return the accepted deleting operation
     */
    default Accepted<Void> beginDeleteById(String id, boolean forceDeletion, Context context) {
        throw new UnsupportedOperationException(
            "[beginDeleteById(String, boolean, Context)] is not supported in " + getClass());
    }

    /**
     * Begins force deleting a virtual machine from Azure, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the resource group the resource is part of
     * @param name the virtual machine name
     * @param forceDeletion force delete without graceful shutdown
     * @return the accepted deleting operation
     */
    Accepted<Void> beginDeleteByResourceGroup(String resourceGroupName, String name, boolean forceDeletion);

    /**
     * Begins force deleting a virtual machine from Azure, identifying it by its name and its resource group.
     *
     * @param resourceGroupName the resource group the resource is part of
     * @param name the virtual machine name
     * @param forceDeletion force delete without graceful shutdown
     * @param context the {@link Context} of the request
     * @return the accepted deleting operation
     */
    default Accepted<Void> beginDeleteByResourceGroup(String resourceGroupName, String name, boolean forceDeletion,
        Context context) {
        throw new UnsupportedOperationException(
            "[beginDeleteByResourceGroup(String, String, boolean, Context)] is not supported in " + getClass());
    }

    /**
     * Lists all the virtual machines by a certain virtual machine scale set with orchestration mode {@link OrchestrationMode#FLEXIBLE}.
     *
     * <p>Note: This method is for {@link OrchestrationMode#FLEXIBLE} virtual machine scale set.
     * For {@link OrchestrationMode#UNIFORM} scale sets, use {@link VirtualMachineScaleSet#virtualMachines()}.
     * </p>
     *
     * @param vmssId resource ID of the virtual machine scale set
     * @return A {@link PagedIterable} of virtual machines
     * @see VirtualMachineScaleSet#virtualMachines()
     */
    PagedIterable<VirtualMachine> listByVirtualMachineScaleSetId(String vmssId);

    /**
     * Lists all the virtual machines by a certain virtual machine scale set with orchestration mode {@link OrchestrationMode#FLEXIBLE}.
     *
     * <p>Note: This method is for {@link OrchestrationMode#FLEXIBLE} virtual machine scale set.
     * For {@link OrchestrationMode#UNIFORM} scale sets, use {@link VirtualMachineScaleSet#virtualMachines()}.
     * </p>
     *
     * @param vmssId resource ID of the virtual machine scale set
     * @return A {@link PagedFlux} of virtual machines
     * @see VirtualMachineScaleSet#virtualMachines()
     */
    PagedFlux<VirtualMachine> listByVirtualMachineScaleSetIdAsync(String vmssId);

    /**
     * Lists all the virtual machines by a certain virtual machine scale set with orchestration mode {@link OrchestrationMode#FLEXIBLE}.
     *
     * <p>Note: This method is for {@link OrchestrationMode#FLEXIBLE} virtual machine scale set.
     * For {@link OrchestrationMode#UNIFORM} scale sets, use {@link VirtualMachineScaleSet#virtualMachines()}.
     * </p>
     *
     * @param vmss virtual machine scale set
     * @return A {@link PagedIterable} of virtual machines
     * @see VirtualMachineScaleSet#virtualMachines()
     */
    PagedIterable<VirtualMachine> listByVirtualMachineScaleSet(VirtualMachineScaleSet vmss);

    /**
     * Lists all the virtual machines by a certain virtual machine scale set with orchestration mode {@link OrchestrationMode#FLEXIBLE}.
     *
     * <p>Note: This method is for {@link OrchestrationMode#FLEXIBLE} virtual machine scale set.
     * For {@link OrchestrationMode#UNIFORM} scale sets, use {@link VirtualMachineScaleSet#virtualMachines()}.
     * </p>
     *
     * @param vmss virtual machine scale set
     * @return A {@link PagedFlux} of virtual machines
     * @see VirtualMachineScaleSet#virtualMachines()
     */
    PagedFlux<VirtualMachine> listByVirtualMachineScaleSetAsync(VirtualMachineScaleSet vmss);
}
