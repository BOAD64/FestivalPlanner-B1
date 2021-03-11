package b1.school.group;

import b1.Data;
import b1.school.person.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Data, Serializable
{
    private String groupCode;
    private ArrayList<Student> students;

    public Group(String groupCode) {
        this.groupCode = groupCode;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudentsList() {
        return this.students;
    }

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String toString() {
        return this.groupCode;
    }
}
