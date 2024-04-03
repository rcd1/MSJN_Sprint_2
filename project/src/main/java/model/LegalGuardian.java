package model;
import java.util.ArrayList;
import java.util.UUID;

public class LegalGuardian extends User{
    
    private ArrayList<Student> students;

    public LegalGuardian(String firstName, String lastName, String email, String password, UUID userID) {
        super(firstName, lastName, email, password, userID);
    }
    
    public void removeStudent(Student student) {
        
    }

    @Override
    protected void viewStudentProfile(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewStudentProfile'");
    }
}
