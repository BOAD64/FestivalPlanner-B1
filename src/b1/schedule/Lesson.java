package b1.schedule;

import b1.school.group.Group;
import b1.school.person.Person;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson extends AppointmentAbstract {
    private Group group;
    private Teacher teacher;

    public Lesson(String name, LocalTime startTime, LocalTime endTime, Room location, String description, Group group, Teacher teacher) {
        super(name, startTime, endTime, location, description);
        this.group = group;
        this.teacher = teacher;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<Person>(this.group.getStudentsList());
        persons.add(this.teacher);
        return persons;
    }
}
