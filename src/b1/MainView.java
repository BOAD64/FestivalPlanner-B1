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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private boolean hamburgerIsOut;
    ListView<Controllers> addList;

    enum Controllers{SCHOOL, GROUP,CLASSROOM, STUDENT, TEACHER, APPOINTMENT}

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
        FileInputStream plusInputStream = null;
        FileInputStream arrowInputStream = null;
        try{
            plusInputStream = new FileInputStream("resources\\plus.png");
            arrowInputStream = new FileInputStream("resources\\arrow.png");

        } catch (Exception e){
            e.printStackTrace();
        }
        if (plusInputStream != null && arrowInputStream != null){
            Image plus = new Image(plusInputStream);
            Image arrow = new Image(arrowInputStream);
            ImageView plusImageView = new ImageView(plus);
            ImageView arrowImageView = new ImageView(arrow);

            this.addList = new ListView<>();
            this.addList.setMaxHeight(200);
            this.addList.setMaxWidth(150);
            this.addList.getItems().addAll(Controllers.values());
            this.addListIsShowing = false;
            this.addList.setVisible(this.addListIsShowing);

            this.hamburgerIsOut = false;

            plusImageView.setOnMouseClicked(event -> {
                this.changeVisibilityOfAddList();
            });

            this.addList.setOnMouseClicked(event -> {
                Controllers controller = this.addList.getSelectionModel().getSelectedItem();

                switch (controller) {
                    case SCHOOL:
                        this.changeVisibilityOfAddList();
                        SchoolController schoolController = new SchoolController(new School("unnamed"));
                        schoolController.show();
                        break;
                    case GROUP:
                        this.changeVisibilityOfAddList();
                        GroupController groupController = new GroupController(new Group("unnamed"));
                        groupController.show();
                        break;
                    case CLASSROOM:
                        this.changeVisibilityOfAddList();
                        ClassroomController classroomController = new ClassroomController(
                                new Classroom(0, 0, "unnamed", 0)
                        );
                        classroomController.show();
                        break;
                    case STUDENT:
                        this.changeVisibilityOfAddList();
                        /*
                        TeacherController teacherController = new TeachterController(new Teacher());
                        teacherController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                    case TEACHER:
                        this.changeVisibilityOfAddList();
                        /*
                        StudentController studentController = new StudentControllor(new Student());
                        studentController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                    case APPOINTMENT:
                        this.changeVisibilityOfAddList();
                        /*
                        AppointmentController appointmentController = new AppointmentControllor(new Appointment());
                        appointmentController.show();
                        */
                        System.out.println("not yet implemented");
                        break;
                }
            });

            //create HBox used for whole hamburger-menu
            HBox hamburger = new HBox();
            hamburger.getChildren().add(arrowImageView);
            hamburger.setSpacing(20);

            //Add combobox(es) in Vbox to test hamburger menu
            ComboBox<String> testComboBox = new ComboBox<>();
            testComboBox.getItems().addAll("pain", "suffering", "agony");
            VBox comboBoxes = new VBox();
            comboBoxes.setMinWidth(150);
            comboBoxes.setAlignment(Pos.TOP_CENTER);
            comboBoxes.getChildren().add(testComboBox);

            comboBoxes.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0.255),
                    CornerRadii.EMPTY, Insets.EMPTY)));

            //Opening and closing of hamburger menu
            arrowImageView.setOnMouseClicked(event -> {

                if (this.hamburgerIsOut){
                    this.hamburgerIsOut = false;
                    hamburger.getChildren().remove(0, 2);
                    arrowImageView.setRotate(0);
                    hamburger.getChildren().addAll(arrowImageView);

                } else {
                    this.hamburgerIsOut = true;
                    hamburger.getChildren().remove(0, 1);
                    arrowImageView.setRotate(180);
                    hamburger.getChildren().addAll(comboBoxes, arrowImageView);

                }
            });

            VBox addMenu = new VBox();
            addMenu.getChildren().addAll(addList, plusImageView);
            addMenu.setSpacing(20);
            addMenu.setAlignment(Pos.BOTTOM_RIGHT);



            borderPane.setTop(hamburger);
            borderPane.setBottom(addMenu);

            BorderPane.setMargin(addMenu, new Insets(5, 20, 20, 5));
            BorderPane.setAlignment(addMenu, Pos.BOTTOM_RIGHT);
        }

        this.stage.setScene(new Scene(borderPane));
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(600);
    }

    private void changeVisibilityOfAddList(){
        this.addListIsShowing = !this.addListIsShowing;
        addList.setVisible(this.addListIsShowing);
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