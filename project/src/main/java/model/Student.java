package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Attributes of Student 
 * Student extends User
 */
public class Student extends User {
    private Major major;
    private int year;
    private double gpa;
    private ArrayList<SemesterPlan> semesterPlans;
    private ArrayList<LegalGuardian> legalGuardians;
    private Advisor advisor;
    private ArrayList<Note> notes;
    private boolean isHonors;
    private boolean hasScholarship;
    private HashMap<Course, Grade> studentGrades;
    private ApplicationID applicationID;
    private ArrayList<SemesterPlan> eightSemesterPlan; // Follows a pseudo singleton design pattern

    /*
     * In the User.java, there are 2 final String representing the UUIDs of a blank
     * Advisor and Major (since they cannot be null). When the student is selecting
     * their Major and the Advisor is selecting their students, check for these IDs
     * and replace
     */

    public Student(UUID studentID) {
        super(studentID);
        this.major = new Major(UUID.fromString(BLANK_MAJOR_ID));
        this.year = 2020;
        this.gpa = 3.2;
        this.advisor = new Advisor(UUID.fromString(BLANK_ADVISOR_ID));
        this.semesterPlans = new ArrayList<>();
        this.legalGuardians = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.studentGrades = new HashMap<>();
        this.applicationID = ApplicationID.UNDECLARED;
    }

    public Student(String firstName, String lastName, String email, String password, UUID userID, Major major, int year,
            double gpa, ArrayList<SemesterPlan> semesterPlans, ArrayList<LegalGuardian> legalGuardians, Advisor advisor,
            ArrayList<Note> notes, boolean isHonors, boolean hasScholarship, HashMap<Course, Grade> studentGrades,
            ApplicationID applicationID) {
        super(firstName, lastName, email, password, userID);
        this.major = major;
        this.year = year;
        this.gpa = gpa;
        this.semesterPlans = semesterPlans;
        this.legalGuardians = legalGuardians;
        this.advisor = advisor;
        this.notes = notes;
        this.isHonors = isHonors;
        this.hasScholarship = hasScholarship;
        this.studentGrades = studentGrades;
        this.applicationID = applicationID;
    }

    // Doesn't initialize other members. Might need a bunch of setters. This is for
    // when a new account is created
    public Student(String firstName, String lastName, String email, String password, UUID userID) {
        super(firstName, lastName, email, password, userID);
        this.major = new Major(UUID.fromString(BLANK_MAJOR_ID));
        this.year = 2020;
        this.gpa = 3.2;
        this.advisor = new Advisor(UUID.fromString(BLANK_ADVISOR_ID));
        this.semesterPlans = new ArrayList<>();
        this.legalGuardians = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.studentGrades = new HashMap<>();
        this.applicationID = ApplicationID.UNDECLARED;
    }

    public double calculateGPA() {
        return -1.0;
    }

    public void setStudentAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void addCourseToSemsterPlan(Course course) {
        if (!semesterPlans.isEmpty()) {
            SemesterPlan lastSemesterPlan = semesterPlans.get(semesterPlans.size() - 1);
            lastSemesterPlan.addCourse(course);
        } else {
            SemesterPlan semesterPlan = new SemesterPlan();
            semesterPlan.addCourse(course);
            semesterPlans.add(semesterPlan);
        }
    }

    public void removeCourseFromSemesterPlan(Course course) {
        if (!semesterPlans.isEmpty()) {
            SemesterPlan lastSemesterPlan = semesterPlans.get(semesterPlans.size() - 1);
            lastSemesterPlan.removeCourse(course);
        }
    }

