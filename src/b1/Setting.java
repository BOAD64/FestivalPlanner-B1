package b1;

public class Setting {

    public static final int ADD_MENU_LABEL_AND_TEXT_HEIGHT = 40;
    public static final int ADD_MENU_BUTTON_HEIGHT = 50;

    public static class Map
    {
        public static final String TilesetPath = Setting.class.getResource("/finalSchoolTileSet.png").getPath().replace("%20", " ");
        public static final String MapJsonPath = Setting.class.getResource("/hogwardsMap.json").getPath().replace("%20", " ");
    }
}
