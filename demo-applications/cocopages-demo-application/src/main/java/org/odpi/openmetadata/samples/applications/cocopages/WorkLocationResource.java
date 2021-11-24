/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages;


import org.odpi.openmetadata.samples.applications.cocopages.properties.WorkLocation;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cocopages/api/v1/work-locations")
@Produces(MediaType.APPLICATION_JSON)
public class WorkLocationResource
{
    private CocoPagesDatabase  database;


    WorkLocationResource(CocoPagesDatabase database)
    {
        this.database = database;
    }

    @GET
    @Path("/{workLocationId}")
    public WorkLocation getWorkLocation(@PathParam(value = "workLocationId") int workLocationId) throws CocoPagesException
    {
        return database.getWorkLocation(workLocationId);
    }

    @GET
    public List<WorkLocation> getWorkLocations()
    {
        return database.getWorkLocations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {
        database.addWorkLocation(workLocation);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {
        database.updateWorkLocation(workLocation);
    }

    @DELETE
    @Path("/{workLocationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteWorkLocation(@PathParam(value = "workLocationId") int workLocationId) throws CocoPagesException
    {
        database.deleteWorkLocation(workLocationId);
    }
}
