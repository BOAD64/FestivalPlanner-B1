package b1.school.person;

import javafx.stage.Stage;

public class TeacherView extends PersonView {

    public TeacherView() {
        super.stage = new Stage();
    }

    @Override
    public Stage getStage() {
        return super.stage;
    }
}
