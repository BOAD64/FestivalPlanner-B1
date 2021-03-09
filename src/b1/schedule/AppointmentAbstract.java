package b1.schedule;

import b1.school.person.Person;
import b1.school.room.Classroom;
import b1.school.room.Room;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class AppointmentAbstract implements Serializable {

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<Person> persons;
    private Room location;
    private String description;

    public AppointmentAbstract(String name, LocalTime startTime, LocalTime endTime, Room location, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.persons = new ArrayList<>();
        this.location = location;
        this.description = description;
    }

    public AppointmentAbstract() {
        this("Undefined", LocalTime.of(0, 0, 0, 0),
                LocalTime.of(0, 5, 0, 0), null, "Undefined");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    public Room getLocation() {
        return this.location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
