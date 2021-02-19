package b1.school;

import b1.View;
import b1.school.classroom.Classroom;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SchoolView implements View {

    private Stage stage;
    private School school;
    private TextField schoolNameField;
    private ListView<Classroom> classroomListView;
    private ListView<Group> groupListView;
    private ListView<Student> studentListView;
    private ListView<Teacher> teacherListView;
    private Button selectClassroomButton;
    private Button selectGroupButton;
    private Button selectStudentButton;
    private Button selectTeacherButton;
    private Button applyButton;
    private Button okButton;


    public SchoolView(School school) {
        this.school = school;
        this.createStage();
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    private void createStage(){
        BorderPane borderPane = new BorderPane();
        Label schoolNameLabel = new Label("Schoolnaam:");
        this.schoolNameField = new TextField(this.school.getSchoolName());
        this.schoolNameField.setMaxWidth(500);
        this.createListViews();

        Label classroomLabel = new Label("lokaalen: ");
        Label groupLabel = new Label("klassen: ");
        Label studentLabel = new Label("studenten: ");
        Label teacherLabel = new Label("leraren: ");

        this.selectClassroomButton = new Button("Open lokaal");
        this.selectGroupButton = new Button("Open klas");
        this.selectStudentButton = new Button("Open student");
        this.selectTeacherButton = new Button("Open leraar");
        this.applyButton = new Button("Apply");
        this.okButton = new Button("Ok");

        GridPane gridPane = new GridPane();
        gridPane.add(classroomLabel, 1, 1);
        gridPane.add(groupLabel, 2, 1);
        gridPane.add(this.classroomListView, 1, 2);
        gridPane.add(this.groupListView, 2, 2);
        gridPane.add(this.selectClassroomButton, 1, 3);
        gridPane.add(this.selectGroupButton, 2, 3);
        gridPane.add(studentLabel, 1, 4);
        gridPane.add(teacherLabel, 2, 4);
        gridPane.add(this.studentListView, 1, 5);
        gridPane.add(this.teacherListView, 2, 5);
        gridPane.add(this.selectStudentButton, 1, 6);
        gridPane.add(this.selectTeacherButton, 2, 6);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                schoolNameLabel, this.schoolNameField,
                gridPane, this.applyButton, this.okButton
        );

        vBox.setSpacing(10);
        borderPane.setTop(vBox);
        this.stage = new Stage();
        this.stage.setScene(new Scene(borderPane));
        stage.setMinWidth(600);
        stage.setMinHeight(700);
        stage.setMaxWidth(600);
        stage.setMaxHeight(700);
    }

    private void createListViews(){
        this.classroomListView = new ListView<>();
        this.groupListView = new ListView<>();
        this.studentListView = new ListView<>();
        this.teacherListView = new ListView<>();

        this.classroomListView.setPrefHeight(200);
        this.groupListView.setPrefHeight(200);
        this.studentListView.setPrefHeight(200);
        this.teacherListView.setPrefHeight(200);

        for (Classroom classroom : this.school.getClassrooms()){
            this.classroomListView.getItems().add(classroom);
        }

        for (Group group : this.school.getGroups()){
            this.groupListView.getItems().add(group);
        }

        for (Student student : this.school.getStudents()){
            this.studentListView.getItems().add(student);
        }

        for (Teacher teacher : this.school.getTeachers()){
            this.teacherListView.getItems().add(teacher);
        }
    }

    public ListView<Classroom> getClassroomListView() {
        return classroomListView;
    }

    public ListView<Group> getGroupListView() {
        return groupListView;
    }

    public ListView<Student> getStudentListView() {
        return studentListView;
    }

    public ListView<Teacher> getTeacherListView() {
        return teacherListView;
    }

    public Button getSelectClassroomButton() {
        return selectClassroomButton;
    }

    public Button getSelectGroupButton() {
        return selectGroupButton;
    }

    public Button getSelectStudentButton() {
        return selectStudentButton;
    }

    public Button getSelectTeacherButton() {
        return selectTeacherButton;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public Button getOkButton() {
        return okButton;
    }
}
