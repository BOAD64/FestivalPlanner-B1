package b1;

import b1.schedule.Appointment;
import b1.schedule.AppointmentController;
import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ScheduleController(new Schedule()).show();
//        new AppointmentController(new Appointment()).show();
    }
}