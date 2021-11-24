/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class GroupMembership
{
    private String distinguishedName = null;
    private Date   dateAdded = null;
    private Date   dateRemoved = null;
    private String userId = null;


    public GroupMembership()
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


    public Date getDateAdded()
    {
        return dateAdded;
    }


    public void setDateAdded(Date dateAdded)
    {
        this.dateAdded = dateAdded;
    }


    public Date getDateRemoved()
    {
        return dateRemoved;
    }


    public void setDateRemoved(Date dateRemoved)
    {
        this.dateRemoved = dateRemoved;
    }


    public String getUserId()
    {
        return userId;
    }


    public void setUserId(String userId)
    {
        this.userId = userId;
    }


    @Override
    public String toString()
    {
        return "GroupMembership{" +
                       "distinguishedName='" + distinguishedName + '\'' +
                       ", dateAdded=" + dateAdded +
                       ", dateRemoved=" + dateRemoved +
                       ", userId='" + userId + '\'' +
                       '}';
    }
}
