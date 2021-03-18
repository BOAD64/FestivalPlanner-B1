package b1.simulation.NPC;

import b1.school.person.Person;
import b1.school.person.PersonController;
import b1.school.person.Student;
import b1.school.person.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.nio.cs.ext.MacHebrew;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StudentNPC extends NPC
{

    public StudentNPC(Point2D position, double angle, Student student) {
        super(position, angle, student);
        this.hitBoxSize = 64;
        this.frame = Math.random() * 10;
        this.sprites = new ArrayList<>();
        this.speed = 100;
        this.rotationSpeed = 1;


        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/testNPC.png"));

            sprites.add(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTarget(Point2D position) {
        super.setTarget(position);
    }

    @Override
    public void openPerson(Point2D mousePos) {
        if (this.position.distance(mousePos) < hitBoxSize * 0.5) {
            StudentController studentController = new StudentController((Student) this.person);
            studentController.show();
        }
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed * 2;
        this.rotationSpeed = speed * 0.05;
    }

    @Override
    public void setCollisionNPCS(ArrayList<NPC> collisionNPCs) {
        super.collisionNPCs = FXCollections.observableList(collisionNPCs);
    }

    @Override
    public void update(double deltaTime) {
        if (target.distanceSq(position) < 10)
            return;

        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());
        double rotation = targetAngle - this.angle;
        while (rotation < -Math.PI) {
            rotation += 2 * Math.PI;
        }
        while (rotation > Math.PI) {
            rotation -= 2 * Math.PI;
        }

        if (rotation < -rotationSpeed) {
            this.angle -= rotationSpeed;
        }
        else if (rotation > rotationSpeed) {
            this.angle += rotationSpeed;
        }
        else {
            this.angle = targetAngle;
        }

        boolean hasCollision = false;
        hasCollision = checkCollision(deltaTime, super.collisionNPCs);

        if (!hasCollision) {
            Point2D nextPos = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle) * deltaTime, this.position.getY() + this.speed * Math.sin(this.angle) * deltaTime);
            this.position = nextPos;
        }
    }

    private boolean checkCollision(double deltaTime, ObservableList<NPC> collisionNPCs) {
        boolean hasCollision = false;
        for (NPC npc : collisionNPCs) {
            if (npc != this) {
                double thereSize = npc.getHitBoxSize();
                if (npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(npc.getPosition().getY() - this.position.getY(),
                            npc.getPosition().getX() - this.position.getX());
                    this.position = new Point2D.Double(this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);
                }
            }
        }
        return hasCollision;
    }

    @Override
    public void draw(Graphics2D graphics) {
        int centerX = sprites.get(0).getWidth() / (2 * 20); //(...) must be 2 in real picture
        int centerY = sprites.get(0).getHeight() / (2 * 20); //(...) must be 2 in real picture
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - centerX, position.getY() - centerY);
        tx.rotate(angle + Math.PI / 2, centerX, centerY);
        tx.scale(0.05, 0.05);

        graphics.drawImage(this.sprites.get(0), tx, null);

        graphics.setColor(Color.white);
        graphics.draw(new Ellipse2D.Double(position.getX() - 32, position.getY() - 32, this.hitBoxSize, this.hitBoxSize));
        graphics.draw(new Line2D.Double(position, target));
    }
}
