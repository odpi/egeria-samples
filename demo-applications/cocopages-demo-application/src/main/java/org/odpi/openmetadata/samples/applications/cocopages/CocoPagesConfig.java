/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Configuration;
import org.odpi.openmetadata.samples.applications.cocopages.properties.Contact;
import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactEmail;
import org.odpi.openmetadata.samples.applications.cocopages.properties.ContactPhone;
import org.odpi.openmetadata.samples.applications.cocopages.properties.WorkLocation;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * CocoPagesConfig defines the structure of the yaml file that initializes the
 * cocopages application.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CocoPagesConfig extends Configuration
{
    private List<Contact>      initialContactList      = null;
    private List<ContactEmail> initialEmailList        = null;
    private List<ContactPhone> initialPhoneList        = null;
    private List<WorkLocation> initialWorkLocationList = null;


    public CocoPagesConfig()
    {
    }


    public List<Contact> getInitialContactList()
    {
        return initialContactList;
    }


    public void setInitialContactList(List<Contact> initialContactList)
    {
        this.initialContactList = initialContactList;
    }


    public List<ContactEmail> getInitialEmailList()
    {
        return initialEmailList;
    }


    public void setInitialEmailList(List<ContactEmail> initialEmailList)
    {
        this.initialEmailList = initialEmailList;
    }


    public List<ContactPhone> getInitialPhoneList()
    {
        return initialPhoneList;
    }


    public void setInitialPhoneList(List<ContactPhone> initialPhoneList)
    {
        this.initialPhoneList = initialPhoneList;
    }


    public List<WorkLocation> getInitialWorkLocationList()
    {
        return initialWorkLocationList;
    }


    public void setInitialWorkLocationList(List<WorkLocation> initialWorkLocationList)
    {
        this.initialWorkLocationList = initialWorkLocationList;
    }


    @Override
    public String toString()
    {
        return "CocoPagesConfig{" +
                       "initialContactList=" + initialContactList +
                       ", initialEmailList=" + initialEmailList +
                       ", initialPhoneList=" + initialPhoneList +
                       ", initialWorkLocationList=" + initialWorkLocationList +
                       '}';
    }
}
