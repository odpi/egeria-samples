/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;


import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.samples.archiveutilities.GovernanceArchiveHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * CocoGovernanceEnginesArchiveWriter creates a physical open metadata archive file containing the descriptions of the
 * data flows from Coco Pharmaceuticals business systems to the data lake.
 */
public class CocoBusinessSystemsArchiveWriter extends OMRSArchiveWriter
{
    private static final String archiveFileName = "CocoClinicalBusinessSystemsArchive.json";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "ac202586-4042-407b-ae51-8096dfda223e";
    private static final String                  archiveName        = "Coco Pharmaceuticals Business Systems";
    private static final String                  archiveLicense     = "Apache 2.0";
    private static final String                  archiveDescription = "The data flows from Coco Pharmaceuticals business systems to the data lake.";
    private static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    private static final String                  originatorName     = "Egeria Project";
    private static final Date                    creationDate       = new Date(1606501094472L);

    /*
     * Specific values for initializing TypeDefs
     */
    private static final long   versionNumber = 1L;
    private static final String versionName   = "1.0";

    private OMRSArchiveBuilder      archiveBuilder;
    private GovernanceArchiveHelper archiveHelper;


    /**
     * Default constructor initializes the archive.
     */
    private CocoBusinessSystemsArchiveWriter()
    {
        List<OpenMetadataArchive> dependentOpenMetadataArchives = new ArrayList<>();

        /*
         * This value allows the Coco Types to be based on the existing open metadata types
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

        this.archiveHelper = new GovernanceArchiveHelper(archiveBuilder,
                                                         archiveGUID,
                                                         archiveName,
                                                         originatorName,
                                                         creationDate,
                                                         versionNumber,
                                                         versionName);
    }




    /**
     * Returns the open metadata archive containing new metadata entities.
     *
     * @return populated open metadata archive object
     */
    protected OpenMetadataArchive getOpenMetadataArchive()
    {

        // todo add content of archive

        archiveHelper.saveGUIDs();

        /*
         * The completed archive is ready to be packaged up and returned
         */
        return this.archiveBuilder.getOpenMetadataArchive();
    }


    /**
     * Generates and writes out the open metadata archive created in the builder.
     */
    private void writeOpenMetadataArchive()
    {
        try
        {
            System.out.println("Writing to file: " + archiveFileName);

            super.writeOpenMetadataArchive(archiveFileName, this.getOpenMetadataArchive());
        }
        catch (Exception error)
        {
            System.out.println("error is " + error.toString());
        }
    }


    /**
     * Main program to initiate the archive writer.
     *
     * @param args ignored
     */
    public static void main(String[] args)
    {
        try
        {
            CocoBusinessSystemsArchiveWriter archiveWriter = new CocoBusinessSystemsArchiveWriter();

            archiveWriter.writeOpenMetadataArchive();
        }
        catch (Exception error)
        {
            System.err.println("Exception: " + error.toString());
            System.exit(-1);
        }
    }
}
