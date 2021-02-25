package b1;

import b1.io.SchoolFile;
import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.person.Student;
import b1.school.room.Room;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    private School school;

    public Main() {
        if (SchoolFile.getSchool() == null) {
            SchoolFile.setSchool(new School());
        }
        this.school = SchoolFile.getSchool();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController mainController = new MainController(this.school);
        mainController.show();
    }
}
