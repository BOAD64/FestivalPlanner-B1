package b1.schedule;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<AppointmentAbstract> appointments;

    public Schedule() {
        this.appointments = new ArrayList<>();
    }

    public ArrayList<AppointmentAbstract> getAppointments() {
        return this.appointments;
    }
}
