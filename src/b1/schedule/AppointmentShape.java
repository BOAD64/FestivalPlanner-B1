package b1.schedule;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class AppointmentShape extends Rectangle2D.Double
{
    private Appointment appointment;

    public AppointmentShape(Appointment appointment, double x, double y, double w, double h)
    {
        super(x, y, w, h);
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
