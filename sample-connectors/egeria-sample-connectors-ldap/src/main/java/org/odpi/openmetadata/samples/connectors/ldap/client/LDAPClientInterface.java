/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.client;

import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPEntry;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPGroup;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPPersonUserAccount;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPSystemUserAccount;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPUserAccount;

import java.util.List;

/**
 * LDAPClientInterface provides the interface that all of the connectors
 * use to integrate with LDAP.
 */
public interface LDAPClientInterface
{
    /*
     * DistinguishedNames of organizational units
     */
    String root = "o=cocoPharma";
    String usersRoot = "ou=users,o=cocoPharma";
    String personRoot = "ou=people,ou=users,o=cocoPharma";
    String systemRoot = "ou=systems,ou=users,o=cocoPharma";
    String groupRoot = "ou=groups,o=cocoPharma";
    String departmentRoot = "ou=departments,ou=groups,o=cocoPharma";
    String roleRoot = "ou=roles,ou=groups,o=cocoPharma";
    String partnerRoot = "ou=partners,ou=groups,o=cocoPharma";
    String projectRoot = "ou=projects,ou=groups,o=cocoPharma";
    
    /*
     * DistinguishedNames of key groups
     */
    String npaGroup = "cn=npa,ou=roles,ou=groups,o=cocoPharma";
    String platformAdminGroup = "cn=platform-administrators,ou=roles,ou=groups,o=cocoPharma";
    String serverAdminGroup = "cn=server-administrators,ou=roles,ou=groups,o=cocoPharma";
    String serverOperatorGroup = "cn=server-operators,ou=roles,ou=groups,o=cocoPharma";
    String serverInvestigatorGroup = "cn=server-investigators,ou=roles,ou=groups,o=cocoPharma";
    String allUsersGroup = "cn=open-metadata-users,ou=roles,ou=groups,o=cocoPharma";
    String managersGroup = "cn=managers,ou=roles,ou=groups,o=cocoPharma";
    String employeesGroup = "cn=employees,ou=roles,ou=groups,o=cocoPharma";
    String contractorsGroup = "cn=contractors,ou=roles,ou=groups,o=cocoPharma";
    String patientsGroup = "cn=patients,ou=partners,ou=groups,o=cocoPharma";
    String hamptonHospitalGroup = "cn=hampton-hospital,ou=partners,ou=groups,o=cocoPharma";
    String foundersGroup = "cn=founders,ou=departments,ou=groups,o=cocoPharma";

    /*
     * LDAP attribute names
     */
    String distinguishedNameAttribute = "dn";
    String commonNameAttribute        = "cn";
    String descriptionAttribute       = "description";
    String userIdAttribute            = "uid";
    String userPasswordAttribute      = "userPassword";
    String surnameAttribute           = "sn";
    String givenNameAttribute         = "givenName";
    String displayNameAttribute       = "displayName";
    String employeeNumberAttribute    = "employeeNumber";
    String employeeTypeAttribute      = "employeeType";
    String emailAttribute             = "mail";
    String managerAttribute           = "manager";
    String uniqueMemberAttribute      = "uniqueMember";
    String orgUnitAttribute           = "organizationalUnit";

    /*
     * LDAP object classes
     */
    String topObjectClass = "ObjectClass: top";
    String applicationProcessObjectClass = "objectclass: applicationProcess";
    String uidObjectClass = "objectclass: uidObject";
    String personObjectClass = "objectclass: person";
    String orgPersonObjectClass = "objectclass: organizationalPerson";
    String inetOrgPersonObjectClass = "objectclass: inetOrgPerson";
    String groupOfUniqueNamesObjectClass = "objectclass: groupOfUniqueNames";


    void storeSystemUserAccount(LDAPSystemUserAccount entry) throws LDAPException;
    void storePersonUserAccount(LDAPPersonUserAccount entry) throws LDAPException;

    LDAPPersonUserAccount       getPerson(String distinguishedName) throws LDAPException;
    LDAPSystemUserAccount       getSystem(String distinguishedName) throws LDAPException;


    List<LDAPPersonUserAccount> getPeople() throws LDAPException;

    List<LDAPSystemUserAccount> getSystems() throws LDAPException;


    void storeGroup(LDAPGroup entry) throws LDAPException;

    LDAPGroup       getGroup(String distinguishedName) throws LDAPException;

    List<LDAPGroup> getGroups() throws LDAPException;

    void addMember(String memberDistinguishedName,
                   String groupDistinguishedName) throws LDAPException;

    void removeMember(String memberDistinguishedName,
                      String groupDistinguishedName) throws LDAPException;

    boolean isMember(String memberDistinguishedName,
                     String groupDistinguishedName) throws LDAPException;

    List<String> getMembers(String groupDistinguishedName) throws LDAPException;


    List<LDAPEntry> findEntries(String rootDistinguishedName,
                                String attributeName,
                                String searchString,
                                int    startFrom,
                                int    pageSize) throws LDAPException;

    void deleteEntry(String distinguishedName) throws LDAPException;


    void disconnect() throws LDAPException;
}
