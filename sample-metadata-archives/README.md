<!-- SPDX-License-Identifier: CC-BY-4.0 -->
<!-- Copyright Contributors to the ODPi Egeria project. -->


# Sample metadata archive

This directory contains the code to generate an open metadata archive containing a new open metadata type called DemoAsset.

Open metadata archives are JSON files that contain open metadata types and instances.  They are loaded into
an Egeria Metadata Server either at start up or once the server is running. 

It does not matter how many times an archive are loaded into a metadata server - nor if the same archive is
loaded into different metadata servers in the same cohort.  The content of the archive is shared without resulting
in duplicates.

## Building the sample

Use `mvn clean install`

## Running the sample

Use `java -jar target/sample-metadata-archives-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Archive file contents

The generated archive file contains the following content:

```json
{
  "class":"OpenMetadataArchive",
  "archiveProperties":{
    "class":"OpenMetadataArchiveProperties",
    "archiveGUID":"85efed9b-97a3-4ac0-ac80-f026571920c2",
    "archiveName":"DemoAssetArchive",
    "archiveDescription":"Specialized types for demonstrating dynamic type extensions.",
    "archiveType":"CONTENT_PACK",
    "originatorName":"Egeria Project",
    "originatorLicense":"Apache 2.0",
    "creationDate":1729163024594,
    "dependsOnArchives":[
      "bce3b0a0-662a-4f87-b8dc-844078a11a6e"
    ]
  },
  "archiveTypeStore":{
    "class":"OpenMetadataArchiveTypeStore",
    "newTypeDefs":[
      {
        "class":"EntityDef",
        "headerVersion":1,
        "guid":"7c39114e-d60a-4f86-ad3f-387c7faa82cc",
        "name":"DemoAsset",
        "status":"ACTIVE_TYPEDEF",
        "version":1,
        "versionName":"1.0",
        "category":"ENTITY_DEF",
        "superType":{
          "headerVersion":1,
          "guid":"ca826f9e-7fb1-4005-921a-fee1c4cd221b",
          "name":"DataAsset",
          "status":"ACTIVE_TYPEDEF"
        },
        "description":"Demonstrates the use of an extended asset type.",
        "origin":"85efed9b-97a3-4ac0-ac80-f026571920c2",
        "createdBy":"Egeria Project",
        "createTime":1729163024594,
        "validInstanceStatusList":[
          "ACTIVE",
          "DELETED"
        ],
        "initialStatus":"ACTIVE",
        "propertiesDefinition":[
          {
            "headerVersion":1,
            "attributeName":"externalResourceId",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Identifier from source system.",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"schema",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"What type of schema?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"verifiedBy",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Who verified this asset?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"verifiedDate",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"When was this asset verified?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"catalog",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Which catalog did this asset come from?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"verified",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Has this asset been verified?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"subjectMatterExperts",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Who are the subject matter experts?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"externalInstanceUpdateTime",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"When was this resource last updated?",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          },
          {
            "headerVersion":1,
            "attributeName":"businessDescription",
            "attributeType":{
              "class":"PrimitiveDef",
              "headerVersion":1,
              "version":1,
              "versionName":"1.0",
              "category":"PRIMITIVE",
              "guid":"b34a64b9-554a-42b1-8f8a-7d5c2339f9c4",
              "name":"string",
              "primitiveDefCategory":"OM_PRIMITIVE_TYPE_STRING"
            },
            "attributeStatus":"ACTIVE_ATTRIBUTE",
            "attributeDescription":"Additional business information.",
            "valuesMinCount":0,
            "valuesMaxCount":1,
            "attributeCardinality":"AT_MOST_ONE",
            "indexable":true,
            "unique":false
          }
        ]
      }
    ]
  }
}
```
## Further reading

* [Open Metadata Archives](https://egeria-project.org/concepts/open-metadata-archive/)
* [Egeria Metadata Access Stores](https://egeria-project.org/egeria-docs/concepts/metadata-access-store/)

----
License: [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/),
Copyright Contributors to the ODPi Egeria project.