package b1.simulation.npc;

import b1.io.SpriteFile;
import b1.school.person.Teacher;
import b1.school.person.TeacherController;
import b1.simulation.Camera;
import b1.simulation.WalkableLayer;

import java.awt.geom.*;
import java.util.ArrayList;

public class TeacherNPC extends NPC {
    public TeacherNPC(Point2D position, double angle, Teacher teacher, WalkableLayer walkableLayer) {
        super(position, angle, teacher, walkableLayer);
        this.hitBoxSize = 32;
        this.frame = Math.random() * 3;
        this.sprites = new ArrayList<>();
        this.speed = 75;
        this.rotationSpeed = 0.1;
        this.getSprites();
    }

    /*
     * Gets figure set from sprite set for single teacher NPC.
     */
    private void getSprites() {
        try {
            SpriteFile.setPath("/students.png");
            int xOffSet = 3;
            int yOffSet = 0;
            int rowStart;

            for (int y = 0; y < 4; y++) {
                rowStart = y * 12;
                for (int x = 0; x < 3; x++) {
                    this.sprites.add(SpriteFile.getSprites().get(rowStart + yOffSet + xOffSet + x));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When hitBox is clicked, teacher information will be opened.
     *
     * @param mousePos current mouse position.
     */
    @Override
    public void openPerson(Point2D mousePos, Camera camera) {
        Point2D correction = new Point2D.Double(mousePos.getX() - 600 / camera.getZoom(),
                mousePos.getY() - 400 / camera.getZoom());
        if (this.position.distanceSq(correction) < this.hitBoxSize * this.hitBoxSize) {
            TeacherController teacherController = new TeacherController((Teacher) this.person);
            teacherController.show();
        }
    }
}
