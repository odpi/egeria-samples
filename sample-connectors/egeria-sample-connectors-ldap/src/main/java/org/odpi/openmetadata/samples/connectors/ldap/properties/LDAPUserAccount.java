/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

/**
 * LDAPUserAccount describes a single user in LDAP.
 */
public class LDAPUserAccount extends LDAPEntry
{
    private String userId = null;
    private String userPassword = null;
}
