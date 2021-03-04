package b1.school.room;

import b1.Controller;
import b1.io.SchoolFile;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClassroomController implements Controller {
    private ClassroomView classroomView;
    private Classroom classroom;

    public ClassroomController()
    {
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
        this.classroomView.getOkButton().setOnAction(event -> this.okButton());
        this.classroomView.getApplyButton().setOnAction(event -> this.applyButton());

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ownerStage);
        stage.show();
    }

    private void okButton(){
        this.applyButton();
        this.getClassroomView().getStage().close();
    }

    private void applyButton(){
        this.classroom.setCapacity(Integer.parseInt(this.classroomView.getClassroomCapacity().getText()));
        this.classroom.setRoomCode(this.classroomView.getClassroomCode().getText());
        this.classroom.setLength(Double.parseDouble(this.getClassroomView().getClassroomLength().getText()));
        this.classroom.setWidth(Double.parseDouble(this.getClassroomView().getClassroomWidth().getText()));
        SchoolFile.getSchool().addClassroom(this.classroom);
    }

}
