/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Group
{
    private String distinguishedName = null;
    private String commonName = null;
    private String description = null;


    public Group()
    {
    }


    public String getDistinguishedName()
    {
        return distinguishedName;
    }


    public void setDistinguishedName(String distinguishedName)
    {
        this.distinguishedName = distinguishedName;
    }


    public String getCommonName()
    {
        return commonName;
    }


    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    @Override
    public String toString()
    {
        return "Group{" +
                       "distinguishedName='" + distinguishedName + '\'' +
                       ", commonName='" + commonName + '\'' +
                       ", description='" + description + '\'' +
                       '}';
    }
}
