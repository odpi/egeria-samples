/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;


import org.odpi.openmetadata.samples.applications.secadmin.properties.Group;
import org.odpi.openmetadata.samples.applications.secadmin.properties.GroupMembership;
import org.odpi.openmetadata.samples.applications.secadmin.properties.User;
import org.odpi.openmetadata.samples.applications.secadmin.properties.UserAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SecAdminDatabase
{
    private Map<String, Group>                 groupMap  = new HashMap<>();
    private Map<String, User>                  userMap   = new HashMap<>();
    private Map<String, List<GroupMembership>> memberMap = new HashMap<>();



    SecAdminDatabase(List<Group>           initialGroups,
                     List<User>            initialUsers,
                     List<GroupMembership> initialMembership)
    {
        if (initialGroups != null)
        {
            for (Group group : initialGroups)
            {
                if ((group != null) && (group.getDistinguishedName() != null))
                {
                    groupMap.put(group.getDistinguishedName(), group);
                }
            }
        }

        if (initialUsers != null)
        {
            for (User user : initialUsers)
            {
                if ((user != null) && (user.getUserId() != null))
                {
                    userMap.put(user.getUserId(), user);
                }
            }
        }

        if (initialMembership != null)
        {
            for (GroupMembership membership : initialMembership)
            {
                if ((membership != null) && (membership.getUserId() != null) && (membership.getDistinguishedName() != null))
                {
                    String distinguishedName = membership.getDistinguishedName();

                    if (membership.getDateAdded() == null)
                    {
                        membership.setDateAdded(new Date());
                    }

                    List<GroupMembership> members = memberMap.get(distinguishedName);

                    if (members == null)
                    {
                        members = new ArrayList<>();
                    }

                    members.add(membership);

                    memberMap.put(distinguishedName, members);
                }
            }
        }
    }


    Group getGroup (String distinguishedName) throws SecAdminException
    {
        if (distinguishedName == null)
        {
            throw new SecAdminException("No distinguishedName");
        }

        return groupMap.get(distinguishedName);
    }

    List<Group> getGroups()
    {
        return new ArrayList<>(groupMap.values());
    }

    void addGroup(Group group) throws SecAdminException
    {
        String distinguishedName = group.getDistinguishedName();

        if (distinguishedName == null)
        {
            throw new SecAdminException("No distinguishedName");
        }

        Group existingGroup = groupMap.put(distinguishedName, group);

        if (existingGroup != null)
        {
            groupMap.put(distinguishedName, existingGroup);

            throw new SecAdminException("distinguishedName in use");
        }
    }

    void updateGroup(Group group) throws SecAdminException
    {
        String distinguishedName = group.getDistinguishedName();

        if (distinguishedName == null)
        {
            throw new SecAdminException("No distinguishedName");
        }

        Group existingGroup = groupMap.put(distinguishedName, group);

        if (existingGroup == null)
        {
            groupMap.remove(distinguishedName);

            throw new SecAdminException("distinguishedName not known");
        }
    }

    void deleteGroup(String distinguishedName) throws SecAdminException
    {
        if (distinguishedName == null)
        {
            throw new SecAdminException("No distinguishedName");
        }

        Group existingGroup = groupMap.get(distinguishedName);

        if (existingGroup != null)
        {
            groupMap.remove(distinguishedName);
        }
        else
        {
            throw new SecAdminException("distinguishedName not known");
        }
    }


    User getUser(String userId) throws SecAdminException
    {
        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        return userMap.get(userId);
    }

    List<User> getUsers()
    {
        return new ArrayList<>(userMap.values());
    }

    void addUser(User user) throws SecAdminException
    {
        String userId = user.getUserId();

        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        User existingUser = userMap.put(userId, user);

        if (existingUser != null)
        {
            userMap.put(userId, existingUser);

            throw new SecAdminException("userId in use");
        }
    }

    void updateUser(User user) throws SecAdminException
    {
        String userId = user.getUserId();

        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        User existingUser = userMap.put(userId, user);

        if (existingUser == null)
        {
            groupMap.remove(userId);

            throw new SecAdminException("userId not known");
        }
    }

    void deleteUser(String userId) throws SecAdminException
    {
        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        User existingUserAccess = userMap.get(userId);

        if (existingUserAccess == null)
        {
            throw new SecAdminException("userId not recognized");
        }

        UserAccess userAccess = getUserAccess(userId);

        if ((userAccess != null) && (userAccess.getActiveGroups() != null))
        {
            for (Group group : userAccess.getActiveGroups())
            {
                if ((group != null) && (group.getDistinguishedName() != null))
                {
                    List<GroupMembership> memberships = memberMap.get(group.getDistinguishedName());

                    if (memberships != null)
                    {
                        for (GroupMembership membership : memberships)
                        {
                            if ((membership != null) && (userId.equals(membership.getUserId())))
                            {
                                membership.setDateRemoved(new Date());
                            }
                        }
                    }
                }
            }
        }

        userMap.remove(userId);
    }


    UserAccess getUserAccess(String userId) throws SecAdminException
    {
        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        UserAccess userAccess = new UserAccess();

        userAccess.setUser(userMap.get(userId));

        List<GroupMembership> membershipHistory = new ArrayList<>();
        List<Group>           activeGroups = new ArrayList<>();

        for (String distinguishedName : groupMap.keySet())
        {
            List<GroupMembership> memberships = memberMap.get(distinguishedName);

            if (memberships != null)
            {
                for (GroupMembership membership : memberships)
                {
                    if (membership != null)
                    {
                        if (userId.equals(membership.getUserId()))
                        {
                            membershipHistory.add(membership);

                            if (membership.getDateRemoved() == null)
                            {
                                activeGroups.add(groupMap.get(distinguishedName));
                            }
                        }
                    }
                }
            }
        }

        if (! membershipHistory.isEmpty())
        {
            userAccess.setGroupHistory(membershipHistory);
        }

        if (! activeGroups.isEmpty())
        {
            userAccess.setActiveGroups(activeGroups);
        }

        return userAccess;
    }


    UserAccess addUserAccess(String userId,
                             String groupDistinguishedName) throws SecAdminException
    {
        /*
         * Ensure existing membership is closed.
         */
        deleteUserAccess(userId, groupDistinguishedName);

        List<GroupMembership> memberships = memberMap.get(groupDistinguishedName);

        if (memberships == null)
        {
            memberships = new ArrayList<>();
        }

        GroupMembership groupMembership = new GroupMembership();

        groupMembership.setDistinguishedName(groupDistinguishedName);
        groupMembership.setUserId(userId);
        groupMembership.setDateAdded(new Date());

        memberships.add(groupMembership);

        memberMap.put(groupDistinguishedName, memberships);

        return getUserAccess(userId);
    }

    UserAccess deleteUserAccess(String userId,
                                String groupDistinguishedName) throws SecAdminException
    {
        if (userId == null)
        {
            throw new SecAdminException("No userId");
        }

        User existingUserAccess = userMap.get(userId);

        if (existingUserAccess == null)
        {
            throw new SecAdminException("userId not recognized");
        }

        List<GroupMembership> memberships = memberMap.get(groupDistinguishedName);

        if (memberships != null)
        {
            for (GroupMembership membership : memberships)
            {
                if ((membership != null) && (userId.equals(membership.getUserId())))
                {
                    membership.setDateRemoved(new Date());
                }
            }
        }

        return getUserAccess(userId);
    }
}
