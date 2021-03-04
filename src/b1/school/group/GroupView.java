package b1.school.group;

import b1.View;
import b1.school.person.Student;
import b1.school.person.StudentController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        if(this.stage == null) {
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
        BorderPane borderPane = new BorderPane();

        //object for stage
        Label groupCodeLabel = new Label("klas naam: ");
        Label studentsLabel = new Label("studenten: ");
        Label selectedStudentLabel = new Label("Open de geselecteerde student");

        this.groupCodeTextField = new TextField(this.group.getGroupCode());

        this.studentListView = new ListView<>();
        studentListView.setPrefHeight(200);
        for(Student student : this.group.getStudentsList()) {
            studentListView.getItems().add(student);
        }

        this.studentButton = new Button("open student");
        this.applyButton = new Button("Apply");
        this.okButton = new Button("Ok");

        //ordering
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                groupCodeLabel, groupCodeTextField,
                studentsLabel, studentListView,
                selectedStudentLabel, studentButton,
                applyButton, okButton
        );
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setTop(hBox);

        //applying to stage
        stage.setScene(new Scene(borderPane));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
    }

}
