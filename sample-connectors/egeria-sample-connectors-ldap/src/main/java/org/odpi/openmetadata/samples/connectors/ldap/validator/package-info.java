/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

/**
 * LDAPValidator is an integration connector running in the Security Integrator OMIS.
 * It looks at the users defined in LDAP and their groups and validates them against the contents of Egeria.
 * It logs discrepancies in the audit log.
 */
package org.odpi.openmetadata.samples.connectors.ldap.validator;