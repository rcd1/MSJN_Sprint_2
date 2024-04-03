package model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The advisor class has an arrayList of students
 */
public class Advisor extends User {
    private ArrayList<Student> students;
    private String department;

    /**
     * The Advisor class is inheriting from parameters from the user.
     * 
     * @param firstName The first name of the advisor.
     * @param lastName  The last name of the advisor.
     * @param email     The email name of the advisor.
     * @param password  The password of the advisor.
     * @param userID    The userID of the advisor.
     */

    public Advisor(UUID userID) {
        super(userID);
        this.students = new ArrayList<>();
        this.department = "";
    }

    public Advisor(String firstName, String lastName, String email, String password, UUID userID) {
        super(firstName, lastName, email, password, userID);
        students = new ArrayList<>();
        this.department = "";
    }

    public Advisor(String firstName, String lastName, String email, String password, UUID userID,
            ArrayList<Student> students, String department) {
        super(firstName, lastName, email, password, userID);
        this.students = students;
        this.department =department;
    }

    /**
     * gets the students the advisor is advising from an arraylist of class Student.
     * 
     * @return the students the advisor is advising.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * a boolean method that adds the student to the array list.
     * 
     * @param student the advisee
     * @return true
     */
    public boolean addStudent(Student student) {
        student.setStudentAdvisor(this);
        return students.add(student);
    }

    /**
     * a boolean method that removes the student to the array list.
     * 
     * @param student the advisee
     * @return true
     */
    public boolean removeStudent(Student student) {
        return true;
    }

    public void addNote(Student student, String note) {

    }

    public void addAdvisor(Student student) {

    }

    @Override
    protected void viewStudentProfile(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewStudentProfile'");
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void reloadStudents() {
        ArrayList<Student> temp = new ArrayList<>();
        for (Student student : students) {
            temp.add(UserList.getStudentByUUID(student.getUserID()));
        }
        this.students = temp;
    }

    public String toString() {
        return super.toString();
    }

    public String getDepartment() {
        return department;
    }

    public void viewStudentSemesterPlan(Student student) {
        ArrayList<SemesterPlan> semesterPlans = student.getSemesterPlans();
        if (semesterPlans != null && !semesterPlans.isEmpty()) {
            System.out.println("Semester plans for " + student.getFirstName() + " " + student.getLastName() + ":");
            for (SemesterPlan semesterPlan : semesterPlans) {
                System.out.println("Semester Plan:");
                semesterPlan.displaySemesterPlan();
            }
        } else {
            System.out.println("No semester plans found for " + student.getFirstName() + " " + student.getLastName() + ":");
        }
    }

    public void displayStudents() {
        String studentToString = "";
        for (int i = 0; i < students.size(); i++) {
            studentToString += (i+1) + ". " + students.get(i).getFirstName() + " " + students.get(i).getLastName() + "\n";
        }
        System.out.println(studentToString);
    }
}
