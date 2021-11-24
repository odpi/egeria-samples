/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

/**
 * The LDAP client implements the calls to LDAP.  It has two implementations.
 * The first calls LDAP and maintains the directory information in LDAP. The
 * second is an in-memory version to allow the LDAP integration to operate without LDAP running.
 */
package org.odpi.openmetadata.samples.connectors.ldap.client;