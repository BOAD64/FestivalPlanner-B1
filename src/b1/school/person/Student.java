package b1.school.person;

public class Student extends Person {

    private String group;

    public Student(String name, short age, String gender, short idNumber, String group) {
        super(name, age, gender, idNumber);
        this.group = group;
    }

    public Student() {
        super();
        this.group = "\"undefined\"";
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender + ", " + this.idNumber + ", " + this.group;
    }
}
