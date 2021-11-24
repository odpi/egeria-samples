/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.secadmin.client;

import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.samples.connectors.orgsync.ffdc.OrgSyncIntegrationConnectorErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SecAdminClient
{
    private RestTemplate   restTemplate = null;
    private HttpHeaders    header = null;
    private String targetRootURL;


    public SecAdminClient(String targetRootURL,
                          String connectorName) throws ConnectorCheckedException
    {
        final String methodName = "SecAdminClient()";
        this.targetRootURL = targetRootURL;

        try
        {
            restTemplate = new RestTemplate();

            /* Ensure that the REST template always uses UTF-8 */
            List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
            converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
            converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            header = new HttpHeaders();

            header.setContentType(MediaType.APPLICATION_JSON);
        }
        catch (Exception error)
        {
            throw new ConnectorCheckedException(
                    OrgSyncIntegrationConnectorErrorCode.UNEXPECTED_EXCEPTION.getMessageDefinition(connectorName,
                                                                                                   error.getClass().getName(),
                                                                                                   methodName,
                                                                                                   error.getMessage()),
                    this.getClass().getName(),
                    methodName,
                    error);
        }
    }


    public Group getGroup(String distinguishedName) throws SecAdminException
    {
        return null;
    }


    public List<Group> getGroups()
    {
        return null;
    }


    public void addGroup(Group group) throws SecAdminException
    {
    }


    public void updateGroup(Group group) throws SecAdminException
    {
    }


    public void deleteGroup(String distinguishedName) throws SecAdminException
    {
    }



    public User getUser(String userId) throws SecAdminException
    {
        return null;
    }


    public List<User> getUsers()
    {
        return null;
    }


    public void addUser(User group) throws SecAdminException
    {

    }


    public void updateUser(User group) throws SecAdminException
    {

    }


    public void deleteUser(String userId) throws SecAdminException
    {

    }


    public UserAccess getUserAccess(String userId) throws SecAdminException
    {
        return null;
    }



    public UserAccess getUserAccess(String userId,
                                    String distinguishedName) throws SecAdminException
    {
        return null;
    }


    public UserAccess deleteUserAccess(String userId,
                                       String distinguishedName) throws SecAdminException
    {
        return null;
    }
}
