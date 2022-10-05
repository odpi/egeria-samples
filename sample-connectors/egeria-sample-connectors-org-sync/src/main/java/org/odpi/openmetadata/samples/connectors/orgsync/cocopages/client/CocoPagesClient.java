/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.cocopages.client;

import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorErrorCode;
import org.odpi.openmetadata.samples.connectors.orgsync.rest.RESTClient;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;

import java.util.List;

public class CocoPagesClient
{
    private String     targetRootURL;
    private RESTClient restClient = null;


    public CocoPagesClient(String   connectorName,
                           String   applicationName,
                           String   targetRootURL,
                           AuditLog auditLog,
                           String   methodName) throws ConnectorCheckedException
    {
        this.targetRootURL = targetRootURL;

        try
        {
            if (targetRootURL != null)
            {
                restClient = new RESTClient(applicationName, targetRootURL, auditLog);
            }
        }
        catch (Exception error)
        {
            throw new ConnectorCheckedException(
                    OrgSyncIntegrationConnectorErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(connectorName,
                                                                                                   error.getClass().getName(),
                                                                                                   methodName,
                                                                                                   error.getMessage()),
                    this.getClass().getName(),
                    methodName,
                    error);
        }
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
