/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Configuration;
import org.odpi.openmetadata.samples.applications.secadmin.properties.Group;
import org.odpi.openmetadata.samples.applications.secadmin.properties.GroupMembership;
import org.odpi.openmetadata.samples.applications.secadmin.properties.User;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * SecAdminConfig defines the structure of the yaml file that initializes the
 * SecAccess application.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SecAdminConfig extends Configuration
{
    private List<User>            initialUsers = null;
    private List<Group>           initialGroups = null;
    private List<GroupMembership> initialMembership = null;


    public SecAdminConfig()
    {
    }


    public List<User> getInitialUsers()
    {
        return initialUsers;
    }


    public void setInitialUsers(List<User> initialUsers)
    {
        this.initialUsers = initialUsers;
    }


    public List<Group> getInitialGroups()
    {
        return initialGroups;
    }


    public void setInitialGroups(List<Group> initialGroups)
    {
        this.initialGroups = initialGroups;
    }


    public List<GroupMembership> getInitialMembership()
    {
        return initialMembership;
    }


    public void setInitialMembership(List<GroupMembership> initialMembership)
    {
        this.initialMembership = initialMembership;
    }


    @Override
    public String toString()
    {
        return "SecAdminConfig{" +
                       "initialUsers=" + initialUsers +
                       ", initialGroups=" + initialGroups +
                       ", initialMembership=" + initialMembership +
                       '}';
    }
}
