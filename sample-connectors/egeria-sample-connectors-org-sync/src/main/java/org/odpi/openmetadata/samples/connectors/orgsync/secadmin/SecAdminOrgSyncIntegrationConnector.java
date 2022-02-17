/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.samples.connectors.orgsync.secadmin;

import org.odpi.openmetadata.accessservices.communityprofile.metadataelements.ActorProfileElement;
import org.odpi.openmetadata.accessservices.communityprofile.metadataelements.SecurityGroupElement;
import org.odpi.openmetadata.accessservices.communityprofile.metadataelements.UserIdentityElement;
import org.odpi.openmetadata.accessservices.communityprofile.properties.SecurityGroupProperties;
import org.odpi.openmetadata.accessservices.communityprofile.properties.UserIdentityProperties;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.ConnectionProperties;
import org.odpi.openmetadata.frameworks.connectors.properties.EndpointProperties;
import org.odpi.openmetadata.integrationservices.organization.connector.OrganizationIntegratorConnector;
import org.odpi.openmetadata.integrationservices.organization.connector.OrganizationIntegratorContext;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorAuditCode;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorErrorCode;
import org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client.Group;
import org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client.SecAdminClient;
import org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client.SecAdminException;
import org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client.User;

import java.util.List;


/**
 * SecAdminOrgSyncIntegrationConnector catalogues profiles and user information.
 */
public class SecAdminOrgSyncIntegrationConnector extends OrganizationIntegratorConnector
{
    private static String applicationName = "Security Admin (SecAdmin)";
    private String targetRootURL = null;

    private OrganizationIntegratorContext myContext = null;
    private SecAdminClient                client    = null;


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

        if (targetRootURL == null)
        {
            throw new SecAdminException(OrgSyncIntegrationConnectorErrorCode.NO_URL.getMessageDefinition(connectorName,
                                                                                                         applicationName),
                                        this.getClass().getName(),
                                        methodName);
        }

        client = new SecAdminClient(targetRootURL, connectorName);

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
            if (client != null)
            {
                List<Group> secAdminGroups = client.getGroups();

                if (secAdminGroups != null)
                {
                    for (Group secAdminGroup : secAdminGroups)
                    {
                        if ((secAdminGroup != null) && (secAdminGroup.getDistinguishedName() != null))
                        {
                            String expectedDocId = applicationName + ": " + secAdminGroup.getDistinguishedName();

                            List<SecurityGroupElement> openMetadataGroups = myContext.getSecurityGroupsForDistinguishedName(secAdminGroup.getDistinguishedName(), 0 , 0);

                            if ((openMetadataGroups == null) || (openMetadataGroups.isEmpty()))
                            {
                                SecurityGroupProperties securityGroupProperties = new SecurityGroupProperties();

                                securityGroupProperties.setDocumentIdentifier(expectedDocId);
                                securityGroupProperties.setDistinguishedName(secAdminGroup.getDistinguishedName());
                                securityGroupProperties.setDomainIdentifier(3);
                                securityGroupProperties.setTitle(secAdminGroup.getCommonName());
                                securityGroupProperties.setDescription(secAdminGroup.getDescription());

                                myContext.createSecurityGroup(securityGroupProperties);
                            }
                            else if (openMetadataGroups.size() == 1)
                            {
                                /*
                                 * SecAdmin is the authoritative source and so its values override any changed from elsewhere.
                                 */
                                SecurityGroupElement existingElement = openMetadataGroups.get(0);
                                SecurityGroupProperties existingProperties = existingElement.getProperties();

                                if ((! secAdminGroup.getDistinguishedName().equals(existingProperties.getDistinguishedName())) ||
                                    (! expectedDocId.equals(existingProperties.getDocumentIdentifier())))
                                {
                                    SecurityGroupProperties securityGroupProperties = new SecurityGroupProperties();

                                    securityGroupProperties.setDocumentIdentifier(expectedDocId);
                                    securityGroupProperties.setDistinguishedName(secAdminGroup.getDistinguishedName());
                                    securityGroupProperties.setDomainIdentifier(3);
                                    securityGroupProperties.setTitle(secAdminGroup.getCommonName());
                                    securityGroupProperties.setDescription(secAdminGroup.getDescription());

                                    myContext.updateSecurityGroup(existingElement.getElementHeader().getGUID(), true, securityGroupProperties);
                                }
                            }
                        }
                    }
                }


                List<User> secAdminUsers = client.getUsers();

                if (secAdminUsers != null)
                {
                    for (User user: secAdminUsers)
                    {
                        if ((user != null) && (user.getUserId() != null))
                        {
                            List<UserIdentityElement> openMetadataUsers    = myContext.getUserIdentitiesByName(user.getUserId(), 0, 0);
                            String                    openMetadataUserGUID = null;

                            if ((openMetadataUsers == null) || (openMetadataUsers.isEmpty()))
                            {
                                UserIdentityProperties newIdentity = new UserIdentityProperties();

                                newIdentity.setQualifiedName(user.getUserId());
                                newIdentity.setDistinguishedName("cn=" + user.getUserId() + ",ou=people,o=cocoPharma");

                                openMetadataUserGUID = myContext.createUserIdentity(newIdentity);
                            }
                            else if (openMetadataUsers.size() == 1)
                            {
                                openMetadataUserGUID = openMetadataUsers.get(0).getElementHeader().getGUID();
                            }
                            else
                            {
                                // error handling
                            }

                            if (openMetadataUserGUID != null)
                            {
                                ActorProfileElement profile = myContext.getActorProfileByUserId(user.getUserId());


                            }
                        }
                    }
                }


            }
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
                                                                                                                               methodName,
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
