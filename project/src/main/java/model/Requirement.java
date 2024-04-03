package model;
/**
 * An Interface representing the Requirements of a College Degree
 * @implNote https://sc.edu/about/offices_and_divisions/advising/documents/major_maps/2016-2017_maps/2016_cs_map_official.pdf
 */
public interface Requirement {
    /**
     * Given a student, 
     * determine if they have satisfied the specified Requirement
     * @param student the Student to be checked
     * @return true if satisfied, false otherwise
     */
    public boolean SatisfiesRequirement(Student student);
}
