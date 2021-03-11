package b1.school.room;

import b1.Setting;
import b1.View;
import b1.io.ImageFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private void createStage() {
        this.stage = new Stage();
        this.stage.getIcons().add(ImageFile.getLogo());
        this.stage.setTitle("Klaslokaal");

        //VBox that has the labels
        Label classroomCodeLabel = new Label("Lokaalcode:");
        Label classroomCapacityLabel = new Label("Capaciteit:");
        Label classroomWidthLabel = new Label("Width:");
        Label classroomLengthLabel = new Label("Length:");
        classroomCodeLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        classroomCapacityLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        classroomWidthLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        classroomLengthLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        VBox labelVBox = new VBox();
        labelVBox.getChildren().addAll(
                classroomCodeLabel, classroomCapacityLabel,
                classroomWidthLabel, classroomLengthLabel);
        labelVBox.setSpacing(10);
        labelVBox.setPadding(new Insets(5, 5, 5, 5));

        //VBox that has the input fields
        this.classroomCode = new TextField(this.classroom.getRoomCode());
        this.classroomCapacity = new TextField("" + this.classroom.getCapacity());
        this.classroomWidth = new TextField("" + this.classroom.getWidth());
        this.classroomLength = new TextField("" + this.classroom.getLength());
        this.classroomCode.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.classroomCapacity.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.classroomWidth.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.classroomLength.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        VBox inputFieldVBox = new VBox();
        inputFieldVBox.getChildren().addAll(
                this.classroomCode, this.classroomCapacity,
                this.classroomWidth, this.classroomLength);
        inputFieldVBox.setSpacing(10);

        //HBox that has the buttons on the bottom
        this.applyButton = new Button("Toepassen");
        this.okButton = new Button("Opslaan");
        this.applyButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        this.okButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(this.applyButton, this.okButton);
        buttonsHBox.setSpacing(5);
        buttonsHBox.setAlignment(Pos.CENTER);

        //ordering the boxes
        HBox topBox = new HBox();
        topBox.getChildren().addAll(labelVBox, inputFieldVBox);

        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(topBox, buttonsHBox);
        mainBox.setSpacing(20);
        mainBox.setPadding(new Insets(10, 10, 10, 10));

        //applying to stage
        this.stage.setScene(new Scene(mainBox));
        this.stage.setMinWidth(325);
        this.stage.setMinHeight(350);
        this.stage.setMaxWidth(325);
        this.stage.setMaxHeight(350);
    }

    public Button getApplyButton() {
        return this.applyButton;
    }

    public Button getOkButton() {
        return this.okButton;
    }

    public TextField getClassroomCode() {
        return this.classroomCode;
    }

    public TextField getClassroomCapacity() {
        return this.classroomCapacity;
    }

    public TextField getClassroomWidth() {
        return this.classroomWidth;
    }

    public TextField getClassroomLength() {
        return this.classroomLength;
    }
}
