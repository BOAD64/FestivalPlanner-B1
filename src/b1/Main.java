package b1;

import b1.school.classroom.ClassroomController;
import b1.school.classroom.ClassroomView;
import javafx.application.Application;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        //start program
        initialize();
        startGUI();
    }

    public static void startGUI(){
        //Application.launch(MainView.class);
        Application.launch(ClassroomView.class);
    }

    public static void initialize(){

        ClassroomController classroomController = new ClassroomController();
        ClassroomView classroomView = new ClassroomView(classroomController);

    }
}
