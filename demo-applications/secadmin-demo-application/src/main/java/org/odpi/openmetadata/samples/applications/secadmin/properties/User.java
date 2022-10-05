/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class User
{
    private String userId = null;
    private String employeeNumber = null;
    private String employeeType = null;
    private String firstName = null;
    private String surname = null;
    private String description = null;
    private String systemName = null;


    public User()
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


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getSurname()
    {
        return surname;
    }


    public void setSurname(String surname)
    {
        this.surname = surname;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getSystemName()
    {
        return systemName;
    }


    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }


    @Override
    public String toString()
    {
        return "User{" +
                       "userId='" + userId + '\'' +
                       ", employeeNumber='" + employeeNumber + '\'' +
                       ", employeeType='" + employeeType + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", surname='" + surname + '\'' +
                       ", description='" + description + '\'' +
                       ", systemName='" + systemName + '\'' +
                       '}';
    }
}
