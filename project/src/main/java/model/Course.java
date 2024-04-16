package model;
import java.util.UUID;
import java.util.ArrayList;
/*
 * The attributes of the course class 
 */
public class Course {
    private UUID courseID;
    private Designator designator;
    private String number;
    private int hours;
    private ArrayList<RequirementSet> prerequisites;
    private ArrayList<RequirementSet> corequisites;
    private ArrayList<Keyword> keywords;
    private ArrayList<SemesterOffered> semestersOffered;
    private String name;
    private String description;
    private int preferredSemester;

    
    /**
     * For DataLoader, I won't be able to fully create requirements the same passthrough I create the courses, so we get a dummy course to fill in
     * @param courseID ID of the course 
     */
    public Course(UUID courseID) {
        this.courseID = courseID;
        this.designator = Designator.NULL;
        this.number = "404";
        this.hours = 0;
        this.prerequisites = new ArrayList<>();
        this.corequisites = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.semestersOffered = new ArrayList<>();
        this.preferredSemester = 0;
        this.name = "";
        this.description = "";
    }

    /**
     * The setter method for the Course's parameters.
     */
    public Course(UUID courseID, Designator designator, String number, int hours,
            ArrayList<RequirementSet> prerequisites, ArrayList<RequirementSet> corequisites,
            ArrayList<Keyword> keywords, ArrayList<SemesterOffered> semestersOffered, String name, String description, int preferredSemester) {
        this.courseID = courseID;
        this.designator = designator;
        this.number = number;
        this.hours = hours;
        this.prerequisites = prerequisites;
        this.corequisites = corequisites;
        this.keywords = keywords;
        this.semestersOffered = semestersOffered;
        this.name = name;
        this.description = description;
        this.preferredSemester = preferredSemester;
    }

    /*
     * An accessor (getter) method for the number values.
     */
    public UUID getCourseID() {
        return courseID;
    }

    /*
     * An mutator(setter) method for the courseID.
     */
    public void setCourseID(UUID courseID) {
        this.courseID = courseID;
    }

    /*
     * An accessor (getter) method for the designator.
     */
    public Designator getDesignator() {
        return designator;
    }

    /*
     * An mutator(setter) method for the designator values.
     */
    public void setDesignator(Designator designator) {
        this.designator = designator;
    }

    /*
     * An accessor (getter) method for the number values.
     */
    public String getNumber() {
        return number;
    }

    /*
     * An mutator (setter) method for the number values.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * An accessor (getter) method for the hour value
     * @return hours
     */
    public int getHours() {
        return hours;
    }


    /**
     * An mutator (setter) method for the hour value
     * @param hours hours connected to the course
     */
    public void setHours(int hours) {
        this.hours = hours;
    }


    

    /**
     * An accessor (getter) for keywords
     * @return keywords
     */
    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * A mutator method for the user class
     * @param keywords The code of the courses such as the Carolina Cores and Honors Courses
     */
    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * An accessor that grabs the preferred semester
     * @return the preferred semester that course should be taken in based on the major maps
     */
    public int getPreferredSemester() {
        return preferredSemester;
    }

    /**
     * A mutator method for the user's preferred semester
     * @param preferredSemester the preferred semester that course should be taken in based on the major maps
     */
    public void setPreferredSemester(int preferredSemester) {
        this.preferredSemester = preferredSemester;
    }
    
    /**
     * Used by CourseList to make sure a course in its list
     * matches the given values
     * @param name the Designator of the Course (ex. CSCE)
     * @param number the number of the course (ex 111L, 350, etc.)
     * @param hours the number of credit hours for this course
     * @return true if this Course has these parameters, false otherwise
     */
    public boolean checkCourse(String name, String number, int hours) {
        return this.designator==Designator.valueOf(name) && this.number.equalsIgnoreCase(number) && this.hours == hours;
    }

    public boolean checkCourse(String name, String number) {
        return this.designator.toString().equalsIgnoreCase(name) && this.number.equalsIgnoreCase(number);
    }
    /**
     * Used by CourseList to add the CourseRequirements after the Courses have been loaded
     */
    public void reloadCourseRequirements() {
        for (RequirementSet requirementSet : prerequisites) {
            ArrayList<Course> temp = new ArrayList<>();
            for (Course course : requirementSet.getRequiredCourses()) {
                temp.add(CourseList.getCourseByUUID(course.getCourseID()));
            }
            requirementSet.requiredCourses = temp;
        }
        for (RequirementSet requirementSet : corequisites) {
            ArrayList<Course> temp = new ArrayList<>();
            for (Course course : requirementSet.getRequiredCourses()) {
                temp.add(CourseList.getCourseByUUID(course.getCourseID()));
            }
            requirementSet.requiredCourses = temp;
        }
    }


    public void addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }

 
    /**
     * An accessor (getter) method for the prerequisites.
     * @return prerequisites
     */
    public ArrayList<RequirementSet> getPrerequisites() {
        return prerequisites;
    }

    /**
     * An accessor (getter) method for the corequisites.
     * @return corequistes 
     */
    public ArrayList<RequirementSet> getCorequisites() {
        return corequisites;
    }

    /**
     * An accessor (getter) method for the semester offered.
     * @return semester offered
     */
    public ArrayList<SemesterOffered> getSemestersOffered() {
        return semestersOffered;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Course) {
            return this.courseID.equals(((Course) o).getCourseID());
        } else {
            return false;
        }
    }

    /**
     * A getter method for course's name
     * @return name of course
     */
    public String getName() {
        return name;
    }

    public String getShortName() {
        return designator.toString() + " " + number;
    }

    /**
     * A setter for the course's name
     * @param name the name of course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * An getter method for the description
     * @return the description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * An setter method for the description.
     * @param description A breif overview of the course a student can take
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean SatisfiesRequirement(Student student) {
        if(prerequisites.isEmpty()) {
            return true;
        }
        
        for(RequirementSet requirementSet : prerequisites) {
            if(requirementSet.SatisfiesRequirement(student)) {
                return true;
            }
        }
        return false;
    }
    
}
