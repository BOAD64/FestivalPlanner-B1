package b1.simulation.NPC;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class NPC {
    Point2D position;
    double angle;
    ArrayList<BufferedImage> sprites;
    double frame;
    Point2D target;
    double rotationSpeed;
    double speed;

    public NPC(Point2D position, double angle) {
        this.position = position;
        this.angle = angle;
    }

    public void setTarget(Point2D position) {
        this.target = position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    abstract public void update();

    abstract public void draw(Graphics2D graphics);
}

//notes van opstart week 5
    /*
        tussen 2 punten lopen. niet de ofset doorsturen tussen de 2 punten. maar de richting en dan vooruit laten lopen.
        bereken met tangus. overstaande gedeeld door aanligende. maar levert fout op

        --> gebruik atan2 functie = atan2(double y, double x) waardes van + PI naar - PI


        Maak een list aan npcs. met beginpositie (spawn) en een richting.
        geef de functies update en draw
        (bind update vast aan de deltatime in clock. hoort al geregeld te zijn)

        Om het plaatje uit te lezen. voeg het toe aan de npc klasse als een bufferdImage (IO klasse?)
        - handig: this.sprites.add(image.getSubimage())

        gebruik affineTransform in draw
        teken de sprite op de gegeven position in de klass
        gebruik een elipse voor de collision box

        om plaatje op de goede plaats te zetten gebruik sprites.get(0).getwith / 2

        mooie formule voor goede verplaatsing
        > X translatie is cos(angle) als de afstand rechtdoor 1 is
        > Y translatie is Sin(angle) als de afstand rechtdoor 1 is
        --> X translatie is (cos(angle)) * snelheid
        --> Y translatie is (sin(angle)) * snelheid
        -newPos = new Point2D.Double(this.pos.getx() + this.speed * Math.cos(this.angle), this.pos.gety() + this.speed * Math.sin(this.angle));

        voor beter lopen gebruik een target. Gebruik een setTarget functie.

        voor een niet instand angle verandering gebruik een target angle.

        voor collision geef alle visitors mee in de update methode.
        als de afstand tussen 2 hitbox cirkels klijner is dan de straal dan hebben ze collision
        dus worltel((x1 - x2)^2 + (y1 - y2)^2)
        AKA --> npc.getPosition.distanceSq(position) < minimumDistance;

     */
