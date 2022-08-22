/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.governanceprogram;

import org.odpi.openmetadata.samples.archiveutilities.SimpleCatalogArchiveHelper;
import org.odpi.openmetadata.samples.archiveutilities.organization.PersonDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * The ProjectDefinition is used to feed the definition of the projects for Coco Pharmaceuticals scenarios.
 */
public enum ProjectDefinition
{
    MANUFACTURING_MOD("Campaign:Manufacturing Modernization",
                      "CAMP-MM",
                      "Manufacturing Modernization Project",
                      "Project to upgrade manufacturing process to support personalized medicine.",
                      true,
                      false,
                      null,
                      ProjectStatusDefinition.ACTIVATING,
                      null,
                      null,
                      PersonDefinition.STEW_FASTER,
                      null),

    CLINICAL_TRIALS("Campaign:Clinical Trials Management",
                    "CAMP-CT",
                    "Drop Foot Clinical Trial",
                    "Clinical trial related to the new treatment for Drop Foot.",
                    true,
                    false,
                    SimpleCatalogArchiveHelper.GOVERNANCE_PROJECT_CLASSIFICATION_NAME,
                    ProjectStatusDefinition.ACTIVE,
                    null,
                    null,
                    PersonDefinition.TANYA_TIDIE,
                    null),

    DROP_FOOT_CLINICAL_TRIAL("Clinical Trial:Drop Foot",
                             "PROJ-CT-DF",
                             "Drop Foot Clinical Trial",
                             "Clinical trial related to the new treatment for Drop Foot.",
                             false,
                             false,
                             SimpleCatalogArchiveHelper.GOVERNANCE_PROJECT_CLASSIFICATION_NAME,
                             ProjectStatusDefinition.ACTIVE,
                             CLINICAL_TRIALS,
                             null,
                             PersonDefinition.TANYA_TIDIE,
                             null),

    DROP_FOOT_CLINICAL_TRIAL_IT("Clinical Trial:Drop Foot:IT Setup",
                                "PROJ-CT-DF-001",
                                "Drop Foot Clinical Trial IT Setup",
                                "Clinical trial related to the new treatment for Drop Foot.",
                                false,
                                false,
                                null,
                                ProjectStatusDefinition.COMPLETED,
                                DROP_FOOT_CLINICAL_TRIAL,
                                null,
                                PersonDefinition.ERIN_OVERVIEW,
                                null),

    DROP_FOOT_TEMPLATES("Clinical Trial:Drop Foot:Templates",
                        "PROJ-CT-DF-002",
                        "Drop Foot Clinical Trial Templates",
                        "Templates for onboarding of Drop Foot related data.",
                        false,
                        true,
                        null,
                        ProjectStatusDefinition.ACTIVATING,
                        DROP_FOOT_CLINICAL_TRIAL_IT,
                        null,
                        PersonDefinition.PETER_PROFILE,
                        null),

    DROP_FOOT_DATA_PIPELINES("Clinical Trial:Drop Foot:Data Pipelines",
                             "PROJ-CT-DF-003",
                             "Drop Foot Clinical Trial Templates",
                             "Pipelines for onboarding of Drop Foot related data.",
                             false,
                             true,
                             null,
                             ProjectStatusDefinition.PLANNED,
                             DROP_FOOT_CLINICAL_TRIAL_IT,
                             new ProjectDefinition[]{ProjectDefinition.DROP_FOOT_TEMPLATES},
                             PersonDefinition.BOB_NITTER,
                             null),
    ;

    private final String                  qualifiedName;
    private final String                  identifier;
    private final String                  displayName;
    private final String                  description;
    private final boolean                 isCampaign;
    private final boolean                 isTask;
    private final String                  projectTypeClassification;
    private final ProjectStatusDefinition projectStatus;
    private final ProjectDefinition       controllingProject;
    private final ProjectDefinition[]     dependentOn;
    private final PersonDefinition        leader;
    private final PersonDefinition[]      members;

    /**
     * The constructor creates an instance of the enum
     *
     * @param qualifiedName   unique id for the  entity
     * @param identifier   unique id for the enum
     * @param displayName   name for the enum
     * @param description   description of the use of this value
     * @param isCampaign is this a collection of related projects
     * @param isTask is this a task within a project
     * @param projectTypeClassification should a classification be added to describe the type of project
     * @param projectStatus what is the status of the project
     * @param controllingProject project that this project reports to - can be null if the project is standalone or a campaign
     * @param leader person to link into the leadership role
     * @param members list of people who are members of the team or organization
     */
    ProjectDefinition(String                     qualifiedName,
                      String                     identifier,
                      String                     displayName,
                      String                     description,
                      boolean                    isCampaign,
                      boolean                    isTask,
                      String                     projectTypeClassification,
                      ProjectStatusDefinition    projectStatus,
                      ProjectDefinition          controllingProject,
                      ProjectDefinition[]        dependentOn,
                      PersonDefinition leader,
                      PersonDefinition[]         members)
    {
        this.qualifiedName = qualifiedName;
        this.identifier = identifier;
        this.displayName = displayName;
        this.description = description;
        this.isCampaign = isCampaign;
        this.isTask = isTask;
        this.projectTypeClassification = projectTypeClassification;
        this.projectStatus = projectStatus;
        this.dependentOn = dependentOn;
        this.controllingProject = controllingProject;
        this.leader = leader;
        this.members = members;
    }

    public String getQualifiedName()
    {
        return qualifiedName;
    }

    public String getIdentifier()
    {
        return identifier;
    }


    public String getDisplayName()
    {
        return displayName;
    }


    public String getDescription()
    {
        return description;
    }


    public boolean isCampaign() { return isCampaign; }


    public boolean isTask() { return isTask; }


    public String getProjectTypeClassification()
    {
        return projectTypeClassification;
    }


    public ProjectStatusDefinition getProjectStatus()
    {
        return projectStatus;
    }


    public ProjectDefinition getControllingProject()
    {
        return controllingProject;
    }


    public List<ProjectDefinition> getDependentOn()
    {
        if (dependentOn != null)
        {
            return Arrays.asList(dependentOn);
        }

        return null;
    }


    public PersonDefinition getLeader()
    {
        return leader;
    }


    public List<PersonDefinition> getMembers()
    {
        if (members != null)
        {
            return Arrays.asList(members);
        }

        return null;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "ProjectDefinition{" + displayName + '}';
    }
}
