<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the Egeria project. -->


# Organization synchronization sample connectors

The organization synchronization connector in this package connects to the Coco Pharmaceuticals demo applications located in this repository:

- HR Information Manager (HRIM)
- cocopages - the company contacts (emails, phone numbers etc) directory
- Security Administration (SecAdmin)

The connector populates the profiles and user identifiers for the people and organizations defined in these systems.

Synchronizing this type of data often requires data to be extracted and matched from different systems.  These are typically:

- HR systems that describe employees and their department (represented by HRIM)
- Company directories covering public contact information for employees, contractors and business partners (represented by cocopages)
- Security admin systems that cover details of people's user account information (represented by SecAdmin).


----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the Egeria project.