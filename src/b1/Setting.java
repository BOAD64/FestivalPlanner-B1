package b1;

import java.awt.*;
import java.net.URL;

public class Setting {

    public static final int ADD_MENU_LABEL_AND_TEXT_HEIGHT = 40;
    public static final int ADD_MENU_BUTTON_HEIGHT = 50;

    public static class Map
    {
        public static final URL TilesetPath = Setting.class.getResource("/finalSchoolTileSet.png");
        public static final URL MapJsonPath = Setting.class.getResource("/hogwardsMapV2.json");
        public static final double MIN_ZOOM = 0.2;
        public static final double MAX_ZOOM = 4.0;
        public static final Color SIM_BACKGROUND_COLOR = new Color(5,5,5);
        public static final double MIN_X_BOUND = 120;
        public static final double MAX_X_BOUND = -3000;
        public static final double MIN_Y_BOUND = 160;
        public static final double MAX_Y_BOUND = -3100;
    }
}
