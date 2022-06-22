<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->

# Repository proxy (adapter) polling example using files 

In development - not for use until more complete

 This [repository proxy](https://egeria-project.org/concepts/repository-proxy/?h=repository) provides an example implementation for interacting with a files in a folder, which uses  
 the open metadata standards of Egeria.

The repository proxy provides:
* a read-only repository connector, that issue calls to the file system.
* an event mapper that calls the repository connector, polling for the metadata associated with a file 
* Audit and error log definitions used by the 2 connectors. 

Furthermore, only a subset of the open types are implemented, namely:
Entity types
* DataFile
* Connection
* ConnectorType
Endpoint
And relationship types
* ConnectionEndpoint
* ConnectionConnectorType
* ConnectionToAsset


## Getting started
TODO
### Configuration
TODO
### Testing 
TODO
----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.

