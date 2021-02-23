package b1.school.person;

public class Student extends Person {
    private String name;
    private int stundentnr;

    public Student(String name, int stundentnr) {
        this.name = name;
        this.stundentnr = stundentnr;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
