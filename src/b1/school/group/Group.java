package b1.school.group;

import b1.school.person.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Student> students;

    public Group() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            this.students.add(student);
        }
    }
}
