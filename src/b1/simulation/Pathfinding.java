package b1.simulation;

import b1.simulation.NPC.NPC;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Pathfinding
{


    private Map map;
    private ArrayList<NPC> npcs;
    private ArrayList<Target> targets;

    public Pathfinding(Map map, ArrayList<NPC> npcs) {
        this.map = map;
        this.npcs = npcs;
        this.targets = new ArrayList<>();
    }

    public void init() {
        for (TileObject tileObject : this.map.getObjects()) {
            addTarget(tileObject);
        }
    }


    //todo: NPCs currently find top left corner of classroom, this can be changed later.
    private void addTarget(TileObject tileObject) {
        Target target = new Target(new Point((int) (tileObject.getLocation().getX() / map.getTileWidth()), (int) (tileObject.getLocation().getY() / map.getTileHeight())));

        long startTime = System.nanoTime();

        target.build(this.map.getWalkableLayer());

        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);  //Total execution time in nano seconds
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);  //Total execution time in nano seconds
        System.out.println(durationInMillis);

        this.targets.add(target);
        System.out.println("x: " + (tileObject.getLocation().getX()) / map.getTileWidth());
        System.out.println("y: " + (tileObject.getLocation().getY()) / map.getTileHeight() + "\n");

    }

    public ArrayList<Target> getTargets() {
        return targets;
    }
}
