/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.cocopages.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContactEntry
{
    private Contact            contact       = null;
    private List<ContactEmail> emailAccounts = null;
    private List<ContactPhone> phoneNumbers  = null;
    private WorkLocation       workLocation  = null;


    public ContactEntry()
    {
    }


    public Contact getContact()
    {
        return contact;
    }


    public void setContact(Contact contact)
    {
        this.contact = contact;
    }


    public List<ContactEmail> getEmailAccounts()
    {
        return emailAccounts;
    }


    public void setEmailAccounts(List<ContactEmail> emailAccounts)
    {
        this.emailAccounts = emailAccounts;
    }


    public List<ContactPhone> getPhoneNumbers()
    {
        return phoneNumbers;
    }


    public void setPhoneNumbers(List<ContactPhone> phoneNumbers)
    {
        this.phoneNumbers = phoneNumbers;
    }


    public WorkLocation getWorkLocation()
    {
        return workLocation;
    }


    public void setWorkLocation(WorkLocation workLocation)
    {
        this.workLocation = workLocation;
    }


    @Override
    public String toString()
    {
        return "ContactEntry{" +
                       "contact=" + contact +
                       ", emailAccounts=" + emailAccounts +
                       ", phoneNumbers=" + phoneNumbers +
                       ", workLocation=" + workLocation +
                       '}';
    }
}
