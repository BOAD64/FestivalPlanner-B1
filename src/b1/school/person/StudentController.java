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
        if(!view.getStage().isShowing()){
            view.getStage().show();
            this.view.getSaveButton().setOnAction(e -> this.saveStudent());
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
            }
        } catch(Exception e) {
            super.showErrorMessage();
        }
    }


}