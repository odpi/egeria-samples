/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.types;

import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveHelper;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.ClassificationDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.ClassificationPropagationRule;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.EntityDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.RelationshipDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.RelationshipEndCardinality;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.RelationshipEndDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefAttribute;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefLink;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * CocoTypesArchiveWriter creates a physical open metadata archive file containing
 * the additional specialized types needed by Coco Pharmaceuticals.
 */
public class CocoTypesArchiveWriter extends OMRSArchiveWriter
{
    private static final String cocoTypesArchiveFileName = "CocoTypesArchive.json";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "50874908-01f1-47e2-83ea-e571109a946e";
    private static final String                  archiveName        = "CocoTypes";
    private static final String                  archiveLicense     = "Apache 2.0";
    private static final String                  archiveDescription = "Specialized types for Coco Pharmaceuticals.";
    private static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    private static final String                  originatorName     = "Egeria Project";
    private static final Date                    creationDate       = new Date(1639984840038L);

    /*
     * Specific values for initializing TypeDefs
     */
    private static final long   versionNumber = 1L;
    private static final String versionName   = "1.0";


    private OMRSArchiveBuilder archiveBuilder;
    private OMRSArchiveHelper  archiveHelper;

    /**
     * Default constructor initializes the archive.
     */
    private CocoTypesArchiveWriter()
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

