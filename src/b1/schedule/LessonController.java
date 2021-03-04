package b1.schedule;

import b1.ErrorMessage;
import b1.io.SchoolFile;
import b1.school.School;
import b1.school.group.StudentGroup;
import b1.school.person.Teacher;
import b1.school.room.Room;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalTime;

public class LessonController extends AppointmentControllerAbstract
{
    private Lesson lesson;
    private LessonView view;
    private School school;

    public LessonController()
    {
        this(new Lesson(null, null, null, null, null, null, null));
    }

    public LessonController(Lesson lesson) {
        this.lesson = lesson;
        this.view = new LessonView();
        this.school = SchoolFile.getSchool();
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        if (!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getCancelButton().setOnAction(onCancelClicked());
            this.view.getSaveButton().setOnAction(onSaveClicked());

            if (this.lesson.getName() != null) {
                this.view.getNameField().setText(this.lesson.getName());
            }
            if (this.lesson.getStartTime() != null) {
                this.view.getBeginTimeHour().increment(this.lesson.getStartTime().getHour());
                this.view.getBeginTimeMinute().increment(this.lesson.getStartTime().getMinute());
            }
            if (this.lesson.getEndTime() != null) {
                this.view.getEndTimeHour().increment(this.lesson.getEndTime().getHour());
                this.view.getEndTimeMinute().increment(this.lesson.getEndTime().getMinute());
            }
            if(this.school.getClassrooms() != null) {
                this.view.getLocationField().setItems(FXCollections.observableList(this.school.getRooms()));
            }
            if(this.lesson.getLocation() != null) {
                this.view.getLocationField().getSelectionModel().select(this.lesson.getLocation());
            }
            if(this.lesson.getDescription() != null) {
                this.view.getDescriptionField().setText(this.lesson.getDescription());
            }

            this.view.getStudentGroupComboBox().setItems(FXCollections.observableList(this.school.getStudentGroups()));
            this.view.getTeacherComboBox().setItems(FXCollections.observableList(this.school.getTeachers()));

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            stage.show();
        }
    }

    public EventHandler<ActionEvent> onCancelClicked() {
        return new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                view.getStage().close();
            }
        };
    }

    public EventHandler<ActionEvent> onSaveClicked() {
        return new EventHandler<ActionEvent>()
        {
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

                if(endTime.isBefore(beginTime))
                {
                    ErrorMessage.show("De begin tijd mag niet voor de eindtijd zijn.");
                    return;
                }

                lesson.setName(name);
                lesson.setStartTime(beginTime);
                lesson.setEndTime(endTime);
                lesson.setLocation(location);
                lesson.setDescription(description);
                lesson.setStudentGroup(studentGroup);
                lesson.setTeacher(teacher);

                SchoolFile.getSchool().getSchedule().addAppointment(lesson);

                view.getStage().close();
            }
        };
    }

    @Override
    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.view.getStage().setOnHidden(eventEventHandler);
    }
}
