package b1.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteFile {
    private ArrayList<BufferedImage> sprites;
    private String path;

    public SpriteFile(String path) {
        this.path = path;
        fillSprites();
    }

    private void fillSprites() {
        this.sprites = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(this.path));
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 12; x++) {
                    this.sprites.add(image.getSubimage(x * (image.getWidth()/12),
                            y * image.getHeight() / 8, (image.getWidth()/12), image.getHeight() / 8));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BufferedImage> getSprites() {
        return this.sprites;
    }
}
