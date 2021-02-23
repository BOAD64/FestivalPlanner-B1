package b1.school;

import b1.school.room.Classroom;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;

import java.util.ArrayList;

public class School {

    private String schoolName;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Group> groups;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public School(String schoolName) {
        this.schoolName = schoolName;
        this.classrooms = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
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
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
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
