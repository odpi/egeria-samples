/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContactEmail
{
    private String recordId = null;
    private String emailType = null;
    private String emailAddress = null;


    public ContactEmail()
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


    public String getEmailType()
    {
        return emailType;
    }


    public void setEmailType(String emailType)
    {
        this.emailType = emailType;
    }


    public String getEmailAddress()
    {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }


    @Override
    public String toString()
    {
        return "ContactEmail{" +
                       "recordId='" + recordId + '\'' +
                       ", emailType='" + emailType + '\'' +
                       ", emailAddress='" + emailAddress + '\'' +
                       '}';
    }
}
