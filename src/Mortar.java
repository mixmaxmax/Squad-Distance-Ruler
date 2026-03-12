public class Mortar implements Weapon{
    private final double[][] table;
    public Mortar() {
        table = new double[][] {
                {50, 1579},
                {100, 1558},
                {150, 1538},
                {200, 1517},
                {250, 1496},
                {300, 1475},
                {350, 1453},
                {400, 1431},
                {450, 1409},
                {500, 1387},
                {550, 1364},
                {600, 1341},
                {650, 1317},
                {700, 1292},
                {750, 1267},
                {800, 1240},
                {850, 1212},
                {900, 1183},
                {950, 1152},
                {1000, 1118},
                {1050, 1081},
                {1100, 1039},
                {1150, 988},
                {1200, 918},
                {1250, 800}
        };
    }

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
                double m1 = table[i][1];
                double m2 = table[i + 1][1];
                return m1 + (m2 - m1) * (distance - d1) / (d2 - d1);
            }
        }

        return -1000;
    }

    @Override
    public String getUnit() {
        return "mil";
    }

    @Override
    public String getDisplayName() {
        return "Mortar 81mm";
    }

}