<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->

# Chart for Egeria Virtual Data Connector

This helm chart will deploy the following components:
- Apache Kafka
- An Egeria cohort with the following participants, all connected to the Kafka event bus
    and configured to exchange events and interoperate
    - Egeria
    - Apache Atlas (with connector and event mapper)
    - IGC connector and event mapper
- Apache Ranger (mariaDB for storage, Solr for indexing)
- GaianDB
- PostGresSQL with sample data from Coco Pharmaceuticals

Additionally, IBM Information Governance Catalog (IGC) 11.7 is required externally. Metadata
from the postgresql data will be loaded into IGC automatically, as well as a suitable
glossary for Coco Pharmaceuticals. Ensure that `odpi-egeria-vdc/templates/configmap.yaml` is
updated with the correct IP addresses for IGC.

You will require a Kubernetes environment to install to. Testing has been done so far
against:

- IBM Cloud (IKS)
- IBM Cloud Private (ICP 3.1)

This chart should work with other providers with the exception of the definitions around
ingress/load balancing - which are how ports are made available external to the cluster.

## Prerequisites

In order to use the charts, you'll first need to have the following installed:

- Kubernetes 1.15 or above
- Helm 3.0 or above

The minimum recommended configurations for Kubernetes are

- Cloud/remote service - 3 nodes, 4GB memory per node
- Local docker for Mac/Windows - 1 node, 8GB memory dedicated to Docker

You could use the Docker-embedded Kubernetes for this on eg. Docker Desktop, or a public
cloud service that provides Kubernetes.

If you need to install helm3, please obtain from https://github.com/helm/helm/releases
before starting and ensure the `helm` executable is in your `$PATH`. The instructions and
examples that follow assume use of this version.

## Caveats

Ranger (including usersync, sync with egeria), Gaian, LDAP are currently not configured.

## Additional configuration

- `odpi-egeria-vdc/values.yaml` is the main place to configure preferences. It is recommended
    to override values by creating a separate file and adding `-f myconfig.yml` or similar.
- `odpi-egeria-vdc/templates/configmap.yaml` currently contains some additional configuration
    which is not pulled in from values. 

## Required helm repositories (prereq)

```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
```

## Installing a chart

```
cd helm-charts
helm dependency update odpi-egeria-vdc
helm install odpi-egeria-vdc vdc
```

## Kafka

By default this chart uses the Bitnami Kafka chart. 

The chart can also be configured to use an external provider. So far this has only been
tested with IBM Event Streams.

First create a Kubernetes secret with the API key needed to use the Kafka service, replacing
`MyReallyLongAPIKey` accordingly:

```
kubectl create secret generic auth-vdc-kafka --from-literal=username=token --from-literal=password=MyReallyLongAPIKey
```

Configure the following configuration overrides, replacing the `brokers` value with the list
of Kafka brokers for your cloud service. The secret created above is also used.

```
kafka:
  internal:
    enabled: false
  external:
    enabled: true
    provider: "IBMEventStreams"
    brokers: "broker1:9093, broker2:9093, broker3:9093"
    secret: auth-vdc-kafka
```

When Egeria configures its connector to use Kafka, it will now set the following properties
in addition to the list of brokers:

```
"security.protocol":"SASL_SSL",
"ssl.protocol":"TLSv1.2",
"ssl.enabled.protocols":"TLSv1.2",
"ssl.endpoint.identification.algorithm":"HTTPS",
"sasl.jaas.config":"org.apache.kafka.common.security.plain.PlainLoginModule required username='${KAFKA_USER}' password='${KAFKA_PASS}';",
"sasl.mechanism":"PLAIN"
```

This fragment is generated using the parameters above by the template code in
[_kafkaext.tpl](templates/_kafkaext.tpl). For other cloud providers, you will need to update
`templates/_kafkaext.tpl` and consider contributing back to the project!

Topics may need to be manually configured - for more details see [topic connector readme](https://egeria.odpi.org/open-metadata-implementation/adapters/open-connectors/event-bus-connectors/open-metadata-topic-connectors/kafka-open-metadata-topic-connector/).

## Load balancing and ingress

The current chart will by default use NodePort for external access. To configure an external
load balancer / ingress service, refer to documentation specific to the cloud provider:

- IBM Cloud - See https://cloud.ibm.com/docs/containers/cs_ingress.html#ingress

----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.
