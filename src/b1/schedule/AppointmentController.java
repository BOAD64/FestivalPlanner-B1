package b1.schedule;

import b1.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.time.LocalTime;

public class AppointmentController implements Controller {

    private Appointment appointment;
    private AppointmentView view;

    public AppointmentController(Appointment appointment) {
        this.appointment = appointment;
        this.view = new AppointmentView();
    }

    @Override
    public void show() {
        if (!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getCancelButton().setOnAction(onCancelClicked());
            this.view.getSaveButton().setOnAction(onSaveClicked());

            this.view.getNameField().setText(this.appointment.getName());
            this.view.getBeginTimeHour().increment(this.appointment.getStartTime().getHour());
            this.view.getBeginTimeMinute().increment(this.appointment.getStartTime().getMinute());
            this.view.getEndTimeHour().increment(this.appointment.getEndTime().getHour());
            this.view.getEndTimeMinute().increment(this.appointment.getEndTime().getMinute());
            this.view.getDescriptionField().setText(this.appointment.getDescription());

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
                String location = view.getLocationField().getText();
                String description = view.getDescriptionField().getText();

                LocalTime beginTime = LocalTime.of(beginHour, beginMinute);
                LocalTime endTime = LocalTime.of(endHour, endMinute);

                appointment.setName(name);
                appointment.setStartTime(beginTime);
                appointment.setEndTime(endTime);
//                appointment.setLocation(location);
                appointment.setDescription(description);

                view.getStage().close();
            }
        };
    }
}
