package b1.schedule;

import b1.Controller;
import javafx.stage.Stage;

import java.time.LocalTime;

public class ScheduleController implements Controller
{
    private final Schedule schedule;
    private final ScheduleView view;

    public ScheduleController(Schedule schedule)
    {
        this.schedule = schedule;
        this.view = new ScheduleView();
        this.view.setAppointments(this.testAppointments());
    }

    private Appointment[][] testAppointments()
    {
        Appointment[][] appointments = new Appointment[5][];

        Appointment appointment1 = new Appointment("Hoeloeloe", LocalTime.of(10, 30, 0), LocalTime.of(11,0,0), null, "test");

        appointments[0] = new Appointment[]{appointment1};
        return appointments;
    }

    @Override
    public void show() {
        if(!this.view.getStage().isShowing()){
            Stage stage = this.view.getStage();
            this.view.draw();
            stage.show();
        }
    }
}
