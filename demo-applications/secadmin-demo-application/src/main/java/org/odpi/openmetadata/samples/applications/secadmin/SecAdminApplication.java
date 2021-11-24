/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * SecAdminApplication provides the main() for SecAdmin.
 */
public class SecAdminApplication extends Application<SecAdminConfig>
{
    public static void main(String[] args) throws Exception
    {
        new SecAdminApplication().run(args);
    }

    public void initialize(Bootstrap<SecAdminConfig> bootstrap)
    {
        // nothing to do yet
    }


    @Override
    public void run(SecAdminConfig  configuration,
                    Environment      environment)
    {
        SecAdminDatabase database = new SecAdminDatabase(configuration.getInitialGroups(),
                                                         configuration.getInitialUsers(),
                                                         configuration.getInitialMembership());

        final GroupResource      groupResource      = new GroupResource(database);
        final UserResource       userResource       = new UserResource(database);
        final UserAccessResource userAccessResource = new UserAccessResource(database);

        environment.jersey().register(groupResource);
        environment.jersey().register(userResource);
        environment.jersey().register(userAccessResource);
    }
}
