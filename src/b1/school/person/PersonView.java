package b1.school.person;

import b1.Setting;
import b1.View;
import b1.io.ImageFile;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

abstract class PersonView implements View {

    Stage stage;
    private Button undoButton;
    private Button saveButton;
    private Button cancelButton;
    VBox mainVBox = new VBox();
    VBox tagsVBox = new VBox();
    VBox inputFieldVBox = new VBox();

    TextField nameField;
    TextField ageField;
    TextField genderField;

    /**
     * Initializes the main HBox and VBox where the TextFields and Labels are added to.
     */
    void initMainBox() {
        HBox mainHBox = new HBox();
        mainHBox.getChildren().addAll(this.tagsVBox, this.inputFieldVBox);
        mainHBox.setSpacing(5);
        mainHBox.setAlignment(Pos.TOP_CENTER);

        this.mainVBox.getChildren().add(mainHBox);
        this.createButtons();

        this.stage.getIcons().add(ImageFile.getLogo());
    }

    private void createButtons() {
        this.undoButton = new Button("Ongedaan maken");
        this.saveButton = new Button("Opslaan");
        this.cancelButton = new Button("Annuleren");

        this.undoButton.setPrefHeight(Setting.addMenuButtonHeigt);
        this.saveButton.setPrefHeight(Setting.addMenuButtonHeigt);
        this.cancelButton.setPrefHeight(Setting.addMenuButtonHeigt);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(this.undoButton, this.cancelButton, this.saveButton);
        buttonHBox.setSpacing(25);
        buttonHBox.setAlignment(Pos.CENTER);
        this.mainVBox.getChildren().add(buttonHBox);
        this.mainVBox.setSpacing(25);
        this.mainVBox.setAlignment(Pos.TOP_CENTER);
    }

    Button getUndoButton() {
        return this.undoButton;
    }

    Button getSaveButton() {
        return this.saveButton;
    }

    Button getCancelButton() {
        return cancelButton;
    }

    void createTags() {
        Label nameLabel = new Label("Naam:");
        Label ageLabel = new Label("Leeftijd:");
        Label genderLabel = new Label("Geslacht:");

        nameLabel.setPrefHeight(Setting.addMenuLabelAndTextHeight);
        ageLabel.setPrefHeight(Setting.addMenuLabelAndTextHeight);
        genderLabel.setPrefHeight(Setting.addMenuLabelAndTextHeight);

        this.tagsVBox.getChildren().addAll(nameLabel, ageLabel, genderLabel);
        this.tagsVBox.setSpacing(5);
    }

    void createInputField() {
        this.nameField = new TextField();
        this.ageField = new TextField();
        this.genderField = new TextField();

        this.nameField.setPrefHeight(Setting.addMenuLabelAndTextHeight);
        this.ageField.setPrefHeight(Setting.addMenuLabelAndTextHeight);
        this.genderField.setPrefHeight(Setting.addMenuLabelAndTextHeight);

        this.inputFieldVBox.getChildren().addAll(this.nameField, this.ageField, this.genderField);
        this.inputFieldVBox.setSpacing(5);
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
