/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.cocopages;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * CocoPagesApplication provides the main() for CocoPages.
 */
public class CocoPagesApplication extends Application<CocoPagesConfig>
{
    public static void main(String[] args) throws Exception
    {
        new CocoPagesApplication().run(args);
    }

    public void initialize(Bootstrap<CocoPagesConfig> bootstrap)
    {
        // nothing to do yet
    }


    @Override
    public void run(CocoPagesConfig  configuration,
                    Environment      environment)
    {
        CocoPagesDatabase database = new CocoPagesDatabase(configuration.getInitialContactList(),
                                                           configuration.getInitialEmailList(),
                                                           configuration.getInitialPhoneList(),
                                                           configuration.getInitialWorkLocationList());

        final WorkLocationResource departmentResource = new WorkLocationResource(database);
        final ContactEntryResource employeeResource = new ContactEntryResource(database);

        environment.jersey().register(departmentResource);
        environment.jersey().register(employeeResource);
    }
}
