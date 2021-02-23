package b1;

import b1.Controller;
import b1.school.person.Student;
import b1.school.person.StudentController;
import b1.school.person.Teacher;
import b1.school.person.TeacherController;

public class MainViewController implements Controller {

    private MainView view;

    @Override
    public void show() {
        if(!view.getStage().isShowing()){
            this.onAction();
            view.getStage().show();
        }
    }

    private void onAction() {
        this.view.getButton().setOnAction(e -> {
            Teacher teacher = new Teacher();
            TeacherController controller = new TeacherController(teacher);
            controller.show();
        });
    }
}
