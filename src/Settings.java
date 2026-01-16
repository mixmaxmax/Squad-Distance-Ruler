import java.awt.*;
import java.util.prefs.Preferences;

public class Settings {
    private Preferences prefs;

    private static final String RESOLUTION_KEY = "resolution";
    private static final String MAP_TYPE_KEY = "map_type";
    private static final String GAME_MAP_KEY = "game_map";
    private static final String ZOOM_LEVEL_KEY = "zoom_level";

    private static final String DEFAULT_RESOLUTION = "2560x1440";
    private static final String DEFAULT_MAP_TYPE = "MAP_ON_DEFAULT_M";
    private static final String DEFAULT_GAME_MAP = "JENSENSRANGE";
    private static final int DEFAULT_ZOOM_LEVEL = 0;

    private static final String WINDOW_X_KEY = "window_x";
    private static final String WINDOW_Y_KEY = "window_y";
    private static final int DEFAULT_WINDOW_X = -1; // -1 означает "центр экрана"
    private static final int DEFAULT_WINDOW_Y = -1;


    public Settings() {
        prefs = Preferences.userRoot().node("squad_ruler");
    }

    public void saveWindowPosition(int x, int y) {
        prefs.putInt(WINDOW_X_KEY, x);
        prefs.putInt(WINDOW_Y_KEY, y);
        save();
    }

    public Point getWindowPosition() {
        int x = prefs.getInt(WINDOW_X_KEY, DEFAULT_WINDOW_X);
        int y = prefs.getInt(WINDOW_Y_KEY, DEFAULT_WINDOW_Y);
        return new Point(x, y);
    }

    public void resetWindowPosition() {
        prefs.putInt(WINDOW_X_KEY, DEFAULT_WINDOW_X);
        prefs.putInt(WINDOW_Y_KEY, DEFAULT_WINDOW_Y);
        save();
    }

    public String getResolution() {
        return prefs.get(RESOLUTION_KEY, DEFAULT_RESOLUTION);
    }

    public String getMapType() {
        return prefs.get(MAP_TYPE_KEY, DEFAULT_MAP_TYPE);
    }

    public String getGameMap() {
        return prefs.get(GAME_MAP_KEY, DEFAULT_GAME_MAP);
    }

    public GameMap getGameMapObject() {
        return GameMapManager.getMap(getGameMap());
    }

    public int getZoomLevel() {
        int savedZoom = prefs.getInt(ZOOM_LEVEL_KEY, DEFAULT_ZOOM_LEVEL);

        GameMap currentMap = getGameMapObject();
        int maxZoom = currentMap.getTotalZoomLevels();

        if (savedZoom >= maxZoom) {
            savedZoom = 0;
            setZoomLevel(0);
            save();
        }

        return savedZoom;
    }

    public void setResolution(String resolution) {
        prefs.put(RESOLUTION_KEY, resolution);
    }

    public void setMapType(String mapType) {
        prefs.put(MAP_TYPE_KEY, mapType);
    }

    public void setGameMap(String gameMap) {
        prefs.put(GAME_MAP_KEY, gameMap);

        GameMap newMap = GameMapManager.getMap(gameMap);
        int currentZoom = getZoomLevel();
        int newMaxZoom = newMap.getTotalZoomLevels();

        if (currentZoom >= newMaxZoom) {
            setZoomLevel(0);
        }
    }

    public void setZoomLevel(int zoomLevel) {
        GameMap currentMap = getGameMapObject();
        int maxZoom = currentMap.getTotalZoomLevels();
        zoomLevel = Math.max(0, Math.min(zoomLevel, maxZoom - 1));
        prefs.putInt(ZOOM_LEVEL_KEY, zoomLevel);
    }

    public void save() {
        try {
            prefs.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getScaleCoefficient() {
        GameMap map = getGameMapObject();
        return map.getScaleCoefficient(
                getResolution(),
                getMapType(),
                getZoomLevel()
        );
    }

    public double pixelsToMeters(double pixels) {
        try {
            return pixels * getScaleCoefficient();
        } catch (IllegalArgumentException e) {
            System.err.println("Error calculating distance: " + e.getMessage());
            return pixels;
        }
    }

    public int getTotalZoomLevels() {
        return getGameMapObject().getTotalZoomLevels();
    }

    public static String getMapTypeDisplayName(String mapType) {
        switch (mapType) {
            case "MAP_ON_DEFAULT_M": return "Map [M]";
            case "MAP_ON_DEFAULT_CAPSLOCK": return "Map [CapsLock]";
            case "MAP_ON_DEFAULT_ENTER": return "Map [Enter]";
            default: return "Map [M]";
        }
    }

    public static String getMapTypeFromDisplayName(String displayName) {
        switch (displayName) {
            case "Map [M]": return "MAP_ON_DEFAULT_M";
            case "Map [CapsLock]": return "MAP_ON_DEFAULT_CAPSLOCK";
            case "Map [Enter]": return "MAP_ON_DEFAULT_ENTER";
            default: return "MAP_ON_DEFAULT_M";
        }
    }

    public static String[] getAllMapTypesDisplayNames() {
        return new String[]{"Map [M]", "Map [CapsLock]", "Map [Enter]"};
    }
}