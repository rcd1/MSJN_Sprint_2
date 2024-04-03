package model;
import java.util.UUID;
/**
 * The class that the UserList will go to find the student and advisor loginning
 * Also the clas the student and advisor will be inheriting
 */
public abstract class User {
    protected String firstName, lastName, email, password;
    protected UUID userID;

    //New students will be given a blank advisor with this ID, we replace student's advisor with a new Advisor if we see this.
    protected static final String BLANK_ADVISOR_ID = "5581ca17-2ddf-4f52-a083-899869f4b5c0";
    protected static final String BLANK_MAJOR_ID = "0273bd9e-bf37-4697-887d-9c176cdeb413";

    public User(UUID userID) {
        this.userID = userID;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
    }

    /**
     * The mutator (setter) method for the User class
     * @param firstName User's first name 
     * @param lastName User's last name
     * @param email User's email
     * @param password User's password
     * @param userID User's userID
     */
    public User(String firstName, String lastName, String email, String password, UUID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    /**
     * An accessor (getter) class for the User's first name
     * @return User's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * An accessor (getter) class for the User's last name
     * @return User's last name
     */
    public String getLastName() {
        return lastName;
    }

     /**
     * An accessor (getter) class for the User's last name
     * @return User's UserID
     */
    public UUID getUserID() {
        return userID;
    }

     /**
     * An accessor (getter) class for the User's email
     * @return User's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * An accessor method of the user's password 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    private void resetPassword(String newPassword) {
        
    }

    /**
     * Allow
     * @param student the usertype profile that the student, advisor, 
     * and administrator can view
     */
    protected abstract void viewStudentProfile(Student student);

    /**
     * Used by UserList, checks if any user has these parameters
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email
     * @return true if this User contains these values, false otherwise
     */
    public boolean checkCredentials(String firstName, String lastName, String email) {
        return this.firstName.equalsIgnoreCase(firstName) && this.lastName.equalsIgnoreCase(lastName) && this.email.equalsIgnoreCase(email);
    }

    public String toString() {
        return "First Name: " + firstName 
        + "\nLast Name: " + lastName
        + "\nEmail: " + email
        + "\nPassword: " + password;
    }
}
