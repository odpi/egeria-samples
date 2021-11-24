/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.secadmin;


public class SecAdminException extends Exception
{
    public SecAdminException(String message)
    {
        super(message);
    }


    public SecAdminException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
