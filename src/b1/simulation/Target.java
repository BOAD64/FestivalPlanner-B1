package b1.simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Target {
    private Point position;
    private int[][] distanceMap;

    public Target(Point position) {
        this.position = position;
    }

    public void build(Layer layer){
        this.distanceMap = new int[layer.getWidth()][layer.getHeight()];
        Arrays.fill(this.distanceMap, 99999);

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> visitedPoints = new ArrayList<>();

        this.distanceMap[this.position.x][this.position.y] = 0;
        queue.add(this.position);
        visitedPoints.add(this.position);

        Point[] offsets = {
                new Point(1,0), new Point(-1,0), new Point(0, 1), new Point(0, -1)};

        while (!queue.isEmpty()){
            Point current = queue.remove();

            OffsetLoop:
            for (Point offset: offsets) {
                Point point = new Point(current.x + offset.x, current.y + offset.y);
                if (point.x < 0 || point.x > layer.getWidth() -1 || point.y < 0 || point.y > layer.getHeight() -1){
                    continue;
                }
                if (visitedPoints.contains(point)){
                    continue;
                }

                for (Tile tile : layer.getTiles()){
                    if (tile.getX() == point.x && tile.getY() == point.y){
                        if (tile.getTileSetIndex() == 243){
                            continue OffsetLoop;
                        }
                    }
                }
                this.distanceMap[point.x][point.y] = this.distanceMap[current.x][current.y] + 1;
                queue.add(point);
                visitedPoints.add(point);
            }
        }
    }

    public int[][] getDistanceMap() {
        return distanceMap;
    }
}
