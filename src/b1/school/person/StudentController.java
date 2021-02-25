package b1.school.person;

import b1.Controller;

public class StudentController extends PersonController implements Controller {

    private StudentView view;
    private Student student;

    public StudentController(Student student) {
        this.view = new StudentView(student);
        this.student = student;
    }

    /**
     * When this method is called it wil open the add student screen, only if its not open already.
     */
    @Override
    public void show() {
        if(!this.view.getStage().isShowing()){
            this.view.getStage().show();
            this.view.getSaveButton().setOnAction(e -> this.saveStudent());
            this.view.getUndoButton().setOnAction(e -> this.undoChanges());
            this.view.getCancelButton().setOnAction(e -> this.view.getStage().close());
        }
    }

    //saves the Student if the input fields have valid values, otherwise it shows an error massage
    private void saveStudent() {
        try {
            if(this.view.getGroupField().getText().isEmpty()|| this.view.getIdField().getText().isEmpty() ||
                    Integer.parseInt(this.view.getIdField().getText()) < 0 || !super.personIsValid(this.view)) {
                super.showErrorMessage();

            } else {
                this.student.setName(this.view.getNameField().getText());
                this.student.setAge(Short.parseShort(this.view.getAgeField().getText()));
                this.student.setGender(this.view.getGenderField().getText());
                this.student.setIdNumber(Short.parseShort(this.view.getIdField().getText()));
                this.student.setGroup(this.view.getGroupField().getText());

                //ToDo add student to school

                this.view.getStage().close();
            }
        } catch(Exception e) {
            super.showErrorMessage();
        }
    }

    //sets the input fields back to the information that was shown upon opening the window
    private void undoChanges() {
        if(this.student.getAge() == -1) {
            this.view.getIdField().setText("");
            this.view.getGroupField().setText("");
            this.view.getNameField().setText("");
            this.view.getAgeField().setText("");
            this.view.getGenderField().setText("");
        } else {
            this.view.getIdField().setText(student.getIdNumber() + "");
            this.view.getGroupField().setText(student.getGroup());
            this.view.getNameField().setText(student.getName());
            this.view.getAgeField().setText(student.getAge() + "");
            this.view.getGenderField().setText(student.getGender());
        }
    }
}