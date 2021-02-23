package b1.schedule;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.person.Person;
import b1.school.person.Student;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleController implements Controller
{
    private final Schedule schedule;
    private final ScheduleView view;

    public ScheduleController(Schedule schedule) {
        this.schedule = schedule;
        this.view = new ScheduleView();
        this.view.setAppointments(this.testAppointments());
    }

    private AppointmentAbstract[][] testAppointments() {
        AppointmentAbstract[][] appointments = new AppointmentAbstract[5][];

        GeneralAppointment appointment1 = new GeneralAppointment("Hoeloeloe", LocalTime.of(10, 30, 0), LocalTime.of(11, 0, 0), null, "test");
        GeneralAppointment appointment2 = new GeneralAppointment("Hallo", LocalTime.of(10, 45, 0), LocalTime.of(11, 15, 0), null, "Moet rood zijn");
        appointment1.getPersons().add(SchoolFile.getSchool().getPersons().get(0));
        appointment1.getPersons().add(SchoolFile.getSchool().getPersons().get(1));

        appointments[0] = new AppointmentAbstract[]{appointment1, appointment2};
        return appointments;
    }

    @Override
    public void show() {
        if (!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
            this.view.draw();
            stage.show();
        }
    }

    private EventHandler<MouseEvent> onCanvasClick() {
        return new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent t) {
                ArrayList<AppointmentShape> appointmentShapes = view.getAppointmentShapes();

                AppointmentShape clickedAppointmentShape = null;
                for (AppointmentShape appointmentShape : appointmentShapes) {
                    if (appointmentShape.contains(t.getX(), t.getY())) {
                        clickedAppointmentShape = appointmentShape;
                    }
                }

                if (clickedAppointmentShape != null) {
                    onAppointmentClick(clickedAppointmentShape);
                }
            }
        };
    }

    private void onAppointmentClick(AppointmentShape appointmentShape) {
        GeneralAppointmentController controller = new GeneralAppointmentController((GeneralAppointment) appointmentShape.getAppointment());
        controller.show();
        this.view.draw();
    }
}
