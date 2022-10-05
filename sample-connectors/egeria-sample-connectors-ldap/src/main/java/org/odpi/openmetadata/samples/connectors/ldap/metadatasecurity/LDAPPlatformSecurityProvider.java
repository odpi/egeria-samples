/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.metadatasecurity.samples;

import org.odpi.openmetadata.metadatasecurity.connectors.OpenMetadataPlatformSecurityProvider;
import org.odpi.openmetadata.samples.connectors.ldap.metadatasecurity.LDAPPlatformSecurityConnector;

/**
 * LDAPPlatformSecurityProvider is the connector provider to the
 * sample platform security connector for the Coco Pharmaceuticals scenarios.
 */
public class LDAPPlatformSecurityProvider extends OpenMetadataPlatformSecurityProvider
{
    /**
     * Constructor used to initialize the ConnectorProviderBase with the Java class name of the specific
     * OMRS Connector implementation.
     */
    public LDAPPlatformSecurityProvider()
    {
        super();

        Class<?>   connectorClass = LDAPPlatformSecurityConnector.class;

        super.setConnectorClassName(connectorClass.getName());
    }
}
