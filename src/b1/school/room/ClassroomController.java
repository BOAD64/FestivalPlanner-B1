package b1.school.room;

import b1.Controller;
import b1.ErrorMessage;
import b1.io.SchoolFile;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClassroomController implements Controller {
    private ClassroomView classroomView;
    private Classroom classroom;

    public ClassroomController() {
        this(new Classroom(0, 0, "", 0));
    }

    public ClassroomController(Classroom classroom) {
        this.classroomView = new ClassroomView(classroom);
        this.classroom = classroom;
    }

    public ClassroomView getClassroomView() {
        return classroomView;
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        Stage stage = classroomView.getStage();
        this.classroomView.getOkButton().setOnAction(event -> this.onOkButtonClicked());
        this.classroomView.getApplyButton().setOnAction(event -> this.onApplyButtonClicked());

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ownerStage);
        stage.show();
    }

    private void onOkButtonClicked() {
        if (this.onApplyButtonClicked()) {
            this.getClassroomView().getStage().close();
        }
    }

    private boolean onApplyButtonClicked() {
        try {
            this.classroom.setCapacity(Integer.parseInt(this.classroomView.getClassroomCapacity().getText()));
            this.classroom.setRoomCode(this.classroomView.getClassroomCode().getText());
            this.classroom.setLength(Double.parseDouble(this.getClassroomView().getClassroomLength().getText()));
            this.classroom.setWidth(Double.parseDouble(this.getClassroomView().getClassroomWidth().getText()));
            SchoolFile.getSchool().addClassroom(this.classroom);
        } catch (Exception e) {
            ErrorMessage.show();
            return false;
        }
        return true;
    }

}
