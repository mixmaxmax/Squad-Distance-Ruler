public class UB32Rocket implements Weapon {
    @Override
    public double getAngle(double distance) {
        if (distance < 50 || distance > 2000) {
            return -1000.0;
        }

        if (distance >=50 && distance < 250) {
            return 0;
        }
        if (distance < 400) {
            return 2.5;
        }
        if (distance < 500) {
            return interpolate(distance, 400, 2.5, 500, 5);
        }
        if (distance < 600) {
            return 5;
        }
        if (distance < 700) {
            return interpolate(distance, 600, 5, 700, 7.5);
        }
        if (distance < 800) {
            return interpolate(distance, 700, 7.5, 800, 8.8);
        }
        if (distance < 900) {
            return interpolate(distance, 800, 8.8, 900, 10);
        }
        if (distance < 1000) {
            return interpolate(distance, 900, 10, 1000, 12);
        }
        if (distance < 1100) {
            return interpolate(distance, 1000, 12, 1100, 13.5);
        }
        if (distance < 1200) {
            return interpolate(distance, 1100, 13.5, 1200, 15.5);
        }
        if (distance < 1300) {
            return interpolate(distance, 1200, 15.5, 1300, 16.3);
        }
        if (distance < 1400) {
            return interpolate(distance, 1300, 16.3, 1400, 18);
        }
        if (distance < 1500) {
            return interpolate(distance, 1400, 18, 1500, 20);
        }
        if (distance < 1600) {
            return interpolate(distance, 1500, 20, 1600, 22.5);
        }
        if (distance < 1700) {
            return interpolate(distance, 1600, 22.5, 1700, 25);
        }
        if (distance < 1800) {
            return interpolate(distance, 1700, 25, 1800, 27.5);
        }
        if (distance < 1900) {
            return interpolate(distance, 1800, 27.5, 1900, 29.8);
        }
        if (distance <= 2000) {
            return interpolate(distance, 1900, 29.8, 2000, 32);
        }
        return -1000;
    }

    private double interpolate(double d, double d1, double a1, double d2, double a2) {
        return a1 + (a2 - a1) * (d - d1) / (d2 - d1);
    }

    @Override
    public String getUnit() {
        return "°";
    }

    @Override
    public String getDisplayName() {
        return "UB-32 Rocket";
    }
}