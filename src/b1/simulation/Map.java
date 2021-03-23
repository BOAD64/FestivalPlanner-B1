package b1.simulation;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Map
{
    private int width;
    private int height;

    private int tileWidth;
    private int tileHeight;

    private int layersSize;

    private ArrayList<BufferedImage> tiles;

    private Layer[] map;
    private HashMap<String, TileObject> tileObject;
    private WalkableLayer walkableLayer;

    public Map(BufferedImage tileSet, JsonObject mapObject) {
        this.tiles = new ArrayList<>();

        this.width = mapObject.getInt("width");
        this.height = mapObject.getInt("height");

        this.tileWidth = mapObject.getInt("tilewidth");
        this.tileHeight = mapObject.getInt("tileheight");

        this.tiles = this.divideImage(tileSet);
        this.map = this.tileImageToTiles(mapObject);
        this.tileObject = this.getTileObjectsFromJson(mapObject);
    }

    private ArrayList<BufferedImage> divideImage(BufferedImage tileSet) {
        ArrayList<BufferedImage> tiles = new ArrayList<BufferedImage>();
        for (int y = 0; y < tileSet.getHeight(); y += this.tileHeight) {
            for (int x = 0; x < tileSet.getWidth(); x += this.tileWidth) {
                tiles.add(tileSet.getSubimage(x, y, this.tileWidth, this.tileHeight));
            }
        }
        return tiles;
    }

    private Layer[] tileImageToTiles(JsonObject tileObject) {
        JsonArray layersArray = tileObject.getJsonArray("layers");
        this.layersSize = layersArray.size();
        ArrayList<Layer> layers = new ArrayList<>(this.layersSize);
        for (int layerCounter = 0; layerCounter < layersSize; layerCounter++) {
            JsonObject layerObject = layersArray.getJsonObject(layerCounter);
            if (layerObject.getJsonArray("data") != null) {
                JsonArray layerData = layerObject.getJsonArray("data");
                ArrayList<Tile> tiles = new ArrayList<>();
                for (int i = 0; i < layerData.size(); i++) {
                    int tileIndex = layerData.getInt(i) - 1;
                    if (tileIndex >= 0) {
                        tiles.add(new Tile(i % this.width, i / this.height, tileIndex));
                    }
                }
                Layer layer;
                if (layerObject.getString("name").equals("walkable")) {
                    layer = new WalkableLayer(tiles.toArray(new Tile[0]), layerObject.getString("name"), this.width, this.height, this.tileWidth, this.tileHeight);
                    this.walkableLayer = (WalkableLayer) layer;
                }
                else
                {
                    layer = new Layer(tiles.toArray(new Tile[0]), layerObject.getString("name"), this.width, this.height, this.tileWidth, this.tileHeight);
                }
                layers.add(layer);
            }
        }
        return layers.toArray(new Layer[0]);
    }

    private HashMap<String, TileObject> getTileObjectsFromJson(JsonObject tileObject) {
        HashMap<String, TileObject> result = new HashMap<>();
        JsonArray layersArray = tileObject.getJsonArray("layers");
        this.layersSize = layersArray.size();
//        ArrayList<TileObject> tileObjects = new ArrayList<>();
        for (int layer = 0; layer < layersSize; layer++) {
            JsonObject layerObject = layersArray.getJsonObject(layer);
            if (layerObject.getString("type").equals("objectgroup")) {
                JsonArray objects = layerObject.getJsonArray("objects");
                for (int objectIndex = 0; objectIndex < objects.size(); objectIndex++) {
                    JsonObject object = objects.getJsonObject(objectIndex);
                    TileObject tileObject1 = new TileObject(object.getInt("height"),
                            object.getInt("id"),
                            object.getString("name"),
                            object.getInt("width"),
                            new Point2D.Double(object.getInt("x"),
                                    object.getInt("y")));

                    result.put(tileObject1.getName(), tileObject1);
                }
            }
        }
        return result;
    }

    public void draw(Graphics2D graphics) {
        for (Layer layer : this.map) {
            if (!layer.getName().equals("walkable")) {
                layer.draw(graphics, this.tiles);
            }
            this.walkableLayer.draw(graphics, this.tiles);
        }
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public HashMap<String, TileObject> getTileObject() {
        return this.tileObject;
    }

    public WalkableLayer getWalkableLayer() {
        return this.walkableLayer;
    }
}
