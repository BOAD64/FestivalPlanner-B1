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

    private ClassroomController classroomController = new ClassroomController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        classroomInit();
        classroomController.show();
    }

    public void classroomInit(){
        this.classroomController.addClassroom(new Classroom("LA136", 15));
        this.classroomController.addClassroom(new Classroom("LA136", 25));
        this.classroomController.addClassroom(new Classroom("LA247", 10));
    }
}