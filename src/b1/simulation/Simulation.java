package b1.simulation;

import b1.Setting;
import b1.io.MapFile;
import b1.io.TilesetFile;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Simulation
{
    private ResizableCanvas canvas;
    private Camera camera;
    private StackPane pane;
    private Map map;

    public Simulation()
    {
        TilesetFile.setPath(Setting.Map.TilesetPath);
        MapFile.setPath(Setting.Map.MapJsonPath);
        this.map = new Map(TilesetFile.getTileset(), MapFile.getMapFile());
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
        this.pane.heightProperty().addListener(this.onPaneResize());
        this.pane.widthProperty().addListener(this.onPaneResize());
        this.canvas = new ResizableCanvas(this::draw, this.pane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.camera = new Camera(this.canvas, this::draw, g2d);
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
    }

    public void init() {

    }

    public void update(double deltaTime) {

    }

    public void draw(FXGraphics2D graphics) {
        graphics.setBackground(Setting.Map.SIM_BACKGROUND_COLOR);
        graphics.clearRect(0,0,(int)this.canvas.getWidth(),(int)this.canvas.getHeight());

        AffineTransform originalTransform = graphics.getTransform();
        graphics.setTransform(camera.getTransform());

        this.map.draw(graphics);

        graphics.setTransform(originalTransform);
    }

    private ChangeListener<Number> onPaneResize() {
        return (observable, oldValue, newValue) -> {
            this.canvas.setWidth(this.pane.getWidth());
            this.canvas.setHeight(this.pane.getHeight());
        };
    }
}
