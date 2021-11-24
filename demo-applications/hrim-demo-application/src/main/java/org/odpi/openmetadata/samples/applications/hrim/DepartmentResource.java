/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import org.odpi.openmetadata.samples.applications.hrim.events.HRIMEvent;
import org.odpi.openmetadata.samples.applications.hrim.events.HRIMEventType;
import org.odpi.openmetadata.samples.applications.hrim.properties.Department;
import org.odpi.openmetadata.samples.applications.hrim.properties.Employee;
import org.odpi.openmetadata.samples.applications.hrim.properties.Person;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hrim/api/v1/departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource
{
    private HRIMDatabase  database;
    private HRIMPublisher publisher;


    DepartmentResource(HRIMDatabase database, HRIMPublisher publisher)
    {
        this.database = database;
        this.publisher = publisher;
    }

    @GET
    @Path("/{departmentCode}")
    public Department getDepartment(@PathParam(value = "departmentCode") String departmentCode) throws HRIMException
    {
        return database.getDepartment(departmentCode);
    }

    @GET
    public List<Department> getDepartments()
    {
        return database.getDepartments();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addDepartment(Department department) throws HRIMException
    {
        database.addDepartment(department);
        this.publishEvent(HRIMEventType.ADD_DEPARTMENT, department);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDepartment(Department department) throws HRIMException
    {
        database.updateDepartment(department);
        this.publishEvent(HRIMEventType.UPDATE_DEPARTMENT, department);
    }

    @DELETE
    @Path("/{departmentCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteDepartment(@PathParam(value = "departmentCode") String departmentCode) throws HRIMException
    {
        Department department = database.deleteDepartment(departmentCode);
        this.publishEvent(HRIMEventType.DELETE_DEPARTMENT, department);
    }


    private void publishEvent(HRIMEventType eventType,
                              Department    department) throws HRIMException
    {
        if (publisher != null)
        {
            HRIMEvent event = new HRIMEvent();

            event.setEventType(eventType);
            event.setDepartment(department);

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
