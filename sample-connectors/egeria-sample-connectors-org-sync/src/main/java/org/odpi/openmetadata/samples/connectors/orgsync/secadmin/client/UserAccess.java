/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserAccess
{
    private User                  user         = null;
    private List<GroupMembership> groupHistory = null;
    private List<Group>           activeGroups = null;


    public UserAccess()
    {
    }


    public User getUser()
    {
        return user;
    }


    public void setUser(User user)
    {
        this.user = user;
    }


    public List<GroupMembership> getGroupHistory()
    {
        return groupHistory;
    }


    public void setGroupHistory(List<GroupMembership> groupHistory)
    {
        this.groupHistory = groupHistory;
    }


    public List<Group> getActiveGroups()
    {
        return activeGroups;
    }


    public void setActiveGroups(List<Group> activeGroups)
    {
        this.activeGroups = activeGroups;
    }


    @Override
    public String toString()
    {
        return "UserAccess{" +
                       "user=" + user +
                       ", groupHistory=" + groupHistory +
                       ", activeGroups=" + activeGroups +
                       '}';
    }
}
