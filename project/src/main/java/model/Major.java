package model;
import java.util.ArrayList;
import java.util.UUID;
/**
 * The attributes of Major 
 */
public class Major {
    private UUID majorid;
    private String majorName;
    private ArrayList<MajorRequirement> majorRequirements;
    private ArrayList<SemesterPlan> recommendedSemesterPlans;

    
    public Major(UUID majorid) {
        this.majorName = "";
        this.majorid = majorid;
        this.majorRequirements = new ArrayList<>();
        this.recommendedSemesterPlans = new ArrayList<>();
    }

    public Major(UUID majorid, String majorName, ArrayList<MajorRequirement> majorRequirements,
            ArrayList<SemesterPlan> recommendedSemesterPlans) {
        this.majorid = majorid;
        this.majorName = majorName;
        this.majorRequirements = majorRequirements;
        this.recommendedSemesterPlans = recommendedSemesterPlans;
        this.recommendedSemesterPlans = recommendedSemesterPlans;
    }

    protected void addCourse(Course course) {

    }

    protected void removeCourse(Course course) {

    }

    protected boolean changeApplicationID(int choice) {
        return true;
    }

    /**
     * Used by MajorList, checks if there is a Major that matches the parameters
     * 
     * @param majorName the name of the Major
     * @return true if the Major contains the specified name, false otherwise
     */
    public boolean checkMajor(String majorName) {
        return this.majorName.equalsIgnoreCase(majorName);
    }

    public UUID getMajorid() {
        return majorid;
    }

    public String getMajorName() {
        return majorName;
    }

    public ArrayList<MajorRequirement> getMajorRequirements() {
        return majorRequirements;
    }

    public ArrayList<SemesterPlan> getRecommendedSemesterPlans() {
        return recommendedSemesterPlans;
    }

    public void addMajorRequirement(MajorRequirement majorRequirement) {
        this.majorRequirements.add(majorRequirement);
    }

}