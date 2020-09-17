<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->

# Egeria Ansible collection

Ansible collection for deploying and managing ODPi Egeria OMAG-based applications on native
Linux environments. Currently it is tested on RedHat Enterprise Linux distribution.

## Playbooks

Two main playbooks are provided:

- `sites.yml` - deploy OMAG server application on site and configure it via admin-service.
- `sites_cleanup.yml` - un-deploy/clean-up OMAG server application from site.

Everything is configurable via set of variables representing one logical application group
(ie. OMAG server profile). For more advanced deployment configurations host groups can be
used to configure/deploy multiple instances of OMAG server applications in integrated mode.
See configuration and variables section or examples for more details below.

>PRIVILEGE ESCALATION: 
>With the current version of the playbooks it is assumed that the ansible user can do
>privilege escalation / sudo to manage system level changes like creating systemd
>configuration files and controlling (start/stop/restart) the service units. For long term
>the playbooks will be improved so this will become optional, so the system changes can be
>done beforehand by system admins, if needed.

## Usage

### Deploy

```shell script
$ ansible-playbook site.yml -i inventories/sample -e target=omagserver1
``` 

This will trigger sequence of tasks to install, configure and start OMAG server instances on
the target groups described as `omagserver1` in the inventory.

There is also an option to separately execute sequences by adding following self-descriptive
tags: `install`, `configure`, and `start`.

```shell script
$ ansible-playbook site_cleanup.yml -i inventories/sample -e target=omagserver1 -t install,configure
```

This will install and configure the OMAG application, but will not start it.

### Un-deploy

```shell script
$ ansible-playbook site_cleanup.yml -i inventories/sample -e target=omagserver1
```

This will run the sequence of tasks to remove all the files, logs and services related to the
specific server application instance on the target systems described in the inventory as
`omagserver1`.

>Note that the target is just another input variable mapped to hosts, so you can also assign
>multiple targets as comma separated list like `omagserver1`,`omagserver2` or use the
>predefined group `all`.

## Roles

### egeria-omag-server

**Deployment** is handled by the `egeria-omag-server` role. This is a configurable role that
will download all the artifacts needed to configure and start an Egeria OMAG server
application. Once this role has been executed against a target, a specific version of the core
Egeria application `server-chassis-spring.jar` will be installed at the pre-configured
location.

If configured, additional Egeria plug-in libraries will be also installed and loaded. For
example, if `egeria-connector-ibm-information-server-package.jar` is available in the
configuration it will be downloaded under a `libs` folder in the home install directory.

System services with pre-configured application names will be also configured and started for
you. See the role-specific configuration and variable section below for more details.

The sequence of tasks: 

1. Downloads the application artifacts 
1. Creates the application structure on the filesystem
1. Downloads additional libraries
1. Copies application properties/configuration files 
1. Creates system service
1. Starts system service

### egeria-omag-server-admin

**Administration** is handled by `egeria-omag-server-admin` role. It looks up for a specific
`admin_omag_*` set of configuration variables and calls the server admin REST API endpoints
to create/update the configuration documents for each Egeria component. See the role-specific
configuration and variable section below for more details.

The sequence of tasks:

1. Configures servers, using the `admin_omag_server_config` dictionary variable
1. Configures repositories, using the `admin_omag_repo_config` dictionary variable
1. Enables access services, using the `admin_omag_access_services_config` dictionary variable
1. Starts the server instance(s), using the `admin_omag_server_startup` list variable

Each of these sequences depends on the presence of a specific variable. If not present or
empty the specific configuration phase is skipped.

>Currently the roles are embedded and maintained within this collection. Once they get more
>stable and mature they may also be available via ansible-galaxy for other Ansible projects
>wanting to deploy and operate Egeria as part of their overall automation.

## Configuration and variables

### egeria-omag-server

- `atr_artifactory_base` - Source Artifactory url. Default: `https://odpi.jfrog.io/odpi`
- `atr_repository` - Source Artifactory repository. Default: `egeria-snapshot-local`
- `home_dir` - Target server home directory (installation base). Default: `/opt/odpi/egeria`
- `app_host` - Application host address or DNS name on the target server.
- `app_port` - Application port on the target server.
- `app_name` - Application name; also used for systemd service name.
- `app_version`- Version of the Egeria core executable (jar).
- `app_version_suffix` - Version suffix (helps for testing snapshot versions). <!--#TODO: add more details-->
- `app_force_replace` - Forces overwriting the destination artifacts; useful for development
    and testing, especially when snapshot versions are rebuilt.
- `app_extra_libs` - Additional Egeria plugin libraries (ie. connectors).
- `app_configs`- Location of the application configuration files to be applied on the target
    server. <!--#TODO: add more details-->

### egeria-omag-server-admin

- `admin_omag_server_config` - OMAG Server core configuration object; it can be used to
    define multiple server configurations (different logical servers). See Examples for
    details. <!-- #TODO: add more details on how mode works and possible options -->
- `admin_omag_repo_config` - Repositories (OMRS) configuration object. You can define
    multiple repository configurations for servers. See Examples for details.
- `admin_omag_access_services_config` - Access services (OMAS) configuration object. You can
    define multiple access services to be enabled per server or simply enable `all` available.
    See Examples for details.
- `admin_omag_server_startup` - List of services to initialize after configuration is
    performed. See Examples for details.

## Examples

#### Exmaple A: OMAG Server with Asset Catalog OMAS and local graph repository

```yaml
app_port: 8080
app_host: localhost
app_name: egeria_omas
app_version: 1.6-SNAPSHOT
app_version_suffix: 1.6-SNAPSHOT
app_extra_libs:
app_configs:
admin_omag_server_config:
  omas-server:
    mode: server-chassis
    url: "http://{{ app_host }}:{{ app_port }}"
    user: admin
    cohort: mycohort
    cohort_topic: mytopic
    consumer: kafkaserver:9092
    producer: kafkaserver:9092
    auditlog_destination: slf4j
    auditlog_severity: []    
admin_omag_repo_config:
  omas-server:
    mode: local-graph-repository
admin_omag_access_services_config:
  omas-server:
    # all:
    asset-catalog:
      body: 
        supportedTypesForSearch:
          - Asset
          - RelationalColumn 
admin_omag_server_startup:
- omas-server
```

See the sample inventory in `inventories/sample/hosts.yml` and adjust `ansible_*` variables
therein to connect to your target environment.

To deploy, configure and start the sample configuration run following command:

```shell script
$ ansible-playbook sites.yml -i inventories/sample -e target=omagserver1
```

To un-deploy the sample configuration run following command:

```shell script
$ ansible-playbook sites_cleanup.yml -i inventories/sample -e target=omagserver1
```

----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.
