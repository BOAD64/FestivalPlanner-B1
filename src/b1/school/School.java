package b1.school;

import b1.schedule.Schedule;
import b1.school.group.Group;
import b1.school.group.StudentGroup;
import b1.school.person.Person;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.util.ArrayList;

public class School
{
    private final Schedule schedule;
    private final ArrayList<Person> persons;
    private final ArrayList<Student> students;
    private final ArrayList<Teacher> teachers;
    private final ArrayList<Room> rooms;
    private final ArrayList<Group> groups;

    private int lastStudentNumber;

    public School() {
        this.persons = new ArrayList<>();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.schedule = new Schedule();
        this.groups = new ArrayList<>();
        this.lastStudentNumber = 0;
    }

    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addStudent(Student student) {
        student.setIdNumber((short) (this.lastStudentNumber + 1));
        this.persons.add(student);
        this.students.add(student);
        this.lastStudentNumber++;
    }

    public void addTeacher(Teacher teacher) {
        this.persons.add(teacher);
        this.teachers.add(teacher);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public int getLastStudentNumber() {
        return lastStudentNumber;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}
