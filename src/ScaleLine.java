import java.awt.*;

public class ScaleLine extends BaseScaleLine {
    public ScaleLine(int startX, int startY, int lineLengthInMeters, String name, int textShift, int fontSize) {
        super(lineLengthInMeters, name, textShift, startX, startY, Color.GREEN, Color.GREEN, 2, fontSize);
    }
}