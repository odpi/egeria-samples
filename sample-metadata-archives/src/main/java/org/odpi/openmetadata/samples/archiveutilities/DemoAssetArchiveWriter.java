/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities;

import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveHelper;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.EntityDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DemoAssetArchiveWriter provides the main method to run the open metadata archive writer
 * that creates an archive file for DemoAsset.
 */
public class DemoAssetArchiveWriter extends OMRSArchiveWriter
{
    private static final String archiveFileName = "DemoAssetType.omarchive";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "85efed9b-97a3-4ac0-ac80-f026571920c2";
    private static final String                  archiveName        = "DemoAssetArchive";
    private static final String                  archiveLicense     = "Apache 2.0";
    private static final String                  archiveDescription = "Specialized types for demonstrating dynamic type extensions.";
    private static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    private static final String                  originatorName     = "Egeria Project";
    private static final Date                    creationDate       = new Date(1729163024594L);

    /*
     * Specific values for initializing TypeDefs
     */
    private static final long   versionNumber = 1L;
    private static final String versionName   = "1.0";


    private final OMRSArchiveBuilder archiveBuilder;
    private final OMRSArchiveHelper  archiveHelper;

    /**
     * Default constructor initializes the archive.
     */
    public DemoAssetArchiveWriter()
    {
        List<OpenMetadataArchive> dependentOpenMetadataArchives = new ArrayList<>();

        /*
         * This value allows the new type to be based on the existing open metadata types
         */
        dependentOpenMetadataArchives.add(new OpenMetadataTypesArchive().getOpenMetadataArchive());

        this.archiveBuilder = new OMRSArchiveBuilder(archiveGUID,
                                                     archiveName,
                                                     archiveDescription,
                                                     archiveType,
                                                     originatorName,
                                                     archiveLicense,
                                                     creationDate,
                                                     dependentOpenMetadataArchives);

        this.archiveHelper = new OMRSArchiveHelper(archiveBuilder,
                                                   archiveGUID,
                                                   originatorName,
                                                   creationDate,
                                                   versionNumber,
                                                   versionName);
    }


    /**
     * Create the type definition for DemoAsset.
     *
     * @return entity type definition (EntityDef)
     */
    private EntityDef getDemoAssetEntity()
    {
        final String guid            = "7c39114e-d60a-4f86-ad3f-387c7faa82cc";
        final String name            = "DemoAsset";
        final String description     = "Demonstrates the use of an extended asset type.";

        final String superTypeName = "DataAsset";

        EntityDef entityDef = archiveHelper.getDefaultEntityDef(guid,
                                                                name,
                                                                this.archiveBuilder.getEntityDef(superTypeName),
                                                                description,
                                                                null);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute1Name            = "externalResourceId";
        final String attribute1Description     = "Identifier from source system.";
        final String attribute2Name            = "schema";
        final String attribute2Description     = "What type of schema?";
        final String attribute3Name            = "verifiedBy";
        final String attribute3Description     = "Who verified this asset?";
        final String attribute4Name            = "verifiedDate";
        final String attribute4Description     = "When was this asset verified?";
        final String attribute5Name            = "catalog";
        final String attribute5Description     = "Which catalog did this asset come from?";
        final String attribute6Name            = "verified";
        final String attribute6Description     = "Has this asset been verified?";
        final String attribute7Name            = "subjectMatterExperts";
        final String attribute7Description     = "Who are the subject matter experts?";
        final String attribute8Name            = "externalInstanceLastUpdateTime";
        final String attribute8Description     = "When was this resource last updated?";
        final String attribute9Name            = "businessDescription";
        final String attribute9Description     = "Additional business information.";

        property = archiveHelper.getStringTypeDefAttribute(attribute1Name,
                                                           attribute1Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute2Name,
                                                           attribute2Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute3Name,
                                                           attribute3Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute4Name,
                                                           attribute4Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute5Name,
                                                           attribute5Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute6Name,
                                                           attribute6Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute7Name,
                                                           attribute7Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute8Name,
                                                           attribute8Description,
                                                           null);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute9Name,
                                                           attribute9Description,
                                                           null);
        properties.add(property);

        entityDef.setPropertiesDefinition(properties);

        return entityDef;
    }


    /**
     * Returns the open metadata type archive containing all the new type definitions.
     *
     * @return populated open metadata archive object
     */
    protected OpenMetadataArchive getOpenMetadataArchive()
    {
        /*
         * Add the extended Asset type
         */
        this.archiveBuilder.addEntityDef(getDemoAssetEntity());


        /*
         * The completed archive is ready to be packaged up and returned
         */
        return this.archiveBuilder.getOpenMetadataArchive();
    }


    /**
     * Generates and writes out an open metadata archive containing the new types.
     */
    public void writeOpenMetadataArchive()
    {
        try
        {
            System.out.println("Writing to file: " + archiveFileName);

            super.writeOpenMetadataArchive(archiveFileName, this.getOpenMetadataArchive());
        }
        catch (Exception error)
        {
            System.out.println("error is " + error);
        }
    }


    /**
     * Main program to initiate the archive writer for the sample.
     *
     * @param args ignored
     */
    public static void main(String[] args)
    {
        try
        {
            DemoAssetArchiveWriter demoAssetArchiveWriter = new DemoAssetArchiveWriter();

            demoAssetArchiveWriter.writeOpenMetadataArchive();
        }
        catch (Exception error)
        {
            System.out.println("Exception: " + error);
            System.exit(-1);
        }
    }
}
