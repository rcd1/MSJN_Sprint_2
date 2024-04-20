package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        UserList users = UserList.getInstance();
        ArrayList<Student> students = users.getStudents();
         for (Student student : students) {
             for (Note note : student.getNotes()) {
                 System.out.println(note.toString());
             }
         }
        //new ApplicationUI().run();
    }
}
