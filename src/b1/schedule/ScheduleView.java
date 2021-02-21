package b1.schedule;

import b1.View;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class ScheduleView implements View
{
    private Stage stage;
    private Appointment[][] appointments;

    private Canvas canvas;
    private FXGraphics2D fxGraphics2D;

    private final int STARTTIME = 3600*7;
    private final int ENDTIME = 3600*23;

    public ScheduleView() {
        this.stage = new Stage();
    }

    public Appointment[][] getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment[][] appointments) {
        this.appointments = appointments;
    }

    public void draw() {
        this.fxGraphics2D.setBackground(Color.white);
        this.fxGraphics2D.clearRect(0, 0, (int) Math.round(this.canvas.getWidth()), (int) Math.round(this.canvas.getHeight()));

        double columnWidth = getColumnWidth();
        for (int i = 0; i < this.appointments.length; i++) {
            this.drawColumn(this.appointments[i], (int) Math.round(columnWidth * i), (int)Math.round(columnWidth));
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

    private void drawColumn(Appointment[] appointments, int offsetX, int width) {
        this.fxGraphics2D.setColor(Color.LIGHT_GRAY);
        this.fxGraphics2D.drawLine(offsetX, 0, offsetX, (int) this.canvas.getHeight());

        if(appointments == null)
        {
            return;
        }

        for(int i = 0; i < appointments.length; i++)
        {
            this.drawAppointment(appointments[i], offsetX, width);
        }
    }

    private void drawAppointment(Appointment appointment, int offsetX, int width)
    {
        int y = this.getAppointmentY(appointment);
        int y2 = this.getAppointmentY2(appointment);

        this.fxGraphics2D.setColor(Color.YELLOW);
        this.fxGraphics2D.fillRect(offsetX,  y, width, (y2-y));

        this.fxGraphics2D.setColor(Color.BLACK);
        this.fxGraphics2D.drawRect(offsetX,  y, width, (y2-y));

        this.fxGraphics2D.drawString(appointment.getName(), offsetX+10, y+10);
    }

    private double getColumnWidth() {
        double columnWidth = this.canvas.getWidth();

        if (this.appointments.length > 0) {
            columnWidth = this.canvas.getWidth() / this.appointments.length;
        }

        return columnWidth;
    }

    private int getAppointmentY(Appointment appointment)
    {
        int startSeconds = appointment.getStartTime().getHour() * 3600 + appointment.getStartTime().getMinute() * 60 + appointment.getStartTime().getSecond();
        return (int)Math.round(this.canvas.getHeight() / (this.ENDTIME - this.STARTTIME) * (startSeconds - this.STARTTIME));
    }

    private int getAppointmentY2(Appointment appointment)
    {
        int endSeconds = appointment.getEndTime().getHour() * 3600 + appointment.getEndTime().getMinute() * 60 + appointment.getEndTime().getSecond();
        return (int)Math.round(this.canvas.getHeight() / (this.ENDTIME - this.STARTTIME) * (endSeconds - this.STARTTIME)) ;
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
