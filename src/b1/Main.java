package b1;

import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import javafx.application.Application;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        System.out.println("development branch");

        startGUI();
    }

    public static void startGUI(){
        Application.launch(MainView.class);
    }
}
