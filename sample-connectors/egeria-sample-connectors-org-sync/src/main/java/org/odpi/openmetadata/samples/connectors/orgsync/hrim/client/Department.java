/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.hrim.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Department
{
    private String departmentCode = null;
    private String departmentName = null;
    private String managerPNUM    = null;


    public Department()
    {
    }

    public String getDepartmentCode()
    {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getManagerPNUM()
    {
        return managerPNUM;
    }

    public void setManagerPNUM(String managerPNUM)
    {
        this.managerPNUM = managerPNUM;
    }


    @Override
    public String toString()
    {
        return "Department{" +
                       "departmentCode='" + departmentCode + '\'' +
                       ", departmentName='" + departmentName + '\'' +
                       ", managerPNUM='" + managerPNUM + '\'' +
                       '}';
    }
}
