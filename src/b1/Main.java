package b1;

import javafx.application.Application;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        //start program

        startGUI();
    }

    public static void startGUI(){
        Application.launch(MainView.class);
    }
}
