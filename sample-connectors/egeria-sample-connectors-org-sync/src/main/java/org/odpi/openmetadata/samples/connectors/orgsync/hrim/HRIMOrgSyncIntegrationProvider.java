/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.samples.connectors.orgsync.hrim;

import org.odpi.openmetadata.frameworks.connectors.ConnectorProviderBase;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.ConnectorType;

import java.util.ArrayList;
import java.util.List;


/**
 * HRIMOrgSyncIntegrationProvider is the connector provider for the org sync integration connector that maintains profiles and user identities relating to
 * the Coco Pharmaceuticals organization managed by the HR Information Manager (HRIM) application.
 */
public class HRIMOrgSyncIntegrationProvider extends ConnectorProviderBase
{
    private static final String connectorTypeGUID          = "534ecc81-60ae-4a17-a01e-8ddada38dc54";
    private static final String connectorTypeQualifiedName = "Egeria:IntegrationConnector:Organization:OrgSync:HRIM";
    private static final String connectorTypeDisplayName   = "Organization Synchronization Sample Integration Connector for HRIM";
    private static final String connectorTypeDescription   = "Connector that maintains profiles and user identities relating to\n" +
                                                                     " the Coco Pharmaceuticals organization managed by the HR Information Manager (HRIM) application.";


    /**
     * Constructor used to initialize the ConnectorProvider with the Java class name of the specific
     * store implementation.
     */
    public HRIMOrgSyncIntegrationProvider()
    {
        super();

        super.setConnectorClassName(HRIMOrgSyncIntegrationConnector.class.getName());

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
