package b1.simulation;

import b1.Setting;
import b1.io.MapFile;
import b1.io.TilesetFile;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class Simulation {
    private ResizableCanvas canvas;
    private StackPane pane;
    private Map map;
    private Clock clock;
    private Slider slider;

    public Simulation() {
        TilesetFile.setPath(Setting.Map.TilesetPath);
        MapFile.setPath(Setting.Map.MapJsonPath);
        this.map = new Map(TilesetFile.getTileset(), MapFile.getMapFile());
        this.clock = new Clock(2, LocalTime.of(8, 0), new Point2D.Double(0, 70));
        this.slider = new Slider(-60, 60, 1);
    }

    public StackPane getPane() {
        return this.pane;
    }

    public void createStage() {
        if(this.pane != null)
        {
            return;
        }

        this.pane = new StackPane();
        this.pane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(255, 65, 65), CornerRadii.EMPTY, Insets.EMPTY)));
        this.pane.heightProperty().addListener(this.onPaneResize());
        this.pane.widthProperty().addListener(this.onPaneResize());
        this.canvas = new ResizableCanvas(this::draw, this.pane);

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        draw(g2d);
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        this.pane.getChildren().add(this.canvas);

        this.slider.setMaxWidth(400);
        this.pane.getChildren().add(this.slider);
        StackPane.setAlignment(this.slider, Pos.BOTTOM_RIGHT);
        this.pane.setMargin(this.slider, new Insets(0,30,30,0));
    }

    public void init() {

    }

    /**
     * currently updates the clock
     * @param deltaTime
     */
    public void update(double deltaTime) {
        this.clock.setSpeedMultiplier(slider.getValue());
        this.clock.update(deltaTime);
        Double newDeltaTime = this.clock.getNewDeltaTime(deltaTime); //use this instead of deltaTime
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0,0,(int)this.canvas.getWidth(),(int)this.canvas.getHeight());
        this.map.draw(graphics);
        double clockXpos = this.canvas.getWidth() - 300;
        if (clockXpos < 0) {
            clockXpos = 0;
        }
        this.clock.draw(graphics, new Point2D.Double(clockXpos, this.canvas.getHeight() - 70));
    }

    private ChangeListener<Number> onPaneResize() {
        return (observable, oldValue, newValue) -> {
            this.canvas.setWidth(this.pane.getWidth());
            this.canvas.setHeight(this.pane.getHeight());
        };
    }
}
