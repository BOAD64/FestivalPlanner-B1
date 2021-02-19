package b1.school.classroom;

import b1.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClassroomView implements View {

    private Classroom classroom;

    public ClassroomView(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public Stage getStage() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        //object for stage
        Label classroomCodeLabel = new Label("Lokaalcode:");
        Label classroomCapacityLabel = new Label("Capaciteit: ");
        Label classroomWidthLabel = new Label("Width: ");
        Label classroomLengthLabel = new Label("Length: ");

        TextField classroomCode = new TextField(this.classroom.getRoomCode());
        TextField classroomCapacity = new TextField("" + this.classroom.getCapacity());
        TextField classroomWidth = new TextField("" + this.classroom.getWidth());
        TextField classroomLength = new TextField("" + this.classroom.getLength());

        Button applyButton = new Button("Apply");
        Button okButton = new Button("Ok");

        //ordering
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                classroomCodeLabel, classroomCode,
                classroomCapacityLabel, classroomCapacity,
                classroomLengthLabel, classroomLength,
                classroomWidthLabel, classroomWidth,
                applyButton, okButton
        );
        vBox.setSpacing(10);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(20);

        borderPane.setTop(hBox);

        //button actions
        applyButton.setOnAction( event -> {
            //read and save from text fields
            this.classroom.setCapacity(Integer.parseInt(classroomCapacity.getText()));
            this.classroom.setRoomCode(classroomCode.getText());
            this.classroom.setLength(Double.parseDouble(classroomLength.getText()));
            this.classroom.setWidth(Double.parseDouble(classroomWidth.getText()));
        });
        okButton.setOnAction( event -> {
            //read and save from text fields
            this.classroom.setCapacity(Integer.parseInt(classroomCapacity.getText()));
            this.classroom.setRoomCode(classroomCode.getText());
            this.classroom.setLength(Double.parseDouble(classroomLength.getText()));
            this.classroom.setWidth(Double.parseDouble(classroomWidth.getText()));
            //exit window
            stage.close();
        });

        //applying to stage
        stage.setScene(new Scene(borderPane));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        return stage;
    }
}
