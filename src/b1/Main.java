package b1;

import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    private MainViewController mainViewController = new MainViewController();

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainViewController.show();
    }

}
