package b1;

import b1.schedule.Lesson;
import b1.schedule.LessonController;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.SchoolController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.group.StudentGroup;
import b1.school.person.Student;
import b1.school.person.StudentController;
import b1.school.person.Teacher;
import b1.school.person.TeacherController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.room.Room;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;

public class MainController implements Controller {

    private MainView view;
    private School school;
    private ArrayList<School> schools;

    private ScheduleController scheduleController;

    public MainController(ArrayList<School> schools) {
        this.schools = schools;
    }

    @Override
    public void show() {
        this.view = new MainView(this.schools);
        Stage stage = this.view.getStage();
        this.school = this.schools.get(0);
        this.view.getSchoolComboBox().setOnAction(this::onSchoolSelect);
        this.view.getSchoolComboBox().getSelectionModel().select(this.school);
        this.onSchoolSelect(null);
        this.view.getAddList().setOnMouseClicked(this::onAddListClicked);
        stage.show();
    }

    private void onAddListClicked(MouseEvent event)
    {
        MainView.Controllers controller = this.view.getAddList().getSelectionModel().getSelectedItem();

        switch (controller) {
            case SCHOOL:
                School school = new School("unnamed");
                this.schools.add(school);
                SchoolController schoolController = new SchoolController(school);
                schoolController.show();
                break;
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

    public void onSchoolSelect(ActionEvent event)
    {
        this.school = this.view.getSchoolComboBox().getSelectionModel().getSelectedItem();
        this.scheduleController = new ScheduleController(this.school, this.view.getSchoolComboBox().getValue().getSchedule());
        this.view.getBorderPane().setCenter(scheduleController.getNode());
        this.view.onSchoolSelect(event);
    }
}
