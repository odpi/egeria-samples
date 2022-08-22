/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.combo;


import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.samples.archiveutilities.GovernanceArchiveHelper;
import org.odpi.openmetadata.samples.archiveutilities.SimpleCatalogArchiveHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * CocoBaseArchiveWriter provides a base class for utilities creating a physical open metadata archive file containing  definitions for
 * Coco Pharmaceuticals.
 */
public abstract class CocoBaseArchiveWriter extends OMRSArchiveWriter
{
    protected static final String                  archiveLicense     = "Apache 2.0";
    protected static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    protected static final String                  originatorName     = "Egeria Project";

    private static final String guidMapFileName = "CocoComboGUIDMap.json";

    /*
     * Common values for naming of elements in the archive.
     */
    protected static final String openMetadataValidValueSetPrefix = "OpenMetadata.ValidValueSet.";

    /*
     * Specific values for initializing TypeDefs
     */
    protected static final long   versionNumber = 1L;
    protected static final String versionName   = "1.0";

    protected       OMRSArchiveBuilder         archiveBuilder;
    protected final SimpleCatalogArchiveHelper archiveHelper;

    private final String archiveFileName;


    /**
     * Constructor for an archive.
     *
     * @param archiveGUID unique identifier of the archive
     * @param archiveName name of the archive
     * @param archiveDescription description of archive
     * @param creationDate date/time this archive writer ran
     * @param archiveFileName name of file to write archive to
     */
    protected CocoBaseArchiveWriter(String                  archiveGUID,
                                    String                  archiveName,
                                    String                  archiveDescription,
                                    Date                    creationDate,
                                    String                  archiveFileName)
    {
        this(archiveGUID, archiveName, archiveDescription, creationDate, archiveFileName, null);
    }


    /**
     * Constructor for an archive.
     *
     * @param archiveGUID unique identifier of the archive
     * @param archiveName name of the archive
     * @param archiveDescription description of archive
     * @param creationDate date/time this archive writer ran
     * @param archiveFileName name of file to write archive to
     * @param additionalDependencies archive that this archive is dependent on
     */
    protected CocoBaseArchiveWriter(String              archiveGUID,
                                    String              archiveName,
                                    String              archiveDescription,
                                    Date                creationDate,
                                    String              archiveFileName,
                                    OpenMetadataArchive additionalDependencies)
    {
        List<OpenMetadataArchive> dependentOpenMetadataArchives = new ArrayList<>();

        /*
         * This value allows the definitions to be based on the existing open metadata types
         */
        dependentOpenMetadataArchives.add(new OpenMetadataTypesArchive().getOpenMetadataArchive());

        /*
         * Add in any additional dependencies.
         */
        if (additionalDependencies != null)
        {
            dependentOpenMetadataArchives.add(additionalDependencies);
        }

        this.archiveBuilder = new OMRSArchiveBuilder(archiveGUID,
                                                     archiveName,
                                                     archiveDescription,
                                                     archiveType,
                                                     originatorName,
                                                     archiveLicense,
                                                     creationDate,
                                                     dependentOpenMetadataArchives);

        this.archiveHelper = new GovernanceArchiveHelper(archiveBuilder,
                                                         archiveGUID,
                                                         archiveName,
                                                         originatorName,
                                                         creationDate,
                                                         versionNumber,
                                                         versionName,
                                                         guidMapFileName);

        this.archiveFileName = archiveFileName;
    }


    /**
     * Provide an alternative archive builder.  Used when consolidating archives.
     *
     * @param archiveBuilder new archive builder
     */
    protected void setArchiveBuilder(OMRSArchiveBuilder archiveBuilder)
    {
        this.archiveBuilder = archiveBuilder;
    }


    /**
     * Returns the open metadata archive containing new metadata entities.
     *
     * @return populated open metadata archive object
     */
    public OpenMetadataArchive getOpenMetadataArchive()
    {

        getArchiveContent();

        archiveHelper.saveGUIDs();

        /*
         * The completed archive is ready to be packaged up and returned
         */
        return this.archiveBuilder.getOpenMetadataArchive();
    }


    /**
     * Implemented by subclass to add the content.
     */
    protected abstract void getArchiveContent();


    /**
     * Generates and writes out the open metadata archive created in the builder.
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
}
