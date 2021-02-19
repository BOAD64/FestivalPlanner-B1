package b1.school.group;

import b1.View;
import b1.school.person.Student;
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

    public GroupView(Group group) {
        this.group = group;
    }

    @Override
    public Stage getStage() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        //object for stage
        Label groupCodeLabel = new Label("klas naam: ");
        Label studentsLabel = new Label("studenten: ");
        Label selectedStudentLabel = new Label("Open de geselecteerde student");

        TextField groupCodeTextField = new TextField(this.group.getGroupCode());

        ListView<Student> studentListView = new ListView<>();
        studentListView.setPrefHeight(200);
        for (Student student : this.group.getStudentsList()) {
            studentListView.getItems().add(student);
        }

        Button studentButton = new Button("open student");
        Button applyButton = new Button("Apply");
        Button okButton = new Button("Ok");

        //ordering
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                groupCodeLabel, groupCodeTextField,
                studentsLabel, studentListView,
                selectedStudentLabel, studentButton,
                applyButton, okButton
        );
        vBox.setSpacing(10);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(20);

        borderPane.setTop(hBox);

        //button actions
        studentButton.setOnAction( event -> {
            //todo: create a studentcontroller with selected student, studentController.getStage().show()

            Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();

            /*
            StudentController studentController = new StudentControllor(selectedStudent);
            studentController.getStage().show();
            */
        });
        applyButton.setOnAction( event -> {
            //read and save from text fields
            this.group.setGroupCode(groupCodeTextField.getText());
        });
        okButton.setOnAction( event -> {
            //read and save from text fields
            this.group.setGroupCode(groupCodeTextField.getText());
            //exit window
            stage.close();
        });

        //applying to stage
        stage.setScene(new Scene(borderPane));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        return stage;
    }

}
