/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

import java.io.Serializable;

/**
 * LDAPEntry describes the common properties found in all entries in LDAP.
 */
public class LDAPEntry implements Serializable
{
    private String distinguishedName = null;
    private String commonName = null;
    private String description = null;

}
