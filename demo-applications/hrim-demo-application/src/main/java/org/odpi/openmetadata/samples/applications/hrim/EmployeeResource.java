/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import org.odpi.openmetadata.samples.applications.hrim.events.HRIMEvent;
import org.odpi.openmetadata.samples.applications.hrim.events.HRIMEventType;
import org.odpi.openmetadata.samples.applications.hrim.properties.Award;
import org.odpi.openmetadata.samples.applications.hrim.properties.Employee;
import org.odpi.openmetadata.samples.applications.hrim.properties.Person;
import org.odpi.openmetadata.samples.applications.hrim.properties.Role;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import java.util.List;

@Path("/hrim/api/v1/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource
{
    private HRIMDatabase  database;
    private HRIMPublisher publisher;


    EmployeeResource(HRIMDatabase database, HRIMPublisher publisher)
    {
        this.database = database;
        this.publisher = publisher;
    }


    @GET
    @Path("/{pnum}")
    public Employee getEmployee(@PathParam(value = "pnum") String pnum) throws HRIMException
    {
        return database.getEmployee(pnum);
    }

    @GET
    public List<Employee> getEmployees() throws HRIMException
    {
        return database.getEmployees();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addApplicant(Person applicant) throws HRIMException
    {
        Employee employee = database.addApplicant(applicant);
        this.publishEvent(HRIMEventType.NEW_APPLICANT, employee);
    }

    @POST
    @Path("/personal-details")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployee(Person personalDetails) throws HRIMException
    {
        Employee employee = database.updateEmployee(personalDetails);
        this.publishEvent(HRIMEventType.UPDATE_PERSONAL_DETAILS, employee);
    }

    @POST
    @Path("/roles")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addRole(Role role) throws HRIMException
    {
        Employee employee = database.addRole(role);
        this.publishEvent(HRIMEventType.ADD_ROLE, employee);
    }

    @POST
    @Path("/roles/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRole(Role role) throws HRIMException
    {
        Employee employee = database.updateRole(role);
        this.publishEvent(HRIMEventType.UPDATE_ROLE, employee);
    }

    @POST
    @Path("/awards")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAward(Award award) throws HRIMException
    {
        Employee employee = database.addAward(award);
        this.publishEvent(HRIMEventType.ADD_EMPLOYEE_AWARD, employee);
    }

    @DELETE
    @Path("/{pnum}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathParam(value = "pnum") String pnum) throws HRIMException
    {
        Employee employee = database.deleteEmployee(pnum);
        this.publishEvent(HRIMEventType.DELETE_EMPLOYEE, employee);
    }


    private void publishEvent(HRIMEventType eventType,
                              Employee      employee) throws HRIMException
    {
        if (publisher != null)
        {
            HRIMEvent event = new HRIMEvent();

            event.setEventType(eventType);
            event.setEmployee(employee);

            try
            {
                publisher.publishEvent(event);
            }
            catch (Exception error)
            {
                throw new HRIMException("Unable to publish event", error);
            }
        }
    }
}
