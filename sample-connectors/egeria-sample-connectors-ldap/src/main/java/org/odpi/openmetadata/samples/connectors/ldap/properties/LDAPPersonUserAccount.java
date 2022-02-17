/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class LDAPPersonUserAccount extends LDAPUserAccount
{
    private static final long          serialVersionUID = 1L;

    private String employeeNumber = null;
    private String employeeType   = null;
    private String displayName    = null;
    private String givenName      = null;
    private String surname        = null;
    private String email          = null;
    private String manager        = null;


    public LDAPPersonUserAccount()
    {
    }


    public String getEmployeeNumber()
    {
        return employeeNumber;
    }


    public void setEmployeeNumber(String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }


    public String getEmployeeType()
    {
        return employeeType;
    }


    public void setEmployeeType(String employeeType)
    {
        this.employeeType = employeeType;
    }


    public String getDisplayName()
    {
        return displayName;
    }


    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }


    public String getGivenName()
    {
        return givenName;
    }


    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }


    public String getSurname()
    {
        return surname;
    }


    public void setSurname(String surname)
    {
        this.surname = surname;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getManager()
    {
        return manager;
    }


    public void setManager(String manager)
    {
        this.manager = manager;
    }


    @Override
    public String toString()
    {
        return "LDAPPersonUserAccount{" +
                       "employeeNumber='" + employeeNumber + '\'' +
                       ", employeeType='" + employeeType + '\'' +
                       ", displayName='" + displayName + '\'' +
                       ", givenName='" + givenName + '\'' +
                       ", surname='" + surname + '\'' +
                       ", email='" + email + '\'' +
                       ", manager='" + manager + '\'' +
                       ", userId='" + getUserId() + '\'' +
                       ", userPassword='" + getUserPassword() + '\'' +
                       ", distinguishedName='" + getDistinguishedName() + '\'' +
                       ", commonName='" + getCommonName() + '\'' +
                       ", description='" + getDescription() + '\'' +
                       '}';
    }
}
