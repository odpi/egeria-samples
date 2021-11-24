/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.cocopages.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Contact
{
    private String recordId     = null;
    private String contactType  = null;
    private String firstName    = null;
    private String lastName     = null;
    private String company      = null;
    private String jobTitle     = null;
    private String userId       = null;
    private int    workLocation = 0;


    public Contact()
    {
    }


    public String getRecordId()
    {
        return recordId;
    }


    public void setRecordId(String recordId)
    {
        this.recordId = recordId;
    }


    public String getContactType()
    {
        return contactType;
    }


    public void setContactType(String contactType)
    {
        this.contactType = contactType;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public String getCompany()
    {
        return company;
    }


    public void setCompany(String company)
    {
        this.company = company;
    }


    public String getJobTitle()
    {
        return jobTitle;
    }


    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }


    public String getUserId()
    {
        return userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    public int getWorkLocation()
    {
        return workLocation;
    }


    public void setWorkLocation(int workLocation)
    {
        this.workLocation = workLocation;
    }


    @Override
    public String toString()
    {
        return "Contact{" +
                       "recordId='" + recordId + '\'' +
                       ", contactType='" + contactType + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", company='" + company + '\'' +
                       ", jobTitle='" + jobTitle + '\'' +
                       ", userId='" + userId + '\'' +
                       ", workLocation=" + workLocation +
                       '}';
    }
}