        this.archiveHelper = new OMRSArchiveHelper(archiveBuilder,
                                                   archiveGUID,
                                                   originatorName,
                                                   creationDate,
                                                   versionNumber,
                                                   versionName);
    }


    /**
     * Create the type definition for BiopsyReport.
     *
     * @return Entity type definition (EntityDef)
     */
    private EntityDef addBiopsyReportEntity()
    {
        final String guid = "78479770-79ae-4bd8-b0ec-bf5e60c01e66";

        final String name            = "BiopsyReport";
        final String description     = "Results from a patient biopsy.";
        final String descriptionGUID = null;

        final String superTypeName = "Document";

        EntityDef entityDef = archiveHelper.getDefaultEntityDef(guid,
                                                                name,
                                                                this.archiveBuilder.getEntityDef(superTypeName),
                                                                description,
                                                                descriptionGUID);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute1Name            = "biopsyScope";
        final String attribute1Description     = "Is this biopsy excisional (targeted removal) or incisional (sample taken).";
        final String attribute1DescriptionGUID = null;
        final String attribute2Name            = "biopsyTechniqueType";
        final String attribute2Description     = "How was the biopsy taken?";
        final String attribute2DescriptionGUID = null;

        property = archiveHelper.getStringTypeDefAttribute(attribute1Name,
                                                           attribute1Description,
                                                           attribute1DescriptionGUID);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute2Name,
                                                           attribute2Description,
                                                           attribute2DescriptionGUID);
        properties.add(property);

        entityDef.setPropertiesDefinition(properties);

        return entityDef;
    }


    private RelationshipDef addBiopsySupportingEvidenceRelationship()
    {
        final String guid            = "54300f97-0140-4adb-b9a9-308514694f8d";
        final String name            = "BiopsySupportingEvidence";
        final String description     = "Link between a biopsy report and other data sources.";
        final String descriptionGUID = null;

        final ClassificationPropagationRule classificationPropagationRule = ClassificationPropagationRule.NONE;

        RelationshipDef relationshipDef = archiveHelper.getBasicRelationshipDef(guid,
                                                                                name,
                                                                                null,
                                                                                description,
                                                                                descriptionGUID,
                                                                                classificationPropagationRule);

        RelationshipEndDef relationshipEndDef;

        /*
         * Set up end 1.
         */
        final String                     end1EntityType               = "BiopsyReport";
        final String                     end1AttributeName            = "report";
        final String                     end1AttributeDescription     = "Report that the evidence is being linked to.";
        final String                     end1AttributeDescriptionGUID = null;
        final RelationshipEndCardinality end1Cardinality              = RelationshipEndCardinality.ANY_NUMBER;

        relationshipEndDef = archiveHelper.getRelationshipEndDef(this.archiveBuilder.getEntityDef(end1EntityType),
                                                                 end1AttributeName,
                                                                 end1AttributeDescription,
                                                                 end1AttributeDescriptionGUID,
                                                                 end1Cardinality);
        relationshipDef.setEndDef1(relationshipEndDef);


        /*
         * Set up end 2.
         */
        final String                     end2EntityType               = "Referenceable";
        final String                     end2AttributeName            = "evidence";
        final String                     end2AttributeDescription     = "Further information to support the report.";
        final String                     end2AttributeDescriptionGUID = null;
        final RelationshipEndCardinality end2Cardinality              = RelationshipEndCardinality.ANY_NUMBER;

        relationshipEndDef = archiveHelper.getRelationshipEndDef(this.archiveBuilder.getEntityDef(end2EntityType),
                                                                 end2AttributeName,
                                                                 end2AttributeDescription,
                                                                 end2AttributeDescriptionGUID,
                                                                 end2Cardinality);
        relationshipDef.setEndDef2(relationshipEndDef);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute1Name            = "notes";
        final String attribute1Description     = "Information for the clinical trials team relating to the evidence.";
        final String attribute1DescriptionGUID = null;

        property = archiveHelper.getArrayStringTypeDefAttribute(attribute1Name,
                                                                attribute1Description,
                                                                attribute1DescriptionGUID);
        properties.add(property);


        relationshipDef.setPropertiesDefinition(properties);

        return relationshipDef;
    }



    private ClassificationDef addReviewedByClinicalTrialsClassification()
    {
        final String guid = "c2fa7555-f366-4869-88f3-897d6f2ec5a4";

        final String name            = "ReviewedByClinicalTrials";
        final String description     = "Declares that a report or data set has been assessed by the clinical trials team.";
        final String descriptionGUID = null;

        final List<TypeDefLink> linkedToEntities = new ArrayList<>();

        linkedToEntities.add(this.archiveBuilder.getEntityDef("Asset"));

        ClassificationDef classificationDef = archiveHelper.getClassificationDef(guid,
                                                                                 name,
                                                                                 null,
                                                                                 description,
                                                                                 descriptionGUID,
                                                                                 linkedToEntities,
                                                                                 false);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute1Name            = "reviewer";
        final String attribute1Description     = "Person responsible for maintaining this relationship.";
        final String attribute1DescriptionGUID = null;
        final String attribute2Name            = "reviewerTypeName";
        final String attribute2Description     = "Type of element used to identify the reviewer.";
        final String attribute2DescriptionGUID = null;
        final String attribute3Name            = "reviewerPropertyName";
        final String attribute3Description     = "Name of property used to identify the reviewer.";
        final String attribute3DescriptionGUID = null;
        final String attribute4Name            = "notes";
        final String attribute4Description     = "Information for the clinical trials team relating to the review.";
        final String attribute4DescriptionGUID = null;

        property = archiveHelper.getStringTypeDefAttribute(attribute1Name,
                                                           attribute1Description,
                                                           attribute1DescriptionGUID);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute2Name,
                                                           attribute2Description,
                                                           attribute2DescriptionGUID);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute3Name,
                                                           attribute3Description,
                                                           attribute3DescriptionGUID);
        properties.add(property);
        property = archiveHelper.getArrayStringTypeDefAttribute(attribute4Name,
                                                                attribute4Description,
                                                                attribute4DescriptionGUID);
        properties.add(property);

        classificationDef.setPropertiesDefinition(properties);

        return classificationDef;
    }



    /**
     * Returns the open metadata type archive containing all of the new type definitions.
     *
     * @return populated open metadata archive object
     */
    protected OpenMetadataArchive getOpenMetadataArchive()
    {
        /*
         * Add the specialized types
         */
        this.archiveBuilder.addEntityDef(addBiopsyReportEntity());
        this.archiveBuilder.addRelationshipDef(addBiopsySupportingEvidenceRelationship());
        this.archiveBuilder.addClassificationDef(addReviewedByClinicalTrialsClassification());

        /*
         * The completed archive is ready to be packaged up and returned
         */
        return this.archiveBuilder.getOpenMetadataArchive();
    }


    /**
     * Generates and writes out an open metadata archive containing all of the connector types
     * describing the ODPi Egeria data store open connectors.
     */
    private void writeOpenMetadataArchive()
    {
        try
        {
            System.out.println("Writing to file: " + cocoTypesArchiveFileName);

            super.writeOpenMetadataArchive(cocoTypesArchiveFileName, this.getOpenMetadataArchive());
        }
        catch (Exception error)
        {
            System.out.println("error is " + error.toString());
        }
    }


    /**
     * Main program to initiate the archive writer for the connector types for data store connectors supported by
     * ODPi Egeria.
     *
     * @param args ignored
     */
    public static void main(String[] args)
    {
        try
        {
            CocoTypesArchiveWriter archiveWriter = new CocoTypesArchiveWriter();

            archiveWriter.writeOpenMetadataArchive();
        }
        catch (Exception error)
        {
            System.err.println("Exception: " + error.toString());
            System.exit(-1);
        }
    }
}
