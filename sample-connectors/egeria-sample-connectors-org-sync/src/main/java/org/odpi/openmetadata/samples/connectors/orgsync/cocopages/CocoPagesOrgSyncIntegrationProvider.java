/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.samples.connectors.orgsync.cocopages;

import org.odpi.openmetadata.frameworks.connectors.ConnectorProviderBase;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ConnectorType;


/**
 * CocoPagesOrgSyncIntegrationProvider is the connector provider for the org sync integration connector that maintains profiles and user identities relating to
 * the Coco Pharmaceuticals organization.
 */
public class CocoPagesOrgSyncIntegrationProvider extends ConnectorProviderBase
{
    private static final String connectorTypeGUID          = "c3596c26-00ce-427c-9257-07a4b3f3b364";
    private static final String connectorTypeQualifiedName = "Egeria:IntegrationConnector:Organization:OrgSync:CocoPages";
    private static final String connectorTypeDisplayName   = "Organization Synchronization Sample Integration Connector for cocopages";
    private static final String connectorTypeDescription   = "Connector that maintains profiles and user identities relating to\n" +
                                                                     " the Coco Pharmaceuticals organization located in the cocopages application.";


    /**
     * Constructor used to initialize the ConnectorProvider with the Java class name of the specific
     * store implementation.
     */
    public CocoPagesOrgSyncIntegrationProvider()
    {
        super();

        super.setConnectorClassName(CocoPagesOrgSyncIntegrationConnector.class.getName());

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
