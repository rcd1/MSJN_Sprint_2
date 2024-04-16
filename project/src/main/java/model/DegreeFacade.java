package model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This facade class can be considered the central hub of the program
 * 
 */

/**
 * The attributes of the DegreeFacade class
 */
public class DegreeFacade {
    private UserList userList;
    private User user;
    private CourseList courseList;
    private MajorList majorList;
    private static DegreeFacade degreeFacade;

    /**
     * An method to get every instance of a userList, courseList, and majorList
     */
    private DegreeFacade() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        majorList = MajorList.getInstance();
    }

    public static DegreeFacade getInstance() {
        if(degreeFacade == null) {
            degreeFacade = new DegreeFacade();
        }
        return degreeFacade;            

    }

    /**
     * An method for when the user is getting into the system
     * 
     * @param email    the email of the user
     * @param password the password of the user
     * @return the user based of their email and password after checking
     *         to see whether their inputs were 1:1 the same based on the userlist.
     */
    public User login(String email, String password) {
        return user = userList.getUser(email, password);
    }

    /**
     * The user will become null once they log out of the system
     */
    public void logout() {
        user = null;
        courseList.logout();
        userList.logout();
        majorList.logout();
    }

    /*
     * public void addCourseToStudent(User user, Course course) {
     * user.addCourseToStudent(student, course);
     * }
     * 
     * public void removeCourseFromStudent(Administrator administrator, Course
     * course) {
     * administrator.removeCourseFromStudent(administrator, course);
     * }
     */

    /**
     * A method for when the student decides to add a course
     * 
     * @param student the person adding the course
     * @param course  the course that the student is adding
     */
    public void addCourseToSemsterPlan(Student student, Course course) {
        student.addCourseToSemsterPlan(course);
    }

    /**
     * A method for when the student decides to remove a course
     * 
     * @param student the person removing the course
     * @param course  the course that the student is removing
     */
    public void removeCourseFromSemesterPlan(Student student, Course course) {
        student.removeCourseFromSemesterPlan(course);
    }

    /**
     * A nmethod used to create a new account for the student
     * 
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param email     the user's email
     * @param password  the user's password
     * @param type      the user's type
     * @return the user based on its parameters
     */
    public User createAccount(String firstName, String lastName, String email, String password, UserType type) {
        return user = userList.addUser(firstName, lastName, email, password, type);
    }

    /**
     * A methods that grabs all the courses from the array list of type course
     * 
     * @return all the courses from the array list of courses
     */
    public ArrayList<Course> getAllCourses() {
        return courseList.getCourses();
    }

    /**
     * A method used to search for courses from an arrayList of type Course
     * 
     * @param keyword searches for the student
     * @return courses from the array list based on the keywords
     */
    public ArrayList<Course> findCourses(String keyword) {
        return courseList.findCourses(keyword);
    }

    /**
     * An accessor(getter) method for a course from an ArrayList of type Course
     * 
     * @param courseID The ID of the course
     * @return the course based on its ID
     */
    public ArrayList<Course> getCourseByUUID(UUID courseID) {
        return getCourseByUUID(courseID);
    }

    /**
     * An accessor (getter) method for the student's major
     * 
     * @param majorName the name of the major
     * @return name of the major from the list
     */
    public Major getMajor(String majorName) {
        return majorList.getMajor(majorName);
    }

    /**
     * A method for displaying the semester plan of the student
     * 
     * @param student the user generating their own semester plan
     */
    public void displaySemesterPlan(Student student) {
        student.displaySemesterPlan();
    }

    public void exit() {
        courseList.logout();
        userList.logout();
        majorList.logout();
    }

    public void displayAdvisorStudentsList(Advisor advisor) {
        advisor.displayStudents();
    }

    public void addStudentToAdvisorList(Advisor advisor, String firstName, String lastName) {
        Student studentToAdd = userList.findStudentByName(firstName, lastName);
        if (studentToAdd != null) {
            advisor.addStudent(studentToAdd);
        }
    }

    public void viewStudentProgress(Student student) {
        student.displayProgress();
    }

    public void saveSemesterPlanToFile(User user, String fileName) {
        if (user instanceof Student) {
            ((Student)user).saveSemesterPlanToFile(fileName);
        } else if (user instanceof Advisor) {
            ArrayList<Student> advisedStudents = ((Advisor) user).getStudents();
            for (Student student : advisedStudents) {
            ArrayList<SemesterPlan> semesterPlans = student.getSemesterPlans();
                for (SemesterPlan semesterPlan : semesterPlans) {
                    semesterPlan.saveSemesterPlanToFile(user, fileName);
                }
            }
        }      
    }

    public void addNote(Student student, String title, String note) {
        student.addNote(new Note(title, note));
    }
}