public class PickupMortar implements Weapon {
    private final double[][] table = {
            {50, 83.8},
            {100, 82.9},
            {200, 80.5},
            {300, 78.0},
            {400, 75.7},
            {500, 73.2},
            {600, 70.5},
            {700, 68.0},
            {800, 65.0},
            {900, 62.0},
            {1000, 58.4},
            {1100, 53.8},
            {1200, 48.2},
            {1250, 40.0}
    };

    @Override
    public double getAngle(double distance) {
        if (distance < 50 || distance > 1250) {
            return -1000.0;
        }

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
        return "Pickup Mortar";
    }
}