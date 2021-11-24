/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages;


import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactEntry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cocopages/api/v1/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactEntryResource
{
    private CocoPagesDatabase  database;


    ContactEntryResource(CocoPagesDatabase database)
    {
        this.database = database;
    }


    @GET
    @Path("/{recordId}")
    public ContactEntry getContactEntry(@PathParam(value = "recordId") String recordId) throws CocoPagesException
    {
        return database.getContactEntry(recordId);
    }

    @GET
    public List<ContactEntry> getContactEntries() throws CocoPagesException
    {
        return database.getContactEntries();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addContact(ContactEntry applicant) throws CocoPagesException
    {
        database.addNewContact(applicant);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContactEntry(ContactEntry contactEntry) throws CocoPagesException
    {
        database.updateContactEntry(contactEntry);
    }


    @DELETE
    @Path("/{recordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteContactEntry(@PathParam(value = "recordId") String recordId) throws CocoPagesException
    {
        database.deleteContactEntry(recordId);
    }

}
