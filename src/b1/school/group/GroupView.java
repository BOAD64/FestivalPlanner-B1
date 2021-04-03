package b1.school.group;

import b1.Setting;
import b1.View;
import b1.io.ImageFile;
import b1.school.person.Student;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GroupView implements View {
    private Group group;
    private Stage stage;

    private Button studentButton;
    private Button applyButton;
    private Button okButton;
    private ListView<Student> studentListView;
    private TextField groupCodeTextField;

    public GroupView(Group group) {
        this.group = group;
    }

    @Override
    public Stage getStage() {
        if (this.stage == null) {
            this.createStage();
        }

        return this.stage;
    }

    public Button getStudentButton() {
        return this.studentButton;
    }

    public Button getApplyButton() {
        return this.applyButton;
    }

    public Button getOkButton() {
        return this.okButton;
    }

    public ListView<Student> getStudentListView() {
        return this.studentListView;
    }

    public TextField getGroupCodeTextField() {
        return this.groupCodeTextField;
    }

    public void createStage() {
        this.stage = new Stage();

        //VBox that has the labels
        Label groupCodeLabel = new Label("Klas naam:");
        Label studentsLabel = new Label("Studenten:");
        groupCodeLabel.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        studentsLabel.setPrefHeight(200);
        VBox labelVBox = new VBox();
        labelVBox.getChildren().addAll(groupCodeLabel, studentsLabel);
        labelVBox.setSpacing(10);
        labelVBox.setPadding(new Insets(5, 5, 5, 20));

        //HBox that has the buttons on the bottom
        this.studentButton = new Button("Open student");
        this.applyButton = new Button("Toepasssen");
        this.okButton = new Button("Opslaan");
        this.studentButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        this.applyButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        this.okButton.setPrefHeight(Setting.ADD_MENU_BUTTON_HEIGHT);
        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(this.studentButton, this.applyButton, this.okButton);
        buttonsHBox.setSpacing(5);
        buttonsHBox.setAlignment(Pos.CENTER);

        //VBox that has the input fields
        this.groupCodeTextField = new TextField(this.group.getGroupCode());
        this.groupCodeTextField.setPrefHeight(Setting.ADD_MENU_LABEL_AND_TEXT_HEIGHT);
        this.studentListView = new ListView<>();
        this.studentListView.setPrefHeight(200);

        VBox inputFieldVBox = new VBox();
        inputFieldVBox.getChildren().addAll(this.groupCodeTextField, this.studentListView);
        inputFieldVBox.setSpacing(10);

        //ordering the boxes
        HBox topBox = new HBox();
        topBox.getChildren().addAll(labelVBox, inputFieldVBox);

        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(topBox, buttonsHBox);
        mainBox.setSpacing(20);
        mainBox.setPadding(new Insets(10, 10, 10, 10));

        //applying to stage
        this.stage.setScene(new Scene(mainBox));
        this.stage.setMinWidth(400);
        this.stage.setMinHeight(400);
        this.stage.setMaxWidth(400);
        this.stage.setMaxHeight(400);
        this.stage.getIcons().add(ImageFile.getLogo());
        this.stage.setTitle("Groep");
    }
}