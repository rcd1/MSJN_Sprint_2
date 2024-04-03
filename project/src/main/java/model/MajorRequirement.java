package model;
import java.util.ArrayList;
/**
 * A class that represents the requirements found in the major
 */
public class MajorRequirement implements Requirement {
    private String title;
    private int minHours;
    private int maxHours;
    private ArrayList<RequirementSet> acceptableCourseSets;

    /**
     * A setter of the Major Requirement 
     * @param title the name of the major
     * @param minHours the minimum amount of hours a student can take
     * @param maxHours the maximum amount of hours a student can take 
     * @param acceptableCourseSets A set of courses accepted by administration
     */
    public MajorRequirement(String title, int minHours, int maxHours, ArrayList<RequirementSet> acceptableCourseSets) {
        this.title = title;
        this.minHours = minHours;
        this.maxHours = maxHours;
        this.acceptableCourseSets = acceptableCourseSets;
    }

    @Override
    public boolean SatisfiesRequirement(Student student) {
        for (RequirementSet requirementSet : acceptableCourseSets) {
            if (requirementSet.SatisfiesRequirement(student)) return true;
        }
        return false;
    }


    /**
     * A getter method for title
     * @return title
     */
    public String getTitle() {
        return title;
    }


    /**
     * A get for MinHours
     * @return minimum amount of hours
     */
    public int getMinHours() {
        return minHours;
    }


    /**
     * A get for MaxHours
     * @return minimum amount of hours
     */
    public int getMaxHours() {
        return maxHours;
    }


    /**
     * A getter of the acceptable courses based on the arrayList of type of Requirement Set
     * @return the acceptable course sets
     */
    public ArrayList<RequirementSet> getAcceptableCourseSets() {
        return acceptableCourseSets;
    }

}
