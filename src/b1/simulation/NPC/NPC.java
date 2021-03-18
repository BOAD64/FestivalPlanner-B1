package b1.simulation.NPC;

import b1.school.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
    private boolean standStill;

    public NPC(Point2D position, double angle, Person person) {
        this.position = position;
        this.angle = angle;
        this.person = person;
        this.target = position;
        this.standStill = false;
    }

    /**
     * Set target position for NPC to walk to.
     *
     * @param position target position.
     */
    public void setTarget(Point2D position) {
        this.target = position;
    }

    /**
     * Fill list to check collision with other NPC's.
     *
     * @param collisionNPCs NPC check list.
     */
    public void setCollisionNPCS(ArrayList<NPC> collisionNPCs) {
        this.collisionNPCs = FXCollections.observableList(collisionNPCs);
    }

    /**
     * Updates position, direction and frame for NPC.
     *
     * @param deltaTime declares animation speed.
     */
    public void update(double deltaTime) {
        if (this.target.distanceSq(this.position) < 10) {
            this.standStill = true;
            return;
        }
        this.standStill = false;
        this.frame += deltaTime;
        if (this.frame >= 3) {
            this.frame = 0;
        }

        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());
        double rotation = targetAngle - this.angle;
        while (rotation < -Math.PI) {
            rotation += 2 * Math.PI;
        }
        while (rotation > Math.PI) {
            rotation -= 2 * Math.PI;
        }

        if (rotation < -this.rotationSpeed) {
            this.angle -= this.rotationSpeed * 100 * deltaTime;
        } else if (rotation > this.rotationSpeed) {
            this.angle += this.rotationSpeed * 100 * deltaTime;
        } else {
            this.angle = targetAngle;
        }
        boolean hasCollision = checkCollision(deltaTime);

        if (!hasCollision) {
            Point2D nextPos = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle) * deltaTime,
                    this.position.getY() + this.speed * Math.sin(this.angle) * deltaTime);
            this.position = nextPos;
        }
    }

    /**
     * Draws NPC sprite in correct direction, action and position.
     *
     * @param graphics
     */
    public void draw(Graphics2D graphics) {
        int centerX = this.sprites.get(0).getWidth() / (2);
        int centerY = this.sprites.get(0).getHeight() / (2) + 25;
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - centerX, this.position.getY() - centerY);

        if (this.standStill) {
            graphics.drawImage(this.sprites.get(1), tx, null);

        } else if (this.angle < Math.toRadians(45) && this.angle > Math.toRadians(-45)) {
            graphics.drawImage(this.sprites.get(6 + (int) this.frame), tx, null);

        } else if (this.angle > Math.toRadians(45) && this.angle < Math.toRadians(135)) {
            graphics.drawImage(this.sprites.get((int) this.frame), tx, null);

        } else if (this.angle < Math.toRadians(-45) && this.angle > Math.toRadians(-135)) {
            graphics.drawImage(this.sprites.get(9 + (int) this.frame), tx, null);

        } else {
            graphics.drawImage(this.sprites.get(3 + (int) this.frame), tx, null);
        }

        //Draw hitBox and target
//        graphics.setColor(Color.white);
//        graphics.draw(new Ellipse2D.Double(this.position.getX() - this.hitBoxSize / 2, this.position.getY() - this.hitBoxSize / 2, this.hitBoxSize, this.hitBoxSize));
//        graphics.draw(new Line2D.Double(this.position, this.target));
    }

    public double getHitBoxSize() {
        return this.hitBoxSize;
    }

    public Point2D getPosition() {
        return this.position;
    }

    /*
     * NPC will be pushed back if collision is detected.
     * @param deltaTime declares animation speed
     * @return collision detected or not.
     */
    private boolean checkCollision(double deltaTime) {
        boolean hasCollision = false;
        for (NPC npc : this.collisionNPCs) {
            if (npc != this) {
                double thereSize = npc.getHitBoxSize();
                if (npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() - this.position.getY(),
                            npc.getPosition().getX() - this.position.getX());
                    this.position = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);
                }
            }
        }
        return hasCollision;
    }

    abstract public void openPerson(Point2D mousePos);
}