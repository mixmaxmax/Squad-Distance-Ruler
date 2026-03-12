public class BM21Grad implements Weapon {
    private final double[][] table = {
            {1000, 14.7},
            {1050, 15.5},
            {1100, 16.3},
            {1150, 17.2},
            {1200, 18.0},
            {1250, 18.9},
            {1300, 19.8},
            {1350, 20.7},
            {1400, 21.7},
            {1450, 22.7},
            {1500, 23.7},
            {1550, 24.7},
            {1600, 25.9},
            {1650, 27.0},
            {1700, 28.2},
            {1750, 29.6},
            {1800, 31.0},
            {1850, 32.6},
            {1900, 34.4},
            {1950, 36.5},
            {2000, 39.4},
            {2050, 45.0}
    };

    @Override
    public double getAngle(double distance) {
        if (distance <= table[0][0]) {
            return -1000;
        }
        if (distance >= table[table.length - 1][0]) {
            return -1000;
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
        return -1000;
    }

    @Override
    public String getUnit() {
        return "°";
    }

    @Override
    public String getDisplayName() {
        return "BM-21 Grad";
    }
}