/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;


import org.odpi.openmetadata.samples.archiveutilities.sustainability.FacilityDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * The SystemDefinition is used to feed the definition of the organization's systems for Coco Pharmaceuticals scenarios.
 */
public enum SystemDefinition
{
    PROCUREMENT("procurement",
             "Purchasing system for Coco Pharmaceuticals.",
             "procurementnpa",
             SystemTypeDefinition.COTS_SERVER,
             "V2.6",
             new String[]{"business-systems", "sustainability"},
                FacilityDefinition.AMSTERDAM_DATA_CENTER,
                0),

    INVENTORY("inventory",
                "Inventory for raw materials and products produced across all sites.",
                "inventorynpa",
              SystemTypeDefinition.COTS_SERVER,
              "V2.6",
              new String[]{"business-systems", "sustainability"},
              FacilityDefinition.AMSTERDAM_DATA_CENTER,
              0),
    ;

    private final String               systemId;
    private final String               description;
    private final String               userId;
    private final SystemTypeDefinition systemType;
    private final String               versionIdentifier;
    private final String[]             zones;
    private final FacilityDefinition   systemLocation;
    private final long                 loadTime;


    /**
     * The constructor creates an instance of the enum
     *
     * @param systemId          unique id for the enum
     * @param description       description of the use of this value
     * @param userId            userId of the server
     * @param systemType        category of system
     * @param versionIdentifier version
     * @param zones             zone membership
     * @param systemLocation    location
     * @param loadTime          time offset to set creationTime
     */
    SystemDefinition(String                 systemId,
                     String                 description,
                     String                 userId,
                     SystemTypeDefinition   systemType,
                     String                 versionIdentifier,
                     String[]               zones,
                     FacilityDefinition     systemLocation,
                     long                   loadTime)
    {
        this.systemId = systemId;
        this.description = description;
        this.userId = userId;
        this.systemType = systemType;
        this.versionIdentifier = versionIdentifier;
        this.zones = zones;
        this.systemLocation = systemLocation;
        this.loadTime = loadTime;
    }


    /**
     * Return the manufactured qualified name.
     *
     * @return string
     */
    public String getQualifiedName()
    {
        return "System:" + systemId;
    }


    /**
     * Return the deployed identifier for this system.
     *
     * @return string
     */
    public String getSystemId()
    {
        return systemId;
    }


    /**
     * Return the description of this system.
     *
     * @return string
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Return the userId used by this server.
     *
     * @return string
     */
    public String getUserId() { return userId; }


    /**
     * Return the list of zones that this server belongs to.
     *
     * @return list of strings
     */
    public List<String> getZones()
    {
        if (zones != null)
        {
            return Arrays.asList(zones);
        }

        return null;
    }


    /**
     * Return the type of system (used to set deployedImplementationType).
     *
     * @return system type definition
     */
    public SystemTypeDefinition getSystemType()
    {
        return systemType;
    }


    /**
     * Return the version of the server.
     *
     * @return string
     */
    public String getVersionIdentifier()
    {
        return versionIdentifier;
    }


    /**
     * Return the facility where this server is located.
     *
     * @return facility identifier
     */
    public FacilityDefinition getSystemLocation()
    {
        return systemLocation;
    }


    /**
     * Return the time offset to set up the creation time.
     *
     * @return long
     */
    public long getLoadTime()
    {
        return loadTime;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "SystemDefinition{" + systemId + '}';
    }
}
