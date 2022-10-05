/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.samples.applications.hrim.properties.Department;
import org.odpi.openmetadata.samples.applications.hrim.properties.Employee;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class HRIMEvent
{
    private HRIMEventType eventType  = null;
    private Employee      employee   = null;
    private Department    department = null;


    public HRIMEvent()
    {
    }


    public HRIMEventType getEventType()
    {
        return eventType;
    }


    public void setEventType(HRIMEventType eventType)
    {
        this.eventType = eventType;
    }


    public Employee getEmployee()
    {
        return employee;
    }


    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }


    public Department getDepartment()
    {
        return department;
    }


    public void setDepartment(Department department)
    {
        this.department = department;
    }


    @Override
    public String toString()
    {
        return "HRIMEvent{" +
                       "eventType=" + eventType +
                       ", employee=" + employee +
                       ", department=" + department +
                       '}';
    }
}
