/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.egeria.connectors.file.eventmapper;


import org.odpi.egeria.connectors.file.auditlog.FileOMRSAuditCode;
import org.odpi.egeria.connectors.file.auditlog.FileOMRSErrorCode;
import org.odpi.egeria.connectors.file.repositoryconnector.FileOMRSMetadataCollection;
import org.odpi.egeria.connectors.file.repositoryconnector.FileOMRSRepositoryConnector;
import org.odpi.egeria.connectors.file.repositoryconnector.model.FileGuid;

import org.odpi.openmetadata.repositoryservices.events.OMRSInstanceEvent;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.repositoryservices.connectors.openmetadatatopic.OpenMetadataTopicListener;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.AttributeTypeDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefCategory;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefPatch;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryeventmapper.OMRSRepositoryEventMapperBase;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceGraph;

import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.ClassificationErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.EntityConflictException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.EntityNotDeletedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.EntityNotKnownException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.EntityProxyOnlyException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.FunctionNotSupportedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.HomeEntityException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.HomeRelationshipException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.InvalidEntityException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.InvalidParameterException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.InvalidRelationshipException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.InvalidTypeDefException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.OMRSLogicErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.PagingErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.PatchErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.PropertyErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.RelationshipConflictException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.RelationshipNotDeletedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.RelationshipNotKnownException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.RepositoryErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.StatusNotSupportedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeDefConflictException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeDefInUseException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeDefKnownException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeDefNotKnownException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeDefNotSupportedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeErrorException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.UserNotAuthorizedException;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.AttributeTypeDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDef;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.TypeDefPatch;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryHelper;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryValidator;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * FileOMRSRepositoryEventMapper supports the event mapper function for Apache File
 * when used as an open metadata repository.
 */
