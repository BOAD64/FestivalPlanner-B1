package b1;

import b1.school.School;
import b1.school.SchoolController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    private boolean addListIsShowing;

    enum Controllers{SCHOOL, GROUP,CLASSROOM, STUDENT, TEACHER, APOINTMENT}

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

            ListView<Controllers> addList = new ListView<>();
            addList.setMaxHeight(200);
            addList.setMaxWidth(150);
            addList.getItems().addAll(Controllers.values());
            this.addListIsShowing = false;
            addList.setVisible(this.addListIsShowing);

            imageView.setOnMouseClicked(event -> {
                this.addListIsShowing = !this.addListIsShowing;
                addList.setVisible(this.addListIsShowing);
            });

            addList.setOnMouseClicked(event -> {
                Controllers controller = addList.getSelectionModel().getSelectedItem();

                switch (controller) {
                    case SCHOOL:
                        SchoolController schoolController = new SchoolController(new School("unnamed"));
                        schoolController.show();
                        break;
                    case GROUP:
                        GroupController groupController = new GroupController(new Group("unnamed"));
                        groupController.show();
                        break;
                    case CLASSROOM:
                        ClassroomController classroomController = new ClassroomController(
                                new Classroom(0, 0, "unnamed", 0)
                        );
                        classroomController.show();
                        break;
                    case STUDENT:
                        /*
                        TeacherController teacherController = new TeachterController(new Teacher());
                        teacherController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                    case TEACHER:
                        /*
                        StudentController studentController = new StudentControllor(new Student());
                        studentController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                    case APOINTMENT:
                        /*
                        ApointmentController apointmentController = new ApointmentControllor(new Apointment());
                        apointmentController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                }
            });

            VBox addMenu = new VBox();
            addMenu.getChildren().addAll(addList, imageView);
            addMenu.setSpacing(20);
            addMenu.setAlignment(Pos.BOTTOM_RIGHT);

            borderPane.setBottom(addMenu);
            BorderPane.setMargin(addMenu, new Insets(5, 20, 20, 5));
            BorderPane.setAlignment(addMenu, Pos.BOTTOM_RIGHT);
        }

        this.stage.setScene(new Scene(borderPane));
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(600);
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