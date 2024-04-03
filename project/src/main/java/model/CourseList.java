package model;
import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private static CourseList courseList;
    private static ArrayList<Course> courses;

    public CourseList() {
        courseList = this;
        courses = DataLoader.getCourses();
        // After loading courses, we can properly add the requirements
        for (Course course : courses) {
            course.reloadCourseRequirements();
        }
    }

    public static CourseList getInstance() {
        if (courseList == null) courseList = new CourseList();
        return courseList;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourse(String name, String number, int hours) {
        for (Course course : courses) {
            if (!course.getDesignator().equals(Designator.FILL) && course.checkCourse(name, number, hours))
            return course;
        }
        return null;
    }

    public Course getCourse(String name, String number) {
        for (Course course : courses) {
            if (!course.getDesignator().equals(Designator.FILL) && course.checkCourse(name, number)) {
            return course;
            }
        }
        return null;
    }

    /**
     * Get all courses in a Subject that are within a specified level
     * @param subject the subject code (CSCE, ENGL, etc)
     * @param level the level of courses we want (1, 2, etc) for 100 level, 200 level, etc
     * @return an array of all courses with the same subject and at a specified level
     */
    public ArrayList<Course> getCoursesSameSubjectInSetLevel(String subject, String level) {
        ArrayList<Course> subset = new ArrayList<>();
        for (Course course : courses) {
            if (!course.getDesignator().equals(Designator.FILL) && course.getDesignator().equals(Designator.valueOf(subject)) && course.getNumber().startsWith(level)) {
                subset.add(course);
            }
        }
        return subset;
    }

    public ArrayList<Course> findCourses(String keyword) {
        ArrayList<Course> foundCourses = new ArrayList<>();
        for (Course course : courses) {
            if (!course.getDesignator().equals(Designator.FILL) && course.getKeywords().contains(Keyword.valueOf(keyword))) {
                foundCourses.add(course);
            }
        }
        return foundCourses;
    }

    /**
     * Find a course with a specific UUID
     * @param courseID the UUID of the course
     * @return the course if found, null otherwise
     */
    public static Course getCourseByUUID(UUID courseID) {
        for (Course i : courses) {
            if (courseID.equals(i.getCourseID())) return i;
        }
        System.out.println("CourseList.java could not find course with UUID " + courseID);
        return null;
    }

    public ArrayList<Course> getCoursesAboveLevelInSubject(Designator designator, String number) {
        ArrayList<Course> returnCourses = new ArrayList<Course>();
        for(Course course : courses) {
            if(!course.getDesignator().equals(Designator.FILL) && course.getDesignator() == designator && (Integer.parseInt(course.getNumber().substring(0, 3)) >= Integer.parseInt(number))) {
                returnCourses.add(course);
            }
        }
        return returnCourses;
    }

    public void logout() {
        DataWriter.saveCourses();
    }
}
