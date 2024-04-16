package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    String title;
    String date;
    String note;
    
    public Note(String title, String date, String note) {
        this.title = title;
        this.date = date;
        this.note = note;
    }

    public Note(String title, String note) {
        this.title = title;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.date = format.format(new Date());
        this.note = note;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String toString() {
        return date + "\n" + title + ":\n" + note;
    }
    
}
