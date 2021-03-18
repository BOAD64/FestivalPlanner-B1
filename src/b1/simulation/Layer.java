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

    public void draw(Graphics2D graphics, ArrayList<BufferedImage> images)
    {
        if(this.cacheImage == null)
        {
            this.cacheImage = new BufferedImage(this.width*this.tileWidth, this.height*this.tileHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D cacheGraphics = this.cacheImage.createGraphics();
            for(Tile tile : this.tiles)
            {
                if(images.size() < tile.getTileSetIndex()-1) {
                    continue;
                }
                cacheGraphics.drawImage(images.get(tile.getTileSetIndex()), AffineTransform.getTranslateInstance(tile.getX() * this.tileWidth, tile.getY() * this.tileHeight), null);
            }
        }
        graphics.drawImage(this.cacheImage, new AffineTransform(),null);
    }

    public String getName() {
        return this.name;
    }
}
