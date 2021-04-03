package b1.simulation.NPC;

import b1.school.person.Person;
import b1.simulation.Target;
import b1.simulation.WalkableLayer;
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
    //    Point2D target;
    Target target;
    double rotationSpeed;
    double speed;
    Person person;
    ObservableList<NPC> collisionNPCs;
    private boolean standStill;
    private Target standardTarget;
    private WalkableLayer walkableLayer;
    private Font font;
    private boolean isHome;
    private boolean hasArrived = false;
    private CallbackNPC callbackNPC;

    public NPC(Point2D position, double angle, Person person, WalkableLayer walkableLayer) {
        this.position = position;
        this.angle = angle;
        this.person = person;
//        this.target = position;
        this.standStill = false;
        this.walkableLayer = walkableLayer;
        this.font = new Font("Rockwell", Font.PLAIN, 10);
    }

//    /**
//     * Set target position for NPC to walk to.
//     *
//     * @param position target position.
//     */
//    public void setTarget(Point2D position) {
//        this.target = position;
//    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Target getStandardTarget() {
        return standardTarget;
    }

    public void setStandardTarget(Target standardTarget) {
        this.standardTarget = standardTarget;
    }

    /**
     * Fill list to check collision with other NPC's.
     *
     * @param collisionNPCs NPC check list.
     */
    public void setCollisionNPCS(ArrayList<NPC> collisionNPCs) {
        this.collisionNPCs = FXCollections.observableList(collisionNPCs);
    }

    public Person getPerson() {
        return this.person;
    }

    /**
     * Updates position, direction and frame for NPC.
     *
     * @param deltaTime declares animation speed.
     */
    public void update(double deltaTime) {
        if(this.target == null || this.isHome) {
            return;
        }
//        deltaTime = Math.abs(deltaTime);
//        int distance = this.target.getDistance(new Point((int)this.position.getX() / 32, (int)this.position.getY() / 32));
        if(this.target.hasArrived(new Point((int) this.position.getX() / 32, (int) this.position.getY() / 32))) {
            this.standStill = true;

            this.hasArrived();

            //ToDo change visible?
            return;
        }

        this.hasArrived = false;
        this.standStill = false;
        this.frame += deltaTime * 10;
        if(this.frame >= 3) {
            this.frame = 0;
        }

        int tileX = (int) this.position.getX() / 32;
        int tileY = (int) this.position.getY() / 32;

        Point direction = this.target.getDirection(new Point(tileX, tileY));

        Point2D targetPos = new Point2D.Double(this.position.getX() + direction.getX(), this.position.getY() + direction.getY());
        //turnspeed
        double targetAngle = Math.atan2(targetPos.getY() - this.position.getY(), targetPos.getX() - this.position.getX());
        double rotation = targetAngle - this.angle;
        while(rotation < -Math.PI) {
            rotation += 2 * Math.PI;
        }
        while(rotation > Math.PI) {
            rotation -= 2 * Math.PI;
        }

        if(rotation < -(this.rotationSpeed * 100 * deltaTime)) {
            this.angle -= this.rotationSpeed * 100 * deltaTime;
        } else if(rotation > this.rotationSpeed * 100 * deltaTime) {
            this.angle += this.rotationSpeed * 100 * deltaTime;
        } else {
            this.angle = targetAngle;
        }
//        this.angle = targetAngle;
        boolean hasCollision = checkCollision(deltaTime);

        if(!hasCollision) {
            Point2D nextPos = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle) * deltaTime,
                    this.position.getY() + this.speed * Math.sin(this.angle) * deltaTime);
            this.updatePosition(nextPos);
        }
    }

    private void updatePosition(Point2D nextPos) {
        if(this.walkableLayer.isWalkable((int) (nextPos.getX() / 32.0), (int) (nextPos.getY() / 32.0))) {
            this.position = nextPos;
        }
    }

    /**
     * Draws NPC sprite in correct direction, action and position.
     *
     * @param graphics
     */
    public void draw(Graphics2D graphics, boolean debug) {
        if(this.isHome) {
            return;
        }

        int centerX = this.sprites.get(0).getWidth() / (2);
        int centerY = this.sprites.get(0).getHeight() / (2) + 25;
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - centerX, this.position.getY() - centerY);

        if(this.standStill) {
            graphics.drawImage(this.sprites.get(1), tx, null);

        } else if(this.angle < Math.toRadians(45) && this.angle > Math.toRadians(-45)) {
            graphics.drawImage(this.sprites.get(6 + (int) this.frame), tx, null);

        } else if(this.angle > Math.toRadians(45) && this.angle < Math.toRadians(135)) {
            graphics.drawImage(this.sprites.get((int) this.frame), tx, null);

        } else if(this.angle < Math.toRadians(-45) && this.angle > Math.toRadians(-135)) {
            graphics.drawImage(this.sprites.get(9 + (int) this.frame), tx, null);

        } else {
            graphics.drawImage(this.sprites.get(3 + (int) this.frame), tx, null);
        }

        //namePlate
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        graphics.setColor(Color.white);
        graphics.setFont(this.font);
        String name = this.person.getName();
        Shape text = font.createGlyphVector(graphics.getFontRenderContext(), name).getOutline();
        double offset = 23 - (text.getBounds2D().getWidth() / 2);
        tx.translate(offset, 0);
        graphics.fill(tx.createTransformedShape(text));
        tx.translate(-offset, 0);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));


        //Draw hitBox and target
        if(this.target != null && debug) {
            this.target.draw(graphics);
            graphics.setColor(Color.white);
            graphics.draw(new Ellipse2D.Double(this.position.getX() - this.hitBoxSize / 2, this.position.getY() - this.hitBoxSize / 2, this.hitBoxSize, this.hitBoxSize));
            graphics.draw(new Line2D.Double(this.position, new Point2D.Double(this.target.getPosition().x * 32, this.target.getPosition().y * 32)));
        }
    }

    private void hasArrived() {
        if(this.callbackNPC != null) {
            this.callbackNPC.hasArrived(this, this.target);
        }

        this.hasArrived = true;
    }

    public void setIsHome(boolean home) {
        this.isHome = home;
    }

    public CallbackNPC getCallbackNPC() {
        return this.callbackNPC;
    }

    public void setCallbackNPC(CallbackNPC callbackNPC) {
        this.callbackNPC = callbackNPC;
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
        for(NPC npc : this.collisionNPCs) {
            if(npc.isHome) {
                continue;
            }

            if(npc != this) {
                double thereSize = npc.getHitBoxSize();
                if(npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() - this.position.getY(),
                            npc.getPosition().getX() - this.position.getX());
                    Point2D nextPos = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);

                    this.updatePosition(nextPos);

                    npc.pushPositionBack(Math.atan2(
                            this.position.getY() - npc.getPosition().getY(),
                            this.position.getX() - npc.getPosition().getX()) + (Math.random())
                            , deltaTime);
                }
            }
        }
        return hasCollision;
    }

    /*
     * Same at the checkCollision but without the pushback code to make sure there wil be no stackOverflows
     */
    private boolean checkCollisionWithoutPushBack(double deltaTime) {
        boolean hasCollision = false;
        for(NPC npc : this.collisionNPCs) {
            if(npc.isHome) {
                continue;
            }

            if(npc != this) {
                double thereSize = npc.getHitBoxSize();
                if(npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() - this.position.getY(),
                            npc.getPosition().getX() - this.position.getX());
                    Point2D nextPos = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);

                    this.updatePosition(nextPos);
                }
            }
        }
        return hasCollision;
    }

    /**
     * Used only by the collision check.
     * Pushes the NPC back in the direction given.
     *
     * @param direction
     * @param deltaTime
     */
    public void pushPositionBack(double direction, double deltaTime) {
        Point2D nextPos = new Point2D.Double(
                this.position.getX() - (this.speed / 2) * Math.cos(direction) * deltaTime,
                this.position.getY() - (this.speed / 2) * Math.sin(direction) * deltaTime);
        this.updatePosition(nextPos);
        checkCollisionWithoutPushBack(deltaTime);
    }

    abstract public void openPerson(Point2D mousePos);

    @Override
    public boolean equals(Object obj) {
        if(!obj.getClass().equals(this.getClass())) {
            return false;
        }
        return ((NPC) obj).getPerson().equals(this.getPerson());
    }

    public void sendToStandardTarget() {
        this.setTarget(this.getStandardTarget());
    }
}