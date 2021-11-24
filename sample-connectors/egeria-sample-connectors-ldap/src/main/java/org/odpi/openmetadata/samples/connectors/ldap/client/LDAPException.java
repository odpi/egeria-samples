/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.client;


import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * The LDAPException is thrown by the connector when it is not possible to retrieve/save the requested entry from LDAP.
 */
public class LDAPException extends ConnectorCheckedException
{
    private static final long    serialVersionUID = 1L;

    private String distinguishedName;


    /**
     * This is the typical constructor used for creating an exception.
     *
     * @param messageDefinition content of message
     * @param className   name of class reporting error
     * @param actionDescription   description of function it was performing when error detected
     * @param distinguishedName name of the invalid parameter if known
     */
    public LDAPException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         String                     distinguishedName)
    {
        super(messageDefinition, className, actionDescription);

        this.distinguishedName = distinguishedName;
    }


    /**
     * This is the typical constructor used for creating an exception.
     *
     * @param messageDefinition content of message
     * @param className   name of class reporting error
     * @param actionDescription   description of function it was performing when error detected
     * @param distinguishedName name of the invalid parameter if known
     * @param relatedProperties  arbitrary properties that may help with diagnosing the problem.
     */
    public LDAPException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         String                     distinguishedName,
                         Map<String, Object>        relatedProperties)
    {
        super(messageDefinition, className, actionDescription, relatedProperties);

        this.distinguishedName = distinguishedName;
    }


    /**
     * This is the constructor used for creating an exception that resulted from a previous error.
     *
     * @param messageDefinition content of message
     * @param className name of class reporting error
     * @param actionDescription description of function it was performing when error detected
     * @param caughtError the error that resulted in this exception.
     * @param distinguishedName name of the invalid parameter if known
     */
    public LDAPException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         Throwable                  caughtError,
                         String                     distinguishedName)
    {
        super(messageDefinition, className, actionDescription, caughtError);

        this.distinguishedName = distinguishedName;
    }


    /**
     * This is the constructor used for creating an exception that resulted from a previous error.
     *
     * @param messageDefinition content of message
     * @param className name of class reporting error
     * @param actionDescription description of function it was performing when error detected
     * @param caughtError the error that resulted in this exception.
     * @param distinguishedName name of the invalid parameter if known
     * @param relatedProperties  arbitrary properties that may help with diagnosing the problem.
     */
    public LDAPException(ExceptionMessageDefinition messageDefinition,
                         String                     className,
                         String                     actionDescription,
                         Throwable                  caughtError,
                         String                     distinguishedName,
                         Map<String, Object>        relatedProperties)
    {
        super(messageDefinition, className, actionDescription, caughtError, relatedProperties);

        this.distinguishedName = distinguishedName;
    }


    /**
     * Return the invalid parameter's name, if known.
     *
     * @return string name
     */
    public String getDistinguishedName()
    {
        return distinguishedName;
    }


    /**
     * JSON-style toString
     *
     * @return string of property names and values for this enum
     */
    @Override
    public String toString()
    {
        return "LDAPException{" +
                "distinguishedName='" + distinguishedName + '\'' +
                ", reportedHTTPCode=" + getReportedHTTPCode() +
                ", reportingClassName='" + getReportingClassName() + '\'' +
                ", reportingActionDescription='" + getReportingActionDescription() + '\'' +
                ", errorMessage='" + getReportedErrorMessage() + '\'' +
                ", reportedErrorMessageId='" + getReportedErrorMessageId() + '\'' +
                ", reportedErrorMessageParameters=" + Arrays.toString(getReportedErrorMessageParameters()) +
                ", reportedSystemAction='" + getReportedSystemAction() + '\'' +
                ", reportedUserAction='" + getReportedUserAction() + '\'' +
                ", reportedCaughtException=" + getReportedCaughtException() +
                ", relatedProperties=" + getRelatedProperties() +
                '}';
    }


    /**
     * Return comparison result based on the content of the properties.
     *
     * @param objectToCompare test object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (!(objectToCompare instanceof LDAPException))
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        LDAPException that = (LDAPException) objectToCompare;
        return Objects.equals(getDistinguishedName(), that.getDistinguishedName());
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), getDistinguishedName());
    }
}
