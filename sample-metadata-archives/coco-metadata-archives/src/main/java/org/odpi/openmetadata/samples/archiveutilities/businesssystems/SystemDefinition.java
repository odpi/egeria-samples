/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;


import java.util.Arrays;
import java.util.List;

/**
 * The SystemDefinition is used to feed the definition of the organization's systems for Coco Pharmaceuticals scenarios.
 */
public enum SystemDefinition
{
    PROCUREMENT("procurement",
             "Procurement",
             "Purchasing system for Coco Pharmaceuticals.",
             "procurementnpa",
             new String[]{"business-systems", "sustainability"}),

    INVENTORY("inventory",
                "Inventory",
                "Inventory for raw materials and products produced across all sites.",
                "inventorynpa",
                new String[]{"business-systems", "sustainability"}),
    ;

    private final String systemId;
    private final String displayName;
    private final String description;
    private final String userId;
    private final String[] zones;


    /**
     * The constructor creates an instance of the enum
     *
     * @param systemId   unique id for the enum
     * @param displayName   name for the enum
     * @param description   description of the use of this value
     */
    SystemDefinition(String       systemId,
                     String       displayName,
                     String       description,
                     String       userId,
                     String[]     zones)
    {
        this.systemId = systemId;
        this.displayName = displayName;
        this.description = description;
        this.userId = userId;
        this.zones = zones;
    }

    public String getQualifiedName()
    {
        return "System:" + systemId;
    }


    public String getSystemId()
    {
        return systemId;
    }


    public String getDisplayName()
    {
        return displayName;
    }


    public String getDescription()
    {
        return description;
    }


    public String getUserId() { return userId; }


    public List<String> getZones()
    {
        if (zones != null)
        {
            return Arrays.asList(zones);
        }

        return null;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "SystemDefinition{" + displayName + '}';
    }
}
