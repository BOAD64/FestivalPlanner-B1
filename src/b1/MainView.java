package b1;

import b1.school.School;
import b1.school.SchoolController;
import b1.school.classroom.Classroom;
import b1.school.classroom.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //groupTest();
        //classroomTest();
        schoolTest();
    }

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