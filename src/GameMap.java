import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private final String name;
    private final String displayName;
    private final int totalZoomLevels;
    private final Map<String, Map<String, double[]>> scaleCoefficients;

    public GameMap(String name, String displayName, int totalZoomLevels,
                   Map<String, Map<String, double[]>> scaleCoefficients) {
        this.name = name;
        this.displayName = displayName;
        this.totalZoomLevels = Math.max(0, totalZoomLevels);
        this.scaleCoefficients = scaleCoefficients != null ? scaleCoefficients : new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getTotalZoomLevels() {
        return totalZoomLevels;
    }


    public double getScaleCoefficient(String resolution, String mapType, int zoomLevel) {
        if (zoomLevel < 0 || zoomLevel >= totalZoomLevels) {
            throw new IllegalArgumentException(
                    String.format("Invalid zoom level %d for map %s. Valid range: 0-%d",
                            zoomLevel, name, totalZoomLevels - 1));
        }

        Map<String, double[]> resolutionCoeffs = scaleCoefficients.get(resolution);
        if (resolutionCoeffs == null) {
            throw new IllegalArgumentException(
                    String.format("No coefficients found for resolution %s in map %s",
                            resolution, name));
        }

        double[] coeffs = resolutionCoeffs.get(mapType);
        if (coeffs == null) {
            throw new IllegalArgumentException(
                    String.format("No coefficients found for map type %s in map %s (resolution: %s)",
                            mapType, name, resolution));
        }

        if (zoomLevel >= coeffs.length) {
            throw new IllegalArgumentException(
                    String.format("Zoom level %d out of bounds for map %s. Coefficients array has only %d values",
                            zoomLevel, name, coeffs.length));
        }

        return coeffs[zoomLevel];
    }

    @Override
    public String toString() {
        return displayName;
    }
}