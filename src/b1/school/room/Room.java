package b1.school.room;

import java.io.Serializable;

public abstract class Room implements Serializable
{
    private double width;
    private double length;

    public Room(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
