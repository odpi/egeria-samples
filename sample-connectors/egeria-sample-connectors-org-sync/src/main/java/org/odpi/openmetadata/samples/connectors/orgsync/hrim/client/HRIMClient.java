/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.hrim.client;

import java.util.List;

public class HRIMClient
{
    private String targetRootURL;

    public HRIMClient(String targetRootURL)
    {
        this.targetRootURL = targetRootURL;
    }


    public Department getDepartment(String departmentCode) throws HRIMException
    {
        return null;
    }

    public List<Department> getDepartments()
    {
        return null;
    }


    public void addDepartment(Department department) throws HRIMException
    {

    }


    public void updateDepartment(Department department) throws HRIMException
    {

    }


    public void deleteDepartment(String departmentCode) throws HRIMException
    {

    }



    public Employee getEmployee(String pnum) throws HRIMException
    {
        return null;
    }


    public List<Employee> getEmployees() throws HRIMException
    {
        return null;
    }


    public void addApplicant(Person applicant) throws HRIMException
    {

    }


    public void updateEmployee(Person personalDetails) throws HRIMException
    {

    }


    public void addRole(Role role) throws HRIMException
    {

    }


    public void updateRole(Role role) throws HRIMException
    {

    }


    public void addAward(Award award) throws HRIMException
    {

    }


    public void deleteEmployee(String pnum) throws HRIMException
    {

    }

}
