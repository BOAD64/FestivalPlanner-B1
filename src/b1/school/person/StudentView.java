package b1.school.person;

import b1.Setting;
import b1.io.SchoolFile;
import b1.school.group.Group;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentView extends PersonView {

    private Student student;

    private TextField idField = new TextField();
    private ComboBox<Group> groupComboBox = new ComboBox<Group>();


    StudentView(Student student) {
        this.student = student;
    }

    @Override
    public Stage getStage() {
        if (super.stage == null) {
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
        super.stage.setHeight(375);
        super.stage.setWidth(400);
        super.stage.setResizable(false);
        super.stage.setScene(scene);
        super.stage.setTitle("Student");
    }

    private void addTags() {
        super.createTags();

        Label idLabel = new Label("Studentnummer:");
        Label groupLabel = new Label("Klas:");

        idLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        groupLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);

        super.tagsVBox.getChildren().addAll(idLabel, groupLabel);
    }

    private void addInputField() {
        super.createInputField();

        this.idField.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.groupComboBox.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.groupComboBox.setItems(FXCollections.observableList(SchoolFile.getSchool().getGroups()));

        //if the age of the Student is not -1 than the program loads the attributes of the Student into the TextFields
        if (this.student.getAge() != -1) {
            super.nameField.setText(this.student.getName());
            super.ageField.setText(this.student.getAge() + "");
            super.genderField.setText(this.student.getGender());
            this.idField.setText(this.student.getIdNumber() + "");
            this.groupComboBox.getSelectionModel().select(this.student.getGroup());
        }
        super.inputFieldVBox.getChildren().addAll(this.idField, this.groupComboBox);
    }

    public TextField getIdField() {
        return this.idField;
    }

    public ComboBox<Group> getGroupComboBox() {
        return this.groupComboBox;
    }
}
