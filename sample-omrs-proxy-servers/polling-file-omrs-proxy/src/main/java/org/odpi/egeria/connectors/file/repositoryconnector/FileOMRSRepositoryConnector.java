/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.egeria.connectors.file.repositoryconnector;


import org.odpi.egeria.connectors.file.auditlog.FileOMRSAuditCode;
import org.odpi.egeria.connectors.file.auditlog.FileOMRSErrorCode;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.EndpointProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSMetadataCollection;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.RepositoryErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FileOMRSRepositoryConnector extends OMRSRepositoryConnector {

    private static final Logger log = LoggerFactory.getLogger(org.odpi.egeria.connectors.file.repositoryconnector.FileOMRSRepositoryConnector.class);

    private final List<String> supportedAttributeTypeNames = Arrays.asList(new String[]{"foo", "bar"});
    private final List<String> supportedTypeNames = Arrays.asList(new String[]{
                                                                                // entity types
                                                                                "CSVFile",
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

//        atlasEntityTypesByName = new HashMap<>();
    }

    public void refreshRepository() {
        // TODO refresh the repository content
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OMRSMetadataCollection getMetadataCollection() throws RepositoryErrorException {
        final String methodName = "getMetadataCollection";
        if (metadataCollection == null) {
            EndpointProperties endpointProperties = connectionProperties.getEndpoint();
            if (endpointProperties == null) {
                //raiseConnectorCheckedException(FileOMRSErrorCode.REST_CLIENT_FAILURE, methodName, null, "null");
            } else {
               this.folderLocation = endpointProperties.getProtocol() + "://" + endpointProperties.getAddress();
               auditLog.logMessage(methodName, FileOMRSAuditCode.CONNECTING_TO_FOLDER.getMessageDefinition(folderLocation));
               // TODO check folder exists

            }

        }
        return super.getMetadataCollection();
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
            // If the metadata collection has not yet been created, attempt to create it now
//            connectToAtlas(methodName);
            //this.folderLocation = endpointProperties.getProtocol() + "://" + endpointProperties.getAddress();
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

//    /**
//     * Throws a ConnectorCheckedException using the provided parameters.
//     * @param errorCode the error code for the exception
//     * @param methodName the name of the method throwing the exception
//     * @param cause the underlying cause of the exception (if any, null otherwise)
//     * @param params any parameters for formatting the error message
//     * @throws ConnectorCheckedException always
//     */
//    private void raiseConnectorCheckedException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws ConnectorCheckedException {
//        if (cause == null) {
//            throw new ConnectorCheckedException(errorCode.getMessageDefinition(params),
//                    this.getClass().getName(),
//                    methodName);
//        } else {
//            throw new ConnectorCheckedException(errorCode.getMessageDefinition(params),
//                    this.getClass().getName(),
//                    methodName,
//                    cause);
//        }
//    }
//
//    /**
//     * Throws a RepositoryErrorException using the provided parameters.
//     * @param errorCode the error code for the exception
//     * @param methodName the name of the method throwing the exception
//     * @param cause the underlying cause of the exception (or null if none)
//     * @param params any parameters for formatting the error message
//     * @throws RepositoryErrorException always
//     */
//    private void raiseRepositoryErrorException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws RepositoryErrorException {
//        if (cause == null) {
//            throw new RepositoryErrorException(errorCode.getMessageDefinition(params),
//                    this.getClass().getName(),
//                    methodName);
//        } else {
//            throw new RepositoryErrorException(errorCode.getMessageDefinition(params),
//                    this.getClass().getName(),
//                    methodName,
//                    cause);
//        }
//    }

}
