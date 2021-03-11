package b1.school;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.person.StudentController;
import b1.school.person.TeacherController;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import b1.school.person.Teacher;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SchoolController implements Controller {

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

        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();

        groups.addAll(this.schoolView.getGroupListView().getItems());
        students.addAll(this.schoolView.getStudentListView().getItems());
        teachers.addAll(this.schoolView.getTeacherListView().getItems());

        this.school.getRooms().removeIf(room -> room instanceof Classroom);
        this.school.getRooms().addAll(this.schoolView.getClassroomListView().getItems());
        this.school.setGroups(groups);
        this.school.setStudents(students);
        this.school.setTeachers(teachers);
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
        this.schoolView.getClassroomListView().getItems().remove(this.schoolView.getClassroomListView().getSelectionModel().getSelectedItem());
    }

    private void onDeleteGroupButtonClicked() {
        this.schoolView.getGroupListView().getItems().remove(this.schoolView.getGroupListView().getSelectionModel().getSelectedItem());
    }

    private void onDeleteStudentButtonClicked() {
        this.schoolView.getStudentListView().getItems().remove(this.schoolView.getStudentListView().getSelectionModel().getSelectedItem());
    }

    private void onDeleteTeacherButtonClicked() {
        this.schoolView.getTeacherListView().getItems().remove(this.schoolView.getTeacherListView().getSelectionModel().getSelectedItem());
    }
}
