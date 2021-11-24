/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.cocopages.client;

import java.util.List;

public class CocoPagesClient
{
    private String targetRootURL;


    public CocoPagesClient(String targetRootURL)
    {
        this.targetRootURL = targetRootURL;
    }


    public ContactEntry getContactEntry(String recordId) throws CocoPagesException
    {
        return null;
    }

    public List<ContactEntry> getContactEntries() throws CocoPagesException
    {
        return null;
    }

    public void addContact(ContactEntry applicant) throws CocoPagesException
    {
    }


    public void updateContactEntry(ContactEntry contactEntry) throws CocoPagesException
    {
    }


    public void deleteContactEntry(String recordId) throws CocoPagesException
    {
    }


    public WorkLocation getWorkLocation(String workLocationId) throws CocoPagesException
    {
        return null;
    }

    public List<WorkLocation> getWorkLocations()
    {
        return null;
    }


    public void addWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {

    }


    public void updateWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {

    }


    public void deleteWorkLocation(String workLocationId) throws CocoPagesException
    {

    }
}
