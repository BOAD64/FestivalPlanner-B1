package b1;

import b1.io.SchoolFile;
import b1.school.School;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    private School school;

    public Main() {
        SchoolFile.setFilePath(System.getProperty("user.dir") + System.getProperty("file.separator")+"/data.dat");
        if (SchoolFile.getSchool() == null) {
            SchoolFile.setSchool(new School("lala land"));
        }
        this.school = SchoolFile.getSchool();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController mainController = new MainController();
        mainController.show();
        mainController.onClose(event -> {SchoolFile.save();});
    }
}
