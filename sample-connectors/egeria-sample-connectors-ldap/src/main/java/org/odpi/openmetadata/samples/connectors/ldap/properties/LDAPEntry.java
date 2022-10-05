/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.odpi.openmetadata.accessservices.securitymanager.properties.ActorProfileProperties;
import org.odpi.openmetadata.accessservices.securitymanager.properties.UserIdentityProperties;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * LDAPEntry describes the common properties found in all entries in LDAP.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              include = JsonTypeInfo.As.PROPERTY,
              property = "class")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = LDAPGroup.class, name = "LDAPGroup"),
                @JsonSubTypes.Type(value = LDAPUserAccount.class, name = "LDAPUserAccount"),
        })
public class LDAPEntry implements Serializable
{
    private static final long          serialVersionUID = 1L;

    private String distinguishedName = null;
    private String commonName        = null;
    private String description       = null;

    public LDAPEntry()
    {
    }


    public String getDistinguishedName()
    {
        return distinguishedName;
    }


    public void setDistinguishedName(String distinguishedName)
    {
        this.distinguishedName = distinguishedName;
    }


    public String getCommonName()
    {
        return commonName;
    }


    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    @Override
    public String toString()
    {
        return "LDAPEntry{" +
                       "distinguishedName='" + distinguishedName + '\'' +
                       ", commonName='" + commonName + '\'' +
                       ", description='" + description + '\'' +
                       '}';
    }
}
