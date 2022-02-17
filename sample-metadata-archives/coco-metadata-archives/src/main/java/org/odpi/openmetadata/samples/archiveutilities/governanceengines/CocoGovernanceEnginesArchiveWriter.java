/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.governanceengines;

import org.odpi.openmetadata.adapters.connectors.discoveryservices.CSVDiscoveryServiceProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.provisioning.MoveCopyFileGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.OriginSeekerGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.remediation.ZonePublisherGovernanceActionProvider;
import org.odpi.openmetadata.adapters.connectors.governanceactions.watchdog.GenericFolderWatchdogGovernanceActionProvider;
import org.odpi.openmetadata.opentypes.OpenMetadataTypesArchive;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveWriter;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.samples.archiveutilities.GovernanceArchiveHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CocoGovernanceEnginesArchiveWriter creates a physical open metadata archive file containing the governance engine definitions
 * needed by Coco Pharmaceuticals.
 */
public class CocoGovernanceEnginesArchiveWriter extends OMRSArchiveWriter
{
    private static final String archiveFileName = "CocoGovernanceEngineDefinitionsArchive.json";

    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "9cbd2b33-e80f-4df2-adc6-d859ebff4c34";
    private static final String                  archiveName        = "CocoGovernanceEngineDefinitions";
    private static final String                  archiveLicense     = "Apache 2.0";
    private static final String                  archiveDescription = "Governance Engines for Coco Pharmaceuticals.";
    private static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    private static final String                  originatorName     = "Egeria Project";
    private static final Date                    creationDate       = new Date(1639984840038L);

    /*
     * Specific values for initializing TypeDefs
     */
    private static final long   versionNumber = 1L;
    private static final String versionName   = "1.0";

    private static String GOVERNANCE_ACTION_ENGINE_TYPE_NAME  = "GovernanceActionEngine";
    private static String GOVERNANCE_ACTION_SERVICE_TYPE_NAME = "GovernanceActionService";

    private static String OPEN_DISCOVERY_ENGINE_TYPE_NAME  = "OpenDiscoveryEngine";
    private static String OPEN_DISCOVERY_SERVICE_TYPE_NAME = "OpenDiscoveryService";

    private OMRSArchiveBuilder      archiveBuilder;
    private GovernanceArchiveHelper archiveHelper;


