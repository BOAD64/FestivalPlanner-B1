package b1.school.person;

import b1.Controller;
import javafx.scene.control.Alert;

public class StudentController implements Controller {

    private StudentView view;
    private Student student;

    public StudentController(Student student) {
        this.view = new StudentView(student);
        this.student = student;
         //ToDo fix nullPointerException
    }

    @Override
    public void show() {
        if(!view.getStage().isShowing()){
            view.getStage().show();
            this.view.getSaveButton().setOnAction(e -> this.saveStudent());
        }
    }

    private void saveStudent() {
        try {
            if(this.view.getNameField().getText().isEmpty() || this.view.getAgeField().getText().isEmpty() ||
                    this.view.getGenderField().getText().isEmpty() || this.view.getIdField().getText().isEmpty() ||
                    this.view.getGroupField().getText().isEmpty() || Integer.parseInt(this.view.getAgeField().getText()) < 1 ||
                    Integer.parseInt(this.view.getIdField().getText()) < 0) {
                this.showErrorMessage();

            } else {
                this.student.setName(this.view.getNameField().getText());
                this.student.setAge(Short.parseShort(this.view.getAgeField().getText()));
                this.student.setGender(this.view.getGenderField().getText());
                this.student.setIdNumber(Short.parseShort(this.view.getIdField().getText()));
                this.student.setGroup(this.view.getGroupField().getText());

                //ToDo add student to school
            }
        } catch(Exception e) {
            this.showErrorMessage();
        }
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Er is iets fout gegaan");
        alert.setContentText("Er was een fout bij het opslaan, check of u valide waarde heeft ingevuld!");
        alert.showAndWait();
    }
}