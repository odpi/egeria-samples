/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * HRIMApplication provides the main() for HRIM.
 */
public class HRIMApplication extends Application<HRIMConfig>
{
    private static String localServerId = "HRIMApplication";

    public static void main(String[] args) throws Exception
    {
        new HRIMApplication().run(args);
    }

    public void initialize(Bootstrap<HRIMConfig> bootstrap)
    {
        // nothing to do yet
    }


    @Override
    public void run(HRIMConfig  configuration,
                    Environment environment)
    {
        HRIMPublisher publisher = null;

        if (configuration.getKafkaTopicName() != null)
        {
            publisher = new HRIMPublisher(localServerId,
                                          configuration.getKafkaTopicName(),
                                          configuration.getKafkaBootstrapServerURL(),
                                          configuration.getKafkaProperties());
        }

        HRIMDatabase database = new HRIMDatabase(configuration.getInitialDepartments(),
                                                 configuration.getInitialEmployees());

        final DepartmentResource departmentResource = new DepartmentResource(database, publisher);
        final EmployeeResource employeeResource = new EmployeeResource(database, publisher);

        environment.jersey().register(departmentResource);
        environment.jersey().register(employeeResource);
    }
}
