package b1.io;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MapFile
{

    private static JsonObject mapFile;
    private static String path;

    /**
     * This method reads the file logo.png to display the logo display in the window bars of every window.
     *
     * @return logo as Image.
     */
    public static JsonObject getMapFile() {
        if (mapFile == null) {
            if(path != null && !path.isEmpty()) {
                try {
                    InputStream inputStream = new FileInputStream(path);
                    JsonReader reader = Json.createReader(inputStream);
                    mapFile = reader.readObject();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mapFile;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        MapFile.path = path;
    }
}
