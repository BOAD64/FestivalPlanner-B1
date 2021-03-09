package b1.schedule;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AppointmentShape extends Rectangle2D.Double
{
    private AppointmentAbstract appointment;
    private Color backgroundColor;

    public AppointmentShape(AppointmentAbstract appointment, double x, double y, double w, double h) {
        super(x, y, w, h);
        this.appointment = appointment;
    }

    public AppointmentAbstract getAppointment() {
        return this.appointment;
    }

    public void setAppointment(AppointmentAbstract appointment) {
        this.appointment = appointment;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void draw(FXGraphics2D fxGraphics2D) {
        fxGraphics2D.setColor(this.backgroundColor);
        fxGraphics2D.fill(this);
        fxGraphics2D.setColor(Color.BLACK);
        fxGraphics2D.draw(this);

        fxGraphics2D.setFont(fxGraphics2D.getFont().deriveFont(12.0f));

        if (this.getHeight() > 15) {
            fxGraphics2D.drawString(this.appointment.getName(), (int) this.getX() + 10, (int) this.getY() + 15);
        }

        if (this.getHeight() > 30) {
            fxGraphics2D.drawString(this.appointment.getStartTime().toString() + " - " +
                    this.appointment.getEndTime().toString(), (int) this.getX() + 10, (int) this.getY() + 30);
        }
    }
}
