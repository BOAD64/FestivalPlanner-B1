package b1.simulation;

import b1.simulation.NPC.NPC;

import java.awt.*;
import java.util.ArrayList;

public class Pathfinding {


    private Map map;
    private ArrayList<NPC> npcs;
    private ArrayList<Target> targets;

    public Pathfinding(Map map, ArrayList<NPC> npcs){
        this.map = map;
        this.npcs = npcs;
        this.targets = new ArrayList<>();
    }

    public void init(){
        for (TileObject tileObject : this.map.getObjects()){
            addTarget(tileObject);
        }

    }


    //todo: NPCs currently find top left corner of classroom, this can be changed later.
    private void addTarget(TileObject tileObject){
        this.targets.add(new Target(new Point((int)(tileObject.getLocation().getX() / map.getTileWidth()),
                (int)(tileObject.getLocation().getY() / map.getTileHeight()))));
        System.out.println("x: " + (tileObject.getLocation().getX()) / map.getTileWidth());
        System.out.println("y: " + (tileObject.getLocation().getY()) / map.getTileHeight() + "\n");

    }


}
