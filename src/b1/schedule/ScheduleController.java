package b1.schedule;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.School;
import b1.school.group.StudentGroup;
import b1.school.person.Person;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.school.room.Room;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleController implements Controller
{
    private School school;
    private final Schedule schedule;
    private final ScheduleView view;
    private SortingType sortingType;
    enum SortingType
    {
        Room,
        Person,
        StudentGroup
    }

    public ScheduleController(School school, Schedule schedule) {
        this.school = school;
        this.schedule = schedule;
        this.sortingType = SortingType.Room;
        this.view = new ScheduleView();
    }

    public SortingType getSortingType() {
        return this.sortingType;
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    @Override
    public void show() {
        if (!this.view.getStage().isShowing()) {
            this.view.setAppointments(sort(this.schedule, this.sortingType));
            Stage stage = this.view.getStage();
            this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
            this.view.draw();
            stage.show();
        }
    }

    public Node getNode(){
        this.view.setAppointments(sort(this.schedule, this.sortingType));
        Stage stage = this.view.getStage();
        this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
        this.view.draw();
        return this.view.getCanvas();
    }

    public void refresh()
    {
        this.view.setAppointments(sort(this.schedule, this.sortingType));
        this.view.draw();
    }

    private HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule, SortingType sortingType)
    {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();
        for(AppointmentAbstract appointment : schedule.getAppointments())
        {
            if(appointment.getStartTime() == null || appointment.getEndTime() == null)
            {
                continue;
            }
            if(sortingType == SortingType.Room)
            {
                ArrayList<AppointmentAbstract> appointments = result.computeIfAbsent(appointment.getLocation(), k -> new ArrayList<>());
                appointments.add(appointment);
            }
        }

        return result;
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
        AppointmentControllerAbstract controller;
        if(appointmentShape.getAppointment() instanceof GeneralAppointment){
            controller = new GeneralAppointmentController((GeneralAppointment) appointmentShape.getAppointment());
        }
        else if(appointmentShape.getAppointment() instanceof Lesson) {
            controller = new LessonController(this.school, (Lesson) appointmentShape.getAppointment());
        }
        else{
            return;
        }

        controller.onClose(event -> {this.refresh();});

        controller.show();
        this.view.draw();
    }
}
