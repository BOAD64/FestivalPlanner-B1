package b1.schedule;

import b1.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.security.krb5.internal.APOptions;

import java.util.ArrayList;

public class ScheduleView implements View
{
    private Stage stage;
    private Appointment[][] appointments;

    private Canvas canvas;

    public ScheduleView()
    {
        this.stage = new Stage();
    }

    public void draw(){
        for(Appointment[] appointmentColumn : this.appointments)
        {
            this.drawColumn(appointmentColumn);
        }
    }

    @Override
    public Stage getStage() {
        this.createStage();
        return this.stage;
    }

    private void createStage() {
        this.canvas = new Canvas();
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(new Group(this.canvas));
        this.stage.setScene(scene);
    }

    private void drawColumn(Appointment[] appointments, double offsetX) {
        this.canvas.
    }
}
