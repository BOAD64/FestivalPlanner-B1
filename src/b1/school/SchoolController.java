package b1.school;

import b1.Controller;
import b1.ErrorMessage;
import b1.io.SchoolFile;
import b1.schedule.AppointmentAbstract;
import b1.schedule.Lesson;
import b1.school.person.StudentController;
import b1.school.person.TeacherController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import b1.school.person.Teacher;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class SchoolController implements Controller
{

    private School school;
    private SchoolView schoolView;

    public SchoolController() {
        this(SchoolFile.getSchool());
    }

    public SchoolController(School school) {
        this.school = school;
        this.schoolView = new SchoolView(this.school);
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {

        this.schoolView.getSelectClassroomButton().setOnAction(event -> this.onOpenClassroomButtonClicked());
        this.schoolView.getSelectGroupButton().setOnAction(event -> this.onOpenGroupButtonClicked());
        this.schoolView.getSelectStudentButton().setOnAction(event -> this.onOpenStudentButtonClicked());
        this.schoolView.getSelectTeacherButton().setOnAction(event -> this.onOpenTeacherButtonClicked());
        this.schoolView.getApplyButton().setOnAction(event -> this.onApplyButtonClicked());
        this.schoolView.getOkButton().setOnAction(event -> this.onOkButtonClicked());

        this.schoolView.getRefreshClassroomButton().setOnAction(event -> this.onRefreshClassroomButtonClicked());
        this.schoolView.getRefreshGroupButton().setOnAction(event -> this.onRefreshGroupButtonClicked());
        this.schoolView.getRefreshStudentButton().setOnAction(event -> this.onRefreshStudentButtonClicked());
        this.schoolView.getRefreshTeacherButton().setOnAction(event -> this.onRefreshTeacherButtonClicked());

        this.schoolView.getDeleteClassroomButton().setOnAction(event -> this.onDeleteClassroomButtonClicked());
        this.schoolView.getDeleteGroupButton().setOnAction(event -> this.onDeleteGroupButtonClicked());
        this.schoolView.getDeleteStudentButton().setOnAction(event -> this.onDeleteStudentButtonClicked());
        this.schoolView.getDeleteTeacherButton().setOnAction(event -> this.onDeleteTeacherButtonClicked());

        this.schoolView.getStage().initModality(Modality.WINDOW_MODAL);
        this.schoolView.getStage().initOwner(ownerStage);
        this.schoolView.getStage().show();
    }

    private void onOpenClassroomButtonClicked() {
        Classroom classroom = this.schoolView.getClassroomListView().getSelectionModel().getSelectedItem();

        if (!(classroom == null)) {
            ClassroomController classroomController = new ClassroomController(classroom);
            classroomController.show();
        }
    }

    private void onOpenGroupButtonClicked() {
        Group group = this.schoolView.getGroupListView().getSelectionModel().getSelectedItem();

        if (!(group == null)) {
            GroupController groupController = new GroupController(group);
            groupController.show();
        }
    }

    private void onOpenTeacherButtonClicked() {
        Teacher teacher = this.schoolView.getTeacherListView().getSelectionModel().getSelectedItem();

        if (!(teacher == null)) {
            TeacherController teacherController = new TeacherController(teacher);
            teacherController.show();
        }
    }

    private void onOpenStudentButtonClicked() {
        Student student = this.schoolView.getStudentListView().getSelectionModel().getSelectedItem();

        if (!(student == null)) {
            StudentController studentController = new StudentController(student);
            studentController.show();
        }

    }

    private void onApplyButtonClicked() {
        this.onRefreshClassroomButtonClicked();
        this.onRefreshGroupButtonClicked();
        this.onRefreshStudentButtonClicked();
        this.onRefreshTeacherButtonClicked();

        this.school.getGroups().clear();
        this.school.getPersons().clear();

        this.school.getGroups().addAll(this.schoolView.getGroupListView().getItems());
        this.school.getPersons().addAll(this.schoolView.getStudentListView().getItems());
        this.school.getPersons().addAll(this.schoolView.getTeacherListView().getItems());

        this.school.getRooms().removeIf(room -> room instanceof Classroom);
        this.school.getRooms().addAll(this.schoolView.getClassroomListView().getItems());
        this.school.setSchoolName(this.schoolView.getSchoolNameField().getText());
    }

    private void onOkButtonClicked() {
        this.onApplyButtonClicked();
        this.schoolView.getStage().close();
    }

    private void onRefreshClassroomButtonClicked() {
        this.schoolView.getClassroomListView().refresh();
    }

    private void onRefreshGroupButtonClicked() {
        this.schoolView.getGroupListView().refresh();
    }

    private void onRefreshStudentButtonClicked() {
        this.schoolView.getStudentListView().refresh();
    }

    private void onRefreshTeacherButtonClicked() {
        this.schoolView.getTeacherListView().refresh();
    }

    private void onDeleteClassroomButtonClicked() {
        Classroom selectedClassroom = this.schoolView.getClassroomListView().getSelectionModel().getSelectedItem();
        boolean isSaveToDelete = true;
        //Lesson/appointment
        for (AppointmentAbstract appointment : SchoolFile.getSchool().getSchedule().getAppointments()) {
            if (appointment.getLocation().equals(selectedClassroom)) {
                isSaveToDelete = false;
                break;
            }
        }
        if (isSaveToDelete) {
            this.schoolView.getClassroomListView().getItems().remove(selectedClassroom);
        }
        else {
            ErrorMessage.show("Het klaslokaal kan niet worden verwijderd aangezien het nog gebruikt wordt.");
        }
    }

    private void onDeleteGroupButtonClicked() {
        Group selectedGroup = this.schoolView.getGroupListView().getSelectionModel().getSelectedItem();
        boolean isSaveToDelete = true;
        for (Student student : SchoolFile.getSchool().getStudents()) {
            if (student.getGroup().equals(selectedGroup)) {
                isSaveToDelete = false;
                break;
            }
        }

        if (isSaveToDelete) {
            for (Lesson lesson : SchoolFile.getSchool().getSchedule().getLessons()) {
                if (lesson.getGroup().equals(selectedGroup)) {
                    isSaveToDelete = false;
                    break;
                }
            }
        }

        if (isSaveToDelete) {
            this.schoolView.getGroupListView().getItems().remove(selectedGroup);
        }
        else {
            ErrorMessage.show("De groep kan niet worden verwijderd aangezien het nog gebruikt wordt.");
        }
    }

    private void onDeleteStudentButtonClicked() {
        Student selectedStudent = this.schoolView.getStudentListView().getSelectionModel().getSelectedItem();
        boolean isSaveToDelete = true;

        for (Group group : SchoolFile.getSchool().getGroups()) {
            if (group.getStudentsList().contains(selectedStudent)) {
                isSaveToDelete = false;
                break;
            }
        }

        if (isSaveToDelete) {
            this.schoolView.getStudentListView().getItems().remove(selectedStudent);
        }
        else {
            ErrorMessage.show("De student kan niet worden verwijderd aangezien het nog gebruikt wordt.");
        }
    }

    private void onDeleteTeacherButtonClicked() {
        Teacher selectedTeacher = this.schoolView.getTeacherListView().getSelectionModel().getSelectedItem();
        boolean isSaveToDelete = true;

        for (Lesson lesson : SchoolFile.getSchool().getSchedule().getLessons()) {
            if (lesson.getTeacher().equals(selectedTeacher)) {
                isSaveToDelete = false;
                break;
            }
        }

        if (isSaveToDelete) {
            this.schoolView.getTeacherListView().getItems().remove(selectedTeacher);
        }
        else {
            ErrorMessage.show("De docent kan niet worden verwijderd aangezien het nog gebruikt wordt.");
        }
    }

    @Override
    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.schoolView.getStage().setOnHidden(eventEventHandler);
    }
}
