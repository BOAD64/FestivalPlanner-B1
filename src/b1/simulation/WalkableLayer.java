package b1.simulation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class WalkableLayer extends Layer
{
    private final boolean[][] walkable;
    public WalkableLayer(Tile[] tiles, String name, int width, int height, int tileWidth, int tileHeight) {
        super(tiles, name, width, height, tileWidth, tileHeight);

        this.walkable = new boolean[width][height];
        for(boolean[] xWalkable : this.walkable)
        {
            Arrays.fill(xWalkable, true);
        }
        for(Tile tile : tiles)
        {
            this.walkable[tile.getX()][tile.getY()] = tile.getTileSetIndex() != 242;
        }
    }

    @Override
    public void draw(Graphics2D graphics, ArrayList<BufferedImage> images)
    {
//        super.draw(graphics, images);

        Color walkableColor = new Color(0, 255, 0, 125);
        Color notWalkableColor = new Color(255, 0, 0, 125);

        for(int x = 0; x < this.walkable.length; x++)
        {
            for(int y = 0; y < this.walkable[0].length; y++)
            {
                graphics.setColor(this.walkable[x][y]?walkableColor:notWalkableColor);
                graphics.fill(new Rectangle(x*32, y*32, 32, 32));
            }
        }
    }


    public boolean isWalkable(int x, int y)
    {
        if(x < 0 || y < 0)
        {
            return false;
        }
        if(x > super.getWidth() - 1 || y > super.getHeight() - 1)
        {
            return false;
        }
        return this.walkable[x][y];
    }

    public boolean[][] getWalkable() {
        return walkable;
    }
}
