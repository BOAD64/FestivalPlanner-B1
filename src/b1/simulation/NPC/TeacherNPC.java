package b1.simulation.NPC;

import b1.school.person.Teacher;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TeacherNPC extends NPC {

    public TeacherNPC(Point2D position, double angle, Teacher teacher) {
        super(position, angle, teacher);
    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public void update(ArrayList<NPC> CollisionNPCs) {

    }

    @Override
    public void draw(Graphics2D graphics) {
    }
}
