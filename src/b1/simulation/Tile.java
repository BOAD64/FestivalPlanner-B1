package b1.simulation;

public class Tile {
    private final int x;
    private final int y;
    private final int tileSetIndex;

    public Tile(int x, int y, int tileSetIndex) {
        this.x = x;
        this.y = y;
        this.tileSetIndex = tileSetIndex;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getTileSetIndex() {
        return this.tileSetIndex;
    }
}
