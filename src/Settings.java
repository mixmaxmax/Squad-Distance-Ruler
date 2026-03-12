import java.awt.*;
import java.util.prefs.Preferences;

public class Settings {
    private Preferences prefs;

    private static final String RESOLUTION_KEY = "resolution";
    private static final String MAP_TYPE_KEY = "map_type";
    private static final String GAME_MAP_KEY = "game_map";
    private static final String ZOOM_LEVEL_KEY = "zoom_level";
    private static final String WEAPON_TYPE_KEY = "weapon_type";

    private static final String DEFAULT_RESOLUTION = "2560x1440";
    private static final String DEFAULT_MAP_TYPE = "MAP_ON_DEFAULT_M";
    private static final String DEFAULT_GAME_MAP = "JENSENSRANGE";
    private static final int DEFAULT_ZOOM_LEVEL = 0;
    private static final String DEFAULT_WEAPON_TYPE = "NO_WEAPON";
    private static final String WINDOW_X_KEY = "window_x";
    private static final String WINDOW_Y_KEY = "window_y";
    private static final int DEFAULT_WINDOW_X = -1;
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

    public void setResolution(String resolution) {
        prefs.put(RESOLUTION_KEY, resolution);
    }

    public String getMapType() {
        return prefs.get(MAP_TYPE_KEY, DEFAULT_MAP_TYPE);
    }

    public void setMapType(String mapType) {
        prefs.put(MAP_TYPE_KEY, mapType);
    }

    public String getGameMap() {
        return prefs.get(GAME_MAP_KEY, DEFAULT_GAME_MAP);
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

    public void setZoomLevel(int zoomLevel) {
        GameMap currentMap = getGameMapObject();
        int maxZoom = currentMap.getTotalZoomLevels();
        zoomLevel = Math.max(0, Math.min(zoomLevel, maxZoom - 1));
        prefs.putInt(ZOOM_LEVEL_KEY, zoomLevel);
    }

    public String getWeaponType() {
        return prefs.get(WEAPON_TYPE_KEY, DEFAULT_WEAPON_TYPE);
    }

    public void setWeaponType(String weaponType) {
        prefs.put(WEAPON_TYPE_KEY, weaponType);
    }

    public static String getWeaponDisplayName(String type) {
        switch (type) {
            case "MORTAR": return "81mm Mortar";
            case "BM21_GRAD": return "BM-21 Grad";
            case "UB32_ROCKET": return "UB-32 Rocket";
            case "PICKUP_MORTAR": return "Pickup Mortar";
            case "HELL_CANNON": return "Hell Cannon";
            case "NO_WEAPON": return "No Weapon";
            default: return "Unknown";
        }
    }

    public static String getWeaponTypeFromDisplayName(String displayName) {
        switch (displayName) {
            case "81mm Mortar": return "MORTAR";
            case "BM-21 Grad": return "BM21_GRAD";
            case "UB-32 Rocket": return "UB32_ROCKET";
            case "Pickup Mortar": return "PICKUP_MORTAR";
            case "Hell Cannon": return "HELL_CANNON";
            case "No Weapon": return "NO_WEAPON";
            default: return "NO_WEAPON";
        }
    }

    public static String[] getAllWeaponDisplayNames() {
        return new String[]{"81mm Mortar", "BM-21 Grad", "UB-32 Rocket", "Pickup Mortar", "Hell Cannon", "No Weapon"};
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