public class FileOMRSRepositoryEventMapper extends OMRSRepositoryEventMapperBase
//        implements OpenMetadataTopicListener
{

    private static final Logger log = LoggerFactory.getLogger(FileOMRSRepositoryEventMapper.class);
    private static final Duration pollDuration = Duration.ofMillis(100);

    private String sourceName;
    private FileOMRSRepositoryConnector fileRepositoryConnector;
    private FileOMRSMetadataCollection fileMetadataCollection;
    private OMRSRepositoryHelper repositoryHelper;

    private String metadataCollectionId;
    private String originatorServerName;
    private String originatorServerType;
    Map<String, String> typeNameToGuidMap =null;

    private Properties fileKafkaProperties;
    private String fileKafkaTopic;

    private PollingThread pollingThread;
//    private EntityMessageDeserializer deserializer;


    /**
     * Default constructor
     */
    public FileOMRSRepositoryEventMapper() {
        super();
        this.sourceName = "FileOMRSRepositoryEventMapper";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() throws ConnectorCheckedException {

        super.start();

        final String methodName = "start";

        auditLog.logMessage(methodName, FileOMRSAuditCode.EVENT_MAPPER_STARTING.getMessageDefinition());

        if (!(repositoryConnector instanceof FileOMRSRepositoryConnector)) {
            raiseConnectorCheckedException(FileOMRSErrorCode.EVENT_MAPPER_IMPROPERLY_INITIALIZED, methodName, null, repositoryConnector.getServerName());
        }
        this.fileRepositoryConnector = (FileOMRSRepositoryConnector) this.repositoryConnector;
        this.repositoryHelper = this.fileRepositoryConnector.getRepositoryHelper();


        // this.deserializer = new EntityMessageDeserializer();

        this.pollingThread = new PollingThread();
        try {
            this.fileMetadataCollection = (FileOMRSMetadataCollection) fileRepositoryConnector.getMetadataCollection();
        } catch (RepositoryErrorException e) {
//            raiseConnectorCheckedException(FileOMRSErrorCode.REST_CLIENT_FAILURE, methodName, e, fileRepositoryConnector.getServerName());
        }
//        this.fileMetadataCollection.setEventMapper(this);

        this.originatorServerName = fileRepositoryConnector.getServerName();
        this.originatorServerType = fileRepositoryConnector.getServerType();
        pollingThread.start();

    }


    /**
     * Class to support multi-threaded consumption of Apache File Kafka events.
     */
    private class PollingThread implements Runnable {

        private final AtomicBoolean running = new AtomicBoolean(false);

        void start() {
            Thread worker = new Thread(this);
            worker.start();
        }

        void stop() {
            running.set(false);
        }
//        void processEvent(String event) {
//            //no op
//        }

        /**
         * Read File.
         */
        @Override
        public void run() {

            final String methodName = "run";
            running.set(true);

            final List<String> supportedTypeNames = Arrays.asList(new String[]{
                    // entity types
                    "DataFile",
                    "Connection",
                    "ConnectorType",
                    "Endpoint",
                    // relationship types
                    "ConnectionEndpoint",
                    "ConnectionConnectorType",
                    "ConnectionToAsset"
                    // classification types
                    // none at this time
            });

            Map<String,String> typeNameToGuidMap = null;
            while (running.get()) {
                // the below is only for local OMRS .
//                OMRSRepositoryEventManager repositoryEventManager = fileRepositoryConnector.getOutboundRepositoryEventManager();

                if (typeNameToGuidMap == null) {
                    typeNameToGuidMap = new HashMap<>();
                    // populate the type name to guid map
                       for (String typeName : supportedTypeNames) {
                        TypeDef typeDef = repositoryHelper.getTypeDefByName("FileOMRSRepositoryEventMapper",
                                                                            typeName);
                        typeNameToGuidMap.put(typeName,typeDef.getGUID());
                       }
                }

                try {
                    // call the repository connector to refresh its contents.
                    fileRepositoryConnector.refreshRepository();

                    List<EntityDetail> dataFiles = getEntitiesByTypeGuid( "DataFile");
//                    List<EntityDetail> dataManagerEntities = getEntitiesByTypeGuid("Connection");
//                    List<EntityDetail> folderEntities = getEntitiesByTypeGuid("ConnectorType");
//                    List<EntityDetail> tabularFileColumnEntities = getEntitiesByTypeGuid( "Endpoint");
//                    List<Relationship>  connectionEndpointRelationships = getRelationshipsByTypeGuid( "ConnectionEndpoint");
//                    List<Relationship>  connectionConnectorTypeRelationships = getRelationshipsByTypeGuid( "ConnectionConnectorType");
//                    List<Relationship>  connectionToAssetRelationship = getRelationshipsByTypeGuid( "ConnectionToAsset");



                    for (EntityDetail dataFile: dataFiles ) {
                        // create a batch event per file
                        List<Relationship> relationshipList = new ArrayList<>();
                        List<EntityDetail> entityList = new ArrayList<>();
                        entityList.add(dataFile);
                        // TODO fill in the lists
                        InstanceGraph instances = new InstanceGraph(entityList, relationshipList);

                        // send the event
                        repositoryEventProcessor.processInstanceBatchEvent("",
                                                                           fileRepositoryConnector.getMetadataCollectionId(),
                                                                           fileRepositoryConnector.getServerName(),
                                                                           fileRepositoryConnector.getServerType(),
                                                                           fileRepositoryConnector.getOrganizationName(),
                                                                           instances);
                    }


                } catch ( Exception e) {
                    //TODO
                }
                //  scope a call /calls to the repository connector for example for a file
                //  wait the polling interval.
            }
        }

        private List<EntityDetail> getEntitiesByTypeGuid(String typeName) throws
                                                                          InvalidParameterException,
                                                                          RepositoryErrorException,
                                                                          TypeErrorException,
                                                                          PropertyErrorException,
                                                                          PagingErrorException,
                                                                          FunctionNotSupportedException,
                                                                          UserNotAuthorizedException {
            String typeGUID = typeNameToGuidMap.get(typeName);
            if (typeGUID == null) {
                // TODO throw Exception
                return null;
            } else {
                return fileMetadataCollection.findEntities(
                        "userId",    //TODO get from config
                        typeGUID,
                        null,
                        null,
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        0);
            }
        }
        private List<Relationship> getRelationshipsByTypeGuid(String typeName) throws
                                                                          InvalidParameterException,
                                                                          RepositoryErrorException,
                                                                          TypeErrorException,
                                                                          PropertyErrorException,
                                                                          PagingErrorException,
                                                                          FunctionNotSupportedException,
                                                                          UserNotAuthorizedException {
            String typeGUID = typeNameToGuidMap.get(typeName);
            if (typeGUID == null) {
                // TODO throw Exception
                return null;
            } else {
                return fileMetadataCollection.findRelationships(
                        "userId",    //TODO get from config
                       typeGUID,
                        null,
                        null,
                        0,
                        null,
                        null,
                        null,
                        null,
                        0);
            }
        }
    }


        /**
         * {@inheritDoc}
         */
        @Override
        public void disconnect() throws ConnectorCheckedException {
            super.disconnect();
            final String methodName = "disconnect";
            pollingThread.stop();
            auditLog.logMessage(methodName, FileOMRSAuditCode.EVENT_MAPPER_SHUTDOWN.getMessageDefinition(fileRepositoryConnector.getServerName()));
        }


//    /**
//     * Sends a refresh entity request event.
//     *
//     * @param typeDefGUID unique identifier of requested entity's TypeDef
//     * @param typeDefName unique name of requested entity's TypeDef
//     * @param entityGUID unique identifier of requested entity
//     * @param homeMetadataCollectionId identifier of the metadata collection that is the home to this entity
//     */
//    public void sendRefreshEntityRequest(String typeDefGUID,
//                                         String typeDefName,
//                                         String entityGUID,
//                                         String homeMetadataCollectionId) {
//        repositoryEventProcessor.processRefreshEntityRequested(
//                sourceName,
//                metadataCollectionId,
//                localServerName,
//                localServerType,
//                localOrganizationName,
//                typeDefGUID,
//                typeDefName,
//                entityGUID,
//                homeMetadataCollectionId);
//    }
//
//    /**
//     * Sends a refresh relationship request event.
//     *
//     * @param typeDefGUID the guid of the TypeDef for the relationship used to verify the relationship identity
//     * @param typeDefName the name of the TypeDef for the relationship used to verify the relationship identity
//     * @param relationshipGUID unique identifier of the relationship
//     * @param homeMetadataCollectionId unique identifier for the home repository for this relationship
//     */
//    public void sendRefreshRelationshipRequest(String typeDefGUID,
//                                               String typeDefName,
//                                               String relationshipGUID,
//                                               String homeMetadataCollectionId) {
//        repositoryEventProcessor.processRefreshRelationshipRequest(
//                sourceName,
//                metadataCollectionId,
//                localServerName,
//                localServerType,
//                localOrganizationName,
//                typeDefGUID,
//                typeDefName,
//                relationshipGUID,
//                homeMetadataCollectionId);
//    }

        /**
         * Throws a ConnectorCheckedException based on the provided parameters.
         *
         * @param errorCode the error code for the exception
         * @param methodName the method name throwing the exception
         * @param cause the underlying cause of the exception (if any, otherwise null)
         * @param params any additional parameters for formatting the error message
         * @throws ConnectorCheckedException always
         */
    private void raiseConnectorCheckedException(FileOMRSErrorCode errorCode, String methodName, Exception cause, String ...params) throws ConnectorCheckedException {
        if (cause == null) {
            throw new ConnectorCheckedException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new ConnectorCheckedException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }


}
