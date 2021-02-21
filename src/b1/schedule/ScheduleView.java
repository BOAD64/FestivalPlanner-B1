package b1.schedule;

import b1.View;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ScheduleView implements View
{
    private Appointment[][] appointments;
    private final ArrayList<AppointmentShape> appointmentShapes;

    private final Stage stage;
    private Canvas canvas;
    private FXGraphics2D fxGraphics2D;

    private final int START_TIME = 3600 * 7;
    private final int END_TIME = 3600 * 23;

    public ScheduleView() {
        this.stage = new Stage();
        this.appointmentShapes = new ArrayList<AppointmentShape>();
    }

    public Appointment[][] getAppointments() {
        return this.appointments;
    }

    public void setAppointments(Appointment[][] appointments) {
        this.appointments = appointments;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public ArrayList<AppointmentShape> getAppointmentShapes() {
        return this.appointmentShapes;
    }

    public void draw() {
        this.appointmentShapes.clear();
        this.fxGraphics2D.setBackground(Color.white);
        this.fxGraphics2D.clearRect(0, 0, (int) Math.round(this.canvas.getWidth()), (int) Math.round(this.canvas.getHeight()));

        int columnWidth = (int)Math.round(this.getColumnWidth());

        this.drawBackground(this.appointments.length, columnWidth);

        Appointment[] currentAppointmentList;
        for (int i = 0; i < this.appointments.length; i++) {

            currentAppointmentList = this.appointments[i];
            if (currentAppointmentList == null) {
                continue;
            }

            for (int j = 0; j < currentAppointmentList.length; j++) {
                this.appointmentShapes.add(this.generateAppointmentShape(currentAppointmentList[j], columnWidth * i, columnWidth));
            }

            for (int j = 0; j < this.appointmentShapes.size(); j++) {
                this.drawAppointment(this.appointmentShapes.get(j));
            }
        }
    }

    @Override
    public Stage getStage() {
        this.stage.setHeight(1080);
        this.stage.setWidth(1920);

        this.stage.heightProperty().addListener(this.onStageResize());
        this.stage.widthProperty().addListener(this.onStageResize());

        this.createStage();

        return this.stage;
    }

    private void createStage() {
        this.canvas = new Canvas();
        this.fxGraphics2D = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        Scene scene = new Scene(new Group(this.canvas));

        this.canvas.setHeight(this.stage.getHeight());
        this.canvas.setWidth(this.stage.getWidth());

        this.canvas.heightProperty().addListener(this.onCanvasResize());
        this.canvas.widthProperty().addListener(this.onCanvasResize());

        this.stage.setScene(scene);
    }

    private void drawBackground(int appointmentCount, int columnWidth)
    {
        this.fxGraphics2D.setColor(Color.LIGHT_GRAY);
        int hourCount = (this.END_TIME - this.START_TIME) / 3600;
        int hourHeight = (int)Math.round(this.canvas.getHeight() / ((this.END_TIME - this.START_TIME) / 3600.0));
        for(int i = 0; i < hourCount; i++)
        {
            this.fxGraphics2D.drawLine(0, hourHeight*i, (int)Math.round(this.canvas.getWidth()), hourHeight*i);
        }

        this.fxGraphics2D.setColor(Color.GRAY);
        for(int i = 0; i < appointmentCount; i++) {
            this.fxGraphics2D.drawLine(i*columnWidth, 0, i*columnWidth, (int) this.canvas.getHeight());
        }
    }

    private AppointmentShape generateAppointmentShape(Appointment appointment, int offsetX, int width)
    {
        int y = this.getAppointmentY(appointment);
        int y2 = this.getAppointmentY2(appointment);
        int height = (y2 - y);

        return new AppointmentShape(appointment, offsetX, y, width, height);
    }

    private AppointmentShape drawAppointment(AppointmentShape appointmentRectangle) {
        Color backColor = Color.YELLOW;
        Appointment appointment = appointmentRectangle.getAppointment();

        for(AppointmentShape appointmentShape : this.appointmentShapes)
        {
            if(appointmentRectangle.intersects(appointmentShape))
            {
                backColor = Color.RED;
            }
        }

        this.fxGraphics2D.setColor(backColor);
        this.fxGraphics2D.fill(appointmentRectangle);
        this.fxGraphics2D.setColor(Color.BLACK);
        this.fxGraphics2D.draw(appointmentRectangle);

        if (appointmentRectangle.getHeight() > 14) {
            this.fxGraphics2D.drawString(appointment.getName(), (int)appointmentRectangle.getX() + 10,
                    (int)appointmentRectangle.getY() + 10);
        }

        if(appointmentRectangle.getHeight()  > 29)
        {
            this.fxGraphics2D.drawString(appointment.getStartTime().toString() +" - "+appointment.getEndTime().toString(),
                    (int)appointmentRectangle.getX() + 10, (int)appointmentRectangle.getMaxY() - 10);
        }

        return appointmentRectangle;
    }

    private double getColumnWidth() {
        double columnWidth = this.canvas.getWidth();

        if (this.appointments.length > 0) {
            columnWidth = this.canvas.getWidth() / this.appointments.length;
        }

        return columnWidth;
    }

    private int getAppointmentY(Appointment appointment) {
        int startSeconds = appointment.getStartTime().getHour() * 3600 + appointment.getStartTime().getMinute() * 60 + appointment.getStartTime().getSecond();
        return (int) Math.round(this.canvas.getHeight() / (this.END_TIME - this.START_TIME) * (startSeconds - this.START_TIME));
    }

    private int getAppointmentY2(Appointment appointment) {
        int endSeconds = appointment.getEndTime().getHour() * 3600 + appointment.getEndTime().getMinute() * 60 + appointment.getEndTime().getSecond();
        return (int) Math.round(this.canvas.getHeight() / (this.END_TIME - this.START_TIME) * (endSeconds - this.START_TIME));
    }

    private ChangeListener<Number> onStageResize() {
        return (observable, oldValue, newValue) -> {
            this.canvas.setWidth(this.stage.getWidth());
            this.canvas.setHeight(this.stage.getHeight());
        };
    }

    private ChangeListener<Number> onCanvasResize() {
        return (observable, oldValue, newValue) -> {
            this.draw();
        };
    }
}
