package b1.school.person;

public class Teacher extends Person {

    private String subject;

    public Teacher(String name, short age, String gender, String subject) {
        super(name, age, gender);
        this.subject = subject;
    }

    public Teacher() {
        super();
        this.subject = "\"undefined\"";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender + ", " + this.subject;
    }
}
