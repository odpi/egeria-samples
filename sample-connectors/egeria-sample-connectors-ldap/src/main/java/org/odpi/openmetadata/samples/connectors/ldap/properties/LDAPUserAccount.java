/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * LDAPUserAccount describes a single user in LDAP.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              include = JsonTypeInfo.As.PROPERTY,
              property = "class")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = LDAPPersonUserAccount.class, name = "LDAPPersonUserAccount"),
                @JsonSubTypes.Type(value = LDAPSystemUserAccount.class, name = "LDAPSystemUserAccount"),
        })
public class LDAPUserAccount extends LDAPEntry
{
    private static final long          serialVersionUID = 1L;

    private String userId = null;
    private String userPassword = null;


    public LDAPUserAccount()
    {
    }


    public String getUserId()
    {
        return userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    public String getUserPassword()
    {
        return userPassword;
    }


    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }


    @Override
    public String toString()
    {
        return "LDAPUserAccount{" +
                       "userId='" + userId + '\'' +
                       ", userPassword='" + userPassword + '\'' +
                       ", distinguishedName='" + getDistinguishedName() + '\'' +
                       ", commonName='" + getCommonName() + '\'' +
                       ", description='" + getDescription() + '\'' +
                       '}';
    }
}
