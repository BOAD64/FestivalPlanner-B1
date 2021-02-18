package b1.schedule;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Appointment> appointments;

    public Schedule() {
        this.appointments = new ArrayList<>();
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }
}
