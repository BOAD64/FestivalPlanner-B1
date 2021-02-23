package b1;

import b1.school.person.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private MainViewController mainViewController = new MainViewController();

    private StudentController controller = new StudentController(new Student());
    //private TeacherController controller = new TeacherController(new Teacher());



    public static void main(String[] args) {
        //launch(Main.class);

        StudentGenerator generator = new StudentGenerator((short)100);
        System.out.println(generator.getNames());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //mainViewController.show();
        controller.show();


    }

}
