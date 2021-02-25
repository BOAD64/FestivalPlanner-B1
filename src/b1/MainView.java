package b1;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView implements View {

    @Override
    public Stage getStage() {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setScene(new Scene(borderPane));
        return stage;
    }
}