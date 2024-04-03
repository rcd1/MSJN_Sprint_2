package model;
public abstract class DataConstants {
    /*---------------------------User--------------------------*/
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_ID = "userId";
    protected static final String USER_USER_NAME = "userName";
    /*-------------------------Student-------------------------*/
    protected static final String STUDENT_FILE_NAME = "project\\src\\main\\java\\data\\students.json";
    protected static final String STUDENT_MAJOR = "major";
    protected static final String STUDENT_YEAR = "year";
    protected static final String STUDENT_GPA = "gpa";
    protected static final String STUDENT_SEMESTER_PLANS = "semesterPlans";
    protected static final String STUDENT_LEGAL_GUARDIANS = "legalGuardians";
    protected static final String STUDENT_ADVISOR = "advisorId";
    protected static final String STUDENT_NOTES = "notes";
    protected static final String STUDENT_IS_HONORS = "isHonors";
    protected static final String STUDENT_HAS_SCHOLARSHIP = "hasScholarship";
    protected static final String STUDENT_GRADES = "studentGrades";
    protected static final String STUDENT_APPLICATION_ID = "applicationID";
    protected static final String STUDENT_GRADES_COURSE_ID = "courseId";
    protected static final String STUDENT_GRADES_GRADE = "grade";
    /*-------------------------Advisor-------------------------*/
    protected static final String ADVISORS_FILE_NAME = "project\\src\\main\\java\\data\\advisors.json";
    protected static final String ADVISOR_STUDENTS = "students";
    protected static final String ADVISOR_STUDENT_ID = "studentId";
    protected static final String ADVISOR_DEPARTMENT = "department";

    /*--------------------------Major--------------------------*/
    protected static final String MAJOR_FILE_NAME = "project\\src\\main\\java\\data\\majors.json";
    protected static final String MAJOR_ID = "majorID";
    protected static final String MAJOR_NAME = "majorName";
    protected static final String MAJOR_REQUIREMENTS = "majorRequirements";
    protected static final String MAJOR_RECOMMENDED_SEMESTER_PLANS = "recommendedSemesterPlans";

    /*--------------------------MajorRequirement--------------------------*/
    protected static final String MAJOR_REQUIREMENT_TITLE = "title";
    protected static final String MAJOR_REQUIREMENT_MINHOURS = "minHours";
    protected static final String MAJOR_REQUIREMENT_MAXHOURS = "maxHours";
    protected static final String MAJOR_REQUIREMENT_ACCEPTABLE_COURSE_SETS = "acceptableCourseSets";


    /*--------------------------RequirementSet--------------------------*/
    protected static final String REQUIREMENT_SET_TITLE = "title";
    protected static final String REQUIREMENT_SET_COURSES = "requiredCourses";
    protected static final String REQUIREMENT_SET_GRADE = "requiredGrade";
    protected static final String REQUIREMENT_SET_MODE = "mode";   

    /*-----------------------------Course-----------------------------*/
    protected static final String COURSE_FILE_NAME = "project\\src\\main\\java\\data\\courses.json";
    protected static final String COURSE_ID = "id";
    protected static final String COURSE_DESIGNATOR = "designator";
    protected static final String COURSE_NUMBER = "number";
    protected static final String COURSE_HOURS = "hours";
    protected static final String COURSE_PREREQUISITES = "prerequisites";
    protected static final String COURSE_COREQUISITES = "corequisites";
    protected static final String COURSE_KEYWORDS = "keywords";
    protected static final String COURSE_SEMESTERS_OFFERED = "semestersOffered";
    protected static final String COURSE_NAME = "name";
    protected static final String COURSE_DESCRIPTION = "description";
    protected static final String COURSE_PREFERRED_SEMESTER = "preferredSemester";


}