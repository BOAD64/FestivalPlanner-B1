package b1.school.person;

import b1.Setting;
import b1.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentGeneratorView implements View {
    private Stage stage;
    private VBox mainBox = new VBox();
    private Button saveButton;
    private Button cancelButton;
    private TextField textField;

    @Override
    public Stage getStage() {
        if (this.stage == null) {
            this.createStage();
        }
        return this.stage;
    }

    private void createStage() {
        this.stage = new Stage();

        this.initMainBox();

        Scene scene = new Scene(this.mainBox);
        this.stage.setHeight(375);
        this.stage.setWidth(400);
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.setTitle("Genereer Studenten");
    }

    private void initMainBox() {
        Label label = new Label("Hoeveelheid: ");
        this.textField = new TextField();

        label.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.textField.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);

        HBox mainHBox = new HBox(label, this.textField);
        mainHBox.getChildren().addAll();
        mainHBox.setSpacing(5);
        mainHBox.setAlignment(Pos.TOP_CENTER);

        this.mainBox.getChildren().addAll(mainHBox);

        this.createButtons();
    }

    private void createButtons() {
        this.saveButton = new Button("Opslaan");
        this.cancelButton = new Button("Annuleren");

        this.saveButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        this.cancelButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(this.cancelButton, this.saveButton);
        buttonHBox.setSpacing(25);
        buttonHBox.setAlignment(Pos.CENTER);
        this.mainBox.getChildren().add(buttonHBox);
        this.mainBox.setSpacing(25);
        this.mainBox.setAlignment(Pos.TOP_CENTER);
    }

    TextField getTextField() {
        return textField;
    }

    Button getSaveButton() {
        return this.saveButton;
    }

    Button getCancelButton() {
        return this.cancelButton;
    }
}
