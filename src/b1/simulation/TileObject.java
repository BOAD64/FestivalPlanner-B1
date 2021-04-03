package b1.simulation;

import java.awt.geom.Point2D;

public class TileObject {
    private int height;
    private int id;
    private String name;
    private int width;
    private Point2D location;

    public TileObject(int height, int id, String name, int width, Point2D location) {
        this.height = height;
        this.id = id;
        this.name = name;
        this.width = width;
        this.location = location;
    }

    public TileObject() {
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Point2D getLocation() {
        return this.location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
