/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class WorkLocation
{
    private int    wlId         = 0;
    private String wlName       = null;
    private String buildingName = null;
    private String streetNumber = null;
    private String streetName   = null;
    private String district     = null;
    private String city         = null;
    private String area         = null;
    private String country      = null;


    public WorkLocation()
    {
    }


    public int getWlId()
    {
        return wlId;
    }


    public void setWlId(int wlId)
    {
        this.wlId = wlId;
    }


    public String getWlName()
    {
        return wlName;
    }


    public void setWlName(String wlName)
    {
        this.wlName = wlName;
    }


    public String getBuildingName()
    {
        return buildingName;
    }


    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }


    public String getStreetNumber()
    {
        return streetNumber;
    }


    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }


    public String getStreetName()
    {
        return streetName;
    }


    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }


    public String getDistrict()
    {
        return district;
    }


    public void setDistrict(String district)
    {
        this.district = district;
    }


    public String getCity()
    {
        return city;
    }


    public void setCity(String city)
    {
        this.city = city;
    }


    public String getArea()
    {
        return area;
    }


    public void setArea(String area)
    {
        this.area = area;
    }


    public String getCountry()
    {
        return country;
    }


    public void setCountry(String country)
    {
        this.country = country;
    }


    @Override
    public String toString()
    {
        return "WorkLocation{" +
                       "wlId=" + wlId +
                       ", wlName='" + wlName + '\'' +
                       ", buildingName='" + buildingName + '\'' +
                       ", streetNumber='" + streetNumber + '\'' +
                       ", streetName='" + streetName + '\'' +
                       ", district='" + district + '\'' +
                       ", city='" + city + '\'' +
                       ", area='" + area + '\'' +
                       ", country='" + country + '\'' +
                       '}';
    }
}
