/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.archiveutilities.organization;


/**
 * The EmployeeTypeDefinition is used to feed the definition of the EmployeeType valid value set for Coco Pharmaceuticals' employees.
 */
public enum EmployeeTypeDefinition
{
    EXECUTIVE           ("Exec",       "Executive",                    "This person is not an employee - they are an executive of the organization."),
    BOARD_ADVISOR       ("BDA",        "Board Advisor",                "This person is on the board and does not work for the organization."),
    FULL_TIME_PERMANENT ("FTP",        "Full-time permanent employee", "This permanent employee is contracted to work 35 hours or more per week."),
    PART_TIME_PERMANENT ("PTP",        "Part-time permanent employee", "This permanent employee is contracted to work less than 35 hours a week."),
    STUDENT             ("It",         "Intern",                       "This temporary employee is a trainee gaining work experience."),
    SABBATICAL          ("Sab",        "On Sabbatical",                "This permanent employee has a leave of absence."),
    CONTRACTOR          ("Contractor", "Contractor",                   "This employee is on a temporary contract."),
    PARTNER             ("Partner",    "Partner",                      "This person works for a partner organization."),
    LEFT                ("Resigner",   "Former Employee - resigned",   "This employee has left the company either because they resigned or took redundancy."),
    RETIRED             ("Retiree",    "Former Employee - retired",    "This employee has retired from the company."),
    DISMISSED           ("RTL",        "Former Employee - dismissed",  "This employee was dismissed from the company."),
    ;

    public static final String validValueSetName        = "EmployeeType";
    public static final String validValueSetDescription = "Describes the type of employment contract that a person has with Coco Pharmaceuticals.";
    public static final String validValueSetUsage       = "Stored in the `employeeType` property of the Person entity.";
    public static final String validValueSetScope       = "Used when the person described has an employment contract with Coco Pharmaceuticals.";

    private final String preferredValue;
    private final String displayName;
    private final String description;


    /**
     * The constructor creates an instance of the enum
     *
     * @param preferredValue   unique id for the enum
     * @param displayName   name for the enum
     * @param description further information about the enum
     */
    EmployeeTypeDefinition(String preferredValue, String displayName, String description)
    {
        this.preferredValue = preferredValue;
        this.displayName = displayName;
        this.description = description;
    }




    /**
     * This is the preferred value that applications should use for this valid value.
     *
     * @return string value
     */
    public String getPreferredValue()
    {
        return preferredValue;
    }


    /**
     * Return the printable name.
     *
     * @return string name
     */
    public String getDisplayName()
    {
        return displayName;
    }


    /**
     * Return the description of the value's meaning.
     *
     * @return string text
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Output of this enum class and main value.
     *
     * @return string showing enum value
     */
    @Override
    public String toString()
    {
        return "EmployeeTypeDefinition{" + displayName + '}';
    }
}
