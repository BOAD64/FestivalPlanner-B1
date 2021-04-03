package b1.simulation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Layer {
    private Tile[] tiles;
    private String name;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;

    private BufferedImage cacheImage;

    public Layer(Tile[] tiles, String name, int width, int height, int tileWidth, int tileHeight) {
        this.tiles = tiles;
        this.name = name;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void draw(Graphics2D graphics, ArrayList<BufferedImage> images) {
        if (this.cacheImage == null) {
            this.cacheImage = new BufferedImage(this.width * this.tileWidth, this.height * this.tileHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D cacheGraphics = this.cacheImage.createGraphics();
            for (Tile tile : this.tiles) {
                if (images.size() < tile.getTileSetIndex() - 1) {
                    continue;
                }
                cacheGraphics.drawImage(images.get(tile.getTileSetIndex()), AffineTransform.getTranslateInstance(tile.getX() * this.tileWidth, tile.getY() * this.tileHeight), null);
            }
        }
        graphics.drawImage(this.cacheImage, new AffineTransform(), null);
    }

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public BufferedImage getCacheImage() {
        return cacheImage;
    }

    public void setCacheImage(BufferedImage cacheImage) {
        this.cacheImage = cacheImage;
    }
}
