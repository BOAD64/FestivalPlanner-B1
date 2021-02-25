package b1.schedule;

import b1.io.SchoolFile;
import b1.school.group.StudentGroup;
import b1.school.person.Teacher;
import b1.school.room.Room;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.time.LocalTime;

public class LessonController extends AppointmentControllerAbstract{
    private Lesson lesson;
    private LessonView view;

    public LessonController(Lesson lesson) {
        this.lesson = lesson;
        this.view = new LessonView();
    }

    @Override
    public void show() {
        if (!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getCancelButton().setOnAction(onCancelClicked());
            this.view.getSaveButton().setOnAction(onSaveClicked());

            this.view.getNameField().setText(this.lesson.getName());
            this.view.getBeginTimeHour().increment(this.lesson.getStartTime().getHour());
            this.view.getBeginTimeMinute().increment(this.lesson.getStartTime().getMinute());
            this.view.getEndTimeHour().increment(this.lesson.getEndTime().getHour());
            this.view.getEndTimeMinute().increment(this.lesson.getEndTime().getMinute());
            this.view.getLocationField().setItems(FXCollections.observableList(SchoolFile.getSchool().getRooms()));
            this.view.getLocationField().getSelectionModel().select(this.lesson.getLocation());
            this.view.getDescriptionField().setText(this.lesson.getDescription());

            stage.show();
        }
    }

    public EventHandler<ActionEvent> onCancelClicked(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getStage().close();
            }
        };
    }

    public EventHandler<ActionEvent> onSaveClicked(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = view.getNameField().getText();
                int beginHour = view.getBeginTimeHour().getValue();
                int beginMinute = view.getBeginTimeMinute().getValue();
                int endHour = view.getEndTimeHour().getValue();
                int endMinute = view.getEndTimeMinute().getValue();
                Room location = view.getLocationField().getValue();
                String description = view.getDescriptionField().getText();
                StudentGroup studentGroup = view.getStudentGroupComboBox().getSelectionModel().getSelectedItem();
                Teacher teacher = view.getTeacherComboBox().getSelectionModel().getSelectedItem();

                LocalTime beginTime = LocalTime.of(beginHour, beginMinute);
                LocalTime endTime = LocalTime.of(endHour, endMinute);

                lesson.setName(name);
                lesson.setStartTime(beginTime);
                lesson.setEndTime(endTime);
                lesson.setLocation(location);
                lesson.setDescription(description);
                lesson.setStudentGroup(studentGroup);
                lesson.setTeacher(teacher);

                view.getStage().close();
            }
        };
    }
}
