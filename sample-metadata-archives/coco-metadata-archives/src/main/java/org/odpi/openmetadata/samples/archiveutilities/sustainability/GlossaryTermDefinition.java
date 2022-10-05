/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.sustainability;


/**
 * The GlossaryTermDefinition is used to populate the sustainability glossary.
 */
public enum GlossaryTermDefinition
{
    SUSTAINABILITY("Sustainability",
             "A means of operating that makes effective use of replaceable resources.",
             "In the broadest sense, sustainability refers to the ability to maintain or support a process continuously over time. In business and policy contexts, sustainability seeks to prevent the depletion of natural or physical resources, so that they will remain available for the long term.",
                  "",
                   "https://en.wikipedia.org/wiki/Sustainability"),

    GHG_PROTOCOL("Greenhouse Gas Protocol",
             "The Greenhouse Gas Protocol set the standards to measure and manage harmful emissions.",
             "GHG Protocol establishes comprehensive global standardized frameworks to measure and manage greenhouse gas (GHG) emissions from private and public sector operations, value chains and mitigation actions.\n" +
                     "\n" +
                     "Building on a 20-year partnership between World Resources Institute (WRI) and the World Business Council for Sustainable Development (WBCSD), GHG Protocol works with governments, industry associations, NGOs, businesses and other organizations.",
                 "GHG Protocol",
                 "https://ghgprotocol.org"),

    EMISSION("Emission",
          "The release of a harmful substance into the atmosphere",
          "Human activity is causing the release of substances into the earth's atmosphere that is impacting our climate and the health of the flora and fauna that we rely on to survive.  Reducing these emissions are the focus of sustainability initiatives in order to reduce the instability in the climate and availability of resources.",
             null,
             null),

    CO2_EMISSION_SCOPE("CO2 Emission Scope",
       "A type of activity that produces CO2.",
       "One aspect of sustainability is to reduce the amount of CO2 that is produced by the organization. The GHG protocol divides the reporting of CO2 emissions into three scopes: Scope 1, Scope 2 and Scope 3, to make it easier to monitor and build plans to reduce emissions.",
                       null,
                       null),

    SITE("Site",
       "A physical location that Coco Pharmaceuticals operates from.",
       "Coco Pharmaceuticals has a number of physical premises that is operates from.  Each of these premises is called a *site*",
         null,
         null),

    FACILITY("Facility",
       "The facility type captures a particular type of operation that Coco Pharmaceuticals has running at one of its sites.",
       "Each type of facility, such as manufacturing, research, offices, ..., needs different equipment and are likely to have different sustainability challenges.  Therefore by breaking down the activity at each site into facilities, it is possible to create a separate focus on each type of facility.",
                  null,
                  null),

    SUSTAINABILITY_DASHBOARD("Sustainability Dashboard",
          "Graphical summary of Coco Pharmaceuticals' sustainability data.",
          "The sustainability dashboard provides detailed information about the impact of the different activities undertaken by Coco Pharmaceuticals' and how this mpact is changing over time.",
                             null,
                             null),

    ;

    private final String name;
    private final String summary;
    private final String description;
    private final String abbreviation;
    private final String url;


    /**
     * The constructor creates an instance of the enum
     *
     * @param name   unique id for the enum
     * @param summary   name for the enum
     * @param description   description of the use of this value
     * @param abbreviation optional abbreviation
     * @param url optional url for the term
     */
    GlossaryTermDefinition(String name,
                           String summary,
                           String description,
                           String abbreviation,
                           String url)
    {
        this.name = name;
        this.summary = summary;
        this.description = description;
        this.abbreviation = abbreviation;
        this.url = url;
    }

    public String getQualifiedName()
    {
        return "GlossaryTerm:" + name;
    }


    public String getName()
    {
        return name;
    }


    public String getSummary()
    {
        return summary;
    }


    public String getDescription()
    {
        return description;
    }


    public String getAbbreviation()
    {
        return abbreviation;
    }


    public String getUrl() { return url; }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "BusinessAreaDefinition{" + summary + '}';
    }
}
