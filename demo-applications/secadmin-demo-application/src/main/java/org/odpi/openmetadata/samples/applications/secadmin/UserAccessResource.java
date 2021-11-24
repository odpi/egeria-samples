/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;


import org.odpi.openmetadata.samples.applications.secadmin.properties.UserAccess;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/secadmin/api/v1/users/access")
@Produces(MediaType.APPLICATION_JSON)
public class UserAccessResource
{
    private SecAdminDatabase  database;


    UserAccessResource(SecAdminDatabase database)
    {
        this.database = database;
    }

    @GET
    @Path("/{userId}")
    public UserAccess getUserAccess(@PathParam(value = "userId") String userId) throws SecAdminException
    {
        return database.getUserAccess(userId);
    }


    @POST
    @Path("/{userId}/groups/{distinguishedName}")
    public UserAccess getUserAccess(@PathParam(value = "userId")            String userId,
                                    @PathParam(value = "distinguishedName") String distinguishedName) throws SecAdminException
    {
        return database.addUserAccess(userId, distinguishedName);
    }


    @DELETE
    @Path("/{userId}/groups/{distinguishedName}")
    public UserAccess deleteUserAccess(@PathParam(value = "userId")            String userId,
                                       @PathParam(value = "distinguishedName") String distinguishedName) throws SecAdminException
    {
        return database.deleteUserAccess(userId, distinguishedName);
    }
}
