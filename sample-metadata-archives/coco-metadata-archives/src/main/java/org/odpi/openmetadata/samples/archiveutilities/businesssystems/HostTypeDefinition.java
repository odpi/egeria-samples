/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;


/**
 * The HostTypeDefinition is used to set up the open metadata type of host.
 */
public enum HostTypeDefinition
{
    BARE_METAL("BareMetalComputer"),

    VIRTUAL_MACHINE("VirtualMachine"),

    DOCKER_CONTAINER("DockerContainer"),

    HADOOP_CLUSTER("HadoopCluster"),

    KUBERNETES_CLUSTER("KubernetesCluster"),

    SOFTWARE_SERVER_PLATFORM("SoftwareServerPlatform"),
    ;

    private final String openMetadataTypeName;

    /**
     * The constructor creates an instance of the enum
     *
     * @param openMetadataTypeName   unique id for the enum
     */
    HostTypeDefinition(String openMetadataTypeName)
    {
        this.openMetadataTypeName = openMetadataTypeName;
    }

    /**
     * This is the name of the open metadata type to use when creating the Host entity.
     *
     * @return string value
     */
    public String getOpenMetadataTypeName()
    {
        return openMetadataTypeName;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "HostTypeDefinition{" + openMetadataTypeName + '}';
    }
}
