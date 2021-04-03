package b1.simulation.npc;

import b1.io.SpriteFile;
import b1.school.person.Student;
import b1.school.person.StudentController;
import b1.simulation.Camera;
import b1.simulation.WalkableLayer;

import java.awt.geom.*;
import java.util.ArrayList;

public class StudentNPC extends NPC {
    public StudentNPC(Point2D position, double angle, Student student, WalkableLayer walkableLayer) {
        super(position, angle, student, walkableLayer);
        this.hitBoxSize = 32;
        this.frame = Math.random() * 3;
        this.sprites = new ArrayList<>();
        this.speed = 100;
        this.rotationSpeed = 0.1;
        this.getSprites();
    }

    /*
     * Gets one of three random figure sets from sprites for single student NPC
     */
    private void getSprites() {
        try {
            SpriteFile.setPath("/students.png");
            int xOffSet = 0;
            int yOffSet = 0;
            int characterSet = 0;
            while (characterSet == 0) {
                characterSet = (int) Math.round(Math.random() * 3);
            }
            switch (characterSet) {
                case 1:
                    xOffSet = 3;
                    yOffSet = 4 * 12;
                    break;
                case 2:
                    xOffSet = 6;
                    yOffSet = 4 * 12;
                    break;
                case 3:
                    xOffSet = 9;
                    yOffSet = 4 * 12;
                    break;
            }
            int rowStart = 0;

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
     * When hitBox is clicked, student information will be opened.
     *
     * @param mousePos current mouse position.
     */
    @Override
    public void openPerson(Point2D mousePos, Camera camera) {
        Point2D correction = new Point2D.Double(mousePos.getX() - 600 / camera.getZoom(),
                mousePos.getY() - 400 / camera.getZoom());
        if (this.position.distanceSq(correction) < this.hitBoxSize * this.hitBoxSize) {
            StudentController studentController = new StudentController((Student) this.person);
            studentController.show();
        }
    }
}
