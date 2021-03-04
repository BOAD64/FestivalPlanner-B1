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
    private AppointmentSorter sorter;

    public ScheduleController() {
        this.school = SchoolFile.getSchool();
        this.schedule = this.school.getSchedule();
        this.sorter = new AppointmentOnRoomSorter();
        this.view = new ScheduleView();
    }

    public AppointmentSorter getSorter() {
        return sorter;
    }

    public void setSorter(AppointmentSorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        if (!this.view.getStage().isShowing()) {
            this.view.setAppointments(this.sorter.sort(this.schedule));
            Stage stage = this.view.getStage();
            this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
            this.view.draw();
            stage.show();
        }
    }

    public Node getNode() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        Stage stage = this.view.getStage();
        this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
        this.view.draw();
        return this.view.getCanvas();
    }

    public void refresh() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        this.view.draw();
    }

//    private HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule, SortingType sortingType) {
//        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();
//        for (AppointmentAbstract appointment : schedule.getAppointments()) {
//            if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
//                continue;
//            }
//            if (sortingType == SortingType.Room) {
//                ArrayList<AppointmentAbstract> appointments = result.computeIfAbsent(appointment.getLocation(), k -> new ArrayList<>());
//                appointments.add(appointment);
//            }
//        }
//
//        return result;
//    }

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
        if (appointmentShape.getAppointment() instanceof GeneralAppointment) {
            controller = new GeneralAppointmentController((GeneralAppointment) appointmentShape.getAppointment());
        }
        else if (appointmentShape.getAppointment() instanceof Lesson) {
            controller = new LessonController((Lesson) appointmentShape.getAppointment());
        }
        else {
            return;
        }

        controller.onClose(event -> {this.refresh();});

        controller.show();
        this.view.draw();
    }
}
