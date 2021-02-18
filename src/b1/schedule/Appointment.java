package b1.schedule;

import java.time.LocalTime;
import java.util.ArrayList;

public class Appointment {

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<Person> persons;
    private Room location;
    private String description;

    public Appointment(String name, LocalTime startTime, LocalTime endTime, Room location, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.persons = new ArrayList<Person>();
        this.location = location;
        this.description = description;
    }

    public Appointment() {
        this("Undefined", LocalTime.of(0, 0, 0, 0),
                LocalTime.of(0, 5, 0, 0), null, "Undefined");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
