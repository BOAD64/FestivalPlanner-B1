package b1.schedule;

import b1.school.group.StudentGroup;
import b1.school.person.Teacher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LessonView extends AppointmentViewAbstract{

    private Stage stage;
    private ComboBox<StudentGroup> studentGroupComboBox;
    private ComboBox<Teacher> teacherComboBox;
    private Button saveButton;
    private Button cancelButton;

    public LessonView() {
        super();
    }

    public Stage getStage() {
        if(this.stage == null) {
            this.stage = new Stage();
            this.createStage();
        }
        return this.stage;
    }

    private void createStage(){
        VBox vBox = new VBox();
        this.saveButton = new Button("Opslaan");
        this.cancelButton = new Button("Annuleren");
        this.teacherComboBox = new ComboBox<>();
        this.studentGroupComboBox = new ComboBox<>();

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().add(this.cancelButton);
        buttonsBox.getChildren().add(this.saveButton);

        GridPane personsGrid = new GridPane();
        personsGrid.add(new Label("Docent: "), 0, 0);
        personsGrid.add(new Label("Klas: "), 0, 1);
        personsGrid.add(this.teacherComboBox, 1, 0);
        personsGrid.add(this.studentGroupComboBox, 1, 1);

        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(super.createAbstractAppointmentElements());
        vBox.getChildren().add(personsGrid);
        vBox.getChildren().add(buttonsBox);

        Scene scene = new Scene(vBox);
        this.stage.setScene(scene);
    }

    public Button getSaveButton() {
        return this.saveButton;
    }

    public Button getCancelButton() {
        return this.cancelButton;
    }

    public ComboBox<StudentGroup> getStudentGroupComboBox() {
        return this.studentGroupComboBox;
    }

    public ComboBox<Teacher> getTeacherComboBox() {
        return this.teacherComboBox;
    }
}
