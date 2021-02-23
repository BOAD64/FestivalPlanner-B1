package b1.school.person;

import b1.Controller;

public class TeacherController extends PersonController implements Controller {

    private TeacherView view;
    private Teacher teacher;

    public TeacherController(Teacher teacher) {
        this.view = new TeacherView(teacher);
        this.teacher = teacher;
    }

    @Override
    public void show() {
        if(!this.view.getStage().isShowing()){
            this.view.getStage().show();
            this.view.getSaveButton().setOnAction(e -> this.saveTeacher());
            this.view.getUndoButton().setOnAction(e -> this.undoChanges());
        }
    }

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

                this.view.getStage().close();
            }
        } catch(Exception e) {
            super.showErrorMessage();
        }
    }

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
