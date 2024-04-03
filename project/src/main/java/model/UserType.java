package model;
/**
 * An enummeration class to dintinguish the different tyoes of users.
 */
public enum UserType {
    /**
     * Student and advisors 
     */
    STUDENT("Student"),
    ADVISOR("Advisor");

    private String phrase;

    /**
     * An mutator(setter) for the phrase value.
     * @param phrase represent the Student or the Advisor 
     */
    private UserType(String phrase) {
        this.phrase = phrase;
    }

    /**
     * A accessor(getter) for the phrase value.
     * @return either the userType Student or Advisor 
     */
    public String getPhrase() {
        return phrase;
    }
}
