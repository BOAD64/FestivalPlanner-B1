package b1;

import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.SchoolController;
import b1.school.person.StudentController;
import b1.school.person.Teacher;
import b1.school.person.TeacherController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;


public class MainView implements View {
    private Stage stage;
    private boolean addListIsShowing;
    private boolean hamburgerIsOut;
    ListView<Controllers> addList;
    private School school;
    private ArrayList<School> schools;

    private BorderPane borderPane;
    private ComboBox<School> schoolComboBox;

    enum Controllers{SCHOOL, GROUP,CLASSROOM, STUDENT, TEACHER, APPOINTMENT}

    public MainView(ArrayList<School> schools){
        this.schools = schools;
    }

    @Override
    public Stage getStage() {

        createStage();
        return this.stage;
    }

    private void createStage() {
        this.stage = new Stage();
        this.borderPane = new BorderPane();
        FileInputStream plusInputStream = null;
        FileInputStream arrowInputStream = null;
        try{
            plusInputStream = new FileInputStream("resources\\plus.png");
            arrowInputStream = new FileInputStream("resources\\arrow.png");

        } catch (Exception e){
            e.printStackTrace();
        }
        if (plusInputStream != null && arrowInputStream != null) {
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
                        School school = new School("unnamed");
                        schools.add(school);
                        SchoolController schoolController = new SchoolController(school);
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


                        StudentController studentController = new StudentController(new Student());
                        studentController.show();


                        System.out.println("not yet implemented");
                        break;
                    case TEACHER:
                        this.changeVisibilityOfAddList();

                        TeacherController teacherController = new TeacherController(new Teacher());
                        teacherController.show();

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

            //Add combobox(es) in Vbox to test hamburger menu
            this.schoolComboBox = new ComboBox<>();
            for (School s : schools){
                this.schoolComboBox.getItems().add(s);
            }
             //test
            VBox comboBoxes = new VBox();
            comboBoxes.setMinWidth(150);
            comboBoxes.setAlignment(Pos.TOP_CENTER);
            comboBoxes.getChildren().add(this.schoolComboBox);

            comboBoxes.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0.255),
                    CornerRadii.EMPTY, Insets.EMPTY)));

            //Opening and closing of hamburger menu
            arrowImageView.setOnMouseClicked(event -> {

                if (this.hamburgerIsOut) {
                    this.hamburgerIsOut = false;
                    hamburger.getChildren().remove(0, 2);
                    arrowImageView.setRotate(0);
                    hamburger.getChildren().addAll(arrowImageView);
                    updateBorderPane();

                } else {
                    this.hamburgerIsOut = true;
                    hamburger.getChildren().remove(0, 1);
                    arrowImageView.setRotate(180);
                    hamburger.getChildren().addAll(comboBoxes, arrowImageView);
                    updateBorderPane();

                }
            });

            VBox addMenu = new VBox();
            addMenu.getChildren().addAll(addList, plusImageView);
            addMenu.setSpacing(20);
            addMenu.setAlignment(Pos.BOTTOM_RIGHT);


            borderPane.setLeft(hamburger);
            borderPane.setRight(addMenu);


            updateBorderPane();

            //borderPane.setCenter(new Label(this.school.getSchoolName()));

            BorderPane.setMargin(addMenu, new Insets(5, 20, 20, 5));
            BorderPane.setAlignment(addMenu, Pos.BOTTOM_RIGHT);
            HBox.setMargin(arrowImageView, new Insets( 5, 5, 5 ,5));

            this.stage.setScene(new Scene(borderPane));
            this.stage.setMinHeight(600);
            this.stage.setMinWidth(600);
        }
    }

    private void updateBorderPane(){
        for (School s : schools){
            if (!this.schoolComboBox.getItems().contains(s)){
                this.schoolComboBox.getItems().add(s);
            }

        }
        if (this.schoolComboBox.getValue() != null){
            ScheduleController scheduleController = new ScheduleController(this.schoolComboBox.getValue().getSchedule());
            borderPane.setCenter(scheduleController.getNode());
        }
    }

    private void changeVisibilityOfAddList(){
        this.addListIsShowing = !this.addListIsShowing;
        addList.setVisible(this.addListIsShowing);
    }
}