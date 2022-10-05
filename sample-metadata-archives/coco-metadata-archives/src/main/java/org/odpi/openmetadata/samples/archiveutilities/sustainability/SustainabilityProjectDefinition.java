/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.sustainability;

import org.odpi.openmetadata.samples.archiveutilities.organization.PersonDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * The ProjectDefinition is used to feed the definition of the projects and teams for Coco Pharmaceuticals scenarios.
 */
public enum SustainabilityProjectDefinition
{
    SUS_GOV("Campaign:Sustainability",
            "Sustainability Campaign",
            "Campaign coordinating the sustainability initiatives in Coco Pharmaceuticals.",
            true,
            false,
            null,
            null,
            null),

    SUS_BOOTSTRAP("Project:Sustainability Bootstrap",
                  "Sustainability Bootstrap Project",
                  "Project to establish a sustainability focus and practices in Coco Pharmaceuticals.",
                  false,
                  false,
                  SustainabilityProjectDefinition.SUS_GOV,
                  null,
                  null),


    ;

    private final String                          qualifiedName;
    private final String                          displayName;
    private final String                          description;
    private final boolean                         isCampaign;
    private final boolean                         isTask;
    private final SustainabilityProjectDefinition superTeam;
    private final PersonDefinition[]              leaders;
    private final PersonDefinition[]              members;

    /**
     * The constructor creates an instance of the enum
     *
     * @param qualifiedName   unique id for the enum
     * @param displayName   name for the enum
     * @param description   description of the use of this value
     * @param isCampaign is this a collection of related projects
     * @param isTask is this a task within a project
     * @param superTeam team that this team reports to - can be null if the entry is an organization
     * @param leaders person to link into the leadership role
     * @param members list of people who are members of the team or organization
     */
    SustainabilityProjectDefinition(String                          qualifiedName,
                                    String                          displayName,
                                    String                          description,
                                    boolean                         isCampaign,
                                    boolean                         isTask,
                                    SustainabilityProjectDefinition superTeam,
                                    PersonDefinition[]              leaders,
                                    PersonDefinition[]              members)
    {
        this.qualifiedName = qualifiedName;
        this.displayName = displayName;
        this.description = description;
        this.isCampaign = isCampaign;
        this.isTask = isTask;
        this.superTeam = superTeam;
        this.leaders = leaders;
        this.members = members;
    }

    public String getQualifiedName()
    {
        return qualifiedName;
    }


    public String getDisplayName()
    {
        return displayName;
    }


    public String getDescription()
    {
        return description;
    }


    public boolean isCampaign()
    {
        return isCampaign;
    }


    public boolean isTask() {return isTask;}


    public SustainabilityProjectDefinition getSuperTeam()
    {
        return superTeam;
    }


    public List<PersonDefinition> getLeaders()
    {
        return Arrays.asList(leaders);
    }


    public List<PersonDefinition> getMembers()
    {
        return Arrays.asList(members);
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "SustainabilityProjectDefinition{" + displayName + '}';
    }
}
