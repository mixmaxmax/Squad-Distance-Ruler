import java.awt.*;

public class ApproximateScaleLine extends BaseScaleLine {
    public ApproximateScaleLine(int startX, int startY, int textShift, int fontSize) {
        super(0, "Approximate Line", textShift, startX, startY, Color.YELLOW, Color.YELLOW, 2, fontSize);
    }

    public void setName (String name) {
        this.name = name;
    }
}