    public void saveSemesterPlanToFile(String fileName) {
        if (!semesterPlans.isEmpty()) {
            ArrayList<SemesterPlan> semesterPlans = this.generateEightSemesterPlan();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < semesterPlans.size(); i++) {
                if(i + 1 == 6) {
                    sb.append("(Current)");
                    sb.append('\n');
                }
                sb.append("Semester: ");
                sb.append(i + 1);
                sb.append('\n');
                ArrayList<Course> courses = semesterPlans.get(i).getCourses();
                for(int j = 0; j < courses.size(); j++) {
                    if(i + 1 < 6) {
                        sb.append(studentGrades.get(courses.get(j)).getLetter());
                        sb.append(": ");
                    }
                    sb.append(courses.get(j).getName());
                    sb.append('\n');
                }
                sb.append("========================================");
                sb.append('\n');
            }
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(sb.toString());
                System.out.println("Semester plan saved to " + fileName);
            } catch (IOException e) {
                System.out.println("Error saving semester plan to file: " + e.getMessage());
            }
        } else {
            System.out.println("No semester plans available");
        }
    }

    public void displaySemesterPlan() {
        if (!semesterPlans.isEmpty()) {
            ArrayList<SemesterPlan> semesterPlans = this.generateEightSemesterPlan();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < semesterPlans.size(); i++) {
                if(i + 1 == 6) {
                    sb.append("(Current)");
                    sb.append('\n');
                }
                sb.append("Semester: ");
                sb.append(i + 1);
                sb.append('\n');
                ArrayList<Course> courses = semesterPlans.get(i).getCourses();
                for(int j = 0; j < courses.size(); j++) {
                    if(i + 1 < 6) {
                        sb.append(studentGrades.get(courses.get(j)).getLetter());
                        sb.append(": ");
                    }
                    sb.append(courses.get(j).getName());
                    sb.append('\n');
                }
                sb.append("========================================");
                sb.append('\n');
            }
            System.out.print(sb.toString());
        } else {
            System.out.println("No semester plans available");
        }
    }

    public void viewNote(Student student, String note) {

    }

    public void notifier() {

    }

    public void whatIf(Major major, ArrayList<Course> courses) {

    }

    @Override
    protected void viewStudentProfile(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewStudentProfile'");
    }

    // This could be renamed to something like "getGrade"
    public Grade satisfiesPrerequisite(Course course) {
        Grade[] returnGrade = new Grade[1];
        returnGrade[0] = null;
        studentGrades.forEach((key, value) -> {
            if (course.getCourseID().equals(key.getCourseID())) {
                returnGrade[0] = value;
                return;
            }
        });
        return returnGrade[0];
    }

    /**
     * 
     * @return a new Array List of type Semester Plan
     */
    public ArrayList<SemesterPlan> generateEightSemesterPlan() {
        ArrayList<SemesterPlan> eightSemesterPlan = new ArrayList<SemesterPlan>(8);
        int offset = semesterPlans.size();
        int planIndex = offset;
        
        // Copy already completed classes into new eight semester plan
        for(int i = 0; i < semesterPlans.size(); i++) {
            eightSemesterPlan.add(new SemesterPlan());
            SemesterPlan semesterPlan = eightSemesterPlan.get(i);
            for(Course course : semesterPlans.get(i).getCourses()) {
                semesterPlan.primitiveAddCourse(course);
            }
        }

        eightSemesterPlan.add(new SemesterPlan());

        ArrayList<SemesterPlan> majorMapSkeleton = major.getRecommendedSemesterPlans();

        HashMap<Course, Grade> availableFillerCourses = (HashMap<Course, Grade>)studentGrades.clone();
        
        int semesterHours = 0;

        // Loop through the arrayList of semesters
        for(int i = 0; i < majorMapSkeleton.size(); i++) {
            ArrayList<Course> skeletonSemesterCourses = majorMapSkeleton.get(i).getCourses();
            
            // Loop through the arrayList of courses in the semester
            for(int j = 0; j < skeletonSemesterCourses.size(); j++) {
                // Determine what course to add, if any
                Course tempCourse = skeletonSemesterCourses.get(j);
                Course courseToAdd = tempCourse;
                int semesterHoursToAdd = courseToAdd.getHours();
                boolean addCourse = true;
                
                // Find course that might need to be added
                // First, handle filler courses
                if(tempCourse.getDesignator() == Designator.FILL) {
                    semesterHoursToAdd = 3;
                    // Get courses that could fill this
                    Keyword searchKeyword = tempCourse.getKeywords().get(0);
                    if(searchKeyword == Keyword.AP0 && applicationID != ApplicationID.UNDECLARED) {
                        searchKeyword = Keyword.valueOf(applicationID.getKeyword().toString());
                    }

                    ArrayList<Course> potentialCourses = CourseList.getInstance().findCourses(searchKeyword.toString());
                    
                    // Check if one of the potential courses has been taken, and if it has, then add that course. Otherwise, add the filler
                    outerLoop: 
                    for(Course potentialCourse : potentialCourses) {
                        for(Course studentCourse : availableFillerCourses.keySet()) {
                            Grade studentGrade = availableFillerCourses.get(studentCourse);
                            if(studentGrade != null && (potentialCourse.equals(studentCourse) &&
                            (studentGrade.getPointValue() >= Grade.C.getPointValue() || studentGrade == Grade.R))) {
                                courseToAdd = studentCourse;
                                semesterHoursToAdd = courseToAdd.getHours();
                                availableFillerCourses.remove(studentCourse);
                                addCourse = studentGrade == Grade.R; // If the course hasn't been completed, but has been determined, add it.
                                break outerLoop;
                            }
                        }
                    }                    
                } else { // Handle non-filler courses
                    semesterHoursToAdd = courseToAdd.getHours();
                    Grade grade = studentGrades.get(tempCourse);
                    if(grade != null && grade.getPointValue() >= Grade.C.getPointValue()) {
                        addCourse = false;
                        availableFillerCourses.remove(courseToAdd);
                    }
                }

                
                if(addCourse) {
                    eightSemesterPlan.get(planIndex).primitiveAddCourse(courseToAdd);
                    semesterHours += semesterHoursToAdd;
                }


                if(semesterHours >= 15 && eightSemesterPlan.size() < 8) {
                    eightSemesterPlan.add(new SemesterPlan());
                    semesterHours = 0;
                    planIndex++;
                }
            }
        }
        return eightSemesterPlan;
    }

    public boolean fillCourse(Course fillerCourse) {
        ArrayList<SemesterPlan> eightSemesterPlan = this.generateEightSemesterPlan();
        Keyword applicationAreaKeyword = Keyword.valueOf(this.applicationID.getKeyword().toString());

        for(SemesterPlan semesterPlan : eightSemesterPlan) {
            for(Course loopCourse : semesterPlan.getCourses()) {
                if(loopCourse.getDesignator() == Designator.FILL) {
                    Keyword loopCourseKeyword = loopCourse.getKeywords().get(0);
                    if(!(loopCourseKeyword == Keyword.AP0 && applicationAreaKeyword == Keyword.AP0)) {
                        Keyword comparisonKeyword;
                        if(loopCourseKeyword == Keyword.AP0) {
                            comparisonKeyword = applicationAreaKeyword;
                        } else {
                            comparisonKeyword = loopCourse.getKeywords().get(0);
                        }
                        if(fillerCourse.getKeywords().contains(comparisonKeyword) && this.studentGrades.get(fillerCourse) == null) {
                            this.studentGrades.put(fillerCourse, Grade.R);
                            return true;
                        }
                    } 
                }
            }
        }
        return false;
    }


    public String toString() {
        return super.toString();

    }

    public void reloadAdvisor() {
        Advisor realOne = UserList.getAdvisorByUUID(advisor.userID);
        this.advisor = realOne;
    }

    /*=
     * Getters for major,Year, GPA, SemesterPlans, LegalGuardians, Advisor, Notes, Honors, Scholarships, Applicarion ID, and Student Grades
     */
    public Major getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public double getGpa() {
        return gpa;
    }

    public ArrayList<SemesterPlan> getSemesterPlans() {
        return semesterPlans;
    }

    public ArrayList<LegalGuardian> getLegalGuardians() {
        return legalGuardians;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public boolean getHonors() {
        return isHonors;
    }

    public boolean getHasScholarship() {
        return hasScholarship;
    }

    public ApplicationID getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(ApplicationID applicationID) {
        this.applicationID = applicationID;
    }

    public HashMap<Course, Grade> getStudentGrades() {
        return studentGrades;
    }

    public void displayProgress() {
        int requirementsProgress = 0;
        int total = major.getMajorRequirements().size();
        ArrayList<String> completedRequirements = new ArrayList<>();
        ArrayList<String> incompleteRequirements = new ArrayList<>();
        for (MajorRequirement majorRequirement : major.getMajorRequirements()) {
            if (majorRequirement.SatisfiesRequirement(this)) {
                requirementsProgress++;
                completedRequirements.add(majorRequirement.getTitle());
            } else {
                incompleteRequirements.add(majorRequirement.getTitle());
            }
        }
        System.out.println("Total Requirements Met: " + requirementsProgress + "/" + total);
        System.out.println("Completed: ");
        for (String string : completedRequirements) {
            System.out.println(string);
        }
        System.out.println("Courses taken: ");
        for (Course course : studentGrades.keySet()) {
            System.out.println(course.getDesignator() + " " + course.getNumber() + ": " +  studentGrades.get(course));
        }
        System.out.println("Selected Application Area: " + applicationID.getName());
    }

    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    public ArrayList<Course> filterAvailableCourses(ArrayList<Course> availableCourses) {
        ArrayList<Course> returnAvailableCourses = new ArrayList<Course>();
        for(Course course : availableCourses) {
            if(studentGrades.get(course) == null) {
                returnAvailableCourses.add(course);
            }
        }
        return returnAvailableCourses;
    }
}
