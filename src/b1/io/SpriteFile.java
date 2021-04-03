package b1.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteFile {
    private static ArrayList<BufferedImage> sprites;
    private static String path;

    public SpriteFile(String filePath) {
        path = filePath;
        sprites = getSprites();
    }

    public ArrayList<BufferedImage> getSprites() {
        if (sprites == null) {
            if (path == null || path.isEmpty()) {
                return null;
            }
            sprites = fillSprites();
        }
        return sprites;
    }

    private ArrayList<BufferedImage> fillSprites() {
        ArrayList<BufferedImage> newSprites = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 12; x++) {
                    newSprites.add(image.getSubimage(x * (image.getWidth() / 12),
                            y * image.getHeight() / 8, (image.getWidth() / 12), image.getHeight() / 8));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSprites;
    }
}
