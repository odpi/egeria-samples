/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.hrim.client;


import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;

import java.util.Arrays;
import java.util.Map;

/**
 * The HRIMException is thrown by the connector when it is not possible to retrieve/save the requested entry from LDAP.
 */
public class HRIMException extends ConnectorCheckedException
{
    private static final long    serialVersionUID = 1L;


    /**
     * This is the typical constructor used for creating an exception.
     *
     * @param messageDefinition content of message
     * @param className   name of class reporting error
     * @param actionDescription   description of function it was performing when error detected
     */
    public HRIMException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription)
    {
        super(messageDefinition, className, actionDescription);
    }


    /**
     * This is the typical constructor used for creating an exception.
     *
     * @param messageDefinition content of message
     * @param className   name of class reporting error
     * @param actionDescription   description of function it was performing when error detected
     * @param relatedProperties  arbitrary properties that may help with diagnosing the problem.
     */
    public HRIMException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         Map<String, Object>        relatedProperties)
    {
        super(messageDefinition, className, actionDescription, relatedProperties);
    }


    /**
     * This is the constructor used for creating an exception that resulted from a previous error.
     *
     * @param messageDefinition content of message
     * @param className name of class reporting error
     * @param actionDescription description of function it was performing when error detected
     * @param caughtError the error that resulted in this exception.
     */
    public HRIMException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         Throwable                  caughtError)
    {
        super(messageDefinition, className, actionDescription, caughtError);
    }


    /**
     * This is the constructor used for creating an exception that resulted from a previous error.
     *
     * @param messageDefinition content of message
     * @param className name of class reporting error
     * @param actionDescription description of function it was performing when error detected
     * @param caughtError the error that resulted in this exception.
     * @param relatedProperties  arbitrary properties that may help with diagnosing the problem.
     */
    public HRIMException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         Throwable                  caughtError,
                         Map<String, Object>        relatedProperties)
    {
        super(messageDefinition, className, actionDescription, caughtError, relatedProperties);
    }


    /**
     * JSON-style toString
     *
     * @return string of property names and values for this enum
     */
    @Override
    public String toString()
    {
        return "HRIMException{" +
                       "reportedHTTPCode=" + getReportedHTTPCode() +
                       ", reportingClassName='" + getReportingClassName() + '\'' +
                       ", reportingActionDescription='" + getReportingActionDescription() + '\'' +
                       ", reportedErrorMessage='" + getReportedErrorMessage() + '\'' +
                       ", reportedErrorMessageId='" + getReportedErrorMessageId() + '\'' +
                       ", reportedErrorMessageParameters=" + Arrays.toString(getReportedErrorMessageParameters()) +
                       ", reportedSystemAction='" + getReportedSystemAction() + '\'' +
                       ", reportedUserAction='" + getReportedUserAction() + '\'' +
                       ", reportedCaughtException=" + getReportedCaughtException() +
                       ", reportedCaughtExceptionClassName='" + getReportedCaughtExceptionClassName() + '\'' +
                       ", relatedProperties=" + getRelatedProperties() +
                       '}';
    }
}
