package b1.io;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class MapFile
{

    private static JsonObject mapFile;
    private static URL path;

    /**
     * This method reads the file logo.png to display the logo display in the window bars of every window.
     *
     * @return logo as Image.
     */
    public static JsonObject getMapFile() {
        if (mapFile == null) {
            if (path != null) {
                try {
                    JsonReader reader = Json.createReader(path.openStream());
                    mapFile = reader.readObject();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mapFile;
    }

    public static URL getPath() {
        return path;
    }

    public static void setPath(URL path) {
        MapFile.path = path;
    }
}
