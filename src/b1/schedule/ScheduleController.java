package b1.schedule;

import b1.Controller;
import b1.io.SchoolFile;
import b1.school.School;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.util.ArrayList;

public class ScheduleController implements Controller {
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
        this.view.getSorterComboBox().setItems(FXCollections.observableList(this.appointmentSorters()));
        this.view.getSorterComboBox().getSelectionModel().select(this.sorter);
        this.view.getSorterComboBox().setOnAction(this::onSorterSelect);
        this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
        this.view.getCanvas().setWidth(Double.MAX_VALUE);
        this.view.getCanvas().setHeight(Double.MAX_VALUE);
        this.view.draw();
//        return this.view.getCanvas();
        return this.view.getBorderPane();
    }

    public void refresh() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        this.view.getCanvas().setWidth(Double.MAX_VALUE);
        this.view.getCanvas().setHeight(Double.MAX_VALUE);
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

    private ArrayList<AppointmentSorter> appointmentSorters()
    {
        ArrayList<AppointmentSorter> result = new ArrayList<>();

        result.add(new AppointmentOnGroupSorter());
        result.add(new AppointmentOnPersonSorter());
        result.add(new AppointmentOnTeacherSorter());
        result.add(new AppointmentOnRoomSorter());

        return result;
    }

    private void onSorterSelect(ActionEvent event)
    {
        AppointmentSorter appointmentSorter = this.view.getSorterComboBox().getValue();
        if(appointmentSorter!= null && !this.sorter.getClass().equals(appointmentSorter.getClass())) {
            this.setSorter(appointmentSorter);
            this.refresh();
        }
    }

    @Override
    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.view.getStage().setOnHidden(eventEventHandler);
    }
}
