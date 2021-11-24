/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.samples.connectors.orgsync.secadmin;

import org.odpi.openmetadata.frameworks.connectors.ConnectorProviderBase;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ConnectorType;


/**
 * SecAdminOrgSyncIntegrationProvider is the connector provider for the org sync integration connector that maintains profiles and user identities relating to
 * the Coco Pharmaceuticals organization found in the Security Administration (SecAdmin) application.
 */
public class SecAdminOrgSyncIntegrationProvider extends ConnectorProviderBase
{
    private static final String connectorTypeGUID          = "1585fa80-b911-408c-a964-1583377d4334";
    private static final String connectorTypeQualifiedName = "Egeria:IntegrationConnector:Organization:OrgSync:SecAdmin";
    private static final String connectorTypeDisplayName   = "Organization Synchronization Sample Integration Connector for SecAdmin";
    private static final String connectorTypeDescription   = "Connector that maintains profiles and user identities relating to\n" +
                                                                     " the Coco Pharmaceuticals organization found in the Security Administration (SecAdmin) application.";


    /**
     * Constructor used to initialize the ConnectorProvider with the Java class name of the specific
     * store implementation.
     */
    public SecAdminOrgSyncIntegrationProvider()
    {
        super();

        super.setConnectorClassName(SecAdminOrgSyncIntegrationConnector.class.getName());

        ConnectorType connectorType = new ConnectorType();
        connectorType.setType(ConnectorType.getConnectorTypeType());
        connectorType.setGUID(connectorTypeGUID);
        connectorType.setQualifiedName(connectorTypeQualifiedName);
        connectorType.setDisplayName(connectorTypeDisplayName);
        connectorType.setDescription(connectorTypeDescription);
        connectorType.setConnectorProviderClassName(this.getClass().getName());

        super.connectorTypeBean = connectorType;
    }
}
