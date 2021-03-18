package b1.simulation.NPC;

import b1.school.person.Person;
import javafx.collections.ObservableList;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//todo: place as many posible shared functions from studentNPC > NPC
//todo: Implement actual sprites
//todo: try finding out how we want to implement States
//todo: maybe already implement a idle state and going to class state.(if we use state as a target setter)
//todo: copy studentNPC to teacherNPC

public abstract class NPC {
    Point2D position;
    double hitBoxSize;
    double angle;
    ArrayList<BufferedImage> sprites;
    double frame;
    Point2D target;
    double rotationSpeed;
    double speed;
    Person person;
    ObservableList<NPC> collisionNPCs;

    public NPC(Point2D position, double angle, Person person) {
        this.position = position;
        this.angle = angle;
        this.person = person;
        this.target = position;
    }

    public void setTarget(Point2D position) {
        this.target = position;
    }

    abstract public void openPerson(Point2D mousePos);

    abstract public void setSpeed(double speed);

    public abstract void setCollisionNPCS(ArrayList<NPC> collisionNPCs);

    abstract public void update(double deltaTime);

    abstract public void draw(Graphics2D graphics);

    public double getHitBoxSize() {
        return this.hitBoxSize;
    };

    public Point2D getPosition() {
        return this.position;
    }
}