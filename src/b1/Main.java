package b1;

import b1.school.person.Student;
import b1.school.person.StudentController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private MainViewController mainViewController = new MainViewController();

    private StudentController controller = new StudentController(new Student());

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //mainViewController.show();

        controller.show();
    }

}
