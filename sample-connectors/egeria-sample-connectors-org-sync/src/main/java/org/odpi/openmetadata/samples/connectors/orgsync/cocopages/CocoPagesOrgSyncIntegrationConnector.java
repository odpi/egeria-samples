/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.samples.connectors.orgsync.cocopages;



import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.ConnectionProperties;
import org.odpi.openmetadata.frameworks.connectors.properties.EndpointProperties;
import org.odpi.openmetadata.integrationservices.organization.connector.OrganizationIntegratorConnector;
import org.odpi.openmetadata.integrationservices.organization.connector.OrganizationIntegratorContext;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorAuditCode;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorErrorCode;


/**
 * CocoPagesOrgSyncIntegrationConnector catalogues profiles and user information found in the cocopages application.
 */
public class CocoPagesOrgSyncIntegrationConnector extends OrganizationIntegratorConnector
{
    private static String applicationName = "cocopages";
    private String targetRootURL = null;

    private OrganizationIntegratorContext myContext = null;


    /**
     * Initialize the connector.
     *
     * @param connectorInstanceId - unique id for the connector instance - useful for messages etc
     * @param connectionProperties - POJO for the configuration used to create the connector.
     */
    @Override
    public void initialize(String connectorInstanceId, ConnectionProperties connectionProperties)
    {
        super.initialize(connectorInstanceId, connectionProperties);

        EndpointProperties endpoint = connectionProperties.getEndpoint();

        if (endpoint != null)
        {
            targetRootURL = endpoint.getAddress();
        }
    }


    /**
     * Indicates that the connector is completely configured and can begin processing.
     * This call can be used to register with non-blocking services.
     *
     * @throws ConnectorCheckedException there is a problem within the connector.
     */
    @Override
    public void start() throws ConnectorCheckedException
    {
        super.start();

        final String methodName = "start";

        myContext = super.getContext();

        /*
         * Record the configuration
         */
        if (auditLog != null)
        {
            auditLog.logMessage(methodName,
                                OrgSyncIntegrationConnectorAuditCode.CONNECTOR_CONFIGURATION.getMessageDefinition(connectorName,
                                                                                                                  applicationName,
                                                                                                                  targetRootURL));
        }
    }


    /**
     * Requests that the connector does a comparison of the metadata in the third party technology and open metadata repositories.
     * Refresh is called when the integration connector first starts and then at intervals defined in the connector's configuration
     * as well as any external REST API calls to explicitly refresh the connector.
     *
     * This method performs two sweeps.  It first retrieves the files in the directory and validates that are in the
     * catalog - adding or updating them if necessary.  The second sweep is to ensure that all of the assets catalogued
     * in this directory actually exist on the file system.
     *
     * @throws ConnectorCheckedException there is a problem with the connector.  It is not able to refresh the metadata.
     */
    @Override
    public void refresh() throws ConnectorCheckedException
    {
        final String methodName = "refresh";

        try
        {

        }
        catch (Exception error)
        {
            if (auditLog != null)
            {
                auditLog.logException(methodName,
                                      OrgSyncIntegrationConnectorAuditCode.UNABLE_TO_RETRIEVE_ELEMENTS.getMessageDefinition(connectorName,
                                                                                                                            error.getClass().getName(),
                                                                                                                            applicationName,
                                                                                                                            targetRootURL,
                                                                                                                            error.getMessage()),
                                      error);


            }

            throw new ConnectorCheckedException(OrgSyncIntegrationConnectorErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(connectorName,
                                                                                                                               error.getClass().getName(),
                                                                                                                               error.getMessage()),
                                                this.getClass().getName(),
                                                methodName,
                                                error);
        }
    }



    /**
     * Shutdown kafka monitoring
     *
     * @throws ConnectorCheckedException something failed in the super class
     */
    @Override
    public void disconnect() throws ConnectorCheckedException
    {
        final String methodName = "disconnect";


        if (auditLog != null)
        {
            auditLog.logMessage(methodName,
                                OrgSyncIntegrationConnectorAuditCode.CONNECTOR_STOPPING.getMessageDefinition(connectorName));
        }

        super.disconnect();
    }
}
