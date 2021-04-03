package b1.simulation;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.*;

public class Target {
    private Point position;
    private int width;
    private int height;
    private int[][] distanceMap;

    public Target(Point position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void build(WalkableLayer layer) {
        this.distanceMap = new int[layer.getWidth()][layer.getHeight()];
        for(int i = 0; i < this.distanceMap.length; i++) {
            this.distanceMap[i] = new int[layer.getHeight()];
            for(int j = 0; j < this.distanceMap[i].length; j++) {
                this.distanceMap[i][j] = 99999;
            }
        }

        this.position = this.findNearestFreePosition(layer);

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> visitedPoints = new ArrayList<>();

        this.distanceMap[this.position.x][this.position.y] = 0;
        queue.add(this.position);
        visitedPoints.add(this.position);

        Point[] offsets = {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};

        while(!queue.isEmpty()) {
            Point current = queue.remove();

            for(Point offset : offsets) {
                Point point = new Point(current.x + offset.x, current.y + offset.y);
                if(point.x < 0 || point.x > layer.getWidth() - 1 || point.y < 0 || point.y > layer.getHeight() - 1) {
                    continue;
                }
                if(visitedPoints.contains(point)) {
                    continue;
                }

                if(!layer.isWalkable(point.x, point.y)) {
                    continue;
                }

                this.distanceMap[point.x][point.y] = this.distanceMap[current.x][current.y] + 1;
                visitedPoints.add(point);
                queue.add(point);
            }
        }
    }

    public Point getDirection(Point point) {
        Point[] offsets = {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};
        Point next = new Point(0, 0);
        for(Point offset : offsets) {
            if(point.x + offset.x < 0 || point.y + offset.y < 0 || point.x + offset.x >= this.distanceMap.length || point.y + offset.y >= this.distanceMap[0].length) {
                continue;
            }
            if(this.distanceMap[point.x + offset.x][point.y + offset.y] < this.distanceMap[point.x][point.y]) {
                next = offset;

                if(Math.random() > 0.7) {
                    break;
                }
            }
        }
        return next;
    }

    public int getDistance(Point point) {
        if(point.x < 0 || point.y < 0) {
            return 0;
        }
        if(point.x >= this.distanceMap.length || point.y >= this.distanceMap[0].length) {
            return 0;
        }
        return this.distanceMap[point.x][point.y];
    }

    public boolean hasArrived(Point point) {
        return this.position.x <= point.x && this.position.x + this.width >= point.x &&
                this.position.y <= point.y && this.position.y + this.height >= point.y;
    }

    public int[][] getDistanceMap() {
        return distanceMap;
    }

    public void draw(Graphics graphics) {
        for(int i = 0; i < this.distanceMap.length; i++) {
            for(int j = 0; j < this.distanceMap[i].length; j++) {
                graphics.setFont(graphics.getFont().deriveFont(10.0f));
                graphics.setColor(Color.GREEN);
                if(this.distanceMap[i][j] != 99999) {
                    graphics.drawString(Integer.toString(this.distanceMap[i][j]), i * 32 + 16, j * 32 + 16);
                }
            }
        }
    }

    public Point getPosition() {
        return this.position;
    }

    private Point findNearestFreePosition(WalkableLayer walkableLayer) {
        if(walkableLayer.isWalkable(this.position.x, this.position.y)) {
            return this.position;
        }

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> visitedPoints = new ArrayList<>();

        queue.add(this.position);
        visitedPoints.add(this.position);

        Point[] offsets = {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};

        while(!queue.isEmpty()) {
            Point current = queue.remove();

            for(Point offset : offsets) {
                Point point = new Point(current.x + offset.x, current.y + offset.y);
                if(point.x < 0 || point.x > walkableLayer.getWidth() - 1 || point.y < 0 || point.y > walkableLayer.getHeight() - 1) {
                    continue;
                }
                if(visitedPoints.contains(point)) {
                    continue;
                }

                if(walkableLayer.isWalkable(point.x, point.y)) {
                    return point;
                }

                visitedPoints.add(point);
                queue.add(point);
            }
        }
        return this.position;
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
