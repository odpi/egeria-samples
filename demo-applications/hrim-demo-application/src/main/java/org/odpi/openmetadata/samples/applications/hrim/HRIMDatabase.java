/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import org.odpi.openmetadata.samples.applications.hrim.properties.Award;
import org.odpi.openmetadata.samples.applications.hrim.properties.Bonus;
import org.odpi.openmetadata.samples.applications.hrim.properties.Department;
import org.odpi.openmetadata.samples.applications.hrim.properties.Employee;
import org.odpi.openmetadata.samples.applications.hrim.properties.Person;
import org.odpi.openmetadata.samples.applications.hrim.properties.Role;
import org.odpi.openmetadata.samples.applications.hrim.properties.Salary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRIMDatabase
{
    private Map<String, Department> departmentMap = new HashMap<>();


    private Map<String, Person>       personMap = new HashMap<>();
    private Map<String, List<Role>>   roleMap   = new HashMap<>();
    private Map<String, List<Award>>  awardMap  = new HashMap<>();
    private Map<String, List<Salary>> salaryMap = new HashMap<>();
    private Map<String, List<Bonus>>  bonusMap  = new HashMap<>();


    public HRIMDatabase(List<Department> initialDepartments,
                        List<Employee>   initialEmployees)
    {
        if (initialDepartments != null)
        {
            for (Department department : initialDepartments)
            {
                if ((department != null) && (department.getDepartmentCode() != null))
                {
                    departmentMap.put(department.getDepartmentCode(), department);
                }
            }
        }


        if (initialEmployees != null)
        {
            for (Employee employee : initialEmployees)
            {
                if ((employee != null) && (employee.getPersonalDetails() != null) && (employee.getPersonalDetails().getPnum() != null))
                {
                    String pnum = employee.getPersonalDetails().getPnum();

                    personMap.put(pnum, employee.getPersonalDetails());

                    if (employee.getSalaryHistory() != null)
                    {
                        salaryMap.put(pnum, employee.getSalaryHistory());
                    }

                    if (employee.getBonusHistory() != null)
                    {
                        bonusMap.put(pnum, employee.getBonusHistory());
                    }

                    if (employee.getAwardHistory() != null)
                    {
                        awardMap.put(pnum, employee.getAwardHistory());
                    }

                    if (employee.getRoles() != null)
                    {
                        roleMap.put(pnum, employee.getRoles());
                    }
                }
            }
        }
    }


    Department getDepartment (String departmentCode) throws HRIMException
    {
        if (departmentCode == null)
        {
            throw new HRIMException("No departmentCode");
        }

        return departmentMap.get(departmentCode);
    }

    List<Department> getDepartments()
    {
        return new ArrayList<>(departmentMap.values());
    }

    void addDepartment(Department department) throws HRIMException
    {
        String departmentCode = department.getDepartmentCode();

        if (departmentCode == null)
        {
            throw new HRIMException("No departmentCode");
        }

        Department existingDepartment = departmentMap.put(departmentCode, department);

        if (existingDepartment != null)
        {
            departmentMap.put(departmentCode, existingDepartment);

            throw new HRIMException("departmentCode in use");
        }
    }

    void updateDepartment(Department department) throws HRIMException
    {
        String departmentCode = department.getDepartmentCode();

        if (departmentCode == null)
        {
            throw new HRIMException("No departmentCode");
        }

        Department existingDepartment = departmentMap.put(departmentCode, department);

        if (existingDepartment == null)
        {
            departmentMap.remove(departmentCode);

            throw new HRIMException("departmentCode not known");
        }
    }

    Department deleteDepartment(String departmentCode) throws HRIMException
    {
        if (departmentCode == null)
        {
            throw new HRIMException("No departmentCode");
        }

        Department existingDepartment = departmentMap.get(departmentCode);

        if (existingDepartment != null)
        {
            return departmentMap.remove(departmentCode);
        }
        else
        {
            throw new HRIMException("departmentCode not known");
        }
    }


    Employee getEmployee(String pnum) throws HRIMException
    {
        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Employee employee = new Employee();

        employee.setPersonalDetails(personMap.get(pnum));
        employee.setSalaryHistory(salaryMap.get(pnum));
        employee.setBonusHistory(bonusMap.get(pnum));
        employee.setAwardHistory(awardMap.get(pnum));
        employee.setRoles(roleMap.get(pnum));

        return employee;
    }


    List<Employee> getEmployees() throws  HRIMException
    {
        List<Employee> employees = new ArrayList<>();

        for (String pnum : personMap.keySet())
        {
            employees.add(this.getEmployee(pnum));
        }

        return employees;
    }


    Employee addApplicant(Person applicant) throws HRIMException
    {
        String pnum = applicant.getPnum();

        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee != null)
        {
            throw new HRIMException("PNUM in use");
        }

        personMap.put(pnum, applicant);

        return getEmployee(pnum);
    }


    Employee updateEmployee(Person personalDetails) throws HRIMException
    {
        String pnum = personalDetails.getPnum();

        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee == null)
        {
            throw new HRIMException("PNUM not recognized");
        }

        personMap.put(pnum, personalDetails);

        return getEmployee(pnum);
    }


    Employee deleteEmployee(String pnum) throws HRIMException
    {
        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee == null)
        {
            throw new HRIMException("PNUM not recognized");
        }

        Employee employee = getEmployee(pnum);

        personMap.remove(pnum);
        awardMap.remove(pnum);
        roleMap.remove(pnum);
        salaryMap.remove(pnum);
        bonusMap.remove(pnum);

        return employee;
    }


    Employee addRole(Role role) throws HRIMException
    {
        String pnum = role.getPnum();

        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee == null)
        {
            throw new HRIMException("PNUM not recognized");
        }

        if (role.getStartDate() == null)
        {
            role.setStartDate(new Date());
        }

        List<Role> roles = roleMap.get(pnum);

        if (roles == null)
        {
            roles = new ArrayList<>();
        }

        roles.add(role);

        roleMap.put(pnum, roles);

        return getEmployee(pnum);
    }


    Employee updateRole(Role newRole) throws HRIMException
    {
        String pnum = newRole.getPnum();

        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee == null)
        {
            throw new HRIMException("PNUM not recognized");
        }

        List<Role> existingRoles = roleMap.get(pnum);
        List<Role> newRoles =  new ArrayList<>();

        for (Role existingRole : existingRoles)
        {
            if (existingRole != null)
            {
                if ((existingRole.getStartDate().equals(newRole.getStartDate())) &&
                    ((existingRole.getRoleName() == null) || (existingRole.getRoleName().equals(newRole.getRoleName()))) &&
                    ((existingRole.getRoleType() == null) || (existingRole.getRoleType().equals(newRole.getRoleType()))))
                {
                    // Matching role, skip it
                }
                else
                {
                    newRoles.add(existingRole);
                }
            }
        }

        newRoles.add(newRole);

        roleMap.put(pnum, newRoles);

        return getEmployee(pnum);
    }


    Employee addAward(Award award) throws HRIMException
    {
        String pnum = award.getRecipientPNUM();

        if (pnum == null)
        {
            throw new HRIMException("No PNUM");
        }

        Person existingEmployee = personMap.get(pnum);

        if (existingEmployee == null)
        {
            throw new HRIMException("PNUM not recognized");
        }

        if (award.getAwardDate() == null)
        {
            award.setAwardDate(new Date());
        }

        List<Award> awards = awardMap.get(pnum);

        if (awards == null)
        {
            awards = new ArrayList<>();
        }

        awards.add(award);

        awardMap.put(pnum, awards);

        return getEmployee(pnum);
    }


}
