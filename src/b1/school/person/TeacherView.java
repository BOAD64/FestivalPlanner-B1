package b1.school.person;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherView extends PersonView {

    private Teacher teacher;

    private TextField subjectField = new TextField();

    public TeacherView(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Stage getStage() {
        if(super.stage == null)
        {
            this.createStage();
        }
        return super.stage;
    }

    private void createStage() {
        super.stage = new Stage();
        this.addTags();
        this.addInputField();

        super.initMainBox();

        Scene scene = new Scene(super.mainVBox);
        super.stage.setHeight(330);
        super.stage.setWidth(400);
        super.stage.setResizable(false);
        super.stage.setScene(scene);
        super.stage.setTitle("Docent toevoegen / bewerken");
    }

    private void addTags() {
        super.createTags();

        Label subjectLabel = new Label("Vak:");
        subjectLabel.setPrefHeight(super.fieldHeight);

        super.tagsVBox.getChildren().add(subjectLabel);
    }

    private void addInputField() {
        super.createInputField();

        this.subjectField.setPrefHeight(super.fieldHeight);

        //if the age of the Teacher is not -1 than the program loads the attributes of the Teacher into the TextFields.
        if(this.teacher.getAge() != -1) {
            super.nameField.setText(this.teacher.getName());
            super.ageField.setText(this.teacher.getAge() + "");
            super.genderField.setText(this.teacher.getGender());
            this.subjectField.setText(this.teacher.getSubject());
        }

        super.inputFieldVBox.getChildren().add(this.subjectField);
    }

    public TextField getSubjectField() {
        return subjectField;
    }
}
