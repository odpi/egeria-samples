/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;


import org.odpi.openmetadata.samples.applications.secadmin.properties.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/secadmin/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private SecAdminDatabase  database;


    UserResource(SecAdminDatabase database)
    {
        this.database = database;
    }


    @GET
    @Path("/{userId}")
    public User getUser(@PathParam(value = "userId") String userId) throws SecAdminException
    {
        return database.getUser(userId);
    }

    @GET
    public List<User> getUsers()
    {
        return database.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User group) throws SecAdminException
    {
        database.addUser(group);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User group) throws SecAdminException
    {
        database.updateUser(group);
    }

    @DELETE
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam(value = "userId") String userId) throws SecAdminException
    {
        database.deleteUser(userId);
    }

}
