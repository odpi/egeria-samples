/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Role
{
    private String pnum            = null;
    private Date   startDate       = null;
    private Date   endDate         = null;
    private String roleType        = null;
    private String roleName        = null;
    private String roleDescription = null;


    public Role()
    {
    }


    public String getPnum()
    {
        return pnum;
    }


    public void setPnum(String pnum)
    {
        this.pnum = pnum;
    }


    public Date getStartDate()
    {
        return startDate;
    }


    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }


    public Date getEndDate()
    {
        return endDate;
    }


    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }


    public String getRoleType()
    {
        return roleType;
    }


    public void setRoleType(String roleType)
    {
        this.roleType = roleType;
    }


    public String getRoleName()
    {
        return roleName;
    }


    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }


    public String getRoleDescription()
    {
        return roleDescription;
    }


    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
    }


    @Override
    public String toString()
    {
        return "Role{" +
                       "pnum='" + pnum + '\'' +
                       ", startDate=" + startDate +
                       ", endDate=" + endDate +
                       ", roleType='" + roleType + '\'' +
                       ", roleName='" + roleName + '\'' +
                       ", roleDescription='" + roleDescription + '\'' +
                       '}';
    }
}
