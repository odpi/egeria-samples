/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.client;

import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPEntry;

import java.util.List;

/**
 * LDAPClientInterface provides the interface that all of the connectors
 * use to integrate with LDAP.
 */
public interface LDAPClientInterface
{
    /*
     * DistinguishedNames of key groups
     */
    String npaGroup = "";
    String serverAdminGroup = "";
    String serverOperatorGroup = "";
    String serverInvestigatorGroup = "";
    String allUsersGroup = "";

    LDAPEntry getEntry(String distinguishedName) throws LDAPException;

    LDAPEntry getEntryByUserId(String userId) throws LDAPException;

    void storeEntry(LDAPEntry entry) throws LDAPException;

    void deleteEntry(String distinguishedName) throws LDAPException;

    boolean isMember(String userId,
                     String groupDistinguishedName) throws LDAPException;


    void addMember(String userId,
                   String groupDistinguishedName) throws LDAPException;

    void removeMember(String userId,
                      String groupDistinguishedName) throws LDAPException;

    List<LDAPEntry> getMembers(String groupDistinguishedName,
                               int    startFrom,
                               int    pageSize) throws LDAPException;

    List<LDAPEntry> findEntries(String attributeName,
                                String searchString,
                                int    startFrom,
                                int    pageSize) throws LDAPException;

    List<LDAPEntry> getEntries(int startFrom,
                               int pageSize) throws LDAPException;
}
