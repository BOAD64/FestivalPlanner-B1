package b1.school.person;

import b1.Controller;
import b1.io.SchoolFile;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TeacherController extends PersonController implements Controller {

    private TeacherView view;
    private Teacher teacher;

    public TeacherController()
    {
        this(new Teacher());
    }

    public TeacherController(Teacher teacher) {
        this.view = new TeacherView(teacher);
        this.teacher = teacher;
    }

    /**
     * When this method is called it wil open the add student screen, only if its not open already.
     */
    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        if(!this.view.getStage().isShowing()){
            this.view.getSaveButton().setOnAction(e -> this.saveTeacher());
            this.view.getUndoButton().setOnAction(e -> this.undoChanges());
            this.view.getCancelButton().setOnAction(e -> this.view.getStage().close());
            this.view.getStage().initModality(Modality.WINDOW_MODAL);
            this.view.getStage().initOwner(ownerStage);
            this.view.getStage().show();
        }
    }

    //saves the Teacher if the input fields have valid values, otherwise it shows an error massage
    private void saveTeacher() {
        try {
            if(this.view.getSubjectField().getText().isEmpty() || !super.personIsValid(this.view)) {
                super.showErrorMessage();

            } else {
                this.teacher.setName(this.view.getNameField().getText());
                this.teacher.setAge(Short.parseShort(this.view.getAgeField().getText()));
                this.teacher.setGender(this.view.getGenderField().getText());
                this.teacher.setSubject(this.view.getSubjectField().getText());

                //ToDo add teacher to school
                SchoolFile.getSchool().addTeacher(this.teacher);

                this.view.getStage().close();
            }
        } catch(Exception e) {
            super.showErrorMessage();
        }
    }

    //sets the input fields back to the information that was shown upon opening the window
    private void undoChanges() {
        if(this.teacher.getAge() == -1) {
            this.view.getSubjectField().setText("");
            this.view.getNameField().setText("");
            this.view.getAgeField().setText("");
            this.view.getGenderField().setText("");
        } else {
            this.view.getSubjectField().setText(teacher.getSubject());
            this.view.getNameField().setText(teacher.getName());
            this.view.getAgeField().setText(teacher.getAge() + "");
            this.view.getGenderField().setText(teacher.getGender());
        }
    }
}
