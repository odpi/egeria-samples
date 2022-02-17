/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.client;

import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.properties.ConnectionProperties;

/**
 * The LDAP client factory uses information in the connector's connection to create a client for the connector.
 */
public class LDAPClientFactory
{
    LDAPClientInterface getClient(AuditLog             auditLog,
                                  ConnectionProperties connectionProperties) throws LDAPException
    {
        final String methodName = "getClient";

        String ldapHost = "localHost";
        int    ldapPort = 10389;

        try
        {
            return new LDAPServerClient(ldapHost, ldapPort, auditLog);
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.usersRoot);
        }
    }
}
