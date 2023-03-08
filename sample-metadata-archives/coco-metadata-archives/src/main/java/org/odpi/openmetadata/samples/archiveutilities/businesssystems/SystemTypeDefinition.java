/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;


/**
 * The SystemTypeDefinition is used to set up the deployedImplementationType.
 */
public enum SystemTypeDefinition
{
    COTS_SERVER("cots-application-server",
         "Custom off-the-shelf application server",
         "Server deployed as part of a COTS application package.  Coco Pharmaceuticals has minimal control over the structure and naming of this type of software server.",
         "These servers drive the business.  They provide standard business functions."),

    HOME_GROWN_APP_SERVER("home-grown-application-server",
         "Home grown application server",
         "Server deployed as part of a bespoke application written by Coco Pharmaceuticals.",
         "These servers drive specialized aspects of the business."),

    ETL_ENGINE("etl-engine",
         "Extract-Transform-Load (ETL) engine",
         "Server deployed to run jobs that copy and transform data from one system to another.",
         "These servers are part of a middleware deployment."),

    DATABASE_SERVER("database-server",
         "Database Server",
         "Database server providing a collection of data that can be flexibly queried.",
         "These servers are part of a middleware deployment."),

    EVENT_BROKER("event-broker",
         "Event Broker",
         "Event broker server providing event notifications based on subscriptions.",
         "These servers are part of a middleware deployment."),

    REPORTING_SERVER("reporting-server",
         "Reporting Server",
         "Server that delivers business reports.",
         "These servers are part of a middleware deployment."),

    OPEN_METADATA_SERVER("metadata-access-server",
         "Open Metadata Access Server",
         "Metadata server that is part of the open metadata ecosystem.",
         "These servers are part of a middleware deployment."),

    ENGINE_HOST_SERVER("engine-host",
         "Open Metadata Engine Host Server",
         "Server hosting governance engine. It is part of the open metadata ecosystem.",
         "These servers are part of a middleware deployment."),

    INTEGRATION_DAEMON("integration-daemon",
         "Open Metadata Engine Host Server",
         "Server hosting integration connectors responsible for synchronizing metadata. It is part of the open metadata ecosystem.",
         "These servers are part of a middleware deployment."),
     ;

    public static final String validValueSetName = "SystemType";
    public static final String validValueSetDescription = "Describes the type of system.";
    public static final String validValueSetUsage = "Used to tag Location entities to show the likely equipment that needs cataloging and managing.";
    public static final String validValueSetScope = "Used for physical types of Locations.";

    private final String preferredValue;
    private final String displayName;
    private final String description;
    private final String usage;

    /**
     * The constructor creates an instance of the enum
     *
     * @param preferredValue   unique id for the enum
     * @param displayName   name for the enum
     * @param description   description of the use of this value
     * @param usage   criteria for use
     */
    SystemTypeDefinition(String preferredValue, String displayName, String description, String usage)
    {
        this.preferredValue = preferredValue;
        this.displayName = displayName;
        this.description = description;
        this.usage = usage;
    }



    /**
     * This is the preferred value that applications should use for this valid value.
     *
     * @return string value
     */
    public String getQualifiedName()
    {
        return validValueSetName + "." + preferredValue;
    }



    /**
     * This is the preferred value that applications should use for this valid value.
     *
     * @return string value
     */
    public String getPreferredValue()
    {
        return preferredValue;
    }


    /**
     * Return the printable name.
     *
     * @return string name
     */
    public String getDisplayName()
    {
        return displayName;
    }


    /**
     * Return the description of the value's meaning.
     *
     * @return string text
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Return how the value should be used.
     *
     * @return string text
     */
    public String getUsage()
    {
        return usage;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "FacilityTypeDefinition{" + displayName + '}';
    }
}
