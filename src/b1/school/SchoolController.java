package b1.school;

import b1.Controller;

public class SchoolController implements Controller {

    private School school;
    private SchoolView schoolView;

    public SchoolController(School school) {
        this.school = school;
        this.schoolView = new SchoolView(this.school);
    }

    @Override
    public void show() {
        this.schoolView.getStage().show();
        this.schoolView.getSelectClassroomButton().setOnAction(event -> openClassroom());
        this.schoolView.getSelectGroupButton().setOnAction(event -> openGroup());
        this.schoolView.getSelectStudentButton().setOnAction(event -> openStudent());
        this.schoolView.getSelectTeacherButton().setOnAction(event -> openTeacher());
        this.schoolView.getApplyButton().setOnAction(event -> apply());
        this.schoolView.getOkButton().setOnAction(event -> ok());
    }

    private void openClassroom() {

    }

    private void openGroup() {

    }

    private void openTeacher() {

    }

    private void openStudent() {

    }

    private void apply() {

    }

    private void ok() {

    }
}
