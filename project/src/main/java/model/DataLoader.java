package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    /*--------------------------------FOR STUDENTS--------------------------------*/
    /**
     * Convert students.json into an ArrayList<Students>
     * 
     * @return the list of all saved students
     */
    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsJSON = (JSONArray) parser.parse(reader);

            for (Object i : studentsJSON) {
                JSONObject studentJSONObject = (JSONObject) i;
                UUID userID = UUID.fromString((String) studentJSONObject.get(USER_ID));
                String firstName = (String) studentJSONObject.get(USER_FIRST_NAME);
                String lastName = (String) studentJSONObject.get(USER_LAST_NAME);
                String email = (String) studentJSONObject.get(USER_EMAIL);
                String password = (String) studentJSONObject.get(USER_PASSWORD);
                Major major = rebuildMajor((String) studentJSONObject.get(STUDENT_MAJOR));
                int year = ((Long) studentJSONObject.get(STUDENT_YEAR)).intValue();
                double gpa = ((double) studentJSONObject.get(STUDENT_GPA));
                ArrayList<SemesterPlan> semesterPlans = rebuildSemesterPlans(
                        (JSONArray) studentJSONObject.get(STUDENT_SEMESTER_PLANS));
                ArrayList<LegalGuardian> legalGuardians = rebuildLegalGuardians(
                        (JSONArray) studentJSONObject.get(STUDENT_LEGAL_GUARDIANS));
                Advisor advisor = rebuildAdvisor((String) studentJSONObject.get(STUDENT_ADVISOR));
                ArrayList<String> notes = rebuildNotes((JSONArray) studentJSONObject.get(STUDENT_NOTES));
                boolean isHonors = ((boolean) studentJSONObject.get(STUDENT_IS_HONORS));
                boolean hasScholarship = ((boolean) studentJSONObject.get(STUDENT_HAS_SCHOLARSHIP));
                HashMap<Course, Grade> studentGrades = rebuildStudentGrades(
                        (JSONArray) studentJSONObject.get(STUDENT_GRADES));
                Long ApplicationIDNumber = ((Long) studentJSONObject.get(STUDENT_APPLICATION_ID));

                ApplicationID applicationID = ApplicationID.getApplicationIDByNumber(ApplicationIDNumber.intValue());

                students.add(new Student(firstName, lastName, email, password, userID, major, year, gpa, semesterPlans,
                        legalGuardians, advisor, notes, isHonors, hasScholarship, studentGrades, applicationID));
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create the Student Grades HashMap from JSON
     * 
     * @param jsonArray the array containing pairs of Course UUIDs and Grade values
     * @return a completed HashMap<Course, Grade>
     */
    private static HashMap<Course, Grade> rebuildStudentGrades(JSONArray jsonArray) {
        HashMap<Course, Grade> studentGrades = new HashMap<>();
        CourseList courseList = CourseList.getInstance();
        for (Object i : jsonArray) {
            JSONObject jsonObject = (JSONObject) i;
            Course course = courseList
                    .getCourseByUUID(UUID.fromString((String) jsonObject.get(STUDENT_GRADES_COURSE_ID)));
            Grade grade = Grade.valueOf((String) jsonObject.get(STUDENT_GRADES_GRADE));
            studentGrades.put(course, grade);
        }
        return studentGrades;
    }

    /**
     * Create the ArrayList<Notes> from JSON
     * 
     * @param jsonArray the array containing a list of Strings
     * @return a completed ArrayList<String>
     */
    private static ArrayList<String> rebuildNotes(JSONArray jsonArray) {
        ArrayList<String> notes = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            notes.add((String) jsonArray.get(i));
        }
        return notes;
    }

    /**
     * Create A Blank version of the Student's Advisor stored in JSON
     * 
     * @param theIDString the String representing the ID of the Advisor stored in
     *                    advisors.json
     * @return A Blank Advisor object containing only the UUID
     */
    private static Advisor rebuildAdvisor(String theIDString) {
        UUID advisorID = UUID.fromString(theIDString);
        return new Advisor(advisorID);
    }

    /**
     * Will not be implmented this Sprint, but would create the list of
     * LegalGuardians stored in students.json
     * 
     * @param jsonArray TBD
     * @return a blank ArrayList for now
     */
    private static ArrayList<LegalGuardian> rebuildLegalGuardians(JSONArray jsonArray) {
        return new ArrayList<LegalGuardian>();
    }

    /**
     * Create the list of courses the student will take each semester as stored in
     * students.json
     * 
     * @param jsonArray an array of arrays of strings, the string representing UUIDs
     *                  for courses in courses.json
     * @return a completed ArrayList
     */
    private static ArrayList<SemesterPlan> rebuildSemesterPlans(JSONArray jsonArray) {
        ArrayList<SemesterPlan> semesterPlans = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            ArrayList<Course> courses = rebuildCoursesByUUIDs((JSONArray) jsonArray.get(i));
            semesterPlans.add(new SemesterPlan(courses));
        }
        return semesterPlans;
    }

    /**
     * Create the full major object based on the ID stored in students.json
     * 
     * @param theIDString a String representing the id of a major stored in
     *                    majors.json
     * @return the full Major object
     */
    private static Major rebuildMajor(String theIDString) {
        MajorList majorList = MajorList.getInstance();
        UUID majorID = UUID.fromString(theIDString);
        return majorList.getMajorByUUID(majorID);
    }

    /*----------------------------------------------------------------------------*/

    /*----------------------------FOR ADVISORS------------------------------------*/
    /**
     * Convert advisors.json into a completed ArrayList<Advisor>
     * 
     * @return an ArrayList
     */
    public static ArrayList<Advisor> getAdvisors() {
        ArrayList<Advisor> advisors = new ArrayList<>();
        try {
            FileReader reader = new FileReader(ADVISORS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorsJSON = (JSONArray) parser.parse(reader);
            for (Object i : advisorsJSON) {
                JSONObject advisorJsonObject = (JSONObject) i;
                UUID advisorID = UUID.fromString((String) advisorJsonObject.get(USER_ID));
                String firstName = (String) advisorJsonObject.get(USER_FIRST_NAME);
                String lastName = (String) advisorJsonObject.get(USER_LAST_NAME);
                String email = (String) advisorJsonObject.get(USER_EMAIL);
                String password = (String) advisorJsonObject.get(USER_PASSWORD);
                ArrayList<Student> students = rebuildStudents((JSONArray) advisorJsonObject.get(ADVISOR_STUDENTS));
                String department = (String) advisorJsonObject.get(ADVISOR_DEPARTMENT);
                advisors.add(new Advisor(firstName, lastName, email, password, advisorID, students, department));
            }
            return advisors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create the list of the advisor's advisees/students as stored in advisors.json
     * 
     * @param jsonArray an array of Strings representing the IDs of students stored
     *                  in student.json
     * @return an ArrayList of Student objects containing only the UUIDs of those
     *         students (they will be completed properly in UserList)
     */
    private static ArrayList<Student> rebuildStudents(JSONArray jsonArray) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            UUID studentID = UUID.fromString((String) jsonArray.get(i));
            students.add(new Student(studentID));
        }
        return students;
    }

    /*----------------------------------------------------------------------------*/

    /*----------------------------------FOR COURSES-------------------------------*/
    /**
     * Convert courses.json into an ArrayList<Course>
     * 
     * @return a list containing all stored courses
     */
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray coursesJSON = (JSONArray) parser.parse(reader);
            for (Object i : coursesJSON) {
                JSONObject courseJSONObject = (JSONObject) i;
                UUID courseId = UUID.fromString((String) courseJSONObject.get(COURSE_ID));
                Designator designator = Designator.valueOf((String) courseJSONObject.get(COURSE_DESIGNATOR));
                String number = (String) courseJSONObject.get(COURSE_NUMBER);
                Long hoursTemp = ((Long) courseJSONObject.get(COURSE_HOURS));
                int hours = hoursTemp.intValue();
                ArrayList<RequirementSet> prerequisites = rebuildCourseRequirements(
                        (JSONArray) courseJSONObject.get(COURSE_PREREQUISITES));
                ArrayList<RequirementSet> corequisites = rebuildCourseRequirements(
                        (JSONArray) courseJSONObject.get(COURSE_COREQUISITES));
                ArrayList<Keyword> keywords = rebuildKeywords((JSONArray) courseJSONObject.get(COURSE_KEYWORDS));
                ArrayList<SemesterOffered> semestersOffered = rebuildSemestersOffered(
                        (JSONArray) courseJSONObject.get(COURSE_SEMESTERS_OFFERED));
                String name = (String) courseJSONObject.get(COURSE_NAME);
                String description = (String) courseJSONObject.get(COURSE_DESCRIPTION);
                Long preferredSemesterTemp = ((Long) courseJSONObject.get(COURSE_PREFERRED_SEMESTER));
                int preferredSemester = preferredSemesterTemp.intValue();

                courses.add(new Course(courseId, designator, number, hours, prerequisites, corequisites, keywords,
                        semestersOffered, name, description, preferredSemester));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Convert strings in the jsonArray into an ArrayList of Semesters a course is
     * offered
     * 
     * @param jsonArray an array of JSONStrings representing the values of the
     *                  SemesterOffered enum
     * @return a full ArrayList
     */
    private static ArrayList<SemesterOffered> rebuildSemestersOffered(JSONArray jsonArray) {
        ArrayList<SemesterOffered> semestersOffered = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            semestersOffered.add(SemesterOffered.valueOf((String) jsonArray.get(i)));
        }
        return semestersOffered;
    }

    /**
     * Rebuild the RequirementSets for a Course
     * 
     * @param jsonArray an array containing the Requirement set type,
     *                  the list of courses in the RequirementSet,
     *                  and the minimum grade needed to pass any or all courses in
     *                  the list
     * @return an ArrayList that will need to be reloaded in CourseList
     */
    private static ArrayList<RequirementSet> rebuildCourseRequirements(JSONArray jsonArray) {
        ArrayList<RequirementSet> requirements = new ArrayList<>();
        for (Object i : jsonArray) {
            JSONObject requirementJsonObject = (JSONObject) i;
            String mode = (String) requirementJsonObject.get(REQUIREMENT_SET_MODE);
            ArrayList<Course> requiredCourses = fillWithBlankCourses(
                    (JSONArray) requirementJsonObject.get(REQUIREMENT_SET_COURSES));
            Grade grade = Grade.valueOf((String) requirementJsonObject.get(REQUIREMENT_SET_GRADE));
            switch (mode) {
                case "a":
                    requirements.add(new AndRequirement(requiredCourses, grade));
                    break;
                case "o":
                    requirements.add(new OrRequirement(requiredCourses, grade));
                    break;
            }
        }
        return requirements;
    }

    /**
     * For rebuildCourseRequirements, because we're in the middle of loading all
     * Courses
     * and RequirementSets have an ArrayList<Course>, we cannot properly load what
     * doesn't exist yet
     * 
     * @param jsonArray an Array of JSONStrings representing the IDs of courses
     *                  stored in courses.json
     * @return a set of Course objects containing only their UUIDs
     */
    private static ArrayList<Course> fillWithBlankCourses(JSONArray jsonArray) {
        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            UUID courseID = UUID.fromString((String) jsonArray.get(i));
            courses.add(new Course(courseID));
        }
        return courses;
    }

    /**
     * Create the set of Keywords associated with the Course
     * 
     * @param jsonArray an array of JSONStrings representing the KeyWord values
     * @return a completed set of Keywords associated with a course
     */
    private static ArrayList<Keyword> rebuildKeywords(JSONArray jsonArray) {
        ArrayList<Keyword> keywords = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            keywords.add(Keyword.valueOf((String) jsonArray.get(i)));
        }
        return keywords;
    }

    /*-----------------------------FOR MAJORS-------------------------------------*/
    /**
     * Convert Majors.json into a list of Majors
     * 
     * @return and ArrayList of complete Major objects
     */
    public static ArrayList<Major> getMajors() {
        ArrayList<Major> majors = new ArrayList<>();
        try {
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray majorsJSON = (JSONArray) parser.parse(reader);
            for (Object i : majorsJSON) {
                JSONObject majorObject = (JSONObject) i;
                UUID majorid = UUID.fromString((String) majorObject.get(MAJOR_ID));
                String majorName = (String) majorObject.get(MAJOR_NAME);
                ArrayList<MajorRequirement> majorRequirements = rebuildMajorRequirements(
                        (JSONArray) majorObject.get(MAJOR_REQUIREMENTS));
                ArrayList<SemesterPlan> recommendedSemesterPlans = rebuildRecommendedSemesterPlans(
                        (JSONArray) majorObject.get(MAJOR_RECOMMENDED_SEMESTER_PLANS));
                majors.add(new Major(majorid, majorName, majorRequirements, recommendedSemesterPlans));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    /**
     * The Major's version of the Student's rebuildSemesterPlans(),
     * Creates a set of SemesterPlans according to an example path through
     * completing the Major
     * 
     * @param jsonArray an array of JSONStrings representing the IDs of courses
     *                  stored in courses.json
     * @return A completed list of SemesterPlans following an example route through
     *         the Major
     */
    private static ArrayList<SemesterPlan> rebuildRecommendedSemesterPlans(JSONArray jsonArray) {
        ArrayList<SemesterPlan> recommendedSemesterPlans = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            ArrayList<Course> courses = rebuildCoursesByUUIDs((JSONArray) jsonArray.get(i));
            recommendedSemesterPlans.add(new SemesterPlan(courses));
        }
        return recommendedSemesterPlans;
    }

    /**
     * Create an ArrayList of the MajorRequirements specified in majors.json
     * 
     * @param jsonArray an Array of JSONObjects containing:
     *                  the name of the MajorRequirement,
     *                  the minimum and maximum credit hours that will be counted
     *                  for the requirement,
     *                  The sets of Courses and Grades that will satisify this
     *                  requirement
     * @return a Completed ArrayList<MajorRequirement>
     */
    private static ArrayList<MajorRequirement> rebuildMajorRequirements(JSONArray jsonArray) {
        ArrayList<MajorRequirement> majorRequirements = new ArrayList<>();
        for (Object i : jsonArray) {
            JSONObject requirementJSON = (JSONObject) i;
            String title = (String) requirementJSON.get(MAJOR_REQUIREMENT_TITLE);
            Long minTemp = ((Long) requirementJSON.get(MAJOR_REQUIREMENT_MINHOURS));
            int minHours = minTemp.intValue();
            Long maxTemp = ((Long) requirementJSON.get(MAJOR_REQUIREMENT_MAXHOURS));
            int maxHours = maxTemp.intValue();
            ArrayList<RequirementSet> acceptableCourseSets = rebuildRequirementSets(
                    (JSONArray) requirementJSON.get(MAJOR_REQUIREMENT_ACCEPTABLE_COURSE_SETS));
            majorRequirements.add(new MajorRequirement(title, minHours, maxHours, acceptableCourseSets));
        }
        return majorRequirements;
    }

    /**
     * Similar to Course's rebuildCourseRequirements(),
     * 
     * @param jsonArray an array containing the Requirement set type,
     *                  the list of courses in the RequirementSet,
     *                  and the minimum grade needed to pass any or all courses in
     *                  the list
     * @return an ArrayList that will need to be reloaded in CourseList
     */
    private static ArrayList<RequirementSet> rebuildRequirementSets(JSONArray jsonArray) {
        ArrayList<RequirementSet> acceptableCourseSets = new ArrayList<>();
        for (Object i : jsonArray) {
            JSONObject setJSON = (JSONObject) i;
            String title = (String) setJSON.get(REQUIREMENT_SET_TITLE);
            ArrayList<Course> requiredCourses = rebuildCoursesByUUIDs((JSONArray) setJSON.get(REQUIREMENT_SET_COURSES));
            Grade requiredGrade = Grade.valueOf((String) setJSON.get(REQUIREMENT_SET_GRADE));
            String mode = (String) setJSON.get(REQUIREMENT_SET_MODE);
            switch (mode) {
                case "a":
                    acceptableCourseSets.add(new AndRequirement(title, requiredCourses, requiredGrade));
                    break;
                case "o":
                    acceptableCourseSets.add(new OrRequirement(title, requiredCourses, requiredGrade));
                    break;
            }
        }
        return acceptableCourseSets;
    }

    /**
     * The backbone of RequirementSets, take the strings found in the json arrays
     * and turn them into REAL COURSES
     * 
     * @param jsonArray an array of JSONStrings that represent the IDs of courses in
     *                  courses.json
     * @return A truly complete list of Course objects
     */
    private static ArrayList<Course> rebuildCoursesByUUIDs(JSONArray jsonArray) {
        ArrayList<Course> requiredCourses = new ArrayList<>();
        CourseList courseList = CourseList.getInstance();
        for (int i = 0; i < jsonArray.size(); i++) {
            UUID courseID = UUID.fromString((String) jsonArray.get(i));
            requiredCourses.add(courseList.getCourseByUUID(courseID));
        }
        return requiredCourses;
    }
}
