package b1;

import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainView extends Application {
    //Classroom c = new Classroom(420, 666, "lol34", 69);
    //private ClassroomController classroomController = new ClassroomController(c);

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        //classroomController.show();
    }
}