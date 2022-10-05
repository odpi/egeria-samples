/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContactPhone
{
    private String recordId = null;
    private String contactType = null;
    private String phoneNumber = null;


    public ContactPhone()
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


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString()
    {
        return "ContactPhone{" +
                       "recordId='" + recordId + '\'' +
                       ", contactType='" + contactType + '\'' +
                       ", phoneNumber='" + phoneNumber + '\'' +
                       '}';
    }
}
