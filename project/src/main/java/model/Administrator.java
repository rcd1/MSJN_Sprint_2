package model;
import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends User{
    /**
     * The administrator has an array list of both students and advisors.
     */
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    
    /**
     * The parameters that the Administrator class inherits from the user.
     * @param firstName the first name of the administrator.
     * @param lastName the last name of the administrator.
     * @param email the email of the administrator.
     * @param password the password of the administrator.
     * @param userID the user ID of the administrator. 
     */
    public Administrator (String firstName, String lastName, String email, String password, UUID userID) {
        super(firstName, lastName, email, password, userID);
    }

    /**
     * 
     * @param student
     * @param course
     * @return
     */
    public boolean addCourseToStudent(Student student, Course course) {
        return true;
    }

    public boolean removeCourseFromStudent(Student student, Course course) {
        return true;
    }

    public boolean addStudentGrade(Student student, Course course, Grade grade) {
        return true;

    }

    private boolean updateStudentGrade(Student student, Course course, Grade grade) {
        return true;
    }

    @Override
    protected void viewStudentProfile(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewStudentProfile'");
    }
}
