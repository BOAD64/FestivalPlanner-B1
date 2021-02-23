package b1;

import b1.school.School;
import b1.school.SchoolController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainView extends Application implements View {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.createStage();
        this.stage.show();


        //groupTest();
        //classroomTest();
        //schoolTest();
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    private void createStage() {
        this.stage = new Stage();
        BorderPane borderPane = new BorderPane();
        FileInputStream inputStream = null;
        try{
            inputStream = new FileInputStream("resources\\plus.png");

        } catch (Exception e){
            e.printStackTrace();
        }
        if (inputStream != null){
            Image plus = new Image(inputStream);
            ImageView imageView = new ImageView(plus);

            imageView.setOnMouseClicked(event -> {
                borderPane.setCenter(new Label("yes"));
            });
            borderPane.setBottom(imageView);
            BorderPane.setAlignment(imageView, Pos.BOTTOM_RIGHT);
        }
        //Button plusButton = new Button();
        //plusButton.setGraphic(new ImageView(plus));



        this.stage.setScene(new Scene(borderPane));
    }



    //tests
    public void groupTest() {
        Student student1 = new Student("biebom", 1);
        Student student2 = new Student("hibie", 2);
        Student student3 = new Student("harry", 3);
        Student student4 = new Student("gg", 4);
        Student student5 = new Student("lolosr", 5);
        Student student6 = new Student("hybra", 6);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        Group group = new Group("gudgrup");
        group.setStudents(students);

        GroupController groupController = new GroupController(group);
        groupController.show();
    }

    public void classroomTest() {
        Classroom c = new Classroom(420, 666, "lol34", 69);
        ClassroomController classroomController = new ClassroomController(c);
        classroomController.show();
    }

    public void schoolTest() {
        Classroom c = new Classroom(420, 666, "lol34", 69);
        Classroom classroom = new Classroom(777, 3434, "holy", 45);

        School school = new School("haha reeee");
        school.addClassroom(classroom);
        SchoolController schoolController = new SchoolController(school);
        schoolController.show();
    }
}