package b1.io;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class ImageFile {

    private static Image logo;

    public static Image getLogo() {
        if(logo == null) {
            try {
                FileInputStream inputStream = new FileInputStream("resources\\logo.png");
                logo = new Image(inputStream);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return logo;
    }
}
