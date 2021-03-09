package b1.school;

import b1.View;
import b1.school.room.Classroom;
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
import javafx.scene.layout.HBox;
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
    private Button refreshClassroom;
    private Button refreshGroup;
    private Button refreshStudent;
    private Button refreshTeacher;
    private Button deleteClassroom;
    private Button deleteGroup;
    private Button deleteStudent;
    private Button deleteTeacher;


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

        Label classroomLabel = new Label("Lokalen: ");
        Label groupLabel = new Label("Klassen: ");
        Label studentLabel = new Label("Studenten: ");
        Label teacherLabel = new Label("Leraren: ");

        this.selectClassroomButton = new Button("Open lokaal");
        this.selectGroupButton = new Button("Open klas");
        this.selectStudentButton = new Button("Open student");
        this.selectTeacherButton = new Button("Open leraar");
        this.applyButton = new Button("Toepassen");
        this.okButton = new Button("Opslaan");

        this.refreshClassroom = new Button("Refresh");
        this.refreshGroup = new Button("Refresh");
        this.refreshStudent = new Button("Refresh");
        this.refreshTeacher = new Button("Refresh");

        this.deleteClassroom = new Button("Delete");
        this.deleteGroup = new Button("Delete");
        this.deleteStudent = new Button("Delete");
        this.deleteTeacher = new Button("Delete");

        HBox classroomButtons = new HBox();
        classroomButtons.setSpacing(10);
        HBox groupButtons = new HBox();
        groupButtons.setSpacing(10);
        HBox studentButtons = new HBox();
        studentButtons.setSpacing(10);
        HBox teacherButtons = new HBox();
        teacherButtons.setSpacing(10);

        classroomButtons.getChildren().addAll(this.selectClassroomButton, this.refreshClassroom, this.deleteClassroom);
        groupButtons.getChildren().addAll(this.selectGroupButton, this.refreshGroup, this.deleteGroup);
        studentButtons.getChildren().addAll(this.selectStudentButton, this.refreshStudent, this.deleteStudent);
        teacherButtons.getChildren().addAll(this.selectTeacherButton, this.refreshTeacher, this.deleteTeacher);

        GridPane gridPane = new GridPane();
        gridPane.add(classroomLabel, 1, 1);
        gridPane.add(groupLabel, 2, 1);
        gridPane.add(this.classroomListView, 1, 2);
        gridPane.add(this.groupListView, 2, 2);
        gridPane.add(classroomButtons, 1, 3);
        gridPane.add(groupButtons, 2, 3);
        gridPane.add(studentLabel, 1, 4);
        gridPane.add(teacherLabel, 2, 4);
        gridPane.add(this.studentListView, 1, 5);
        gridPane.add(this.teacherListView, 2, 5);
        gridPane.add(studentButtons, 1, 6);
        gridPane.add(teacherButtons, 2, 6);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                schoolNameLabel, this.schoolNameField,
                gridPane, this.applyButton, this.okButton
        );

        vBox.setSpacing(10);
        borderPane.setTop(vBox);
        this.stage = new Stage();
        this.stage.setScene(new Scene(borderPane));
        this.stage.setMinWidth(600);
        this.stage.setMinHeight(700);
        this.stage.setMaxWidth(600);
        this.stage.setMaxHeight(700);
    }

    private void createListViews(){
        this.createClassroomListView();
        this.createGroupListView();
        this.createStudentListView();
        this.createTeacherListView();
    }

    public void createClassroomListView(){
        this.classroomListView = new ListView<>();
        this.classroomListView.setPrefHeight(200);
        for (Classroom classroom : this.school.getClassrooms()){
            this.classroomListView.getItems().add(classroom);
        }
    }

    public void createGroupListView(){
        this.groupListView = new ListView<>();
        this.groupListView.setPrefHeight(200);
        for (Group group : this.school.getGroups()){
            this.groupListView.getItems().add(group);
        }
    }

    public void createStudentListView(){
        this.studentListView = new ListView<>();
        this.studentListView.setPrefHeight(200);
        for (Student student : this.school.getStudents()){
            this.studentListView.getItems().add(student);
        }
    }

    public void createTeacherListView(){
        this.teacherListView = new ListView<>();
        this.teacherListView.setPrefHeight(200);
        for (Teacher teacher : this.school.getTeachers()){
            this.teacherListView.getItems().add(teacher);
        }
    }

    public ListView<Classroom> getClassroomListView() {
        return this.classroomListView;
    }

    public ListView<Group> getGroupListView() {
        return this.groupListView;
    }

    public ListView<Student> getStudentListView() {
        return this.studentListView;
    }

    public ListView<Teacher> getTeacherListView() {
        return this.teacherListView;
    }

    public Button getSelectClassroomButton() {
        return this.selectClassroomButton;
    }

    public Button getSelectGroupButton() {
        return this.selectGroupButton;
    }

    public Button getSelectStudentButton() {
        return this.selectStudentButton;
    }

    public Button getSelectTeacherButton() {
        return this.selectTeacherButton;
    }

    public Button getApplyButton() {
        return this.applyButton;
    }

    public Button getOkButton() {
        return this.okButton;
    }

    public Button getRefreshClassroom() {
        return this.refreshClassroom;
    }

    public Button getRefreshGroup() {
        return this.refreshGroup;
    }

    public Button getRefreshStudent() {
        return this.refreshStudent;
    }

    public Button getRefreshTeacher() {
        return this.refreshTeacher;
    }

    public TextField getSchoolNameField() {
        return this.schoolNameField;
    }

    public Button getDeleteClassroom() {
        return this.deleteClassroom;
    }

    public Button getDeleteGroup() {
        return this.deleteGroup;
    }

    public Button getDeleteStudent() {
        return this.deleteStudent;
    }

    public Button getDeleteTeacher() {
        return this.deleteTeacher;
    }
}
