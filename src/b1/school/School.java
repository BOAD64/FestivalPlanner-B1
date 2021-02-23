package b1.school;

import b1.school.person.Person;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.util.ArrayList;

public class School
{
    private final ArrayList<Person> persons;
    private final ArrayList<Student> students;
    private final ArrayList<Teacher> teachers;
    private final ArrayList<Room> rooms;

    public School() {
        this.persons = new ArrayList<>();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.rooms = new ArrayList<>();
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
}
