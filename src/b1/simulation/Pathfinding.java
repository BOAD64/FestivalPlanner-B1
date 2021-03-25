package b1.simulation;

import b1.simulation.NPC.NPC;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Pathfinding
{
    private Map map;
    private HashMap<String, Target> targets;

    public Pathfinding(Map map) {
        this.map = map;
        this.targets = new HashMap<>();
        for (java.util.Map.Entry<String, TileObject> tileObject : this.map.getTileObject().entrySet()) {
            addTarget(tileObject.getValue());
        }
    }

    //todo: NPCs currently find top left corner of classroom, this can be changed later.
    private void addTarget(TileObject tileObject) {
        Target target = new Target(new Point((int) (tileObject.getLocation().getX() / map.getTileWidth()), (int) (tileObject.getLocation().getY() / map.getTileHeight())));
        target.build(this.map.getWalkableLayer());
        this.targets.put(tileObject.getName(), target);
    }

    public HashMap<String, Target> getTargets() {
        return this.targets;
    }
}
