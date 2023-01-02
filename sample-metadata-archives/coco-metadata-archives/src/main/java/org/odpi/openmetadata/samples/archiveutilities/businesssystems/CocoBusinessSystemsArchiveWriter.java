/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.businesssystems;



import org.odpi.openmetadata.samples.archiveutilities.combo.CocoBaseArchiveWriter;

import java.util.Date;


/**
 * CocoBusinessSystemsArchiveWriter creates a physical open metadata archive file containing the descriptions of the
 * data flows from Coco Pharmaceuticals business systems to the data lake.
 */
public class CocoBusinessSystemsArchiveWriter extends CocoBaseArchiveWriter
{
    private static final String archiveFileName = "CocoBusinessSystemsArchive.json";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "ac202586-4042-407b-ae51-8096dfda223e";
    private static final String                  archiveName        = "Coco Pharmaceuticals Business Systems";
    private static final String                  archiveDescription = "The data flows from Coco Pharmaceuticals business systems to the data lake.";


    /**
     * Default constructor initializes the archive.
     */
    public CocoBusinessSystemsArchiveWriter()
    {
        super(archiveGUID,
              archiveName,
              archiveDescription,
              new Date(),
              archiveFileName);
    }


    /**
     * Add the content to the archive builder.
     */
    public void getArchiveContent()
    {
        addSystems();
    }

    private void addSystems()
    {
        for (SystemDefinition systemDefinition : SystemDefinition.values())
        {
            archiveHelper.addAsset("SoftwareServer",
                                   systemDefinition.getQualifiedName(),
                                   systemDefinition.getDisplayName(),
                                   systemDefinition.getDescription(),
                                   systemDefinition.getZones(),
                                   null,
                                   null);
        }
    }
}

