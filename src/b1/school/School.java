package b1.school;

import b1.schedule.Schedule;
import b1.school.person.Person;
import b1.school.room.Classroom;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class School implements Serializable {

    private String schoolName;
    private ArrayList<Room> rooms;
    private ArrayList<Group> groups;
    private ArrayList<Person> persons;
    private Schedule schedule;

    public School(String schoolName) {
        this.schoolName = schoolName;
        this.rooms = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.schedule = new Schedule();
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
        }
    }

    public ArrayList<Classroom> getClassrooms() {
        ArrayList<Classroom> result = new ArrayList<>();
        for (Room room : this.rooms) {
            if (room instanceof Classroom) {
                result.add((Classroom) room);
            }
        }

        return result;
    }

    public void addClassroom(Classroom classroom) {
        if (!this.rooms.contains(classroom)) {
            this.rooms.add(classroom);
        }
    }

    public ArrayList<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        if (!this.groups.contains(group)) {
            this.groups.add(group);
        }
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (Person person : this.persons) {
            if (person instanceof Student) {
                students.add((Student) person);
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

    public void addStudent(Student student) {
        if (!this.persons.contains(student)) {
            this.persons.add(student);
        }
    }

    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Person person : this.persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher) person);
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

    public void addTeacher(Teacher teacher) {
        if (!this.persons.contains(teacher)) {
            this.persons.add(teacher);
        }
    }

    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return this.schoolName;
    }
}
