package b1;

import b1.school.person.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        startGUI();
    }

    public static void startGUI() {
        Application.launch(MainView.class);
    }
    
}
