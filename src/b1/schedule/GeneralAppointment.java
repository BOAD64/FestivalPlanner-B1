package b1.schedule;

import b1.school.room.Room;

import java.time.LocalTime;

public class GeneralAppointment extends AppointmentAbstract
{
    public GeneralAppointment(String name, LocalTime startTime, LocalTime endTime, Room location, String description) {
        super(name, startTime, endTime, location, description);
    }
}
