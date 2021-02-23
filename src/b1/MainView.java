package b1;

import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ScheduleController(new Schedule()).show();
//        new AppointmentController(new Appointment()).show();
    }
}