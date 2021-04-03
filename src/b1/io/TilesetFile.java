package b1.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class TilesetFile {

    private static BufferedImage tileset;
    private static URL path;

    /**
     * This method reads the file logo.png to display the logo display in the window bars of every window.
     *
     * @return logo as Image.
     */
    public static BufferedImage getTileset() {
        if (tileset == null) {
            if (path != null) {
                try {
                    tileset = ImageIO.read(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return tileset;
    }

    public static URL getPath() {
        return path;
    }

    public static void setPath(URL path) {
        TilesetFile.path = path;
    }
}
