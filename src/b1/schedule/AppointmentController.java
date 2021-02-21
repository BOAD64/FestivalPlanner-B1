package b1.schedule;

import b1.Controller;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

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
            stage.show();
        }
    }
}
