package b1.schedule;

import b1.View;
import b1.io.ImageFile;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleView implements View {
    private HashMap<Object, ArrayList<AppointmentAbstract>> appointments;
    private final ArrayList<AppointmentShape> appointmentShapes;

    private final Stage stage;
    private BorderPane borderPane;
    private ComboBox<AppointmentSorter> sorterComboBox;
    private ResizableCanvas canvas;
    private FXGraphics2D fxGraphics2D;
    private double scroll = 0.0;

    private final int START_TIME = 3600 * 7;
    private final int END_TIME = 3600 * 23;

    public ScheduleView() {
        this.stage = new Stage();
        this.appointmentShapes = new ArrayList<>();

    }

    public HashMap<Object, ArrayList<AppointmentAbstract>> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(HashMap<Object, ArrayList<AppointmentAbstract>> appointments) {
        this.appointments = appointments;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public ArrayList<AppointmentShape> getAppointmentShapes() {
        return this.appointmentShapes;
    }

    public BorderPane getBorderPane() {
        return this.borderPane;
    }

    public ComboBox<AppointmentSorter> getSorterComboBox() {
        return this.sorterComboBox;
    }

    /**
     * This method draws the appointments and the columns on the canvas.
     */
    public void draw() {
        this.appointmentShapes.clear();
        this.fxGraphics2D.setBackground(Color.white);
        this.fxGraphics2D.clearRect(0, 0, (int) Math.round(this.canvas.getWidth()), (int) Math.round(this.canvas.getHeight()));

        AffineTransform originalTransform = this.fxGraphics2D.getTransform();
        this.fxGraphics2D.translate(this.scroll, 0);

        int columnWidth = (int) Math.round(this.getColumnWidth());

        this.drawBackground(this.appointments.size(), columnWidth);

        int index = 0;
        for (Map.Entry<Object, ArrayList<AppointmentAbstract>> appointments : this.appointments.entrySet()) {
            this.fxGraphics2D.setColor(Color.BLACK);
            this.fxGraphics2D.setFont(this.fxGraphics2D.getFont().deriveFont(15.0f));
            this.fxGraphics2D.drawString(appointments.getKey().toString(), (int) Math.round(columnWidth * (index + 0.5)), 20);

            for (int j = 0; j < appointments.getValue().size(); j++) {
                this.appointmentShapes.add(this.generateAppointmentShape(appointments.getValue().get(j), columnWidth * index, columnWidth));
            }
            index = index + 1;
        }

        for (int j = 0; j < this.appointmentShapes.size(); j++) {
            this.drawAppointment(this.appointmentShapes.get(j));
        }
        this.fxGraphics2D.setTransform(originalTransform);
    }

    @Override
    public Stage getStage() {
        this.stage.setHeight(1080);
        this.stage.setWidth(1920);

        this.stage.heightProperty().addListener(this.onStageResize());
        this.stage.widthProperty().addListener(this.onStageResize());

        this.createStage();

        return this.stage;
    }

    public void createStage() {
        this.borderPane = new BorderPane();
        HBox topBox = new HBox();
        this.canvas = new ResizableCanvas(g -> draw(), this.borderPane);
        this.fxGraphics2D = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        Scene scene = new Scene(new Group(this.canvas));
        javafx.scene.control.Label sorterComboBoxLabel = new javafx.scene.control.Label("Column type: ");
        this.sorterComboBox = new ComboBox<>();

        this.canvas.setHeight(this.stage.getHeight());
        this.canvas.setWidth(this.stage.getWidth());
        this.canvas.setOnScroll(this::onScroll);
        sorterComboBoxLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        sorterComboBoxLabel.setPadding(new Insets(0,10,0,10));

        topBox.getChildren().addAll(sorterComboBoxLabel, this.sorterComboBox);
        this.borderPane.setTop(topBox);
        this.borderPane.setCenter(this.canvas);
        this.stage.setScene(scene);
        this.stage.getIcons().add(ImageFile.getLogo());
    }

    /*
     * This method draws the lines for the columns on the canvas.
     */
    private void drawBackground(int appointmentCount, int columnWidth) {
        this.fxGraphics2D.setColor(Color.LIGHT_GRAY);
        int hourCount = (this.END_TIME - this.START_TIME) / 3600;
        int hourHeight = (int) Math.round(this.canvas.getHeight() / ((this.END_TIME - this.START_TIME) / 3600.0));
        for (int i = 0; i < hourCount; i++) {
            this.fxGraphics2D.drawLine(0, hourHeight * i, (int) Math.round(this.getScheduleWidth()), hourHeight * i);
        }

        this.fxGraphics2D.setColor(Color.GRAY);
        for (int i = 0; i < appointmentCount; i++) {
            this.fxGraphics2D.drawLine(i * columnWidth, 0, i * columnWidth, (int) this.canvas.getHeight());
        }
    }

    private AppointmentShape generateAppointmentShape(AppointmentAbstract appointment, int offsetX, int width) {
        int y = this.getAppointmentY(appointment);
        int y2 = this.getAppointmentY2(appointment);
        int height = (y2 - y);

        return new AppointmentShape(appointment, offsetX, y, width, height);
    }

    /*
     * This method draws the appointments in the right column on the canvas.
     */
    private void drawAppointment(AppointmentShape appointmentRectangle) {
        Color backColor = Color.YELLOW;

        for (AppointmentShape appointmentShape : this.appointmentShapes) {
            if (appointmentRectangle != appointmentShape &&
                    appointmentRectangle.intersects(appointmentShape)) {
                backColor = Color.RED;
            }
        }

        appointmentRectangle.setBackgroundColor(backColor);
        appointmentRectangle.draw(this.fxGraphics2D);
    }

    private double getColumnWidth() {
        double columnWidth = this.canvas.getWidth();

        if (this.appointments.size() > 0) {
            columnWidth = this.canvas.getWidth() / this.appointments.size();
        }

        return Math.max(460.0, columnWidth);
    }

    private double getScheduleWidth()
    {
        return this.getColumnWidth()*this.appointments.size();
    }

    private int getAppointmentY(AppointmentAbstract appointment) {
        int startSeconds = appointment.getStartTime().getHour() * 3600 + appointment.getStartTime().getMinute() * 60 + appointment.getStartTime().getSecond();
        return (int) Math.round(this.canvas.getHeight() / (this.END_TIME - this.START_TIME) * (startSeconds - this.START_TIME));
    }

    private int getAppointmentY2(AppointmentAbstract appointment) {
        int endSeconds = appointment.getEndTime().getHour() * 3600 + appointment.getEndTime().getMinute() * 60 + appointment.getEndTime().getSecond();
        return (int) Math.round(this.canvas.getHeight() / (this.END_TIME - this.START_TIME) * (endSeconds - this.START_TIME));
    }

    private ChangeListener<Number> onStageResize() {
        return (observable, oldValue, newValue) -> {
            this.canvas.setWidth(this.stage.getWidth());
            this.canvas.setHeight(this.stage.getHeight());
        };
    }

    private void onScroll(ScrollEvent event) {
        this.scroll+=event.getDeltaY();
        this.scroll = Math.max(Math.min(this.scroll, 0), -this.getColumnWidth()*(this.appointments.size()-(this.canvas.getWidth() / this.getColumnWidth())));
        this.draw();
    }
}
