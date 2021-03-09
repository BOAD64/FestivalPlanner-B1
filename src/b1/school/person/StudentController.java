package b1.school.person;

import b1.Controller;
import b1.ErrorMessage;
import b1.io.SchoolFile;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentController extends PersonController implements Controller {

    private StudentView view;
    private Student student;

    public StudentController() {
        this(new Student());
    }

    public StudentController(Student student) {
        super();
        this.view = new StudentView(student);
        this.student = student;
    }

    /**
     * When this method is called it wil open the add student screen, only if its not open already.
     */
    @Override
    public void show() {
        show(null);
    }

    public void show(Stage ownerStage) {
        if(!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getSaveButton().setOnAction(e -> this.saveStudent());
            this.view.getUndoButton().setOnAction(e -> this.undoChanges());
            this.view.getCancelButton().setOnAction(e -> this.view.getStage().close());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            stage.show();
        }
    }

    //saves the Student if the input fields have valid values, otherwise it shows an error massage
    private void saveStudent() {
        try {
            if(this.view.getGroupComboBox().getSelectionModel().isEmpty()|| this.view.getIdField().getText().isEmpty() ||
                    Integer.parseInt(this.view.getIdField().getText()) < 0 || !super.personIsValid(this.view)) {
                ErrorMessage.show();

            } else {
                this.student.setName(this.view.getNameField().getText());
                this.student.setAge(Short.parseShort(this.view.getAgeField().getText()));
                this.student.setGender(this.view.getGenderField().getText());
                this.student.setIdNumber(Short.parseShort(this.view.getIdField().getText()));
                this.student.setGroup(this.view.getGroupComboBox().getValue());

                this.student.getGroup().addStudent(this.student);

                SchoolFile.getSchool().addStudent(this.student);

                this.view.getStage().close();
            }
        } catch(Exception e) {
            ErrorMessage.show();
        }
    }

    //sets the input fields back to the information that was shown upon opening the window
    private void undoChanges() {
        if(this.student.getAge() == -1) {
            this.view.getIdField().setText("");
            this.view.getGroupComboBox().getSelectionModel().clearSelection();
            this.view.getNameField().setText("");
            this.view.getAgeField().setText("");
            this.view.getGenderField().setText("");
        } else {
            this.view.getIdField().setText(this.student.getIdNumber() + "");
            this.view.getGroupComboBox().getSelectionModel().select(this.student.getGroup());
            this.view.getNameField().setText(this.student.getName());
            this.view.getAgeField().setText(this.student.getAge() + "");
            this.view.getGenderField().setText(this.student.getGender());
        }
    }
}