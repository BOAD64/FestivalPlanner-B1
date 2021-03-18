package b1.simulation;

import java.awt.geom.Point2D;

public class TileObject
{
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
}
