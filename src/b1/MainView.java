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



public class MainView implements View {
    private Stage stage;
    private boolean addListIsShowing;
    private boolean hamburgerIsOut;
    ListView<Controllers> addList;
    private School school;

    enum Controllers{SCHOOL, GROUP,CLASSROOM, STUDENT, TEACHER, APPOINTMENT}

    public MainView(School school){
        this.school = school;
    }

    @Override
    public Stage getStage() {

        createStage();
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
            ComboBox<String> testComboBox = new ComboBox<>();
            testComboBox.getItems().addAll("pain", "suffering", "agony"); //test
            VBox comboBoxes = new VBox();
            comboBoxes.setMinWidth(150);
            comboBoxes.setAlignment(Pos.TOP_CENTER);
            comboBoxes.getChildren().add(testComboBox);

            comboBoxes.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0.255),
                    CornerRadii.EMPTY, Insets.EMPTY)));

            //Opening and closing of hamburger menu
            arrowImageView.setOnMouseClicked(event -> {

                if (this.hamburgerIsOut) {
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


            borderPane.setLeft(hamburger);
            borderPane.setRight(addMenu);



            ScheduleController scheduleController = new ScheduleController(this.school.getSchedule());
            borderPane.setCenter(scheduleController.getNode());

            //borderPane.setCenter(new Label(this.school.getSchoolName()));

            BorderPane.setMargin(addMenu, new Insets(5, 20, 20, 5));
            BorderPane.setAlignment(addMenu, Pos.BOTTOM_RIGHT);
            HBox.setMargin(arrowImageView, new Insets( 5, 5, 5 ,5));

            this.stage.setScene(new Scene(borderPane));
            this.stage.setMinHeight(600);
            this.stage.setMinWidth(600);
        }
    }

    private void changeVisibilityOfAddList(){
        this.addListIsShowing = !this.addListIsShowing;
        addList.setVisible(this.addListIsShowing);
    }
}