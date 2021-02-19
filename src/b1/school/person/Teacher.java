package b1.school.person;

public class Teacher {
    private String name;
    private int ID;

    public Teacher(String name, int stundentnr) {
        this.name = name;
        this.ID = stundentnr;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
