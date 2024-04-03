package model;
/**
 * An Enum representing the grades of the student 
 */
public enum Grade {
    CR("CR",4.0), // Means bypassed by means of a placement test
    A("A",4.0),
    B_PLUS("B+",3.5),
    B("B",3.0),
    C_PLUS("C+", 2.5),
    C("C",2.0),
    D_PLUS("D+",1.5),
    D("D",1.0),
    F("F",0.0),
    W("W", 0.0),
    WF("WF", 0.0),
    R("R", 0.0); // Means the course has been registered but not taken yet. Used for filling electives

    private final String letter;
    private final double pointValue;
    /**
     * Create a Grade Enum
     * @param letter the String representing the Grade
     * @param pointValue the number of points the Grade is worth
     */
    Grade(String letter, double pointValue) {
        this.letter = letter;
        this.pointValue = pointValue;
    }
    /**
     * Get the Grade in String format
     * @return the String representing the Grade
     */
    public String getLetter() {
        return letter;
    }
    /**
     * Return the Grade Point Value of the Grade
     * @return a double representing the number of points
     */
    public double getPointValue() {
        return pointValue;
    }
}
