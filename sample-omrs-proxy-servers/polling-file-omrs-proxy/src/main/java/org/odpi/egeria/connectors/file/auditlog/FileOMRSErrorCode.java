/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.egeria.connectors.file.auditlog;

import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageSet;

public enum FileOMRSErrorCode implements ExceptionMessageSet {

    INVALID_CLASSIFICATION_FOR_ENTITY(400, "OMRS-FILE-REPOSITORY-400-006",
            "Sample file repository proxy is unable to assign a classification of type {0} to an entity of type {1} because the classification type is not valid for this type of entity",
            "The system is unable to classify an entity because the ClassificationDef for the classification does not list this entity type, or one of its super-types.",
            "Update the ClassificationDef to include the entity's type and rerun the request. Alternatively use a different classification."),
    INVALID_RELATIONSHIP_ENDS(400, "OMRS-FILE-REPOSITORY-400-047",
            "A {0} request has been made to repository {1} for a relationship that has one or more ends of the wrong or invalid type.  Relationship type is {2}; entity proxy for end 1 is {3} and entity proxy for end 2 is {4}",
            "The system is unable to perform the request because the instance has invalid values.",
            "Correct the caller's code and retry the request."),
    INVALID_INSTANCE(400, "OMRS-FILE-REPOSITORY-400-061",
            "An invalid instance has been detected by repository helper method {0}.  The instance is {1}",
            "The system is unable to work with the supplied instance because key values are missing from its contents.",
            "This is probably a logic error in the connector. Raise a git issue to get this investigated and fixed."),
    HOME_REFRESH(400, "OMRS-FILE-REPOSITORY-400-063",
            "Method {0} is unable to request a refresh of instance {1} as it is a local member of metadata collection {2} in repository {3}",
            "The system is unable to process the request.",
            "Review the error message and other diagnostics created at the same time."),
    EVENT_MAPPER_NOT_INITIALIZED(400, "OMRS-FILE-REPOSITORY-400-064 ",
                                 "There is no valid event mapper for repository \"{1}\"",
                                 "Appropriate event could not be produced for request",
                                 "Check the system logs and diagnose or report the problem."),
    EMBEDDED_IN_MEMORY_REPO_NOT_INITIALIZED(400, "OMRS-FILE-REPOSITORY-400-065 ",
                                 "The embedded in memory repository failed to initialise \"{1}\"",
                                 "Connector unable to start",
                                 "Check the system logs and diagnose or report the problem."),
    FOLDER_NOT_SUPPLIED_IN_CONFIG(400, "OMRS-FILE-REPOSITORY-400-066 ",
                                  "The endpoint was not supplied in the connector configuration \"{1}\"",
                                  "Connector unable to continue",
                                  "Supply a valid folder location in the configuration endpoint."),
    FAILED_TO_START_CONNECTOR(400, "OMRS-FILE-REPOSITORY-400-067 ",
                                   "The same file connector failed to start",
                                   "Connector is unable to be used",
                                   "Review your configuration to ensure it is valid."),
    COLLECTION_FAILED_INITIALISE(400, "OMRS-FILE-REPOSITORY-400-068 ",
                                 "The file metadata collection failed to initialise",
                                 "Connector is unable to be used",
                                 "Review your configuration to ensure it is valid. Check the logs and debug."),

