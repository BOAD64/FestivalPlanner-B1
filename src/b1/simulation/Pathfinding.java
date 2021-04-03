package b1.simulation;

import java.awt.*;
import java.util.HashMap;

public class Pathfinding {
    private Map map;
    private HashMap<String, Target> targets;

    public Pathfinding(Map map) {
        this.map = map;
        this.targets = new HashMap<>();
        for (java.util.Map.Entry<String, TileObject> tileObject : this.map.getTileObject().entrySet()) {
            addTarget(tileObject.getValue());
        }
    }

    private void addTarget(TileObject tileObject) {
        Target target = new Target(
                new Point((int) (tileObject.getLocation().getX() / map.getTileWidth()),
                        (int) (tileObject.getLocation().getY() / map.getTileHeight())),
                tileObject.getWidth() / this.map.getTileWidth(),
                tileObject.getHeight() / this.map.getTileHeight());
        target.build(this.map.getWalkableLayer());
        this.targets.put(tileObject.getName(), target);
    }

    public HashMap<String, Target> getTargets() {
        return this.targets;
    }
}
