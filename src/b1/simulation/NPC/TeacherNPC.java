package b1.simulation.NPC;

import b1.school.person.Teacher;
import b1.school.person.TeacherController;
import javafx.collections.FXCollections;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TeacherNPC extends NPC {

    public TeacherNPC(Point2D position, double angle, Teacher teacher) {
        super(position, angle, teacher);
    }

    @Override
    public void openPerson(Point2D mousePos) {
        TeacherController teacherController = new TeacherController((Teacher)this.person);
        teacherController.show();
    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public void setCollisionNPCS(ArrayList<NPC> collisionNPCs)
    {
        super.collisionNPCs = FXCollections.observableList(collisionNPCs);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void draw(Graphics2D graphics) {
    }
}
