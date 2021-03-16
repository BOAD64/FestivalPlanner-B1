package b1.simulation;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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

    private int[][] map;

    private BufferedImage cacheImage;

    public Map(BufferedImage tileSet, JsonObject mapObject) {
        this.tiles = new ArrayList<>();

        this.width = mapObject.getInt("width");
        this.height = mapObject.getInt("height");

        this.tileWidth = mapObject.getInt("tilewidth");
        this.tileHeight = mapObject.getInt("tileheight");

        this.tiles = this.divideImage(tileSet);
        this.map = this.tileImageToTiles(mapObject);

        this.cacheImage = null;
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

    private int[][] tileImageToTiles(JsonObject tileObject) {
        this.layersSize = tileObject.getJsonArray("layers").size();
        int[][] map = new int[this.width * this.height][layersSize];
        for (int layer = 0; layer < layersSize; layer++) {
            if (tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data") != null) {
                int dataSize = tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data").size();
                for (int i = 0; i < dataSize; i++) {
                    int tileIndex = tileObject.getJsonArray("layers").getJsonObject(layer).getJsonArray("data").getInt(i) - 1;
                    if (tileIndex >= 0) {
                        map[i][layer] = tileIndex;
                    }
                }
            }
        }
        return map;
    }

    public void draw(Graphics2D graphics) {
        if (this.cacheImage == null) {
            this.cacheImage = new BufferedImage(this.width*this.tileWidth, this.height*this.tileHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D cacheGraphics = this.cacheImage.createGraphics();
            for (int layer = 0; layer < this.layersSize; layer++) {
                int i = 0;

                for (int y = 0; y < this.height; y++) {
                    for (int x = 0; x < this.width; x++) {

                        if (this.map[i][layer] <= 0) {
                            i++;
                            continue;
                        }
                        cacheGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                        cacheGraphics.drawImage(this.tiles.get(this.map[i][layer]), AffineTransform.getTranslateInstance(x * this.tileWidth, y * this.tileHeight), null);
                        i++;
                    }
                }
            }
        }

        graphics.drawImage(this.cacheImage, new AffineTransform(), null);
    }
}
