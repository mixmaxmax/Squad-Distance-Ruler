import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HellCannon implements Weapon {
    private final double[][] lowAngleTable = {
            {600, 20.0},
            {700, 25.0},
            {800, 30.0},
            {850, 35.0},
            {900, 40.0},
            {925, 45.0}
    };

    private final double[][] highAngleTable = {
            {150, 85.0},
            {200, 83.5},
            {300, 80.5},
            {400, 77.0},
            {500, 73.5},
            {600, 70.0},
            {700, 65.0},
            {800, 60.0},
            {875, 55.0},
            {900, 50.0}
    };

    @Override
    public double getAngle(double distance) {
        if (distance >= lowAngleTable[0][0] && distance <= lowAngleTable[lowAngleTable.length - 1][0]) {
            return interpolate(lowAngleTable, distance);
        }
        return -1000.0;
    }

    @Override
    public List<Double> getAngles(double distance) {
        List<Double> angles = new ArrayList<>();

        if (distance >= lowAngleTable[0][0] && distance <= lowAngleTable[lowAngleTable.length - 1][0]) {
            double low = interpolate(lowAngleTable, distance);
            if (low != -1000.0) angles.add(low);
        }

        if (distance >= highAngleTable[0][0] && distance <= highAngleTable[highAngleTable.length - 1][0]) {
            double high = interpolate(highAngleTable, distance);
            if (high != -1000.0) angles.add(high);
        }

        return angles.isEmpty() ? Collections.emptyList() : angles;
    }

    private double interpolate(double[][] table, double distance) {
        for (int i = 0; i < table.length - 1; i++) {
            double d1 = table[i][0];
            double d2 = table[i + 1][0];
            if (distance >= d1 && distance <= d2) {
                double a1 = table[i][1];
                double a2 = table[i + 1][1];
                return a1 + (a2 - a1) * (distance - d1) / (d2 - d1);
            }
        }

        for (double[] point : table) {
            if (Math.abs(point[0] - distance) < 0.001) {
                return point[1];
            }
        }
        return -1000.0;
    }

    @Override
    public String getUnit() {
        return "°";
    }

    @Override
    public String getDisplayName() {
        return "Hell Cannon";
    }
}