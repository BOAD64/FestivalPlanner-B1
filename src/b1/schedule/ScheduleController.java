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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleController implements Controller
{
    private final Schedule schedule;
    private final ScheduleView view;
    private SortingType sortingType;
    enum SortingType
    {
        Room,
        Person,
        StudentGroup
    }

    public ScheduleController(Schedule schedule) {
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
        Stage stage = new Stage();
        stage.setScene(new Scene(new Group(getNode())));
        stage.show();
        /*
        if (!this.view.getStage().isShowing()) {
            this.view.setAppointments(sort(this.schedule, this.sortingType));
            Stage stage = this.view.getStage();
            this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
            this.view.draw();
            stage.show();
        }*/
    }

    public Node getNode(){
        return this.view.getNode();
    }

    private HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule, SortingType sortingType)
    {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();
        for(AppointmentAbstract appointment : schedule.getAppointments())
        {
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
            controller = new LessonController((Lesson) appointmentShape.getAppointment());
        }
        else{
            return;
        }

        controller.show();
        this.view.draw();
    }

    private HashMap<Object, ArrayList<AppointmentAbstract>> testAppointments() {
        HashMap<Object, ArrayList<AppointmentAbstract>> appointments = new HashMap<Object, ArrayList<AppointmentAbstract>>();
        Teacher teacher = new Teacher();
        StudentGroup testGroup = new StudentGroup("");
        testGroup.getStudentsList().add(new Student());


        Lesson appointment1 = new Lesson("Hoeloeloe", LocalTime.of(10, 30, 0), LocalTime.of(11, 0, 0), new Room(10,10), "test", testGroup, teacher);
        Lesson appointment2 = new Lesson("Hallo", LocalTime.of(10, 45, 0), LocalTime.of(11, 15, 0), new Room(10,10), "Moet rood zijn", testGroup, teacher);
        Lesson appointment3 = new Lesson("Hallo", LocalTime.of(10, 45, 0), LocalTime.of(11, 15, 0), new Room(10,10), "Moet rood zijn", testGroup, teacher);
        School school = SchoolFile.getSchool();
        ArrayList<Person> persons = school.getPersons();
        appointment1.getPersons().add(persons.get(0));
        appointment1.getPersons().add(persons.get(1));

        appointments.put(appointment1.getLocation(), new ArrayList<AppointmentAbstract>(){{add(appointment1);}});
        appointments.put(appointment2.getLocation(), new ArrayList<AppointmentAbstract>(){{add(appointment2);}});
        appointments.put(appointment3.getLocation(), new ArrayList<AppointmentAbstract>(){{add(appointment3);}});
        return appointments;
    }
}
