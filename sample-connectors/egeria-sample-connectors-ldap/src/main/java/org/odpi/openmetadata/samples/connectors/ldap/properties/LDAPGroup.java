/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * LDAPGroup describes the properties of an LDAP group
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class LDAPGroup extends LDAPEntry
{
    private static final long          serialVersionUID = 1L;

    private String organizationalUnit = null;


    public LDAPGroup()
    {
    }


    public String getOrganizationalUnit()
    {
        return organizationalUnit;
    }


    public void setOrganizationalUnit(String organizationalUnit)
    {
        this.organizationalUnit = organizationalUnit;
    }


    @Override
    public String toString()
    {
        return "LDAPGroup{" +
                       "organizationalUnit='" + organizationalUnit + '\'' +
                       ", distinguishedName='" + getDistinguishedName() + '\'' +
                       ", commonName='" + getCommonName() + '\'' +
                       ", description='" + getDescription() + '\'' +
                       '}';
    }
}
