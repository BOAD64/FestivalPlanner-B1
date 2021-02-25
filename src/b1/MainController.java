package b1;

import b1.school.classroom.Classroom;
import b1.school.classroom.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController extends Application implements Controller {

    private MainView view;

    @Override
    public void show() {
        this.view = new MainView();
        this.view.getStage().show();

        groupTest();
        classroomTest();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show();
    }

    public void groupTest() {
        ArrayList<Student> students = new ArrayList<>();

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
}
