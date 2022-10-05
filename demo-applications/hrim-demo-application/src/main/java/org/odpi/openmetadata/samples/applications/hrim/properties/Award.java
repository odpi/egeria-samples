/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Award
{
    private Date   awardDate = null;
    private String awardType = null;
    private String awardOrganization = null;
    private String recipientPNUM = null;

    public Award()
    {
    }


    public Date getAwardDate()
    {
        return awardDate;
    }


    public void setAwardDate(Date awardDate)
    {
        this.awardDate = awardDate;
    }


    public String getAwardType()
    {
        return awardType;
    }


    public void setAwardType(String awardType)
    {
        this.awardType = awardType;
    }


    public String getAwardOrganization()
    {
        return awardOrganization;
    }


    public void setAwardOrganization(String awardOrganization)
    {
        this.awardOrganization = awardOrganization;
    }


    public String getRecipientPNUM()
    {
        return recipientPNUM;
    }


    public void setRecipientPNUM(String recipientPNUM)
    {
        this.recipientPNUM = recipientPNUM;
    }


    @Override
    public String toString()
    {
        return "Award{" +
                       "awardDate=" + awardDate +
                       ", awardType='" + awardType + '\'' +
                       ", awardOrganization='" + awardOrganization + '\'' +
                       ", recipientPNUM='" + recipientPNUM + '\'' +
                       '}';
    }
}
