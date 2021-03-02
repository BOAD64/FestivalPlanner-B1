package b1.school;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.room.Classroom;
import b1.school.room.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import b1.school.person.Teacher;

import java.util.ArrayList;

public class SchoolController implements Controller {

    private School school;
    private SchoolView schoolView;

    public SchoolController()
    {
        this(SchoolFile.getSchool());
    }

    public SchoolController(School school) {
        this.school = school;
        this.schoolView = new SchoolView(this.school);
    }

    @Override
    public void show() {

        this.schoolView.getSelectClassroomButton().setOnAction(event -> openClassroom());
        this.schoolView.getSelectGroupButton().setOnAction(event -> openGroup());
        this.schoolView.getSelectStudentButton().setOnAction(event -> openStudent());
        this.schoolView.getSelectTeacherButton().setOnAction(event -> openTeacher());
        this.schoolView.getApplyButton().setOnAction(event -> apply());
        this.schoolView.getOkButton().setOnAction(event -> ok());

        this.schoolView.getRefreshClassroom().setOnAction(event -> this.refreshClassroom());
        this.schoolView.getRefreshGroup().setOnAction(event -> this.refreshGroup());
        this.schoolView.getRefreshStudent().setOnAction(event -> this.refreshStudent());
        this.schoolView.getRefreshTeacher().setOnAction(event -> this.refreshTeacher());
        this.schoolView.getStage().show();
    }

    private void openClassroom() {
        Classroom classroom = this.schoolView.getClassroomListView().getSelectionModel().getSelectedItem();

        if (!(classroom == null)) {
            ClassroomController classroomController = new ClassroomController(classroom);
            classroomController.show();
        }
    }

    private void openGroup() {
        Group group = this.schoolView.getGroupListView().getSelectionModel().getSelectedItem();

        if (!(group == null)) {
            GroupController groupController = new GroupController(group);
            groupController.show();
        }
    }

    private void openTeacher() {
        //todo open teacher window
        Teacher teacher = this.schoolView.getTeacherListView().getSelectionModel().getSelectedItem();

        if (!(teacher == null)) {
            /*
            TeacherController teacherController = new TeachterController(teacher);
            teacherController.show();
            */
        }
    }

    private void openStudent() {
        //todo open student window
        Student student = this.schoolView.getStudentListView().getSelectionModel().getSelectedItem();

        if (!(student == null)){
            /*
            StudentController studentController = new StudentControllor(selectedStudent);
            studentController.show();
            */
        }

    }

    private void apply() {
        refreshClassroom();
        refreshGroup();
        refreshStudent();
        refreshTeacher();

        ArrayList<Classroom> classrooms = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();

        classrooms.addAll(this.schoolView.getClassroomListView().getItems());
        groups.addAll(this.schoolView.getGroupListView().getItems());
        students.addAll(this.schoolView.getStudentListView().getItems());
        teachers.addAll(this.schoolView.getTeacherListView().getItems());

        this.school.setClassrooms(classrooms);
        this.school.setGroups(groups);
        this.school.setStudents(students);
        this.school.setTeachers(teachers);
        this.school.setSchoolName(this.schoolView.getSchoolNameField().getText());
    }

    private void ok() {
        apply();
        this.schoolView.getStage().close();
    }

    private void refreshClassroom(){
        this.schoolView.getClassroomListView().refresh();
    }

    private void refreshGroup(){
        this.schoolView.getGroupListView().refresh();
    }

    private void refreshStudent(){
        this.schoolView.getStudentListView().refresh();
    }

    private void refreshTeacher(){
        this.schoolView.getTeacherListView().refresh();
    }

}
