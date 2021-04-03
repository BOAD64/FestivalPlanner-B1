package b1.simulation.NPC;

        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;

        import java.awt.geom.Point2D;
        import java.util.ArrayList;

        import static org.junit.jupiter.api.Assertions.*;

class StudentNPCTest {

    @Test
    void checkCollisionWithTwoCloseStudents() {
        //Arrange
        StudentNPC student1 = new StudentNPC(new Point2D.Double(200, 200),0,null, null);
        StudentNPC student2 = new StudentNPC(new Point2D.Double(210, 200),0,null, null);
        ArrayList<NPC> arrayList = new ArrayList<>();
        arrayList.add(student1);
        arrayList.add(student2);
        student1.setCollisionNPCS(arrayList);
        student2.setCollisionNPCS(arrayList);
        //Act
        boolean resultValue = student1.checkCollision(0.5);
        //Assert
        Assertions.assertTrue(resultValue);
    }

}