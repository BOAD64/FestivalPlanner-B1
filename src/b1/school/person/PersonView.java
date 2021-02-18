package b1.school.person;

import b1.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

abstract class PersonView implements View {

    Stage stage;
    Button undoButton;
    Button saveButton;
    short fieldHeight = 40;
    VBox mainVBox = new VBox();
    VBox tagsVBox = new VBox();
    VBox inputFieldVBox = new VBox();

    TextField nameField;
    TextField ageField;
    TextField genderField;

    void createButtons() {
        this.undoButton = new Button("Ongedaan maken");
        this.saveButton = new Button("Opslaan");

        this.undoButton.setPrefHeight(this.fieldHeight + 10);
        this.saveButton.setPrefHeight(this.fieldHeight + 10);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(this.undoButton, this.saveButton);
        buttonHBox.setSpacing(40);
        buttonHBox.setAlignment(Pos.CENTER);
        this.mainVBox.getChildren().add(buttonHBox);
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    void createTags() {
        Label nameLabel = new Label("Naam:");
        Label ageLabel = new Label("Leeftijd:");
        Label genderLabel = new Label("Geslacht:");

        nameLabel.setPrefHeight(this.fieldHeight);
        ageLabel.setPrefHeight(this.fieldHeight);
        genderLabel.setPrefHeight(this.fieldHeight);

        this.tagsVBox.getChildren().addAll(nameLabel, ageLabel, genderLabel);
        this.tagsVBox.setAlignment(Pos.TOP_RIGHT);
    }

    void createInputField() {
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField genderField = new TextField();

        nameField.setPrefHeight(this.fieldHeight);
        ageField.setPrefHeight(this.fieldHeight);
        genderField.setPrefHeight(this.fieldHeight);

        this.inputFieldVBox.getChildren().addAll(nameField, ageField, genderField);
        this.inputFieldVBox.setAlignment(Pos.TOP_RIGHT);
    }

    public TextField getNameField() {
        return this.nameField;
    }

    public TextField getAgeField() {
        return this.ageField;
    }

    public TextField getGenderField() {
        return this.genderField;
    }
}
