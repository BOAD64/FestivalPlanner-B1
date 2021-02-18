package b1.school.classroom;

import b1.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ClassroomController implements Controller {
    private ClassroomView classroomView;
    private ArrayList<Classroom> classrooms;



    public ClassroomController() {
        this.classroomView;
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

    public ArrayList<Classroom> getClassroomList() {
        return classrooms;
    }

    @Override
    public void show() {

    }
}
