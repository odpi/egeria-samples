/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.egeria.connectors.file.repositoryconnector;


import org.odpi.egeria.connectors.file.auditlog.FileOMRSAuditCode;
import org.odpi.egeria.connectors.file.auditlog.FileOMRSErrorCode;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.EndpointProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSMetadataCollection;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.EntityDetail;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceStatus;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.*;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectionCheckedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileOMRSRepositoryConnector extends OMRSRepositoryConnector {

    private static final Logger log = LoggerFactory.getLogger(org.odpi.egeria.connectors.file.repositoryconnector.FileOMRSRepositoryConnector.class);

    private final List<String> supportedAttributeTypeNames = Arrays.asList(new String[]{"foo", "bar"});
    private final List<String> supportedTypeNames = Arrays.asList(new String[]{
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

    private String folderLocation;

    /**
     * Default constructor used by the OCF Connector Provider.
     */
    public FileOMRSRepositoryConnector() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() throws ConnectorCheckedException {

        super.start();
        final String methodName = "start";

        auditLog.logMessage(methodName, FileOMRSAuditCode.REPOSITORY_SERVICE_STARTING.getMessageDefinition());

        if (metadataCollection == null) {
            try {
                connectToFolder(methodName);
            } catch (RepositoryErrorException cause) {
                raiseConnectorCheckedException(FileOMRSErrorCode.FAILED_TO_START_CONNECTOR, methodName, null);
            }
        }

        auditLog.logMessage(methodName, FileOMRSAuditCode.REPOSITORY_SERVICE_STARTED.getMessageDefinition(getServerName()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void disconnect() {
        final String methodName = "disconnect";
        auditLog.logMessage(methodName, FileOMRSAuditCode.REPOSITORY_SERVICE_SHUTDOWN.getMessageDefinition(getServerName()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OMRSMetadataCollection getMetadataCollection() throws RepositoryErrorException {
        final String methodName = "getMetadataCollection";
        if (metadataCollection == null) {
            // If the metadata collection has not yet been created, attempt to create it now
            connectToFolder(methodName);
        }

        return super.getMetadataCollection();
    }

    public void refreshRepository() {
        // TODO refresh the repository content


        File folder = new File(this.folderLocation);

        if(!folder.exists()){
            // TODO error
        } else if(!folder.isDirectory()) {
            // TODO error
        } else {
            File[] dataFiles = folder.listFiles();
            OMRSMetadataCollection inMemoryMetadataCollection = null;
            try {
                FileOMRSMetadataCollection fileMetadataCollection = (FileOMRSMetadataCollection)getMetadataCollection();
                inMemoryMetadataCollection = fileMetadataCollection.getInMemoryMetadataCollection();
            } catch( RepositoryErrorException e) {
                //TODO
            }
            InstanceProperties initialProperties=null;
            for (File dataFile:dataFiles) {
                // add data file entity
                try {
                    String canonicalName = dataFile.getCanonicalPath();
                    String name = dataFile.getName();


                     initialProperties = repositoryHelper.addStringPropertyToInstance("refreshRepository",
                                                                                                        null,
                                                                                                        "name",
                                                                                                        name,
                                                                                                        "refreshRepository");
                    initialProperties = repositoryHelper.addStringPropertyToInstance("refreshRepository",
                                                                                     initialProperties,
                                                                                     "qualifiedName",
                                                                                     canonicalName,  // TODO prefix
                                                                                     "refreshRepository");
                    initialProperties = repositoryHelper.addStringPropertyToInstance("refreshRepository",
                                                                                     initialProperties,
                                                                                     "GUID",
                                                                                     canonicalName,    //dodo generate a unique quid.
                                                                                     "refreshRepository");
                    int lastDotIndex = name.lastIndexOf(".");
                    if (name.length() >2 && lastDotIndex != -1 && lastDotIndex < name.length()-1) {
                        // if we can see a file type then add then add as an attribute
                        String fileType = name.substring(lastDotIndex+1 );
                        repositoryHelper.addStringPropertyToInstance("refreshRepository",
                                                                                         initialProperties,
                                                                                         "fileType",
                                                                                         fileType,
                                                                                         "refreshRepository");
                    }
                } catch( IOException e) {
                    // TODO
                }

                /*
                    TODO add
                        final String attribute1Name            = "createTime";
                        final String attribute1Description     = "Creation time of the data store.";
                        final String attribute2Name            = "modifiedTime";
                        final String attribute2Description     = "Last known modification time.";
                 */



                String entityTypeGUID = repositoryHelper.getTypeDefByName("FileOMRSMetadatacollection",
                                                                    "DataFile").getGUID();
                try {
                    EntityDetail addedEntity = inMemoryMetadataCollection.addEntity(
                            "userId",
                            entityTypeGUID,
                            initialProperties,
                            null,
                            InstanceStatus.ACTIVE);
                } catch (InvalidParameterException e) {
                    //TODO
                } catch (RepositoryErrorException e) {
                    //TODO
                } catch (TypeErrorException e) {
                    //TODO
                } catch (PropertyErrorException e) {
                    //TODO
                } catch (ClassificationErrorException e) {
                    //TODO
                } catch (StatusNotSupportedException e) {
                    //TODO
                } catch (FunctionNotSupportedException e) {
                    //TODO
                } catch (UserNotAuthorizedException e) {
                    //TODO
                }

            }


        }
    }

    /**
     * Attempt to connect to the folder.
     *
     * @param methodName the method attempting to connect
     * @throws RepositoryErrorException if there is any issue connecting
     */
    private void connectToFolder(String methodName) throws RepositoryErrorException {
        EndpointProperties endpointProperties = connectionProperties.getEndpoint();
        if (endpointProperties == null) {
            raiseRepositoryErrorException(FileOMRSErrorCode.FOLDER_NOT_SUPPLIED_IN_CONFIG, methodName, null, "null");
        } else {
            this.folderLocation = endpointProperties.getAddress();
            metadataCollection = new FileOMRSMetadataCollection(this,
                                                                serverName,
                                                                repositoryHelper,
                                                                repositoryValidator,
                                                                metadataCollectionId,
                                                                supportedAttributeTypeNames,
                                                                supportedTypeNames,
                                                                folderLocation
            );
        }
    }






    /**
     * Throws a ConnectorCheckedException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the name of the method throwing the exception
     * @param cause the underlying cause of the exception (if any, null otherwise)
     * @param params any parameters for formatting the error message
     * @throws ConnectorCheckedException always
     */
    private void raiseConnectorCheckedException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws ConnectorCheckedException {
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

    /**
     * Throws a RepositoryErrorException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the name of the method throwing the exception
     * @param cause the underlying cause of the exception (or null if none)
     * @param params any parameters for formatting the error message
     * @throws RepositoryErrorException always
     */
    private void raiseRepositoryErrorException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws RepositoryErrorException {
        if (cause == null) {
            throw new RepositoryErrorException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new RepositoryErrorException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }

}
