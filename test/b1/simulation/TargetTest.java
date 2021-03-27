package b1.simulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TargetTest
{
    Target target;
    @BeforeEach
    void setUp() {
        WalkableLayer walkableLayer = new WalkableLayer(
                new Tile[]{
                        new Tile(4,0, 242),
                        new Tile(4,1, 242),
                        new Tile(5,1, 242),
                        new Tile(6,1, 242),
                        new Tile(6,0, 242)
                },
                "WalkableLayer", 10, 10, 32, 32);
        this.target = new Target(new Point(7, 3));
        this.target.build(walkableLayer);
    }

    @Test
    void testGetDirectionWithEnclosedPoint() {
        Assertions.assertEquals( new Point(0,0), this.target.getDirection(new Point(5,0)));
    }

    @Test
    void testGetDirectionWithPointNearTarget() {
        Assertions.assertEquals( new Point(0,1), this.target.getDirection(new Point(7,2)));
    }

    @Test
    void testGetDirectionWithPointNearTarget2() {
        Assertions.assertEquals( new Point(1,0), this.target.getDirection(new Point(6,3)));
    }

    @Test
    void testGetDirectionWithPointOnTarget()
    {
        Assertions.assertEquals( new Point(0,0), this.target.getDirection(new Point(7,3)));
    }
}