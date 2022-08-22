/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.combo;


import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.samples.archiveutilities.GovernanceArchiveHelper;
import org.odpi.openmetadata.samples.archiveutilities.businesssystems.CocoBusinessSystemsArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.clinicaltrialtemplates.CocoClinicalTrialsArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.governanceengines.CocoGovernanceEnginesArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.governanceprogram.CocoGovernanceProgramArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.organization.CocoOrganizationArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.sustainability.CocoSustainabilityArchiveWriter;
import org.odpi.openmetadata.samples.archiveutilities.types.CocoTypesArchiveWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * CocoComboArchiveWriter creates a physical open metadata archive file containing the combination of Coco Pharmaceuticals open metadata instances.
 */
public class CocoComboArchiveWriter extends CocoBaseArchiveWriter
{
    private static final String archiveFileName = "CocoComboArchive.json";

    /*
     * This is the header information for the archive.
     */
    private static final String archiveGUID        = "655b1965-4c29-4a0e-8a5d-3f55a37b3799";
    private static final String archiveName        = "Coco Pharmaceuticals Combination";
    private static final String archiveDescription = "The combination of the contents of the Coco Pharmaceuticals' business systems, clinical trials templates, organization, and sustainability definitions.";


    /**
     * Default constructor initializes the archive.
     */
    public CocoComboArchiveWriter()
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
    protected void getArchiveContent()
    {
        CocoClinicalTrialsArchiveWriter    clinicalTrialsArchiveWriter    = new CocoClinicalTrialsArchiveWriter();
        CocoGovernanceProgramArchiveWriter governanceProgramArchiveWriter = new CocoGovernanceProgramArchiveWriter();
        CocoGovernanceEnginesArchiveWriter governanceEnginesArchiveWriter = new CocoGovernanceEnginesArchiveWriter();
        CocoBusinessSystemsArchiveWriter   businessSystemsArchiveWriter   = new CocoBusinessSystemsArchiveWriter();
        CocoOrganizationArchiveWriter      organizationArchiveWriter      = new CocoOrganizationArchiveWriter();
        CocoSustainabilityArchiveWriter    sustainabilityArchiveWriter    = new CocoSustainabilityArchiveWriter();

        /*
         * By setting the builder to the combo builder, the archive writers create content in the combo builder rather than their own private builder.
         */
        clinicalTrialsArchiveWriter.setArchiveBuilder(archiveBuilder);
        governanceProgramArchiveWriter.setArchiveBuilder(archiveBuilder);
        governanceEnginesArchiveWriter.setArchiveBuilder(archiveBuilder);
        businessSystemsArchiveWriter.setArchiveBuilder(archiveBuilder);
        organizationArchiveWriter.setArchiveBuilder(archiveBuilder);
        sustainabilityArchiveWriter.setArchiveBuilder(archiveBuilder);

        clinicalTrialsArchiveWriter.writeOpenMetadataArchive();
        governanceProgramArchiveWriter.writeOpenMetadataArchive();
        governanceEnginesArchiveWriter.writeOpenMetadataArchive();
        businessSystemsArchiveWriter.writeOpenMetadataArchive();
        organizationArchiveWriter.writeOpenMetadataArchive();
        sustainabilityArchiveWriter.writeOpenMetadataArchive();
    }

}
