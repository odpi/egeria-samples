/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages;

import org.odpi.openmetadata.samples.applications.cocopages.properties.Contact;
import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactEmail;
import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactEntry;
import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactPhone;
import org.odpi.openmetadata.samples.applications.cocopages.properties.WorkLocation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CocoPagesDatabase
{
    private Map<Integer, WorkLocation> workLocationMap = new HashMap<>();

    private Map<String, Contact>            personMap = new HashMap<>();
    private Map<String, List<ContactEmail>> emailMap  = new HashMap<>();
    private Map<String, List<ContactPhone>> phoneMap  = new HashMap<>();


    public CocoPagesDatabase(List<Contact>      initialContacts,
                             List<ContactEmail> initialEmailAccounts,
                             List<ContactPhone> initialPhoneNumbers,
                             List<WorkLocation> initialWorkLocations)
    {
        if (initialContacts != null)
        {
            for (Contact contact : initialContacts)
            {
                if ((contact != null) && (contact.getRecordId() != null))
                {
                    personMap.put(contact.getRecordId(), contact);
                }
            }
        }

        if (initialEmailAccounts != null)
        {
            for (ContactEmail contactEmail : initialEmailAccounts)
            {
                if (contactEmail != null)
                {
                    String recordId = contactEmail.getRecordId();

                    if (recordId != null)
                    {
                        List<ContactEmail> existingEmailAccounts = emailMap.get(recordId);

                        if (existingEmailAccounts == null)
                        {
                            existingEmailAccounts = new ArrayList<>();
                        }

                        existingEmailAccounts.add(contactEmail);
                        emailMap.put(recordId, existingEmailAccounts);
                    }
                }
            }
        }

        if (initialPhoneNumbers != null)
        {
            for (ContactPhone contactPhone : initialPhoneNumbers)
            {
                if (contactPhone != null)
                {
                    String recordId = contactPhone.getRecordId();

                    if (recordId != null)
                    {
                        List<ContactPhone> existingPhoneNumbers = phoneMap.get(recordId);

                        if (existingPhoneNumbers == null)
                        {
                            existingPhoneNumbers = new ArrayList<>();
                        }

                        existingPhoneNumbers.add(contactPhone);
                        phoneMap.put(recordId, existingPhoneNumbers);
                    }
                }
            }
        }

        if (initialWorkLocations != null)
        {
            for (WorkLocation workLocation : initialWorkLocations)
            {
                if ((workLocation != null) && (workLocation.getWlId() != 0))
                {
                    workLocationMap.put(workLocation.getWlId(), workLocation);
                }
            }
        }
    }
    

    WorkLocation getWorkLocation (int wlId) throws CocoPagesException
    {
        if (wlId == 0)
        {
            throw new CocoPagesException("No wlId");
        }

        return workLocationMap.get(wlId);
    }

    List<WorkLocation> getWorkLocations()
    {
        return new ArrayList<>(workLocationMap.values());
    }

    void addWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {
        int wlId = workLocation.getWlId();

        if (wlId == 0)
        {
            throw new CocoPagesException("No wlId");
        }

        WorkLocation existingWorkLocation = workLocationMap.put(wlId, workLocation);

        if (existingWorkLocation != null)
        {
            workLocationMap.put(wlId, existingWorkLocation);

            throw new CocoPagesException("wlId in use");
        }
    }

    void updateWorkLocation(WorkLocation workLocation) throws CocoPagesException
    {
        int wlId = workLocation.getWlId();

        if (wlId == 0)
        {
            throw new CocoPagesException("No wlId");
        }

        WorkLocation existingWorkLocation = workLocationMap.put(wlId, workLocation);

        if (existingWorkLocation == null)
        {
            workLocationMap.remove(wlId);

            throw new CocoPagesException("wlId not known");
        }
    }

    void deleteWorkLocation(int wlId) throws CocoPagesException
    {
        if (wlId == 0)
        {
            throw new CocoPagesException("No wlId");
        }

        WorkLocation existingWorkLocation = workLocationMap.get(wlId);

        if (existingWorkLocation != null)
        {
            workLocationMap.remove(wlId);
        }
        else
        {
            throw new CocoPagesException("wlId not known");
        }
    }


    ContactEntry getContactEntry(String recordId) throws CocoPagesException
    {
        if (recordId == null)
        {
            throw new CocoPagesException("No Record Id");
        }

        ContactEntry entry = new ContactEntry();

        entry.setContact(personMap.get(recordId));
        entry.setEmailAccounts(emailMap.get(recordId));
        entry.setPhoneNumbers(phoneMap.get(recordId));
        entry.setWorkLocation(workLocationMap.get(entry.getContact().getWorkLocation()));

        return entry;
    }


    List<ContactEntry> getContactEntries() throws  CocoPagesException
    {
        List<ContactEntry> employees = new ArrayList<>();

        for (String recordId : personMap.keySet())
        {
            employees.add(this.getContactEntry(recordId));
        }

        return employees;
    }


    void addNewContact(ContactEntry newContact) throws CocoPagesException
    {
        String recordId = newContact.getContact().getRecordId();
        int    wlId = newContact.getContact().getWorkLocation();

        if (recordId == null)
        {
            throw new CocoPagesException("No Record Id");
        }

        if (workLocationMap.get(wlId) == null)
        {
            throw new CocoPagesException("Unknown workLocationId in Contact record");
        }

        Contact existingContactEntry = personMap.get(recordId);

        if (existingContactEntry != null)
        {
            throw new CocoPagesException("Record Id in use");
        }

        personMap.put(recordId, newContact.getContact());

        if (newContact.getEmailAccounts() != null)
        {
            emailMap.put(recordId, newContact.getEmailAccounts());
        }

        if (newContact.getPhoneNumbers() != null)
        {
            phoneMap.put(recordId, newContact.getPhoneNumbers());
        }
    }


    void updateContactEntry(ContactEntry contactEntry) throws CocoPagesException
    {
        String recordId = contactEntry.getContact().getRecordId();
        int    wlId     = contactEntry.getContact().getWorkLocation();

        if (recordId == null)
        {
            throw new CocoPagesException("No Record Id");
        }

        if (workLocationMap.get(wlId) == null)
        {
            throw new CocoPagesException("Unknown workLocationId in Contact record");
        }

        Contact existingContactEntry = personMap.get(recordId);

        if (existingContactEntry == null)
        {
            throw new CocoPagesException("Record Id not recognized");
        }

        personMap.put(recordId, contactEntry.getContact());

        if (contactEntry.getEmailAccounts() != null)
        {
            emailMap.put(recordId, contactEntry.getEmailAccounts());
        }

        if (contactEntry.getPhoneNumbers() != null)
        {
            phoneMap.put(recordId, contactEntry.getPhoneNumbers());
        }
    }


    void deleteContactEntry(String recordId) throws CocoPagesException
    {
        if (recordId == null)
        {
            throw new CocoPagesException("No Record Id");
        }

        Contact existingContactEntry = personMap.get(recordId);

        if (existingContactEntry == null)
        {
            throw new CocoPagesException("Record Id not recognized");
        }

        personMap.remove(recordId);
        phoneMap.remove(recordId);
        emailMap.remove(recordId);
    }
}
