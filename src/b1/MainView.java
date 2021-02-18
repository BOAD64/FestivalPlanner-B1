package b1;

import b1.school.classroom.Classroom;
import b1.school.classroom.ClassroomController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;


public class MainView extends Application {
    Classroom c = new Classroom("lol34", 69);
    private ClassroomController classroomController = new ClassroomController(c);

    @Override
    public void start(Stage primaryStage) throws Exception {
        classroomController.show();
    }
}