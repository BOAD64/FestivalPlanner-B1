package b1.school;

import b1.school.person.Person;
import b1.school.room.Classroom;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;

import java.util.ArrayList;

public class School {

    private String schoolName;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Group> groups;
    private ArrayList<Person> persons;

    public School(String schoolName) {
        this.schoolName = schoolName;
        this.classrooms = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.persons = new ArrayList<>();
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public ArrayList<Classroom> getClassrooms() {
        return this.classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public ArrayList<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (Person person : this.persons) {
            if (person instanceof Student) {
                students.add((Student)person);
            }
        }
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        for (Student student : students) {
            if (!this.persons.contains(student)) {
                this.persons.add(student);
            }
        }
    }

    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Person person : this.persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher)person);
            }
        }
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (!this.persons.contains(teacher)) {
                this.persons.add(teacher);
            }
        }
    }

    public void addClassroom(Classroom classroom) {
        if (!this.classrooms.contains(classroom)) {
            this.classrooms.add(classroom);
        }
    }

    @Override
    public String toString(){
        return this.schoolName;
    }
}
