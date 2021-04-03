package b1.simulation;

import b1.Setting;
import b1.io.MapFile;
import b1.io.SchoolFile;
import b1.io.TilesetFile;
import b1.school.School;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.simulation.NPC.NPC;
import b1.simulation.NPC.StudentNPC;
import b1.simulation.NPC.TeacherNPC;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.geom.Point2D;
import java.time.LocalTime;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Simulation
{
    private School school;
    private ResizableCanvas canvas;
    private Camera camera;
    private StackPane pane;
    private Map map;
    private Clock clock;
    private Slider slider;
    private ArrayList<NPC> NPCs;
    private Button clockSpeedButton;
    private Button pauseButton;
    private TextField speedValueField;
    private Pathfinding pathfinding;
    private ScheduleManager scheduleManager;
    private boolean debug = false;

    //NPC test
    private Point2D mousePos;

    public Simulation() {
        this.school = SchoolFile.getSchool(); //moet dit of moet maincontroller.getschool???
        TilesetFile.setPath(Setting.Map.TilesetPath);
        MapFile.setPath(Setting.Map.MapJsonPath);
        this.map = new Map(TilesetFile.getTileset(), MapFile.getMapFile());
        this.clock = new Clock(2, LocalTime.of(8, 0), new Point2D.Double(0, 70));
        this.slider = new Slider(-20, 20, 1);
        this.clockSpeedButton = new Button("tijd snelheid naar 1x");
        this.speedValueField = new TextField("Snelheid: 1x");
        this.pauseButton = new Button("Pauze");
        this.NPCs = new ArrayList<>();
        this.pathfinding = new Pathfinding(this.map);

        //Uncomment to test NPCs
        //addSingleStudentTestNPCs();
        addNPCs();

        this.scheduleManager = new ScheduleManager(this.school.getSchedule(), this.clock, this.NPCs, this.pathfinding.getTargets());
        this.scheduleManager.init();
        this.mousePos = new Point2D.Double(500, 500);
    }

    private void addNPCs() {
        TileObject loadingZone = this.map.getTileObject().get("LoadingZone");
        for (Student student : this.school.getStudents()) {
            StudentNPC studentNPC = new StudentNPC(new Point2D.Double(loadingZone.getLocation().getX() + 16 + Math.random() * (loadingZone.getWidth() - 32),
                    loadingZone.getLocation().getY() + 16 + Math.random() * (loadingZone.getHeight() - 16)), 0, student, this.map.getWalkableLayer());
            studentNPC.setCollisionNPCS(this.NPCs);
            studentNPC.setStandardTarget(this.pathfinding.getTargets().get("StudentRoom"));
            studentNPC.sendToStandardTarget();

            this.NPCs.add(studentNPC);
        }
        for (Teacher teacher : this.school.getTeachers()) {
            TeacherNPC teacherNPC = new TeacherNPC(new Point2D.Double(loadingZone.getLocation().getX() + 16 + Math.random() * (loadingZone.getWidth() - 32),
                    loadingZone.getLocation().getY() + 16 + Math.random() * (loadingZone.getHeight() - 16)), 0, teacher, this.map.getWalkableLayer());
            teacherNPC.setCollisionNPCS(this.NPCs);
            teacherNPC.setStandardTarget(this.pathfinding.getTargets().get("TeacherRoom"));
            teacherNPC.sendToStandardTarget();
            this.NPCs.add(teacherNPC);
        }
    }

    //test only one student npc
    private void addSingleStudentTestNPCs() {
        StudentNPC studentNPC = new StudentNPC(new Point2D.Double(200, 200), 0, new Student("testBoy", (short) 34, "Bird", (short) 1, new Group("Birdy Boys")), this.map.getWalkableLayer());
        studentNPC.setCollisionNPCS(this.NPCs);
        this.NPCs.add(studentNPC);
    }

    public StackPane getPane() {
        return this.pane;
    }

    public void createStage() {
        if (this.pane != null) {
            return;
        }

        this.pane = new StackPane();
        this.pane.heightProperty().addListener(this.onPaneResize());
        this.pane.widthProperty().addListener(this.onPaneResize());
        this.canvas = new ResizableCanvas(this::draw, this.pane);

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.camera = new Camera(this.canvas, this::draw, g2d);
        draw(g2d);
        new AnimationTimer()
        {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        //NPC test
        canvas.setOnMouseMoved(e -> {
            try {
                this.mousePos = camera.getTransform().inverseTransform(new Point2D.Double(e.getX(), e.getY()), null);
            }
            catch (Exception exeption) {
                exeption.printStackTrace();
            }

        });
        canvas.setOnMouseClicked(e -> CheckIfNPCclicked(e));

        //add keyboard functionality:
        //F3 shows debug
        //K pauses
        //J slows time
        //L speeds up time
        this.pane.setOnKeyPressed(event -> {
            if(event.getCode().getName().equals("F3")){
                this.debug=!this.debug;
            }
            if (event.getCode().equals(KeyCode.K)){
                this.clock.pause();
            }
            if (event.getCode().equals(KeyCode.J)){
                if (slider.getValue() > 0){
                    slider.setValue(slider.getValue() - 0.5);
                }
            }
            if (event.getCode().equals(KeyCode.L)){
                if (slider.getValue() <= 19.5){
                    slider.setValue(slider.getValue() + 0.5);
                }
            }
        });

        this.pane.getChildren().add(this.canvas);

        //clock functions
        VBox clockVBox = new VBox();
        HBox clockHBox = new HBox();
        this.clockSpeedButton.setOnAction(e -> this.slider.setValue(1));
        this.pauseButton.setOnMouseClicked(event -> this.clock.pause());

        this.speedValueField.setEditable(false);
        clockHBox.getChildren().add(pauseButton);
        clockHBox.getChildren().add(clockSpeedButton);
        clockHBox.getChildren().add(speedValueField);
        clockHBox.setAlignment(Pos.CENTER);
        clockVBox.setSpacing(10);


        this.slider.setMaxWidth(400);
        clockVBox.getChildren().add(clockHBox);
        clockVBox.getChildren().add(this.slider);
        clockVBox.setAlignment(Pos.CENTER);
        clockVBox.setMaxSize(400, 40);

        this.pane.getChildren().add(clockVBox);
        StackPane.setAlignment(clockVBox, Pos.BOTTOM_RIGHT);
        this.pane.setMargin(clockVBox, new Insets(0, 30, 30, 0));

        //camera functions
        VBox zoomButtons = new VBox();
        zoomButtons.setMaxSize(25, 50);
        Button plus = new Button("+");
        Button min = new Button("-");
        plus.setOnAction(this::onZoomButtonPress);
        min.setOnAction(this::onZoomButtonPress);
        plus.setPrefSize(25, 25);
        min.setPrefSize(25, 25);
        zoomButtons.getChildren().addAll(plus, min);
        this.pane.getChildren().add(zoomButtons);
        StackPane.setAlignment(zoomButtons, Pos.TOP_RIGHT);
    }

    private void CheckIfNPCclicked(MouseEvent e) {
        try {
            this.mousePos = camera.getTransform().inverseTransform(new Point2D.Double(e.getX(), e.getY()), null);
        }
        catch (Exception exeption) {
            exeption.printStackTrace();
        }
        for (NPC npc : this.NPCs) {
            npc.openPerson(this.mousePos);
        }
    }

    /**
     * currently updates the clock
     *
     * @param deltaTime
     */
    public void update(double deltaTime) {
        this.speedValueField.setText("Snelheid: " + (Math.round(slider.getValue() * 100) / 100.0) + "x");
        if (!this.clock.isPaused()){
            this.clock.setSpeedMultiplier(slider.getValue());
        }

        this.clock.update(deltaTime);
        double newDeltaTime = this.clock.getNewDeltaTime(deltaTime); //use this instead of deltaTime

        this.scheduleManager.update(newDeltaTime);

        if (newDeltaTime > 0) {
            for (NPC npc : this.NPCs) {
                npc.update(newDeltaTime);
            }
            this.NPCs.sort((p1, p2) -> (int)(p1.getPosition().getY() - p2.getPosition().getY()));
        }
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setBackground(Setting.Map.SIM_BACKGROUND_COLOR);
        graphics.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();
        graphics.setTransform(camera.getTransform());

        //with camera
        {
            this.map.draw(graphics, this.debug);
            for (NPC npc : this.NPCs) {
                npc.draw(graphics, this.debug);
            }
        }

        graphics.setTransform(originalTransform);

        //without camera
        {
            double clockXpos = this.canvas.getWidth() - 330;
            if (clockXpos < 0) {
                clockXpos = 0;
            }
            this.clock.draw(graphics, new Point2D.Double(clockXpos, this.canvas.getHeight() - 100));
        }
    }

    private ChangeListener<Number> onPaneResize() {
        return (observable, oldValue, newValue) -> {
            this.canvas.setWidth(this.pane.getWidth());
            this.canvas.setHeight(this.pane.getHeight());
        };
    }

    private void onZoomButtonPress(ActionEvent e) {
        double zoom = this.camera.getZoom();
        switch (((Button) e.getSource()).getText()) {
            case "+": {
                this.camera.setZoom(zoom * 1.2);
                break;
            }
            case "-": {
                this.camera.setZoom(zoom * 0.8);
                break;
            }
        }
    }
}
