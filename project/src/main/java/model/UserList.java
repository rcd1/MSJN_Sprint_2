package model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Attributes of the UserList
 * It has an array list of students and advisors
 */
public class UserList {
    private static UserList userList;
    private static ArrayList<Student> students;
    private static ArrayList<Advisor> advisors;

    /**
     * Method for when the user list is access. It will either load/reload students or advisors
     */
    public UserList() {
        userList = this;
        students = DataLoader.getStudents();
        advisors = DataLoader.getAdvisors();
        reloadStudentsInAdvisors();
        reloadAdvisorInStudents();
    }


    /**
     * A method that gets an instance of the User List 
     * @return User List 
     */
    public static UserList getInstance() {
        if (userList == null) userList = new UserList();
        return userList;
    } 

    /**
     * An array use to compared the users email and password typed in to the actual one saved in the User List for them to login
     * @param email the email that will be compared to the acutal email
     * @param password the password that will be compared to the actual password
     * @return the user if the email and password matches
     */
    public User getUser(String email, String password) {
        for (Student student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        for (Advisor advisor : advisors) {
            if (advisor.getEmail().equals(email) && advisor.getPassword().equals(password)) {
                return advisor;
            }
        }
        return null;
    }

    // To be removed
    public void addUser(User newUser) {
        
        if (newUser instanceof Student) {
            students.add((Student) newUser);
        } else if (newUser instanceof Advisor) {
            advisors.add((Advisor) newUser);
        } else {
            throw new IllegalArgumentException("Unsupported user type: " + newUser.getClass().getName());
        }
    }

    //Could be improved by using methods inside the enum like in state design pattern
    public User addUser(String firstName, String lastName, String email, String password, UserType type) {
        User user = null;
        UUID uuid = UUID.randomUUID();

        switch(type) {
            case STUDENT: 
                user = new Student(firstName, lastName, email, password, uuid);
                students.add((Student) user);
                break;
            case ADVISOR:
                user = new Advisor(firstName, lastName, email, password, uuid);
                advisors.add((Advisor) user);
                break;
        }
        return user;
    }

    
    /**
     * gets the students from an array list of type Student
     * @return students
     */
    public static ArrayList<Student> getStudents() {
        return students;
    }


    /**
     * gets the advisors from an array list of type Advisor
     * @return students
     */
    public static ArrayList<Advisor> getAdvisors() {
        return advisors;
    }




    /*-----------------------------------DATALOADER STUFF------------------------------------*/

     /**
     * Helper method to turn empty Advisor Objects into the real stuff
     */
    private void reloadAdvisorInStudents() {
        for (Student student : students) {
            student.reloadAdvisor();
        }
    }
    public static Advisor getAdvisorByUUID(UUID userID) {
        for (Advisor advisor : advisors) {
            if (userID.equals(advisor.getUserID())) return advisor;
        }
        System.out.println("Error in UserList.getAdvisorByUUID(): Unable to find advisor with UUID " + userID);
        return null;
    }

    /**
     * Helper method that turns the Blank Student objects in the Advisor's list into the real deal
     */
    private void reloadStudentsInAdvisors() {
        for (Advisor advisor : advisors) {
            advisor.reloadStudents();
        }
    }

    public static Student getStudentByUUID(UUID userID) {
        for (Student student : students) {
            if (userID.equals(student.getUserID())) return student;
        }
        System.out.println("Error in UserList.getStudentByUUID(): Unable to find student with UUID " + userID);
        return null;
    }

    /**
     * The data writer will save the users information after they logout
     */
    public void logout() {
        DataWriter.saveUsers();
    }


    public Student findStudentByName(String firstName, String lastName) {
        for (Student student : students) {
            if (student.firstName.toLowerCase().equals(firstName) && student.lastName.toLowerCase().equals(lastName)) {
                return student;
            }
        }
        System.out.println("UserList unable to find student with name " + firstName + " " + lastName);
        return null;
    }
}
