package b1;

import b1.school.classroom.Classroom;
import b1.school.classroom.ClassroomController;
import b1.school.classroom.ClassroomView;
import javafx.application.Application;
import javafx.scene.Scene;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        //start program
        //initialize();
        startGUI();
    }

    public static void startGUI(){
        Application.launch(MainView.class);
    }


    public static void initialize(){

        ClassroomController classroomController = new ClassroomController();
        classroomController.addClassroom(new Classroom("LA136", 15));
        classroomController.addClassroom(new Classroom("LA247", 10));

        classroomController.show();

    }




}
