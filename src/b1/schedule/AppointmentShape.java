package b1.schedule;

import java.awt.geom.Rectangle2D;

public class AppointmentShape extends Rectangle2D.Double
{
    private AppointmentAbstract appointment;

    public AppointmentShape(AppointmentAbstract appointment, double x, double y, double w, double h)
    {
        super(x, y, w, h);
        this.appointment = appointment;
    }

    public AppointmentAbstract getAppointment() {
        return this.appointment;
    }

    public void setAppointment(AppointmentAbstract appointment) {
        this.appointment = appointment;
    }
}
