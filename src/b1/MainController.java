package b1;

import b1.io.SchoolFile;
import b1.schedule.Lesson;
import b1.schedule.LessonController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.*;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Controller
{

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

        this.scheduleController = new ScheduleController();
        this.view.setScheduleControllerNode(this.scheduleController.getNode());
        this.fillAddMenuList(this.view.getAddList());

        this.view.getAddList().setOnMouseClicked(this::onAddListClicked);
        stage.show();
    }

    private void fillAddMenuList(ListView<AddMenuItem> addMenu) {
        addMenu.getItems().add(new AddMenuItem(GroupController.class, "Groep"));
        addMenu.getItems().add(new AddMenuItem(ClassroomController.class, "Klaslokaal"));
        addMenu.getItems().add(new AddMenuItem(StudentController.class, "Student"));
        addMenu.getItems().add(new AddMenuItem(TeacherController.class, "Docent"));
        addMenu.getItems().add(new AddMenuItem(LessonController.class, "Les"));

    }

    private void onAddListClicked(MouseEvent event) {
        AddMenuItem menuItem = this.view.getAddList().getSelectionModel().getSelectedItem();
        menuItem.onclick();
//
//        switch (controller) {
//            case GROUP:
//                Group group = new Group("unnamed");
//                this.school.addGroup(group);
//                GroupController groupController = new GroupController(group);
//                groupController.show();
//                break;
//            case CLASSROOM:
//                Classroom classroom = new Classroom(0, 0, "unnamed", 0);
//                this.school.addRoom(classroom);
//                ClassroomController classroomController = new ClassroomController(classroom);
//                classroomController.show();
//                break;
//            case STUDENT:
//                Student student = new Student();
//                this.school.addStudent(student);
//                StudentController studentController = new StudentController(student);
//                studentController.show();
//                break;
//            case TEACHER:
//                Teacher teacher = new Teacher();
//                this.school.addTeacher(teacher);
//                TeacherController teacherController = new TeacherController(teacher);
//                teacherController.show();
//                break;
//            case APPOINTMENT: {
//                Lesson lesson = new Lesson(null, null, null, null, null, null, null);
//                this.school.getSchedule().getAppointments().add(lesson);
//                LessonController lessonController = new LessonController(this.school, lesson);
//                lessonController.onClose(event1 -> {this.scheduleController.refresh();});
//                lessonController.show();
//                break;
//            }
//        }

        this.view.onAddListClicked(event);
    }
}
