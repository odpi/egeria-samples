/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public enum HRIMEventType implements Serializable
{
    ADD_DEPARTMENT,
    UPDATE_DEPARTMENT,
    DELETE_DEPARTMENT,

    NEW_APPLICANT,
    UPDATE_PERSONAL_DETAILS,
    ADD_EMPLOYEE_AWARD,
    ADD_ROLE,
    UPDATE_ROLE,
    DELETE_EMPLOYEE
}
