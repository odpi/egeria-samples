/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.ldap.client;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPEntry;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPGroup;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPPersonUserAccount;
import org.odpi.openmetadata.samples.connectors.ldap.properties.LDAPSystemUserAccount;

import java.util.ArrayList;
import java.util.List;

public class LDAPServerClient implements LDAPClientInterface
{
    private String   serverHost;
    private int      portNumber;
    private AuditLog auditLog;

    private LdapConnection connection;


    public LDAPServerClient(String   serverHost,
                            int      portNumber,
                            AuditLog auditLog) throws LdapException
    {
        final String methodName = "LDAPServerClient constructor";

        this.serverHost = serverHost;
        this.portNumber = portNumber;
        this.auditLog = auditLog;

        if (auditLog != null)
        {
            auditLog.logMessage(methodName, LDAPAuditCode.CONNECTION_INITIALIZING.getMessageDefinition(serverHost, Integer.toString(portNumber)));
        }

        this.connection = new LdapNetworkConnection(serverHost, portNumber);
        this.connection.setTimeOut(0); // ok for demo systems
        this.connection.bind(); // anonymous bind
    }


    /**
     * Store the entry in the LDAP server.  If the entry exists already it is removed and re-added so all of the properties need to be
     * set up in the supplied entry.
     *
     * @param entry properties for the entry
     * @throws LDAPException unable to store the entry
     */
    @Override
    public void storeSystemUserAccount(LDAPSystemUserAccount entry) throws LDAPException
    {
        final String methodName = "storeSystemUserAccount";

        try
        {
            if (connection.exists(entry.getDistinguishedName()))
            {
                connection.delete(entry.getDistinguishedName());
            }

            connection.add(new DefaultEntry(entry.getDistinguishedName(),
                                            topObjectClass,
                                            applicationProcessObjectClass,
                                            uidObjectClass,
                                            LDAPClientInterface.commonNameAttribute, entry.getCommonName(),
                                            LDAPClientInterface.userIdAttribute, entry.getUserId(),
                                            LDAPClientInterface.descriptionAttribute, entry.getDescription()));
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    entry.getDistinguishedName());
        }
    }


    /**
     * Store the entry in the LDAP server.  If the entry exists already it is removed and re-added so all of the properties need to be
     * set up in the supplied entry.
     *
     * @param entry properties for the entry
     * @throws LDAPException unable to store the entry
     */
    @Override
    public void storePersonUserAccount(LDAPPersonUserAccount entry) throws LDAPException
    {
        final String methodName = "storePersonUserAccount";

        try
        {
            if (connection.exists(entry.getDistinguishedName()))
            {
                connection.delete(entry.getDistinguishedName());
            }

            connection.add(new DefaultEntry(entry.getDistinguishedName(),
                                            topObjectClass,
                                            personObjectClass,
                                            orgPersonObjectClass,
                                            inetOrgPersonObjectClass,
                                            LDAPClientInterface.commonNameAttribute, entry.getCommonName(),
                                            LDAPClientInterface.descriptionAttribute, entry.getDescription(),
                                            LDAPClientInterface.displayNameAttribute, entry.getDisplayName(),
                                            LDAPClientInterface.employeeNumberAttribute, entry.getEmployeeNumber(),
                                            LDAPClientInterface.employeeTypeAttribute, entry.getEmployeeType(),
                                            LDAPClientInterface.givenNameAttribute, entry.getGivenName(),
                                            LDAPClientInterface.emailAttribute, entry.getEmail(),
                                            LDAPClientInterface.managerAttribute, entry.getManager(),
                                            LDAPClientInterface.userIdAttribute, entry.getUserId(),
                                            LDAPClientInterface.userPasswordAttribute, entry.getUserPassword()));
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    entry.getDistinguishedName());
        }
    }


    @Override
    public LDAPPersonUserAccount getPerson(String distinguishedName) throws LDAPException
    {
        final String methodName = "getPerson";

        try
        {
            Entry entry = connection.lookup(distinguishedName);

            if (entry != null)
            {
                return getPerson(entry);
            }

        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    distinguishedName);
        }

        return null;
    }



    @Override
    public LDAPSystemUserAccount getSystem(String distinguishedName) throws LDAPException
    {
        final String methodName = "getSystem";

        try
        {
            Entry entry = connection.lookup(distinguishedName);

            if (entry != null)
            {
                return getSystem(entry);
            }

        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    distinguishedName);
        }

        return null;
    }


    private String getLDAPValue(Entry  entry,
                                String attributeName) throws LdapException
    {
        if (LDAPClientInterface.distinguishedNameAttribute.equals(attributeName))
        {
            Dn dn = entry.getDn();

            return dn.getName();
        }
        else
        {
            Attribute attribute = entry.get(attributeName);

            if (attribute != null)
            {
                return attribute.getString();
            }
            else
            {
                return null;
            }
        }
    }

    private LDAPSystemUserAccount getSystem(Entry entry) throws LdapException
    {
        LDAPSystemUserAccount result = new LDAPSystemUserAccount();

        result.setDistinguishedName(getLDAPValue(entry, LDAPClientInterface.distinguishedNameAttribute));
        result.setCommonName(getLDAPValue(entry, LDAPClientInterface.commonNameAttribute));
        result.setDescription(getLDAPValue(entry, LDAPClientInterface.descriptionAttribute));
        result.setUserId(getLDAPValue(entry, LDAPClientInterface.userIdAttribute));
        result.setUserPassword(getLDAPValue(entry, LDAPClientInterface.userPasswordAttribute));

        return result;
    }

    private LDAPPersonUserAccount getPerson(Entry entry) throws LdapException
    {
        LDAPPersonUserAccount result = new LDAPPersonUserAccount();

        result.setDistinguishedName(getLDAPValue(entry, LDAPClientInterface.distinguishedNameAttribute));
        result.setCommonName(getLDAPValue(entry, LDAPClientInterface.commonNameAttribute));
        result.setDescription(getLDAPValue(entry, LDAPClientInterface.descriptionAttribute));
        result.setUserId(getLDAPValue(entry, LDAPClientInterface.userIdAttribute));
        result.setUserPassword(getLDAPValue(entry, LDAPClientInterface.userPasswordAttribute));
        result.setDisplayName(getLDAPValue(entry, LDAPClientInterface.displayNameAttribute));
        result.setGivenName(getLDAPValue(entry, LDAPClientInterface.givenNameAttribute));
        result.setSurname(getLDAPValue(entry, LDAPClientInterface.surnameAttribute));
        result.setEmployeeNumber(getLDAPValue(entry, LDAPClientInterface.employeeNumberAttribute));
        result.setEmployeeType(getLDAPValue(entry, LDAPClientInterface.employeeTypeAttribute));
        result.setManager(getLDAPValue(entry, LDAPClientInterface.managerAttribute));
        result.setEmail(getLDAPValue(entry, LDAPClientInterface.emailAttribute));

        return result;
    }


    @Override
    public List<LDAPPersonUserAccount> getPeople() throws LDAPException
    {
        final String methodName = "getPeople";

        List<LDAPPersonUserAccount> results = new ArrayList<>();

        try
        {
            EntryCursor cursor = connection.search(LDAPClientInterface.personRoot, "(objectclass=*)", SearchScope.ONELEVEL);

            for (Entry entry : cursor)
            {
                if (entry != null)
                {
                    results.add(getPerson(entry));
                }
            }

            cursor.close();
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.usersRoot);
        }

        if (! results.isEmpty())
        {
            return results;
        }

        return null;
    }

    @Override
    public List<LDAPSystemUserAccount> getSystems() throws LDAPException
    {
        final String methodName = "getSystems";

        List<LDAPSystemUserAccount> results = new ArrayList<>();

        try
        {
            EntryCursor cursor = connection.search(LDAPClientInterface.systemRoot, "(objectclass=*)", SearchScope.ONELEVEL);

            for (Entry entry : cursor)
            {
                if (entry != null)
                {
                    results.add(getSystem(entry));
                }
            }

            cursor.close();
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.usersRoot);
        }

        if (! results.isEmpty())
        {
            return results;
        }

        return null;
    }


    /**
     * Store the entry in the LDAP server.  If the entry exists already it is removed and re-added so all of the properties need to be
     * set up in the supplied entry.
     *
     * @param entry properties for the entry
     * @throws LDAPException unable to store the entry
     */
    @Override
    public void storeGroup(LDAPGroup entry) throws LDAPException
    {
        final String methodName = "storeGroup";

        try
        {
            if (connection.exists(entry.getDistinguishedName()))
            {
                connection.delete(entry.getDistinguishedName());
            }

            connection.add(new DefaultEntry(entry.getDistinguishedName(),
                                            topObjectClass,
                                            applicationProcessObjectClass,
                                            uidObjectClass,
                                            LDAPClientInterface.commonNameAttribute, entry.getCommonName(),
                                            LDAPClientInterface.orgUnitAttribute, entry.getOrganizationalUnit(),
                                            LDAPClientInterface.descriptionAttribute, entry.getDescription()));
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.usersRoot);
        }
    }


    @Override
    public LDAPGroup getGroup(String distinguishedName) throws LDAPException
    {
        final String methodName = "getGroup";

        try
        {
            Entry entry = connection.lookup(distinguishedName);

            if (entry != null)
            {
                return getGroup(entry);
            }

        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    distinguishedName);
        }

        return null;
    }

    private LDAPGroup getGroup(Entry entry) throws LdapException
    {
        LDAPGroup result = new LDAPGroup();

        result.setDistinguishedName(getLDAPValue(entry, LDAPClientInterface.distinguishedNameAttribute));
        result.setCommonName(getLDAPValue(entry, LDAPClientInterface.commonNameAttribute));
        result.setDescription(getLDAPValue(entry, LDAPClientInterface.descriptionAttribute));
        result.setOrganizationalUnit(getLDAPValue(entry, LDAPClientInterface.orgUnitAttribute));

        return result;
    }

    @Override
    public List<LDAPGroup> getGroups() throws LDAPException
    {
        final String methodName = "getGroups";

        List<LDAPGroup> results = new ArrayList<>();

        try
        {
            EntryCursor cursor = connection.search(LDAPClientInterface.groupRoot, "(objectclass=*)", SearchScope.SUBTREE);

            for (Entry entry : cursor)
            {
                if (entry != null)
                {
                    results.add(getGroup(entry));
                }
            }

            cursor.close();
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.groupRoot);
        }

        if (! results.isEmpty())
        {
            return results;
        }

        return null;
    }


    @Override
    public void addMember(String memberDistinguishedName,
                          String groupDistinguishedName) throws LDAPException
    {

    }

    @Override
    public void removeMember(String memberDistinguishedName,
                             String groupDistinguishedName) throws LDAPException
    {

    }


    @Override
    public boolean isMember(String memberDistinguishedName,
                            String groupDistinguishedName) throws LDAPException
    {
        List<String> members = this.getMembers(groupDistinguishedName);

        if (members != null)
        {
            return members.contains(memberDistinguishedName);
        }

        return false;
    }


    @Override
    public List<String> getMembers(String groupDistinguishedName) throws LDAPException
    {
        final String methodName = "getMembers";

        List<String> results = new ArrayList<>();

        try
        {
            Entry groupEntry = connection.lookup(groupDistinguishedName);

            if (groupEntry != null)
            {
                for (Attribute attribute : groupEntry.getAttributes())
                {
                    if (attribute != null)
                    {
                        if (LDAPClientInterface.uniqueMemberAttribute.equals(attribute.getId()))
                        {
                            results.add(attribute.getString());
                        }
                    }
                }
            }
            else
            {
                return null;
            }
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    groupDistinguishedName);
        }

        if (! results.isEmpty())
        {
            return results;
        }

        return null;
    }

    @Override
    public List<LDAPEntry> findEntries(String rootDistinguishedName,
                                       String attributeName,
                                       String searchString,
                                       int    startFrom,
                                       int    pageSize) throws LDAPException
    {
        return null;
    }



    @Override
    public void deleteEntry(String distinguishedName) throws LDAPException
    {
        final String methodName = "deleteEntry";

        try
        {
            if (connection.exists(distinguishedName))
            {
                connection.delete(distinguishedName);
            }
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    distinguishedName);
        }
    }

    @Override
    public void disconnect() throws LDAPException
    {
        final String methodName = "disconnect";

        try
        {
            connection.close();
        }
        catch (Exception error)
        {
            throw new LDAPException(LDAPErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(),
                                    this.getClass().getName(),
                                    methodName,
                                    error,
                                    LDAPClientInterface.root);
        }
    }

}
