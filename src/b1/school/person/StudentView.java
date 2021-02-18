package b1.school.person;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentView extends PersonView {

    private VBox tagsVBox = new VBox();
    private VBox inputFieldVBox = new VBox();
    private VBox mainVBox = new VBox();
    private Student student;
    private short fieldHeight = 40;

    public StudentView(Student student) {
        super.stage = new Stage();
        this.student = student;
    }

    @Override
    public Stage getStage() {
        this.createStage();
        return super.stage;
    }

    private void createStage() {
        this.createTags();
        this.createInputField();
        this.tagsVBox.setSpacing(5);
        this.inputFieldVBox.setSpacing(5);
        this.mainVBox.setSpacing(25);
        this.mainVBox.setAlignment(Pos.TOP_CENTER);

        HBox mainHBox = new HBox();
        mainHBox.getChildren().addAll(this.tagsVBox, this.inputFieldVBox);
        mainHBox.setSpacing(5);
        mainHBox.setAlignment(Pos.TOP_CENTER);

        mainVBox.getChildren().add(mainHBox);
        this.createButtons();

        Scene scene = new Scene(mainVBox);
        super.stage.setHeight(375);
        super.stage.setWidth(350);
        super.stage.setResizable(false);
        super.stage.setScene(scene);
    }

    private void createTags() {
        Label nameLabel = new Label("Naam:");
        Label ageLabel = new Label("Leeftijd:");
        Label genderLabel = new Label("Geslacht:");
        Label idLabel = new Label("Studentnummer:");
        Label groupLabel = new Label("Klas:");

        nameLabel.setPrefHeight(this.fieldHeight);
        ageLabel.setPrefHeight(this.fieldHeight);
        genderLabel.setPrefHeight(this.fieldHeight);
        idLabel.setPrefHeight(this.fieldHeight);
        groupLabel.setPrefHeight(this.fieldHeight);

        this.tagsVBox.getChildren().addAll(nameLabel, ageLabel, genderLabel, idLabel, groupLabel);
        this.tagsVBox.setAlignment(Pos.TOP_RIGHT);
    }

    private void createInputField() {
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField genderField = new TextField();
        TextField idField = new TextField();
        TextField groupField = new TextField();

        nameField.setPrefHeight(this.fieldHeight);
        ageField.setPrefHeight(this.fieldHeight);
        genderField.setPrefHeight(this.fieldHeight);
        idField.setPrefHeight(this.fieldHeight);
        groupField.setPrefHeight(this.fieldHeight);

        if(this.student != null) {
            nameField.setText(this.student.getName());
            ageField.setText(this.student.getAge() + "");
            genderField.setText(this.student.getGender());
            idField.setText(this.student.getIdNumber() + "");
            groupField.setText(this.student.getGroup());
        }

        this.inputFieldVBox.getChildren().addAll(nameField, ageField, genderField, idField, groupField);
        this.inputFieldVBox.setAlignment(Pos.TOP_RIGHT);
    }

    private void createButtons() {
        Button undoButton = new Button("Ongedaan maken");
        Button saveButton = new Button("Opslaan");

        undoButton.setPrefHeight(this.fieldHeight + 10);
        saveButton.setPrefHeight(this.fieldHeight + 10);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(undoButton, saveButton);
        buttonHBox.setSpacing(40);
        buttonHBox.setAlignment(Pos.CENTER);
        this.mainVBox.getChildren().add(buttonHBox);
    }
}