    /**
     * Default constructor initializes the archive.
     */
    private CocoGovernanceEnginesArchiveWriter()
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
     * Create an entity for the AssetGovernance governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetGovernanceEngine()
    {
        final String assetGovernanceEngineName        = "AssetGovernance";
        final String assetGovernanceEngineDisplayName = "AssetGovernance Governance Action Engine";
        final String assetGovernanceEngineDescription = "Monitors, validates and enriches metadata relating to assets.";

        return archiveHelper.addGovernanceEngine(GOVERNANCE_ACTION_ENGINE_TYPE_NAME,
                                                 assetGovernanceEngineName,
                                                 assetGovernanceEngineDisplayName,
                                                 assetGovernanceEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Create an entity for the AssetDiscovery governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetDiscoveryEngine()
    {
        final String assetDiscoveryEngineName        = "AssetDiscovery";
        final String assetDiscoveryEngineDisplayName = "AssetDiscovery Open Discovery Engine";
        final String assetDiscoveryEngineDescription = "Extracts metadata about a digital resource and attach it to its asset description.";

        return archiveHelper.addGovernanceEngine(OPEN_DISCOVERY_ENGINE_TYPE_NAME,
                                                 assetDiscoveryEngineName,
                                                 assetDiscoveryEngineDisplayName,
                                                 assetDiscoveryEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Create an entity for the FTP governance action service.
     *
     * @return unique identifier for the governance engine
     */
    private String getFTPGovernanceActionService()
    {
        final String ftpGovernanceServiceName = "ftp-governance-action-service";
        final String ftpGovernanceServiceDisplayName = "FTP Governance Action Service";
        final String ftpGovernanceServiceDescription = "Simulates FTP from an external party.";
        final String ftpGovernanceServiceProviderClassName = MoveCopyFileGovernanceActionProvider.class.getName();
        final String ftpGovernanceServiceConfigurationPropertyName = "noLineage";

        Map<String, Object> configurationProperties = new HashMap<>();

        configurationProperties.put(ftpGovernanceServiceConfigurationPropertyName, "");

        return archiveHelper.addGovernanceService(GOVERNANCE_ACTION_ENGINE_TYPE_NAME,
                                                  ftpGovernanceServiceProviderClassName,
                                                  configurationProperties,
                                                  ftpGovernanceServiceName,
                                                  ftpGovernanceServiceDisplayName,
                                                  ftpGovernanceServiceDescription,
                                                  null,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addCopyFileRequestType(String governanceEngineGUID,
                                        String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "copy-file";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Create an entity for the generic watchdog governance action service.
     *
     * @return unique identifier for the governance engine
     */
    private String getWatchdogGovernanceActionService()
    {
        final String governanceServiceName = "new-measurements-watchdog-governance-action-service";
        final String governanceServiceDisplayName = "New Measurements Watchdog Governance Action Service";
        final String governanceServiceDescription = "Initiates a governance action process when a new weekly measurements file arrives.";
        final String governanceServiceProviderClassName = GenericFolderWatchdogGovernanceActionProvider.class.getName();

        return archiveHelper.addGovernanceService(GOVERNANCE_ACTION_SERVICE_TYPE_NAME,
                                                  governanceServiceProviderClassName,
                                                  null,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addWatchNestedInFolderRequestType(String governanceEngineGUID,
                                                   String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "watch-nested-in-folder";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Add a governance service that moves files from one location to another and creates lineage.
     *
     * @return unique identifier fo the governance service
     */
    private String addProvisionFileGovernanceActionService()
    {
        final String governanceServiceName = "provision-weekly-measurements-governance-action-service";
        final String governanceServiceDisplayName = "File Provisioning Governance Action Service";
        final String governanceServiceDescription = "Provisions weekly measurement files from the landing area to the data lake.";
        final String governanceServiceProviderClassName = MoveCopyFileGovernanceActionProvider.class.getName();

        final String targetFileNameConfigurationPropertyName = "targetFileNamePattern";
        final String targetFileNameConfigurationPropertyValue = "DropFoot_{1, number,000000}.csv";
        final String destinationFolderNameConfigurationPropertyName = "destinationFolder";
        final String destinationFolder = "./data-lake/research/clinical-trials/drop-foot/weekly-measurements";

        Map<String, Object> configurationProperties = new HashMap<>();

        configurationProperties.put(targetFileNameConfigurationPropertyName, targetFileNameConfigurationPropertyValue);
        configurationProperties.put(destinationFolderNameConfigurationPropertyName, destinationFolder);

        return archiveHelper.addGovernanceService(GOVERNANCE_ACTION_SERVICE_TYPE_NAME,
                                                  governanceServiceProviderClassName,
                                                  configurationProperties,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addProvisionFileRequestType(String governanceEngineGUID,
                                             String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "move-file";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Add a governance service that walks backwards through an asset's lineage to find an origin classification.  If found, the same origin is added
     * to the asset.
     *
     * @return unique identifier fo the governance service
     */
    private String addOriginSeekerGovernanceActionService()
    {
        final String governanceServiceName = "origin-seeker-governance-action-service";
        final String governanceServiceDisplayName = "Locate and Set Origin Governance Action Service";
        final String governanceServiceDescription = "Navigates back through the lineage relationships to locate the origin classification(s) from the source(s) and sets it on the requested asset if the origin is unique.";
        final String governanceServiceProviderClassName = OriginSeekerGovernanceActionProvider.class.getName();

        final String targetFileNameConfigurationPropertyName = "targetFileNamePattern";
        final String targetFileNameConfigurationPropertyValue = "DropFoot_{1, number,000000}.csv";
        final String destinationFolderNameConfigurationPropertyName = "destinationFolder";
        final String destinationFolder = "./data-lake/research/clinical-trials/drop-foot/weekly-measurements";

        Map<String, Object> configurationProperties = new HashMap<>();

        configurationProperties.put(targetFileNameConfigurationPropertyName, targetFileNameConfigurationPropertyValue);
        configurationProperties.put(destinationFolderNameConfigurationPropertyName, destinationFolder);

        return archiveHelper.addGovernanceService(GOVERNANCE_ACTION_SERVICE_TYPE_NAME,
                                                  governanceServiceProviderClassName,
                                                  configurationProperties,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addSeekOriginRequestType(String governanceEngineGUID,
                                          String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "seek-origin";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Add a governance service that walks backwards through an asset's lineage to find an origin classification.  If found, the same origin is added
     * to the asset.
     *
     * @return unique identifier fo the governance service
     */
    private String addZonePublisherGovernanceActionService()
    {
        final String governanceServiceName = "zone-publisher-governance-action-service";
        final String governanceServiceDisplayName = "Update Asset's Zone Membership Governance Action Service";
        final String governanceServiceDescription = "Set up the zone membership for one or more assets supplied as action targets.";
        final String governanceServiceProviderClassName = ZonePublisherGovernanceActionProvider.class.getName();

        final String publishZoneConfigurationPropertyName = "publishZones";
        final String publishZoneConfigurationPropertyValue = "data-lake,clinical-trials";

        Map<String, Object> configurationProperties = new HashMap<>();

        configurationProperties.put(publishZoneConfigurationPropertyName, publishZoneConfigurationPropertyValue);

        return archiveHelper.addGovernanceService(GOVERNANCE_ACTION_SERVICE_TYPE_NAME,
                                                  governanceServiceProviderClassName,
                                                  configurationProperties,
                                                  governanceServiceName,
                                                  governanceServiceDisplayName,
                                                  governanceServiceDescription,
                                                  null,
                                                  null);
    }


    /**
     * Set up the request type that links the governance engine to the governance service.
     *
     * @param governanceEngineGUID unique identifier of the governance engine
     * @param governanceServiceGUID unique identifier of the governance service
     */
    private void addSetZoneMembershipRequestType(String governanceEngineGUID,
                                                 String governanceServiceGUID)
    {
        final String governanceServiceRequestType = "set-zone-membership";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, governanceServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Create an entity for the CSV Asset Discovery governance service.
     *
     * @return unique identifier for the governance engine
     */
    private String getCSVAssetDiscoveryService()
    {
        final String discoveryServiceName = "csv-asset-discovery-service";
        final String discoveryServiceDisplayName = "CSV Asset Discovery Service";
        final String discoveryServiceDescription = "Discovers columns for CSV Files.";
        final String discoveryServiceProviderClassName = CSVDiscoveryServiceProvider.class.getName();

        return archiveHelper.addGovernanceService(OPEN_DISCOVERY_SERVICE_TYPE_NAME,
                                                  discoveryServiceProviderClassName,
                                                  null,
                                                  discoveryServiceName,
                                                  discoveryServiceDisplayName,
                                                  discoveryServiceDescription,
                                                  null,
                                                  null);
    }


    private void addSmallCSVRequestType(String governanceEngineGUID,
                                        String governanceServiceGUID)
    {
        final String discoveryServiceRequestType = "small-csv";

        archiveHelper.addSupportedGovernanceService(governanceEngineGUID, discoveryServiceRequestType, null, governanceServiceGUID);
    }


    /**
     * Create an entity for the AssetQuality governance engine.
     *
     * @return unique identifier for the governance engine
     */
    private String getAssetQualityEngine()
    {
        final String assetQualityEngineName        = "AssetQuality";
        final String assetQualityEngineDisplayName = "AssetQuality Open Discovery Engine";
        final String assetQualityEngineDescription = "Assess the quality of a digital resource identified by the asset in the request.";

        return archiveHelper.addGovernanceEngine(OPEN_DISCOVERY_ENGINE_TYPE_NAME,
                                                 assetQualityEngineName,
                                                 assetQualityEngineDisplayName,
                                                 assetQualityEngineDescription,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null,
                                                 null);
    }


    /**
     * Returns the open metadata archive containing new metadata entities.
     *
     * @return populated open metadata archive object
     */
    protected OpenMetadataArchive getOpenMetadataArchive()
    {
        String assetGovernanceEngineGUID = this.getAssetGovernanceEngine();

        this.addCopyFileRequestType(assetGovernanceEngineGUID, this.getFTPGovernanceActionService());
        this.addWatchNestedInFolderRequestType(assetGovernanceEngineGUID, this.getWatchdogGovernanceActionService());
        this.addProvisionFileRequestType(assetGovernanceEngineGUID, this.addProvisionFileGovernanceActionService());
        this.addSeekOriginRequestType(assetGovernanceEngineGUID, this.addOriginSeekerGovernanceActionService());
        this.addSetZoneMembershipRequestType(assetGovernanceEngineGUID, this.addZonePublisherGovernanceActionService());

        String assetDiscoveryEngineGUID = this.getAssetDiscoveryEngine();

        this.addSmallCSVRequestType(assetDiscoveryEngineGUID, this.getCSVAssetDiscoveryService());

        String assetQualityEngineGUID = this.getAssetQualityEngine();
        // todo add services when they written

        archiveHelper.saveGUIDs();

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
            System.out.println("Writing to file: " + archiveFileName);

            super.writeOpenMetadataArchive(archiveFileName, this.getOpenMetadataArchive());
        }
        catch (Exception error)
        {
            System.out.println("error is " + error.toString());
        }
    }


    /**
     * Main program to initiate the archive writer for the connector types for data store connectors supported by Egeria.
     *
     * @param args ignored
     */
    public static void main(String[] args)
    {
        try
        {
            CocoGovernanceEnginesArchiveWriter archiveWriter = new CocoGovernanceEnginesArchiveWriter();

            archiveWriter.writeOpenMetadataArchive();
        }
        catch (Exception error)
        {
            System.err.println("Exception: " + error.toString());
            System.exit(-1);
        }
    }
}
