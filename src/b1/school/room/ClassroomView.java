package b1.school.room;

import b1.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClassroomView implements View {

    private Classroom classroom;

    private Button applyButton;
    private Button okButton;



    private TextField classroomCode;
    private TextField classroomCapacity;
    private TextField classroomWidth;
    private TextField classroomLength;

    private Stage stage;

    public ClassroomView(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public Stage getStage(){
        if(this.stage == null)
        {
            this.createStage();
        }
        return this.stage;
    }


    public void createStage() {
        this.stage = new Stage();
        BorderPane borderPane = new BorderPane();

        //object for stage
        Label classroomCodeLabel = new Label("Lokaalcode:");
        Label classroomCapacityLabel = new Label("Capaciteit: ");
        Label classroomWidthLabel = new Label("Width: ");
        Label classroomLengthLabel = new Label("Length: ");

        this.classroomCode = new TextField(this.classroom.getRoomCode());
        this.classroomCapacity = new TextField("" + this.classroom.getCapacity());
        this.classroomWidth = new TextField("" + this.classroom.getWidth());
        this.classroomLength = new TextField("" + this.classroom.getLength());

        this.applyButton = new Button("Toepassen");
        this.okButton = new Button("Opslaan");

        //ordering
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                classroomCodeLabel, classroomCode,
                classroomCapacityLabel, classroomCapacity,
                classroomLengthLabel, classroomLength,
                classroomWidthLabel, classroomWidth,
                this.applyButton, this.okButton
        );
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setTop(hBox);

        //button actions
        this.applyButton.setOnAction( event -> {
            //read and save from text fields
            this.classroom.setCapacity(Integer.parseInt(classroomCapacity.getText()));
            this.classroom.setRoomCode(classroomCode.getText());
            this.classroom.setLength(Double.parseDouble(classroomLength.getText()));
            this.classroom.setWidth(Double.parseDouble(classroomWidth.getText()));
        });
        this.okButton.setOnAction( event -> {
            //read and save from text fields
            this.classroom.setCapacity(Integer.parseInt(classroomCapacity.getText()));
            this.classroom.setRoomCode(classroomCode.getText());
            this.classroom.setLength(Double.parseDouble(classroomLength.getText()));
            this.classroom.setWidth(Double.parseDouble(classroomWidth.getText()));
            //exit window
            this.stage.close();
        });

        //applying to stage
        this.stage.setScene(new Scene(borderPane));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
    }

    public Button getApplyButton() {
        return this.applyButton;
    }

    public Button getOkButton() {
        return this.okButton;
    }

    public TextField getClassroomCode() {
        return classroomCode;
    }

    public TextField getClassroomCapacity() {
        return classroomCapacity;
    }

    public TextField getClassroomWidth() {
        return classroomWidth;
    }

    public TextField getClassroomLength() {
        return classroomLength;
    }
}
