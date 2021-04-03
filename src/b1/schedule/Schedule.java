package b1.schedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private final ArrayList<AppointmentAbstract> appointments;

    public Schedule() {
        this.appointments = new ArrayList<>();
    }

    public ArrayList<AppointmentAbstract> getAppointments() {
        return this.appointments;
    }

    public ArrayList<Lesson> getLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (AppointmentAbstract lesson : this.appointments) {
            if (lesson instanceof Lesson) {
                lessons.add((Lesson) lesson);
            }
        }
        return lessons;
    }

    public void addAppointment(AppointmentAbstract appointment) {
        if (!this.appointments.contains(appointment)) {
            this.appointments.add(appointment);
        }
    }
}
