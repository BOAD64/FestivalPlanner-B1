package b1.simulation;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.*;

public class Target
{
    private Point position;
    private int[][] distanceMap;

    public Target(Point position) {
        this.position = position;
    }

    public void build(WalkableLayer layer) {
        this.distanceMap = new int[layer.getWidth()][layer.getHeight()];
        for (int i = 0; i < this.distanceMap.length; i++) {
            this.distanceMap[i] = new int[layer.getHeight()];
            for (int j = 0; j < this.distanceMap[i].length; j++) {
                this.distanceMap[i][j] = 99999;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> visitedPoints = new ArrayList<>();

        this.distanceMap[this.position.x][this.position.y] = 0;
        queue.add(this.position);
        visitedPoints.add(this.position);

        Point[] offsets = {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};

        while (!queue.isEmpty()) {
            Point current = queue.remove();

            for (Point offset : offsets) {
                Point point = new Point(current.x + offset.x, current.y + offset.y);
                if (point.x < 0 || point.x > layer.getWidth() - 1 || point.y < 0 || point.y > layer.getHeight() - 1) {
                    continue;
                }
                if (visitedPoints.contains(point)) {
                    continue;
                }

//                Tile foundTile = null;
//                for (Tile tile : layer.getTiles()) {
//                    if (tile.getX() == point.x && tile.getY() == point.y) {
//                        if (tile.getTileSetIndex() == 242) {
//                            foundTile = tile;
//                        }
//                        break;
//                    }
//                }

                if(!layer.isWalkable(point.x, point.y))
                {
                    continue;
                }

//                if(foundTile != null)
//                {
//                    continue;
//                }

                this.distanceMap[point.x][point.y] = this.distanceMap[current.x][current.y] + 1;
                visitedPoints.add(point);
                queue.add(point);
            }
        }
    }

    public Point getDirection(Point point) {
        Point[] offsets = {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};
        Point next = new Point(0,0);
        for (Point offset : offsets) {
            if (point.x + offset.x < 0 || point.y + offset.y < 0 || point.x+offset.x >= this.distanceMap.length || point.y+offset.y >= this.distanceMap[0].length) {
                continue;
            }
            if (this.distanceMap[point.x + offset.x][point.y + offset.y] < this.distanceMap[point.x][point.y]) {
                next = offset;

                if(Math.random() > 0.7) {
                    break;
                }
            }
        }
        return next;
    }

    public int getDistance(Point point) {
        if(point.x < 0 || point.y < 0)
        {
            return 0;
        }
        if(point.x >= this.distanceMap.length || point.y >= this.distanceMap[0].length) {
            return 0;
        }
        return this.distanceMap[point.x][point.y];
    }

    public int[][] getDistanceMap() {
        return distanceMap;
    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < this.distanceMap.length; i++) {
            for (int j = 0; j < this.distanceMap[i].length; j++) {
                graphics.setFont(graphics.getFont().deriveFont(10.0f));
                graphics.setColor(Color.GREEN);
                if(this.distanceMap[i][j] != 99999)
                {
                    graphics.drawString(Integer.toString(this.distanceMap[i][j]), i * 32 + 16, j * 32 + 16);
                }
            }
        }
    }

    public Point getPosition() {
        return this.position;
    }

    private static int compare(Tile u1, Tile u2) {
        if (u1.getX() == u2.getX()) {
            return Integer.compare(u1.getY(), u2.getY());
        }
        return Integer.compare(u1.getX(), u2.getX());
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
