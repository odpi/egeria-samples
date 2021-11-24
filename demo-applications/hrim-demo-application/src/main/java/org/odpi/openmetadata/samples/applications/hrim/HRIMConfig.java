/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Configuration;
import org.odpi.openmetadata.samples.applications.hrim.properties.Department;
import org.odpi.openmetadata.samples.applications.hrim.properties.Employee;

import java.util.List;
import java.util.Properties;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * HRIMConfig defines the structure of the yaml file that initializes the
 * HRIM application.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class HRIMConfig extends Configuration
{
    private String     kafkaTopicName;
    private String     kafkaBootstrapServerURL;
    private Properties kafkaProperties = null;

    private List<Employee>   initialEmployees = null;
    private List<Department> initialDepartments = null;


    public HRIMConfig()
    {
    }


    public String getKafkaTopicName()
    {
        return kafkaTopicName;
    }


    public void setKafkaTopicName(String kafkaTopicName)
    {
        this.kafkaTopicName = kafkaTopicName;
    }


    public String getKafkaBootstrapServerURL()
    {
        return kafkaBootstrapServerURL;
    }


    public void setKafkaBootstrapServerURL(String kafkaBootstrapServerURL)
    {
        this.kafkaBootstrapServerURL = kafkaBootstrapServerURL;
    }


    public Properties getKafkaProperties()
    {
        return kafkaProperties;
    }


    public void setKafkaProperties(Properties kafkaProperties)
    {
        this.kafkaProperties = kafkaProperties;
    }


    public List<Employee> getInitialEmployees()
    {
        return initialEmployees;
    }


    public void setInitialEmployees(List<Employee> initialEmployees)
    {
        this.initialEmployees = initialEmployees;
    }


    public List<Department> getInitialDepartments()
    {
        return initialDepartments;
    }


    public void setInitialDepartments(List<Department> initialDepartments)
    {
        this.initialDepartments = initialDepartments;
    }


    @Override
    public String toString()
    {
        return "HRIMConfig{" +
                       "kafkaTopicName='" + kafkaTopicName + '\'' +
                       ", kafkaBootstrapServerURL='" + kafkaBootstrapServerURL + '\'' +
                       ", kafkaProperties=" + kafkaProperties +
                       ", initialEmployees=" + initialEmployees +
                       ", initialDepartments=" + initialDepartments +
                       '}';
    }
}
