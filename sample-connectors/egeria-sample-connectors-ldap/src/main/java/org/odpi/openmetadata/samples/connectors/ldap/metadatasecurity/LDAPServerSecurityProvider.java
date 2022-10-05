/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.metadatasecurity;

import org.odpi.openmetadata.metadatasecurity.connectors.OpenMetadataServerSecurityProvider;

/**
 * LDAPServerSecurityProvider is the connector provider to the
 * sample server security connector for the Coco Pharmaceuticals scenarios.
 */
public class LDAPServerSecurityProvider extends OpenMetadataServerSecurityProvider
{
    /**
     * Constructor used to initialize the ConnectorProviderBase with the Java class name of the specific
     * OMRS Connector implementation.
     */
    public LDAPServerSecurityProvider()
    {
        super();

        Class<?> connectorClass = org.odpi.openmetadata.samples.connectors.ldap.metadatasecurity.LDAPServerSecurityConnector.class;

        super.setConnectorClassName(connectorClass.getName());
    }
}
