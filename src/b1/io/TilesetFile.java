package b1.io;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class TilesetFile
{

    private static BufferedImage tileset;
    private static String path;

    /**
     * This method reads the file logo.png to display the logo display in the window bars of every window.
     *
     * @return logo as Image.
     */
    public static BufferedImage getTileset() {
        if (tileset == null) {
            if(path != null && !path.isEmpty()) {
                try {
                    File file = new File(path);
                    tileset = ImageIO.read(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return tileset;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        TilesetFile.path = path;
    }
}
