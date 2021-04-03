package b1.simulation;

import b1.Setting;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private Point2D centerPoint = new Point2D.Double(-600, -400);
    private double zoom = 1;
    private Point2D lastMousePos;
    private Canvas canvas;
    private Resizable resizable;
    private FXGraphics2D g2d;

    public Camera(Canvas canvas, Resizable resizable, FXGraphics2D g2d) {
        this.canvas = canvas;
        this.resizable = resizable;
        this.g2d = g2d;

        canvas.setOnMousePressed(e -> {
            lastMousePos = new Point2D.Double(e.getX(), e.getY());
        });
        canvas.setOnMouseDragged(e -> mouseDragged(e));
        canvas.setOnScroll(e -> mouseScroll(e));
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        return tx;
    }

    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            centerPoint = new Point2D.Double(
                    centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom,
                    centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom
            );
            centerPoint.setLocation(
                    Math.max(Math.min(centerPoint.getX(), Setting.Map.MIN_X_BOUND), Setting.Map.MAX_X_BOUND),
                    Math.max(Math.min(centerPoint.getY(), Setting.Map.MIN_Y_BOUND), Setting.Map.MAX_Y_BOUND)
            );
            lastMousePos = new Point2D.Double(e.getX(), e.getY());
            resizable.draw(g2d);
        }
    }

    public void mouseScroll(ScrollEvent e) {
        this.zoom *= (1 + e.getDeltaY() / 250.0f);
        this.zoom = Math.max(Math.min(this.zoom, Setting.Map.MAX_ZOOM), Setting.Map.MIN_ZOOM);
        this.resizable.draw(g2d);
    }

    public void setZoom(double zoom) {
        this.zoom = Math.max(Math.min(zoom, Setting.Map.MAX_ZOOM), Setting.Map.MIN_ZOOM);
        resizable.draw(g2d);
    }

    public double getZoom() {
        return zoom;
    }
}
