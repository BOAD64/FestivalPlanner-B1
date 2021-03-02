package b1;
import b1.io.SchoolFile;
import b1.school.person.*;
import b1.schedule.Lesson;
import b1.schedule.LessonController;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.SchoolController;
import b1.school.group.Group;
import b1.school.group.GroupController;
//import b1.school.person.Student;
//import b1.school.person.StudentController;
//import b1.school.person.Teacher;
//import b1.school.person.TeacherController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController implements Controller {

    private MainView view;
    private School school;

    private ScheduleController scheduleController;

    public MainController() {
    }

    @Override
    public void show() {
        this.view = new MainView();
        Stage stage = this.view.getStage();
        this.school = SchoolFile.getSchool();

        this.scheduleController = new ScheduleController(this.school, this.school.getSchedule());
        this.view.setScheduleControllerNode(this.scheduleController.getNode());

        this.view.getAddList().setOnMouseClicked(this::onAddListClicked);
        stage.show();
    }

    private void onAddListClicked(MouseEvent event)
    {
        MainView.Controllers controller = this.view.getAddList().getSelectionModel().getSelectedItem();

        switch (controller) {
            case GROUP:
                Group group = new Group("unnamed");
                this.school.addGroup(group);
                GroupController groupController = new GroupController(group);
                groupController.show();
                break;
            case CLASSROOM:
                Classroom classroom = new Classroom(0, 0, "unnamed", 0);
                this.school.addRoom(classroom);
                ClassroomController classroomController = new ClassroomController(classroom);
                classroomController.show();
                break;
            case STUDENT:
                Student student = new Student();
                this.school.addStudent(student);
                StudentController studentController = new StudentController(student);
                studentController.show();
                break;
            case TEACHER:
                Teacher teacher = new Teacher();
                this.school.addTeacher(teacher);
                TeacherController teacherController = new TeacherController(teacher);
                teacherController.show();
                break;
            case APPOINTMENT: {
                Lesson lesson = new Lesson(null, null, null, null, null, null, null);
                this.school.getSchedule().getAppointments().add(lesson);
                LessonController lessonController = new LessonController(this.school, lesson);
                lessonController.onClose(event1 -> {this.scheduleController.refresh();});
                lessonController.show();
                break;
            }
        }

        this.view.onAddListClicked(event);
    }
}
