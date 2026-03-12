import java.util.List;
import java.util.Collections;
public interface Weapon {
    double getAngle(double distance);
    String getUnit();
    String getDisplayName();
    default List<Double> getAngles(double distance) {
        double angle = getAngle(distance);
        if (angle == -1000.0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(angle);
    }
}