    INVALID_FOLDER_FAILURE(500, "OMRS-FILE-REPOSITORY-500-001 ",
            "The Connector sample file polling connector could not was not successfully read the folder \"{0}\"",
            "The system was unable to access the folder.",
            "Check that the endpoint in the folder identifies a folder that exists and can be read."),
    INVALID_SEARCH(500, "OMRS-FILE-REPOSITORY-500-002 ",
            "The Sample file repository proxy was unable to process the search \"{0}\"",
            "The system was unable to run the search.",
            "Check the system logs and diagnose or report the problem."),
    REGEX_NOT_IMPLEMENTED(501, "OMRS-FILE-REPOSITORY-501-001 ",
            "Repository {0} is not able to support the regular expression \"{1}\"",
            "This repository has a fixed subset of regular expressions it can support.",
            "No action required, this is a limitation of the technology. To search using such regular expressions, the metadata of interest" +
                    " must be synchronized to a cohort repository that can support such regular expressions."),
    NO_HISTORY(501, "OMRS-FILE-REPOSITORY-501-002 ",
            "Repository {0} is not able to service historical queries",
            "This repository does not retain historical metadata, so cannot support historical queries.",
            "No action required, this is a limitation of the technology. To search such history, the metadata of interest" +
                    " must be synchronized to a cohort repository that can support history."),

    EVENT_MAPPER_IMPROPERLY_INITIALIZED(400, "OMRS-FILE-REPOSITORY-400-002 ",
            "The event mapper has been improperly initialized for repository \"{1}\"",
            "The system will be unable to process any events",
            "Check the system logs and diagnose or report the problem."),
    TYPEDEF_NOT_SUPPORTED(404, "OMRS-FILE-REPOSITORY-404-001 ",
            "The typedef \"{0}\" is not supported by repository \"{1}\"",
            "The system is currently unable to support the requested the typedef.",
            "Request support through Egeria GitHub issue."),
    ENTITY_NOT_KNOWN(404, "OMRS-FILE-REPOSITORY-404-002 ",
            "The entity identified with guid {0} passed on the {1} call is not known to the open metadata repository {2}",
            "The system is unable to retrieve the properties for the requested entity because the supplied guid is not recognized.",
            "The guid is supplied by the caller to the server.  It may have a logic problem that has corrupted the guid, or the entity has been deleted since the guid was retrieved."),
    RELATIONSHIP_NOT_KNOWN(404, "OMRS-FILE-REPOSITORY-404-003 ",
            "The relationship identified with guid {0} passed on the {1} call is not known to the open metadata repository {2}",
            "The system is unable to retrieve the properties for the requested relationship because the supplied guid is not recognized.",
            "The guid is supplied by the caller to the OMRS.  It may have a logic problem that has corrupted the guid, or the relationship has been deleted since the guid was retrieved."),

    ;

    private ExceptionMessageDefinition messageDefinition;

    /**
     * The constructor for FileOMRSErrorCode expects to be passed one of the enumeration rows defined in
     * FileOMRSErrorCode above.   For example:
     *
     *     FileOMRSErrorCode   errorCode = FileOMRSErrorCode.NULL_INSTANCE;
     *
     * This will expand out to the 5 parameters shown below.
     *
     * @param newHTTPErrorCode - error code to use over REST calls
     * @param newErrorMessageId - unique Id for the message
     * @param newErrorMessage - text for the message
     * @param newSystemAction - description of the action taken by the system when the error condition happened
     * @param newUserAction - instructions for resolving the error
     */
    FileOMRSErrorCode(int newHTTPErrorCode, String newErrorMessageId, String newErrorMessage, String newSystemAction, String newUserAction) {
        this.messageDefinition = new ExceptionMessageDefinition(newHTTPErrorCode,
                newErrorMessageId,
                newErrorMessage,
                newSystemAction,
                newUserAction);
    }

    /**
     * Retrieve a message definition object for an exception.  This method is used when there are no message inserts.
     *
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition() {
        return messageDefinition;
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are values to be inserted into the message.
     *
     * @param params array of parameters (all strings).  They are inserted into the message according to the numbering in the message text.
     * @return message definition object.
     */
    @Override
    public ExceptionMessageDefinition getMessageDefinition(String... params) {
        messageDefinition.setMessageParameters(params);
        return messageDefinition;
    }

    /**
     * toString() JSON-style
     *
     * @return string description
     */
    @Override
    public String toString() {
        return "FileOMRSErrorCode{" +
                "messageDefinition=" + messageDefinition +
                '}';
    }

}
