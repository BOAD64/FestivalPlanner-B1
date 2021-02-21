package b1.schedule;

import b1.school.group.StudentGroup;
import b1.school.person.Person;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson extends Appointment{

    private StudentGroup studentGroup;
    private Teacher teacher;

    public Lesson(String name, LocalTime startTime, LocalTime endTime, Room location, String description, StudentGroup studentGroup, Teacher teacher) {
        super(name, startTime, endTime, location, description);
        this.studentGroup = studentGroup;
        this.teacher = teacher;
    }

    public StudentGroup getStudentGroup() {
        return this.studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>(this.studentGroup.getStudents());
        persons.add(this.teacher);
        return persons;
    }
}
