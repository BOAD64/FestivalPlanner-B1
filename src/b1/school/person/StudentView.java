package b1.school.person;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentView extends PersonView {

    private Student student;

    private TextField idField = new TextField();
    private TextField groupField = new TextField();


    public StudentView(Student student) {
        super.stage = new Stage();
        this.student = student;
        this.createStage();
    }

    @Override
    public Stage getStage() {
        return super.stage;
    }

    private void createStage() {
        this.addTags();
        this.addInputField();

        super.initMainBox();

        Scene scene = new Scene(super.mainVBox);
        super.stage.setHeight(375);
        super.stage.setWidth(350);
        super.stage.setResizable(false);
        super.stage.setScene(scene);
    }

    private void addTags() {
        super.createTags();

        Label idLabel = new Label("Studentnummer:");
        Label groupLabel = new Label("Klas:");

        idLabel.setPrefHeight(super.fieldHeight);
        groupLabel.setPrefHeight(super.fieldHeight);

        super.tagsVBox.getChildren().addAll(idLabel, groupLabel);
    }

    private void addInputField() {
        super.createInputField();

        this.idField.setPrefHeight(super.fieldHeight);
        this.groupField.setPrefHeight(super.fieldHeight);

        if(this.student.getAge() != -1) {
            super.nameField.setText(this.student.getName());
            super.ageField.setText(this.student.getAge() + "");
            super.genderField.setText(this.student.getGender());
            this.idField.setText(this.student.getIdNumber() + "");
            this.groupField.setText(this.student.getGroup());
        }
        super.inputFieldVBox.getChildren().addAll(this.idField, this.groupField);
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getGroupField() {
        return groupField;
    }
}
