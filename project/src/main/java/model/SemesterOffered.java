package model;
public enum SemesterOffered {
    SPRING("Spring"),
    FALL("Fall"),
    SUMMER("Summer");

    private String name;

    private SemesterOffered(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
