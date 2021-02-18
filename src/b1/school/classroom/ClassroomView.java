package b1.school.classroom;

import b1.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClassroomView extends Application implements View {

    private Stage stage;
    private ClassroomController classroomController;

    public ClassroomView(ClassroomController classroomController) {
        this.stage = new Stage();
        this.classroomController = classroomController;
    }

    @Override
    public Stage getStage() {
        BorderPane pane = new BorderPane();
        ComboBox comboBox = new ComboBox();
        for (Classroom classroom : this.classroomController.getClassroomList()){
            comboBox.getItems().add(classroom);
        }
        pane.setTop(comboBox);


        this.stage.setMaxHeight(500);
        this.stage.setMaxWidth(500);
        this.stage.setScene(new Scene(pane));
        this.stage.setTitle("Lokaal");
        return this.stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = this.getStage();
        stage.show();
    }
}
