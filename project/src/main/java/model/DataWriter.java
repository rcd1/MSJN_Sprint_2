package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriter extends DataConstants {

    // puts user list into JSON files
    public static void saveUsers() {
        saveAdvisors();
        saveStudents();
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_PASSWORD, user.getPassword());

        return userDetails;
    }

    public static void saveAdvisors() {
        UserList userList = UserList.getInstance();
        ArrayList<Advisor> advisors = userList.getAdvisors();
        JSONArray jsonAdvisors = new JSONArray();

        for (int i = 0; i < advisors.size(); i++) {
            jsonAdvisors.add(getAdvisorJSON(advisors.get(i)));

        }
        try (FileWriter file = new FileWriter(ADVISORS_FILE_NAME)) {
            file.write(jsonAdvisors.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getAdvisorJSON(Advisor advisor) {
        JSONObject advisorDetails = addUserInfoToJSONObject(advisor);
        advisorDetails.put(ADVISOR_STUDENTS, buildStudentsJSON(advisor.getStudents()));
        advisorDetails.put(ADVISOR_DEPARTMENT, advisor.getDepartment());
        return advisorDetails;

    }

    private static JSONArray buildStudentsJSON(ArrayList<Student> students) {
        JSONArray arrayStudents = new JSONArray();
        for (Student student : students) {
            arrayStudents.add(student.getUserID().toString());
        }
        return arrayStudents;
    }

    public static void saveStudents() {
        UserList userList = UserList.getInstance();
        ArrayList<Student> students = userList.getStudents();
        JSONArray jsonStudents = new JSONArray();

        for (int i = 0; i < students.size(); i++) {
            jsonStudents.add(getStudentJSON(students.get(i)));
        }

        try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
            file.write(jsonStudents.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // unfinished
    // Student details/info
    public static JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        studentDetails = addUserInfoToJSONObject((User)student);
        studentDetails.put(STUDENT_MAJOR, student.getMajor().getMajorid().toString());
        studentDetails.put(STUDENT_YEAR, student.getYear());
        studentDetails.put(STUDENT_GPA, student.getGpa());
        studentDetails.put(STUDENT_SEMESTER_PLANS, buildSemesterPlansJSON(student.getSemesterPlans()));
        studentDetails.put(STUDENT_LEGAL_GUARDIANS, buildLegalGuardiansJSON(student.getLegalGuardians()));
        studentDetails.put(STUDENT_ADVISOR, student.getAdvisor().getUserID().toString());
        studentDetails.put(STUDENT_NOTES, buildNotesJSON(student.getNotes()));
        studentDetails.put(STUDENT_IS_HONORS, student.getHonors());
        studentDetails.put(STUDENT_HAS_SCHOLARSHIP, student.getHasScholarship());
        studentDetails.put(STUDENT_GRADES, buildStudentGradeJSON(student.getStudentGrades()));
        studentDetails.put(STUDENT_APPLICATION_ID, student.getApplicationID().getNumber());

        return studentDetails;
        

    }

    private static JSONObject addUserInfoToJSONObject(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        return userDetails;

    }


    private static JSONArray buildStudentGradeJSON(HashMap<Course, Grade> studentGrades) {
        JSONArray studentGradesArray = new JSONArray();
        for (Course course : studentGrades.keySet()) {
            JSONObject objectStudentGrade = new JSONObject();
            objectStudentGrade.put(STUDENT_GRADES_COURSE_ID, course.getCourseID().toString());
            objectStudentGrade.put(STUDENT_GRADES_GRADE, studentGrades.get(course).toString());
            studentGradesArray.add(objectStudentGrade);
        }
        return studentGradesArray;
    }

    private static JSONArray buildLegalGuardiansJSON(ArrayList<LegalGuardian> legalGuardians) {
        JSONArray guardiansArray = new JSONArray();
        for (LegalGuardian legalGuardian : legalGuardians) {
            guardiansArray.add(legalGuardian.getUserID().toString());
        }
        return guardiansArray;
    }

    private static JSONArray buildNotesJSON(ArrayList<String> notes) {
        JSONArray notesArray = new JSONArray();
        for (String string : notes) {
            notesArray.add(string);  
        }
        return notesArray;
    }

    // Course Details/Info
    public static void saveCourses() {
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> courseList = courses.getCourses();
        JSONArray jsonCourses = new JSONArray();

        for (int i = 0; i < courseList.size(); i++) {
            jsonCourses.add(getCourseJSON(courseList.get(i)));
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from the given Course object.
     *
     * @param course the Course object to create the JSONObject from
     * @return the JSONObject created from the Course object
     */
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getCourseID().toString());
        courseDetails.put(COURSE_DESIGNATOR, course.getDesignator().toString());
        courseDetails.put(COURSE_NUMBER, course.getNumber());
        courseDetails.put(COURSE_HOURS, course.getHours());
        courseDetails.put(COURSE_PREREQUISITES, buildCourseRequisitesJSON(course.getPrerequisites()));
        courseDetails.put(COURSE_COREQUISITES, buildCourseRequisitesJSON(course.getCorequisites()));
        courseDetails.put(COURSE_KEYWORDS, buildKeywordsJSON(course.getKeywords()));
        courseDetails.put(COURSE_SEMESTERS_OFFERED, buildSemestersOfferedJSON(course.getSemestersOffered()));
        courseDetails.put(COURSE_PREFERRED_SEMESTER, course.getPreferredSemester());
        courseDetails.put(COURSE_NAME, course.getName());
        courseDetails.put(COURSE_DESCRIPTION, course.getDescription());
        return courseDetails;
    }

    private static Object buildSemestersOfferedJSON(ArrayList<SemesterOffered> semestersOffered) {
        JSONArray semestersArray = new JSONArray();
        for (SemesterOffered semesterOffered : semestersOffered) {
            semestersArray.add(semesterOffered.toString());
        }
        return semestersArray;
    }

    private static Object buildKeywordsJSON(ArrayList<Keyword> keywords) {
        JSONArray keywordsArray = new JSONArray();
        for (Keyword keyword : keywords) {
            keywordsArray.add(keyword.toString());
        }
        return keywordsArray;
    }

    private static JSONArray buildCourseRequisitesJSON(ArrayList<RequirementSet> prerequisites) {
        JSONArray requisiteArray = new JSONArray();
        for (RequirementSet requirementSet : prerequisites) {
            JSONObject objectPrerequisite = new JSONObject();
            objectPrerequisite.put(REQUIREMENT_SET_COURSES, buildCourseSetJSON(requirementSet.getRequiredCourses()));
            objectPrerequisite.put(REQUIREMENT_SET_GRADE, requirementSet.getRequiredGrade().toString());
            if (requirementSet instanceof AndRequirement) {
                objectPrerequisite.put(REQUIREMENT_SET_MODE, "a");
            } else if (requirementSet instanceof OrRequirement) {
                objectPrerequisite.put(REQUIREMENT_SET_MODE, "o");
            }
            requisiteArray.add(objectPrerequisite);
        }
        return requisiteArray;
    }

    private static JSONArray buildCourseSetJSON(ArrayList<Course> requiredCourses) {
        JSONArray courseArray = new JSONArray();
        for (Course course : requiredCourses) {
            courseArray.add(course.getCourseID().toString());
        }
        return courseArray;

    }

    public static void saveMajors() {
        MajorList majorList = MajorList.getInstance();
        ArrayList<Major> majors = majorList.getMajors();
        JSONArray jsonMajors = new JSONArray();

        for (int i = 0; i < majors.size(); i++) {
            jsonMajors.add(getMajorJSON(majors.get(i)));
        }

        try (FileWriter file = new FileWriter(MAJOR_FILE_NAME)) {
            file.write(jsonMajors.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getMajorJSON(Major major) {
        JSONObject majorDetails = new JSONObject();
        majorDetails.put(MAJOR_ID, major.getMajorid().toString());
        majorDetails.put(MAJOR_NAME, major.getMajorName().toString());
        majorDetails.put(MAJOR_REQUIREMENTS, buildMajorRequirementsJSON(major.getMajorRequirements()));
        majorDetails.put(MAJOR_RECOMMENDED_SEMESTER_PLANS, buildSemesterPlansJSON(major.getRecommendedSemesterPlans()));

        return majorDetails;
    }

    private static JSONArray buildSemesterPlansJSON(ArrayList<SemesterPlan> recommendedSemesterPlans) {
        JSONArray bigArray = new JSONArray();
        for (SemesterPlan semesterPlan : recommendedSemesterPlans) {
            JSONArray courseArray = buildCourseSetJSON(semesterPlan.getCourses());
            bigArray.add(courseArray);
        }
        return bigArray;
    }

    private static JSONArray buildMajorRequirementsJSON(ArrayList<MajorRequirement> majorRequirements) {
        JSONArray majorRequirementDetails = new JSONArray();
        for (MajorRequirement majorRequirement : majorRequirements) {
            JSONObject objectMajorReq = new JSONObject();
            objectMajorReq.put(MAJOR_REQUIREMENT_TITLE, majorRequirement.getTitle());
            objectMajorReq.put(MAJOR_REQUIREMENT_MINHOURS, majorRequirement.getMinHours());
            objectMajorReq.put(MAJOR_REQUIREMENT_MAXHOURS, majorRequirement.getMaxHours());
            objectMajorReq.put(MAJOR_REQUIREMENT_ACCEPTABLE_COURSE_SETS,
                    buildAcceptableCourseSetsJSON(majorRequirement.getAcceptableCourseSets()));
            majorRequirementDetails.add(objectMajorReq);
        }
        return majorRequirementDetails;
    }

    private static JSONArray buildAcceptableCourseSetsJSON(ArrayList<RequirementSet> acceptableCourseSets) {
        JSONArray requirementSetArray = new JSONArray();
        for (RequirementSet requirementSet : acceptableCourseSets) {
            JSONObject objectRequirementSet = new JSONObject();
            objectRequirementSet.put(REQUIREMENT_SET_TITLE, requirementSet.getTitle());
            objectRequirementSet.put(REQUIREMENT_SET_COURSES, buildCourseSetJSON(requirementSet.getRequiredCourses()));
            objectRequirementSet.put(REQUIREMENT_SET_GRADE, requirementSet.getRequiredGrade().toString());
            if (requirementSet instanceof AndRequirement) {
                objectRequirementSet.put(REQUIREMENT_SET_MODE, "a");
            } else if (requirementSet instanceof OrRequirement) {
                objectRequirementSet.put(REQUIREMENT_SET_MODE, "o");
            }
            requirementSetArray.add(objectRequirementSet);
        }
        return requirementSetArray;
    }
}
