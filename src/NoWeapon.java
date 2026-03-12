public class NoWeapon implements Weapon {
    @Override
    public double getAngle(double distance) {
        return -1000;
    }
    @Override
    public String getUnit() {
        return "";
    }
    @Override
    public String getDisplayName() {
        return "No Weapon";
    }
}