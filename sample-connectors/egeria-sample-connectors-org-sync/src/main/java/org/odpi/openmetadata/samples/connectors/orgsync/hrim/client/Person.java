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
public class Person
{
    private String         pnum           = null;
    private String         firstName      = null;
    private String         lastName       = null;
    private EmployeeStatus employeeStatus = null;
    private int            jobLevel       = 0;
    private String         departmentCode = null;
    private String         role           = null;
    private int            locationCode   = 0;
    private int            taxStatus      = 1;


    public Person()
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


    public String getFirstName()
    {
        return firstName;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public EmployeeStatus getEmployeeStatus()
    {
        return employeeStatus;
    }


    public void setEmployeeStatus(EmployeeStatus employeeStatus)
    {
        this.employeeStatus = employeeStatus;
    }


    public int getJobLevel()
    {
        return jobLevel;
    }


    public void setJobLevel(int jobLevel)
    {
        this.jobLevel = jobLevel;
    }


    public String getDepartmentCode()
    {
        return departmentCode;
    }


    public void setDepartmentCode(String departmentCode)
    {
        this.departmentCode = departmentCode;
    }


    public String getRole()
    {
        return role;
    }


    public void setRole(String role)
    {
        this.role = role;
    }


    public int getLocationCode()
    {
        return locationCode;
    }


    public void setLocationCode(int locationCode)
    {
        this.locationCode = locationCode;
    }


    public int getTaxStatus()
    {
        return taxStatus;
    }


    public void setTaxStatus(int taxStatus)
    {
        this.taxStatus = taxStatus;
    }


    @Override
    public String toString()
    {
        return "Person{" +
                       "pnum='" + pnum + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", employeeStatus=" + employeeStatus +
                       ", jobLevel=" + jobLevel +
                       ", departmentCode='" + departmentCode + '\'' +
                       ", role='" + role + '\'' +
                       ", locationCode=" + locationCode +
                       ", taxStatus=" + taxStatus +
                       '}';
    }
}
