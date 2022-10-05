/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.ffdc;

import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageSet;

/**
 * The OrgSyncIntegrationConnectorErrorCode is used to define first failure data capture (FFDC) for errors that occur when working with
 * the org sync integration connectors.  It is used in conjunction with both Checked and Runtime (unchecked) exceptions.
 *
 * The 5 fields in the enum are:
 * <ul>
 *     <li>HTTP Error Code - for translating between REST and JAVA - Typically the numbers used are:</li>
 *     <li><ul>
 *         <li>500 - internal error</li>
 *         <li>400 - invalid parameters</li>
 *         <li>404 - not found</li>
 *         <li>409 - data conflict errors - eg item already defined</li>
 *     </ul></li>
 *     <li>Error Message Id - to uniquely identify the message</li>
 *     <li>Error Message Text - includes placeholder to allow additional values to be captured</li>
 *     <li>SystemAction - describes the result of the error</li>
 *     <li>UserAction - describes how a consumer should correct the error</li>
 * </ul>
 */
public enum OrgSyncIntegrationConnectorErrorCode implements ExceptionMessageSet
{
    NO_URL(400, "ORG-SYNC-INTEGRATION-CONNECTOR-400-001",
             "The {0} integration connector is unable to connect to the {1} application because the URL is null",
             "The connector shuts down.",
             "Add the URL of the application to the networkAddress attribute of the connector's endpoint in the Connection object associated with its configuration."),

    UNEXPECTED_EXCEPTION(500, "ORG-SYNC-INTEGRATION-CONNECTOR-500-001",
             "The {0} integration connector received an unexpected {1} exception in method {2}; the error message was: {3}",
             "The connector is unable to catalog one or more element.",
             "Use the details from the error message to determine the cause of the error and retry the request once it is resolved."),

    CLIENT_SIDE_REST_API_ERROR(503, "ORG-SYNC-INTEGRATION-CONNECTOR-503-001",
                               "A client-side exception of {0} was received from API call {1} to URL {2}.  The error message was {3}",
                               "The integration has issued a call to the open metadata access service REST API in a remote server and has received an exception from the local client libraries.",
                               "Look for errors in the local server's console to understand and correct the source of the error.")
    ;


    private ExceptionMessageDefinition messageDefinition;


    /**
     * The constructor for OrgSyncIntegrationConnectorErrorCode expects to be passed one of the enumeration rows defined in
     * OrgSyncIntegrationConnectorErrorCode above.   For example:
     *
     *     OrgSyncIntegrationConnectorErrorCode   errorCode = OrgSyncIntegrationConnectorErrorCode.ERROR_SENDING_EVENT;
     *
     * This will expand out to the 5 parameters shown below.
     *
     *
     * @param httpErrorCode   error code to use over REST calls
     * @param errorMessageId   unique Id for the message
     * @param errorMessage   text for the message
     * @param systemAction   description of the action taken by the system when the error condition happened
     * @param userAction   instructions for resolving the error
     */
    OrgSyncIntegrationConnectorErrorCode(int  httpErrorCode, String errorMessageId, String errorMessage, String systemAction, String userAction)
    {
        this.messageDefinition = new ExceptionMessageDefinition(httpErrorCode,
                                                                errorMessageId,
                                                                errorMessage,
                                                                systemAction,
                                                                userAction);
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are no message inserts.
     *
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition()
    {
        return messageDefinition;
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are values to be inserted into the message.
     *
     * @param params array of parameters (all strings).  They are inserted into the message according to the numbering in the message text.
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition(String... params)
    {
        messageDefinition.setMessageParameters(params);

        return messageDefinition;
    }


    /**
     * JSON-style toString
     *
     * @return string of property names and values for this enum
     */
    @Override
    public String toString()
    {
        return "OrgSyncIntegrationConnectorErrorCode{" +
                       "messageDefinition=" + messageDefinition +
                       '}';
    }
}
