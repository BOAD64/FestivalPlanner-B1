package b1.school.person;

import b1.school.group.Group;

public class Student extends Person {

    private short idNumber;
    private Group group;

    public Student(String name, short age, String gender, short idNumber, Group group) {
        super(name, age, gender);
        this.idNumber = idNumber;
        this.group = group;
    }

    /**
     * Default constructor if no parameters are given.
     * This method first calls the default constructor of its super class.
     */
    public Student() {
        super();
        this.group = null;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public short getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(short idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender + ", " + this.idNumber + ", " + this.group;
    }
}
