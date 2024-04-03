package model;
import java.util.ArrayList;
import java.util.UUID;
/**
 * Attriutes of Major List 
 */
public class MajorList {
    private static MajorList majorList;
    private static ArrayList<Major> majors;

    /**
     * A setter method for the Major List 
     * The data loader will grab tha major
     */
    public MajorList() {
        majorList = this;
        majors = DataLoader.getMajors();
    }

    /**
     * It will be get an instance of major list
     * If the major list is empty, then a new majorLisst will be generated
     * @return major list
     */
    public static MajorList getInstance() {
        if (majorList == null) majorList = new MajorList();
        return majorList;
    }

    /**
     * A getter enthod for majors using the Array List of type Major
     * @return majors 
     */
    public ArrayList<Major> getMajors() {
        return majors;
    }

    /**
     * An accessor method for the student's major
     * It will search for the student majors and then return it
     * @param majorName the name of student's major (i.e. CS, CIS)
     * @return the major of the student
     */
    public Major getMajor(String majorName) {
        for (Major major : majors) {
            if (major.checkMajor(majorName))
            return major;
        }
        return null;
    }

    /**
     * Find a major with the specified UUID
     * @param majorID UUID of the major
     * @return the Major object if found, null otherwise
     */
    public static Major getMajorByUUID(UUID majorID) {
        for (Major i : majors) {
            if (majorID.equals(i.getMajorid())) return i;
    }
    System.out.println("MajorList.java could not find Major with UUID " + majorID);
    return null;
    }

    /**
     * The datawriter will save the major when the user logouts
     */
    public void logout() {
        DataWriter.saveMajors();
    }
}
