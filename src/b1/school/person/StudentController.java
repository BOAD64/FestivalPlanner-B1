package b1.school.person;

import b1.Controller;

public class StudentController extends PersonController implements Controller {

    private StudentView view;
    private Student student;

    public StudentController(Student student) {
        this.view = new StudentView(student);
        this.student = student;
    }

    @Override
    public void show() {
        if(!this.view.getStage().isShowing()){
            this.view.getStage().show();
            this.view.getSaveButton().setOnAction(e -> this.saveStudent());
            this.view.getUndoButton().setOnAction(e -> this.undoChanges());
        }
    }

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