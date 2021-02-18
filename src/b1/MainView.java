package b1;

import b1.school.person.Student;
import b1.school.person.StudentController;
import b1.school.person.StudentView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;


public class MainView implements View {

    private Stage stage;

    public MainView() {
        this.stage = new Stage();
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    public Button getButton() {
        return new Button();
    }
}