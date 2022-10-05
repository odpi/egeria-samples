/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim.properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee
{
    private Person       personalDetails = null;
    private List<Role>   roles           = null;
    private List<Salary> salaryHistory   = null;
    private List<Bonus>  bonusHistory    = null;
    private List<Award>  awardHistory    = null;


    public Employee()
    {
    }


    public Person getPersonalDetails()
    {
        return personalDetails;
    }


    public void setPersonalDetails(Person personalDetails)
    {
        this.personalDetails = personalDetails;
    }


    public List<Role> getRoles()
    {
        return roles;
    }


    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }


    public List<Salary> getSalaryHistory()
    {
        return salaryHistory;
    }


    public void setSalaryHistory(List<Salary> salaryHistory)
    {
        this.salaryHistory = salaryHistory;
    }


    public List<Bonus> getBonusHistory()
    {
        return bonusHistory;
    }


    public void setBonusHistory(List<Bonus> bonusHistory)
    {
        this.bonusHistory = bonusHistory;
    }


    public List<Award> getAwardHistory()
    {
        return awardHistory;
    }


    public void setAwardHistory(List<Award> awardHistory)
    {
        this.awardHistory = awardHistory;
    }


    @Override
    public String toString()
    {
        return "Employee{" +
                       "personalDetails=" + personalDetails +
                       ", roles=" + roles +
                       ", salaryHistory=" + salaryHistory +
                       ", bonusHistory=" + bonusHistory +
                       ", awardHistory=" + awardHistory +
                       '}';
    }
}
