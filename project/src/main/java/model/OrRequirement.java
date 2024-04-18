package model;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * A Class representing a Degree requirement 
 * where one set of courses out of all the possible courses will satisfy
 * the entire requirement
 */
public class OrRequirement extends RequirementSet {
    

    public OrRequirement(ArrayList<Course> courses, Grade requiredGrade) {
        super(courses, requiredGrade);
        //TODO Auto-generated constructor stub
    }
    
    public OrRequirement(String title, ArrayList<Course> courses, Grade requiredGrade) {
        super(title, courses, requiredGrade);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean SatisfiesRequirement(Student student) {
        if(requiredCourses.size() == 0) {
            return true;
        }
        HashMap<Course, Grade> grades = student.getStudentGrades();
        for(Course course : requiredCourses) {
            Grade grade = grades.get(course);
            if(grade != null && grade.getPointValue() >= requiredGrade.getPointValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < requiredCourses.size(); i++) {
            sb.append(requiredCourses.get(i).getShortName());
            if(i != requiredCourses.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
