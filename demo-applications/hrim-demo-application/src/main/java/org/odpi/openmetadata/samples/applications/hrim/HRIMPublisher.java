/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.applications.hrim;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.odpi.openmetadata.samples.applications.hrim.events.HRIMEvent;

import java.util.Properties;

/**
 * HRIMPublisher sends events to Kafka
 */
public class HRIMPublisher
{
    private final String                        localServerId;
    private final Properties                    producerProperties;
    private       KafkaProducer<String, String> producer;
    private       String                        topicName;

    public HRIMPublisher(String     localServerId,
                         String     topicName,
                         String     bootstrapServerURL,
                         Properties properties)
    {
        this.localServerId = localServerId;

        this.producerProperties = properties;
        this.producerProperties.put("bootstrap.servers", bootstrapServerURL);
        this.producerProperties.put("server.id", localServerId);

        this.producer = new KafkaProducer<>(producerProperties);
        this.topicName = topicName;
    }

    public void publishEvent(HRIMEvent event) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();

        String eventString = objectMapper.writeValueAsString(event);

        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, localServerId, eventString);

        producer.send(record);
    }
}
