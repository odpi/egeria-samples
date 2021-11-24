/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;


import org.odpi.openmetadata.samples.applications.secadmin.properties.Group;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/secadmin/api/v1/groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource
{
    private SecAdminDatabase  database;


    GroupResource(SecAdminDatabase database)
    {
        this.database = database;
    }

    @GET
    @Path("/{distinguishedName}")
    public Group getGroup(@PathParam(value = "distinguishedName") String distinguishedName) throws SecAdminException
    {
        return database.getGroup(distinguishedName);
    }

    @GET
    public List<Group> getGroups()
    {
        return database.getGroups();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addGroup(Group group) throws SecAdminException
    {
        database.addGroup(group);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateGroup(Group group) throws SecAdminException
    {
        database.updateGroup(group);
    }

    @DELETE
    @Path("/{distinguishedName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteGroup(@PathParam(value = "distinguishedName") String distinguishedName) throws SecAdminException
    {
        database.deleteGroup(distinguishedName);
    }
}
