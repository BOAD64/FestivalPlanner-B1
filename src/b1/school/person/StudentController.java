package b1.school.person;

import b1.Controller;

public class StudentController implements Controller {

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
        }
    }

}