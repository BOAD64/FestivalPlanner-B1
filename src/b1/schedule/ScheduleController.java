package b1.schedule;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.School;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScheduleController implements Controller {
    private School school;
    private final Schedule schedule;
    private final ScheduleView view;
    private AppointmentSorter sorter;

    public ScheduleController() {
        this.school = SchoolFile.getSchool();
        this.schedule = this.school.getSchedule();
        this.sorter = new AppointmentOnTeacherSorter();
        this.view = new ScheduleView();
    }

    public AppointmentSorter getSorter() {
        return this.sorter;
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

    /**
     * @return canvas with schedule as Node.
     */
    public Node getNode() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        if(this.view.getCanvas() == null) {
            this.view.createStage();
        }
        this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
        this.view.draw();
        return this.view.getCanvas();
    }

    public void refresh() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        this.view.draw();
    }


    private EventHandler<MouseEvent> onCanvasClick() {
        return new EventHandler<MouseEvent>() {
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
        } else if (appointmentShape.getAppointment() instanceof Lesson) {
            controller = new LessonController((Lesson) appointmentShape.getAppointment());
        } else {
            return;
        }

        controller.onClose(event -> {
            this.refresh();
        });

        controller.show();
        this.view.draw();
    }
}
