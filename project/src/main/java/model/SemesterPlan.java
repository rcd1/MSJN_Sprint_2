package model;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class SemesterPlan {
    private ArrayList<Course> courses;
    private int hours;
    
    
    private int calculateHours() {
        int sum = 0;
        for (Course course : courses) {
            sum += course.getHours();
            if(course.getDesignator() == Designator.FILL) {
                sum += 3;
            }
        }
        return sum;
    }


    public SemesterPlan() {
        courses = new ArrayList<>();
        hours = 0;
    }

    
    public SemesterPlan(ArrayList<Course> courses) {
        this.courses = courses;
        hours = calculateHours();
    }


    public SemesterPlan(ArrayList<Course> courses, int hours) {
        this.courses = courses;
        this.hours = hours;
    }

    public boolean addCourse(Course course) {
        if (courses.contains(course) || calculateHours() > 18) return false;
        courses.add(course);
        if (calculateHours() > 18) {
            courses.remove(course);
            return false;
        }
        return true;
    }

    public void primitiveAddCourse(Course course) {
        courses.add(course);
    }

    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    public void displaySemesterPlan() {
        if (courses.isEmpty()) {
            System.out.println("Semester Plan is empty.");
        } else {
            System.out.println("Semester Plan");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println("Course " + (i + 1) + ":");
                System.out.println("Course ID: " + course.getCourseID());
                System.out.println(course.getDesignator() + " " + course.getNumber());
                System.out.println("Hours: " + course.getHours());
            }
        }
        System.out.println("Total hours: " + calculateHours());
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }


    public int getHours() {
        return hours;
    }
    
    public void saveSemesterPlanToFile(User user, String fileName) {
        StringBuilder sb = new StringBuilder();
        
        if (user instanceof Student) {
            ArrayList<SemesterPlan> semesterPlans = ((Student) user).getSemesterPlans();
            for (SemesterPlan semesterPlan : semesterPlans) {
                sb.append("Semester Plan:\n");
                ArrayList<Course> courses = semesterPlan.getCourses();
                for (Course course : courses) {
                    sb.append(course.getName()).append("\n");
                }
                sb.append("========================================\n");
            }
        } else if (user instanceof Advisor) {
            ArrayList<Student> advisedStudents = ((Advisor) user).getStudents();
            for (Student student : advisedStudents) {
                ArrayList<SemesterPlan> semesterPlans = student.getSemesterPlans();
                for (SemesterPlan semesterPlan : semesterPlans) {
                    sb.append("Semester Plan for ").append(student.getFirstName()).append(" ").append(student.getLastName()).append(":\n");
                    ArrayList<Course> courses = semesterPlan.getCourses();
                    for (Course course : courses) {
                        sb.append(course.getName()).append("\n");
                    }
                    sb.append("========================================\n");
                }
            }
        }
    
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(sb.toString());
            System.out.println("Semester plan saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving semester plan to file: " + e.getMessage());
        }
    }    
}
