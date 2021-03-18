package b1.simulation.NPC;

import b1.io.SpriteFile;
import b1.school.person.Student;
import b1.school.person.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StudentNPC extends NPC
{
    private boolean standStill;

    public StudentNPC(Point2D position, double angle, Student student) {
        super(position, angle, student);
        this.hitBoxSize = 32;
        this.frame = Math.random() * 3;
        this.sprites = new ArrayList<>();
        this.speed = 100;
        this.rotationSpeed = 0.1;
        this.standStill = false;


        try {
            SpriteFile spriteFile = new SpriteFile("/students.png");
            int xOfSet = 0;
            int yOfSet = 0;
            int characterset = (int)Math.round(Math.random() * 3);
            switch (characterset) {
                case 1 :
                    xOfSet = 3;
                    yOfSet = 4 * 12;
                    break;
                case 2 :
                    xOfSet = 6;
                    yOfSet = 4 * 12;
                    break;
                case 3 :
                    xOfSet = 9;
                    yOfSet = 4 * 12;
                    break;
            }
            int rowStart = 0;

            for (int y = 0; y < 4; y++) {
                rowStart += y * 12;
                for (int x = 0; x < 3; x++) {
                    this.sprites.add(spriteFile.getSprites().get(rowStart + yOfSet + xOfSet));
                    this.sprites.add(spriteFile.getSprites().get(rowStart + yOfSet + xOfSet + 1));
                    this.sprites.add(spriteFile.getSprites().get(rowStart + yOfSet + xOfSet + 2));
                }
            }
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
        if (this.position.distanceSq(mousePos) < this.hitBoxSize * hitBoxSize) {
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
        if (this.target.distanceSq(this.position) < 10){
            standStill = true;
            return;
        }
        standStill = false;
        frame += deltaTime;

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
        }
        else if (rotation > this.rotationSpeed) {
            this.angle += this.rotationSpeed * 100 * deltaTime;
        }
        else {
            this.angle = targetAngle;
        }
        boolean hasCollision = checkCollision(deltaTime);

        if (!hasCollision) {
            Point2D nextPos = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle) * deltaTime,
                    this.position.getY() + this.speed * Math.sin(this.angle) * deltaTime);
            this.position = nextPos;
        }
    }

    private boolean checkCollision(double deltaTime) {
        boolean hasCollision = false;
        for (NPC npc : this.collisionNPCs) {
            if (npc != this) {
                double thereSize = npc.getHitBoxSize();
                if (npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() -this.position.getY(),
                            npc.getPosition().getX() -this.position.getX());
                    this.position = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);
                }
            }
        }
        return hasCollision;
    }

    @Override
    public void draw(Graphics2D graphics) {
        AffineTransform tx = new AffineTransform();
        tx.translate(70, 70);
        for (BufferedImage image: this.sprites) {
            graphics.drawImage(image, tx, null);
            tx.translate(48, 0);
        }
        /*
        int centerX = this.sprites.get(0).getWidth() / (2);
        int centerY = this.sprites.get(0).getHeight() / (2) + 25;
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - centerX, this.position.getY() - centerY);

        if (standStill) {
            System.out.println("standstill");
            graphics.drawImage(this.sprites.get(1), tx, null);

        } else if (angle < Math.toRadians(45) && angle > Math.toRadians(-45)) {
            System.out.println("right");
            graphics.drawImage(this.sprites.get(7), tx, null);

        } else if (angle > Math.toRadians(45) && angle < Math.toRadians(135)) {
            System.out.println("down");
            graphics.drawImage(this.sprites.get(1), tx, null);

        } else if (angle < Math.toRadians(-45)&& angle > Math.toRadians(-135)) {
            System.out.println("upp");
            graphics.drawImage(this.sprites.get(2), tx, null);

        } else {
            System.out.println("left");
            graphics.drawImage(this.sprites.get(9), tx, null);
        }


        graphics.setColor(Color.white);
        graphics.draw(new Ellipse2D.Double(position.getX() - this.hitBoxSize/2, position.getY() - this.hitBoxSize/2, this.hitBoxSize, this.hitBoxSize));
        graphics.draw(new Line2D.Double(this.position, this.target));*/
    }
}
