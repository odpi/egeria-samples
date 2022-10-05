/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;


public class HRIMException extends Exception
{
    public HRIMException(String message)
    {
        super(message);
    }


    public HRIMException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
