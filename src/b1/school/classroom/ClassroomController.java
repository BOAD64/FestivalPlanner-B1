package b1.school.classroom;

import b1.Controller;

import java.util.ArrayList;

public class ClassroomController implements Controller {
    private ClassroomView classroomView;
    private Classroom classroom;


    public ClassroomController(Classroom classroom) {
        this.classroomView = new ClassroomView(classroom);
        this.classroom = classroom;
    }

    public ClassroomView getClassroomView() {
        return classroomView;
    }

    @Override
    public void show() {
        classroomView.getStage().show();
    }
}
