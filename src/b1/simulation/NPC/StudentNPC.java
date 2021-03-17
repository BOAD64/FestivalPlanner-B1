package b1.simulation.NPC;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StudentNPC extends NPC {

    public StudentNPC(Point2D position, double angle) {
        super(position, angle);
        this.frame = Math.random() * 10;
        this.target = position;
        this.rotationSpeed = 0.1;
        this.sprites = new ArrayList<>();
        this.speed = 2;
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/testNPC.png"));
            sprites.add(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTarget(Point2D position) {
        super.setTarget(position);
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }

    @Override
    public void update() {
        if(target.distanceSq(position) < 10)
            return;

        this.angle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());

        this.position = new Point2D.Double(
                this.position.getX() + this.speed * Math.cos(this.angle),
                this.position.getY() + this.speed * Math.sin(this.angle));
    }

    @Override
    public void draw(Graphics2D graphics) {
        int centerX = sprites.get(0).getWidth()/(2 * 20); //(...) must be 2 in real picture
        int centerY = sprites.get(0).getHeight()/(2 * 20); //(...) must be 2 in real picture
        AffineTransform tx = new AffineTransform();

        tx.translate(position.getX() - centerX, position.getY() - centerY);
        tx.rotate(angle + Math.PI/2, centerX, centerY);
        tx.scale(0.05, 0.05);

        graphics.drawImage(this.sprites.get(0), tx, null);



        graphics.setColor(Color.white);
        graphics.draw(new Ellipse2D.Double(position.getX()-32, position.getY()-32, 64, 64));
    }
}
