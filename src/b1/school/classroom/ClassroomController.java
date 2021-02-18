package b1.school.classroom;

import b1.Controller;
import b1.Main;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ClassroomController implements Controller {
    private ClassroomView classroomView;
    private ArrayList<Classroom> classrooms;


    public ClassroomController() {
        this.classroomView = new ClassroomView();
        this.classrooms = new ArrayList<>();
    }



    public void addClassroom(Classroom classroom) {
        if (!this.classrooms.contains(classroom)) {
            this.classrooms.add(classroom);
        }
    }

    public Classroom getClassroom(String roomCode) {
        for (Classroom classroom : this.classrooms) {
            if (classroom.getRoomCode().equals(roomCode)) {
                return classroom;
            }
        }
        return null;
    }

    public ClassroomView getClassroomView() {
        return classroomView;
    }

    public ArrayList<Classroom> getClassroomList() {
        return classrooms;
    }

    @Override
    public void show() {
        classroomView.addClassrooms(this.classrooms);
        classroomView.getStage().show();
    }
}
