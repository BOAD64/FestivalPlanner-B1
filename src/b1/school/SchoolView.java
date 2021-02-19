package b1.school;

import b1.View;
import b1.school.classroom.Classroom;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.awt.*;
import java.util.ArrayList;

public class SchoolView implements View {

    private Stage stage;
    private School school;
    private TextField schoolNameField;
    private ListView<Classroom> classroomListView;
    private ListView<Group> groupListView;
    private ListView<Student> studentListView;
    private ListView<Teacher> teacherListView;


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
        this.createListViews();

        this.stage = new Stage();

    }

    private void createListViews(){
        this.classroomListView = new ListView<>();
        this.classroomListView.setPrefHeight(200);

        this.groupListView = new ListView<>();
        this.groupListView.setPrefHeight(200);

        this.studentListView = new ListView<>();
        this.studentListView.setPrefHeight(200);

        this.teacherListView = new ListView<>();
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
}
