/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.egeria.connectors.file.repositoryconnector;

import org.odpi.egeria.connectors.file.auditlog.FileOMRSErrorCode;
import org.odpi.egeria.connectors.file.eventmapper.FileOMRSRepositoryEventMapper;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;

import org.odpi.openmetadata.frameworks.connectors.Connector;
import org.odpi.openmetadata.frameworks.connectors.ConnectorBroker;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectionCheckedException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ConnectorType;
import org.odpi.openmetadata.repositoryservices.archivemanager.OMRSArchiveManager;
import org.odpi.openmetadata.adapters.repositoryservices.inmemory.repositoryconnector.InMemoryOMRSRepositoryConnectorProvider;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSMetadataCollection;
import org.odpi.openmetadata.adminservices.configuration.properties.OpenMetadataExchangeRule;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.localrepository.repositoryconnector.LocalOMRSConnectorProvider;
import org.odpi.openmetadata.repositoryservices.localrepository.repositoryconnector.LocalOMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.localrepository.repositorycontentmanager.OMRSRepositoryContentHelper;
import org.odpi.openmetadata.repositoryservices.localrepository.repositorycontentmanager.OMRSRepositoryContentManager;
import org.odpi.openmetadata.repositoryservices.localrepository.repositorycontentmanager.OMRSRepositoryContentValidator;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.search.SearchClassifications;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.search.SearchProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSFixedTypeMetadataCollectionBase;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.MatchCriteria;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.SequencingOrder;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryHelper;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryValidator;
import org.odpi.openmetadata.repositoryservices.eventmanagement.OMRSRepositoryEventExchangeRule;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class FileOMRSMetadataCollection extends OMRSFixedTypeMetadataCollectionBase {

    private static final Logger log = LoggerFactory.getLogger(org.odpi.egeria.connectors.file.repositoryconnector.FileOMRSMetadataCollection.class);

    private final SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private FileOMRSRepositoryConnector fileRepositoryConnector;
    private Set<InstanceStatus> availableStates;
    private FileOMRSRepositoryEventMapper eventMapper = null;
    private String folderLocation = null;
    String metadataCollectionId = null;
    OMRSMetadataCollection inMemoryMetadataCollection = null;

    /**
     * @param parentConnector      connector that this metadata collection supports.
     *                             The connector has the information to call the metadata repository.
     * @param repositoryName       name of this repository.
     * @param repositoryHelper     helper that provides methods to repository connectors and repository event mappers
     *                             to build valid type definitions (TypeDefs), entities and relationships.
     * @param repositoryValidator  validator class for checking open metadata repository objects and parameters
     * @param metadataCollectionId unique identifier for the repository
     * @param supportedAttributeTypeNames supported attribute type names
     * @param supportedTypeNames   supported type names
     * @param auditLog             audit log
     * @param folderLocation       folder location
     */

    public FileOMRSMetadataCollection(FileOMRSRepositoryConnector parentConnector,
                                      String repositoryName,
                                      OMRSRepositoryHelper repositoryHelper,
                                      OMRSRepositoryValidator repositoryValidator,
                                      String metadataCollectionId,
                                      List<String> supportedAttributeTypeNames,
                                      List<String> supportedTypeNames,
                                      AuditLog auditLog,
                                      String folderLocation) throws  RepositoryErrorException {
        super(parentConnector,
              repositoryName,
              repositoryHelper,
              repositoryValidator,
              metadataCollectionId,
              supportedAttributeTypeNames,
              supportedTypeNames);

        this.folderLocation = folderLocation;
        this.metadataCollectionId = metadataCollectionId;
        try {
            OMRSRepositoryConnector embeddedInMemoryConnector = initializeInMemoryRepositoryConnector(  repositoryHelper,
                                                                                                        repositoryValidator,
                                                                                                        auditLog);
            this.inMemoryMetadataCollection = embeddedInMemoryConnector.getMetadataCollection();
        } catch (ConnectionCheckedException e) {
            raiseRepositoryErrorException(FileOMRSErrorCode.COLLECTION_FAILED_INITIALISE, "FileOMRSMetadataCollection constructor", e, "null");
        } catch (ConnectorCheckedException e) {
            raiseRepositoryErrorException(FileOMRSErrorCode.COLLECTION_FAILED_INITIALISE, "FileOMRSMetadataCollection constructor", e, "null");
        }
    }
    OMRSMetadataCollection getInMemoryMetadataCollection() {
        return inMemoryMetadataCollection;
    }
    private OMRSRepositoryConnector initializeInMemoryRepositoryConnector(OMRSRepositoryHelper  repositoryHelper,
                                                                          OMRSRepositoryValidator repositoryValidator,
                                                                          AuditLog auditLog) throws ConnectionCheckedException ,ConnectorCheckedException {

        Connection connection = new Connection();
        ConnectorType connectorType = new ConnectorType();
        connection.setConnectorType(connectorType);
        connectorType.setConnectorProviderClassName(InMemoryOMRSRepositoryConnectorProvider.class.getName());
        ConnectorBroker connectorBroker = new ConnectorBroker();
        Connector connector = connectorBroker.getConnector(connection);
        OMRSRepositoryConnector repositoryConnector = (OMRSRepositoryConnector) connector;

        OMRSRepositoryContentManager localRepositoryContentManager = new OMRSRepositoryContentManager("USER_ID", auditLog);

        LocalOMRSRepositoryConnector localOMRSRepositoryConnector = (LocalOMRSRepositoryConnector) new LocalOMRSConnectorProvider(metadataCollectionId,
                                                                                                                                  connection,
                                                                                                                                  null,
                                                                                                                                  null,
                                                                                                                                  localRepositoryContentManager,
                                                                                                                                  new OMRSRepositoryEventExchangeRule(OpenMetadataExchangeRule.ALL, null))
                .getConnector(connection);

        localOMRSRepositoryConnector.setAuditLog(auditLog);
        localOMRSRepositoryConnector.setRepositoryHelper(repositoryHelper);
        localOMRSRepositoryConnector.setRepositoryValidator(repositoryValidator);
        localOMRSRepositoryConnector.setMetadataCollectionId(metadataCollectionId);
//        localRepositoryContentManager.setupEventProcessor(localOMRSRepositoryConnector, localRepositoryEventManager);
        repositoryConnector.setRepositoryHelper(repositoryHelper);
        repositoryConnector.setRepositoryValidator(repositoryValidator);
        repositoryConnector.setMetadataCollectionId(metadataCollectionId);
        repositoryConnector.start();
        localOMRSRepositoryConnector.start();

        return localOMRSRepositoryConnector;
    }

    /**
     * Return a list of entities that match the supplied criteria.  The results can be returned over many pages.
     *
     * @param userId unique identifier for requesting user.
     * @param entityTypeGUID String unique identifier for the entity type of interest (null means any entity type).
     * @param entitySubtypeGUIDs optional list of the unique identifiers (guids) for subtypes of the entityTypeGUID to
     *                           include in the search results. Null means all subtypes.
     * @param matchProperties Optional list of entity property conditions to match.
     * @param fromEntityElement the starting element number of the entities to return.
     *                                This is used when retrieving elements
     *                                beyond the first page of results. Zero means start from the first element.
     * @param limitResultsByStatus By default, entities in all non-DELETED statuses are returned.  However, it is possible
     *                             to specify a list of statuses (eg ACTIVE) to restrict the results to.  Null means all
     *                             status values except DELETED.
     * @param matchClassifications Optional list of entity classifications to match.
     * @param asOfTime Requests a historical query of the entity.  Null means return the present values.
     * @param sequencingProperty String name of the entity property that is to be used to sequence the results.
     *                           Null means do not sequence on a property name (see SequencingOrder).
     * @param sequencingOrder Enum defining how the results should be ordered.
     * @param pageSize the maximum number of result entities that can be returned on this request.  Zero means
     *                 unrestricted return results size.
     * @return a list of entities matching the supplied criteria; null means no matching entities in the metadata
     * collection.
     * @throws InvalidParameterException a parameter is invalid or null.
     * @throws TypeErrorException the type guid passed on the request is not known by the
     *                              metadata collection.
     * @throws RepositoryErrorException there is a problem communicating with the metadata repository where
     *                                    the metadata collection is stored.
     * @throws PropertyErrorException the properties specified are not valid for any of the requested types of
     *                                  entity.
     * @throws PagingErrorException the paging/sequencing parameters are set up incorrectly.
     * @throws UserNotAuthorizedException the userId is not permitted to perform this operation.
     */
    @Override
    public List<EntityDetail> findEntities(String                    userId,
                                           String                    entityTypeGUID,
                                           List<String>              entitySubtypeGUIDs,
                                           SearchProperties          matchProperties,
                                           int                       fromEntityElement,
                                           List<InstanceStatus>      limitResultsByStatus,
                                           SearchClassifications     matchClassifications,
                                           Date                      asOfTime,
                                           String                    sequencingProperty,
                                           SequencingOrder           sequencingOrder,
                                           int                       pageSize) throws InvalidParameterException,
                                                                                      RepositoryErrorException,
                                                                                      TypeErrorException,
                                                                                      PropertyErrorException,
                                                                                      PagingErrorException,
                                                                                      UserNotAuthorizedException,
                                                                                      FunctionNotSupportedException
    {

        // call the embedded in memory connector
        List<EntityDetail>  foundEntities = null;
        // TODO do we need to fix up provenance headers
        if (inMemoryMetadataCollection != null) {
            return inMemoryMetadataCollection.findEntities(userId,
                                                           entityTypeGUID,
                                                           entitySubtypeGUIDs,
                                                           matchProperties,
                                                           fromEntityElement,
                                                           limitResultsByStatus,
                                                           matchClassifications,
                                                           asOfTime,
                                                           sequencingProperty,
                                                           sequencingOrder,
                                                           pageSize);
        } else {
            return null;
        }
    }

    /**
     * Return a list of relationships that match the requested conditions.  The results can be received as a series of
     * pages.
     *
     * @param userId unique identifier for requesting user.
     * @param relationshipTypeGUID unique identifier (guid) for the relationship's type.  Null means all types
     *                             (but may be slow so not recommended).
     * @param relationshipSubtypeGUIDs optional list of the unique identifiers (guids) for subtypes of the
     *                                 relationshipTypeGUID to include in the search results. Null means all subtypes.
     * @param matchProperties Optional list of relationship property conditions to match.
     * @param fromRelationshipElement the starting element number of the entities to return.
     *                                This is used when retrieving elements
     *                                beyond the first page of results. Zero means start from the first element.
     * @param limitResultsByStatus By default, relationships in all non-DELETED statuses are returned.  However, it is possible
     *                             to specify a list of statuses (eg ACTIVE) to restrict the results to.  Null means all
     *                             status values except DELETED.
     * @param asOfTime Requests a historical query of the relationships for the entity.  Null means return the
     *                 present values.
     * @param sequencingProperty String name of the property that is to be used to sequence the results.
     *                           Null means do not sequence on a property name (see SequencingOrder).
     * @param sequencingOrder Enum defining how the results should be ordered.
     * @param pageSize the maximum number of result relationships that can be returned on this request.  Zero means
     *                 unrestricted return results size.
     * @return a list of relationships.  Null means no matching relationships.
     * @throws InvalidParameterException one of the parameters is invalid or null.
     * @throws TypeErrorException the type guid passed on the request is not known by the
     *                              metadata collection.
     * @throws RepositoryErrorException there is a problem communicating with the metadata repository where
     *                                    the metadata collection is stored.
     * @throws PropertyErrorException the properties specified are not valid for any of the requested types of
     *                                  relationships.
     * @throws PagingErrorException the paging/sequencing parameters are set up incorrectly.
     * @throws FunctionNotSupportedException the repository does not support one of the provided parameters.
     * @throws UserNotAuthorizedException the userId is not permitted to perform this operation.
     * @see OMRSRepositoryHelper#getExactMatchRegex(String)
     */
    @Override
    public  List<Relationship> findRelationships(String                    userId,
                                                 String                    relationshipTypeGUID,
                                                 List<String>              relationshipSubtypeGUIDs,
                                                 SearchProperties          matchProperties,
                                                 int                       fromRelationshipElement,
                                                 List<InstanceStatus>      limitResultsByStatus,
                                                 Date                      asOfTime,
                                                 String                    sequencingProperty,
                                                 SequencingOrder           sequencingOrder,
                                                 int                       pageSize) throws InvalidParameterException,
                                                                                            TypeErrorException,
                                                                                            RepositoryErrorException,
                                                                                            PropertyErrorException,
                                                                                            PagingErrorException,
                                                                                            FunctionNotSupportedException,
                                                                                            UserNotAuthorizedException
    {
        final String  methodName = "findRelationships";

        this.findRelationshipsParameterValidation(userId,
                                                  relationshipTypeGUID,
                                                  relationshipSubtypeGUIDs,
                                                  matchProperties,
                                                  fromRelationshipElement,
                                                  limitResultsByStatus,
                                                  asOfTime,
                                                  sequencingProperty,
                                                  sequencingOrder,
                                                  pageSize);

        /*
         * Perform operation
         */
        reportUnsupportedOptionalFunction(methodName);
        return null;
    }

    /**
     * Throw an EntityNotKnownException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param cause the underlying cause of the exception (if any, otherwise null)
     * @param params any parameters for formatting the error message
     * @throws EntityNotKnownException always
     */
    private void raiseEntityNotKnownException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws EntityNotKnownException {
        if (cause == null) {
            throw new EntityNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new EntityNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }

    /**
     * Throw a RelationshipNotKnownException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param cause the underlying cause of the exception (if any, otherwise null)
     * @param params any parameters for formatting the error message
     * @throws RelationshipNotKnownException always
     */
    private void raiseRelationshipNotKnownException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws RelationshipNotKnownException {
        if (cause == null) {
            throw new RelationshipNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new RelationshipNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }

    /**
     * Throw a RepositoryErrorException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param cause the underlying cause of the exception (if any, otherwise null)
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

    /**
     * Throw a TypeDefNotKnownException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param cause the underlying cause of the exception (if any, otherwise null)
     * @param params any parameters for formatting the error message
     * @throws TypeDefNotKnownException always
     */
    private void raiseTypeDefNotKnownException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws TypeDefNotKnownException {
        if (cause == null) {
            throw new TypeDefNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new TypeDefNotKnownException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }

    /**
     * Throw a TypeDefNotSupportedException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param cause the underlying cause of the exception (if any, otherwise null)
     * @param params any parameters for formatting the error message
     * @throws TypeDefNotSupportedException always
     */
    private void raiseTypeDefNotSupportedException(FileOMRSErrorCode errorCode, String methodName, Throwable cause, String ...params) throws TypeDefNotSupportedException {
        if (cause == null) {
            throw new TypeDefNotSupportedException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName);
        } else {
            throw new TypeDefNotSupportedException(errorCode.getMessageDefinition(params),
                    this.getClass().getName(),
                    methodName,
                    cause);
        }
    }

    /**
     * Throw a FunctionNotSupportedException using the provided parameters.
     * @param errorCode the error code for the exception
     * @param methodName the method throwing the exception
     * @param params any parameters for formatting the error message
     * @throws FunctionNotSupportedException always
     */
    private void raiseFunctionNotSupportedException(FileOMRSErrorCode errorCode, String methodName, String ...params) throws FunctionNotSupportedException {
        throw new FunctionNotSupportedException(errorCode.getMessageDefinition(params),
                this.getClass().getName(),
                methodName);
    }

}
