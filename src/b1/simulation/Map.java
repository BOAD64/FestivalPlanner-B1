package b1.simulation;

import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Map
{
    private int width;
    private int height;

    private int tileWidth;
    private int tileHeight;

    private int layersSize;

    private ArrayList<BufferedImage> tiles;

    private Layer[] map;

    public Map(BufferedImage tileSet, JsonObject mapObject) {
        this.tiles = new ArrayList<>();

        this.width = mapObject.getInt("width");
        this.height = mapObject.getInt("height");

        this.tileWidth = mapObject.getInt("tilewidth");
        this.tileHeight = mapObject.getInt("tileheight");

        this.tiles = this.divideImage(tileSet);
        this.map = this.tileImageToTiles(mapObject);
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
        this.layersSize = tileObject.getJsonArray("layers").size();
        Layer[] map = new Layer[layersSize];
        for (int layer = 0; layer < layersSize; layer++) {
            if (tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data") != null) {
                int dataSize = tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data").size();
                ArrayList<Tile> tiles = new ArrayList<>();
                for (int i = 0; i < dataSize; i++) {
                    int tileIndex = tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data").getInt(i) - 1;
                    if (tileIndex >= 0) {
                        tiles.add(new Tile(i % this.width,i / this.height ,tileIndex));
                    }
                }
                map[layer] = new Layer(tiles.toArray(new Tile[0]), this.width, this.height, this.tileWidth, this.tileHeight);
            }
        }
        return map;
    }

    public void draw(Graphics2D graphics) {
//        for (int layer = 0; layer < this.map.length; layer++) {
//            int i = 0;
//
//            for (int y = 0; y < this.height; y++) {
//                for (int x = 0; x < this.width; x++) {
//
//                    if (this.map[i][layer] <= 0) {
//                        i++;
//                        continue;
//                    }
//                    cacheGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//                    cacheGraphics.drawImage(this.tiles.get(this.map[i][layer]), AffineTransform.getTranslateInstance(x * this.tileWidth, y * this.tileHeight), null);
//                    i++;
//                }
//            }
//        }

        for(Layer layer : this.map)
        {
            layer.draw(graphics, this.tiles);
        }
    }
}
