package b1.school.group;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.person.Student;
import b1.school.person.StudentController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupController implements Controller
{

    private Group group;
    private GroupView groupView;

    public GroupController() {
        this(new Group(""));
    }

    public GroupController(Group group) {
        this.group = group;
        this.groupView = new GroupView(group);
    }

    public GroupView getGroupView() {
        return groupView;
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        //button actions
        this.groupView.createStage();
        this.groupView.getStudentButton().setOnAction(event -> this.onStudentButtonClick());
        this.groupView.getApplyButton().setOnAction(event -> this.onApplyButtonClick());
        this.groupView.getOkButton().setOnAction(event -> this.onOkButtonClick());
        this.groupView.getStage().initModality(Modality.WINDOW_MODAL);
        this.groupView.getStage().initOwner(ownerStage);
        this.groupView.getStage().show();
    }

    private void onStudentButtonClick() {
        Student selectedStudent = this.groupView.getStudentListView().getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            StudentController studentController = new StudentController(selectedStudent);
            studentController.show();
        }
    }

    private void onApplyButtonClick() {
        //read and save from text fields
        this.group.setGroupCode(this.groupView.getGroupCodeTextField().getText());
        SchoolFile.getSchool().addGroup(this.group);
    }

    private void onOkButtonClick() {
        //read and save from text fields
        this.onApplyButtonClick();
        //exit window
        this.groupView.getStage().close();
    }
}
