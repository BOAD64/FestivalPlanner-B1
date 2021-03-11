package b1;

import java.net.URL;

public class Setting {

    public static final int ADD_MENU_LABEL_AND_TEXT_HEIGHT = 40;
    public static final int ADD_MENU_BUTTON_HEIGHT = 50;

    public static class Map
    {
        public static final URL TilesetPath = Setting.class.getResource("/finalSchoolTileSet.png");
        public static final URL MapJsonPath = Setting.class.getResource("/hogwardsMapV2.json");
    }
}
