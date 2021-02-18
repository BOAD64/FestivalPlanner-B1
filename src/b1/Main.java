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
    
}
