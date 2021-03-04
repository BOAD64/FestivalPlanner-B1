package b1.schedule;

import b1.ErrorMessage;
import b1.io.ScheduleFile;
import b1.io.SchoolFile;
import b1.school.person.Person;
import b1.school.room.Classroom;
import b1.school.room.Room;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalTime;
import java.util.ArrayList;

public class GeneralAppointmentController extends AppointmentControllerAbstract
{
    private GeneralAppointment appointment;
    private GeneralAppointmentView view;

    public GeneralAppointmentController()
    {
        this(new GeneralAppointment(null, null, null, null, null));
    }

    public GeneralAppointmentController(GeneralAppointment appointment) {
        this.appointment = appointment;
        this.view = new GeneralAppointmentView();
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
            this.view.getPersonAddButton().setOnAction(this.onPersonAddButton());
            this.view.getPersonRemoveButton().setOnAction(this.onPersonRemoveButton());

            this.view.getNameField().setText(this.appointment.getName());
            this.view.getBeginTimeHour().increment(this.appointment.getStartTime().getHour());
            this.view.getBeginTimeMinute().increment(this.appointment.getStartTime().getMinute());
            this.view.getEndTimeHour().increment(this.appointment.getEndTime().getHour());
            this.view.getEndTimeMinute().increment(this.appointment.getEndTime().getMinute());
            this.view.getLocationField().setItems(FXCollections.observableList(SchoolFile.getSchool().getRooms()));
            this.view.getLocationField().getSelectionModel().select(this.appointment.getLocation());
            this.view.getDescriptionField().setText(this.appointment.getDescription());
            this.view.getParticipantsList().setItems(FXCollections.observableList(this.appointment.getPersons()));
            this.view.getPersonComboBox().setItems(FXCollections.observableList(SchoolFile.getSchool().getPersons()));

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
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

                LocalTime beginTime = LocalTime.of(beginHour, beginMinute);
                LocalTime endTime = LocalTime.of(endHour, endMinute);

                if(endTime.isBefore(beginTime))
                {
                    ErrorMessage.show("De begin tijd mag niet voor de eindtijd zijn.");
                    return;
                }

                appointment.setName(name);
                appointment.setStartTime(beginTime);
                appointment.setEndTime(endTime);
                appointment.setLocation(location);
                appointment.setDescription(description);

                view.getStage().close();
            }
        };
    }

    public EventHandler<ActionEvent> onPersonAddButton(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person person = view.getPersonComboBox().getValue();
                appointment.getPersons().add(person);
                view.getParticipantsList().refresh();
            }
        };
    }

    public EventHandler<ActionEvent> onPersonRemoveButton(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person selectPerson = view.getParticipantsList().getSelectionModel().getSelectedItem();
                appointment.getPersons().remove(selectPerson);
                view.getParticipantsList().refresh();
            }
        };
    }

    @Override
    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.view.getStage().setOnCloseRequest(eventEventHandler);
    }
}
