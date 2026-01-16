import java.awt.*;

public abstract class BaseScaleLine {
    protected int lineLengthInMeters;
    protected int startX;
    protected int endX;
    protected int startY;
    protected int endY;
    protected String name;
    protected int textShift;
    protected Color lineColor;
    protected Color textColor;
    protected int lineThickness;
    protected Font textFont;

    public BaseScaleLine(int lineLengthInMeters, String name, int textShift, int startX, int startY,
                         Color lineColor, Color textColor, int lineThickness, int fontSize) {
        this.lineLengthInMeters = lineLengthInMeters;
        this.startX = startX;
        this.startY = startY;
        this.endY = startY;
        this.name = name;
        this.textShift = textShift;
        this.lineColor = lineColor;
        this.textColor = textColor;
        this.lineThickness = lineThickness;
        this.textFont = new Font("Arial", Font.BOLD, fontSize);
    }

    public void setStartX(int x) {
        this.startX = x;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setYPosition(int y) {
        this.startY = y;
        this.endY = y;
    }

    public void drawScaleLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(lineThickness));
        g2d.drawLine(startX, startY, endX, endY);

        g2d.drawLine(startX, startY + 4, startX, endY - 4);
        g2d.drawLine(endX, startY + 4, endX, endY - 4);

        g2d.setColor(textColor);
        g2d.setFont(textFont);
        g2d.drawString(name, calculateTextX(textShift), startY - 5);
    }

    protected int calculateTextX(int textShift) {
        return startX - textShift;
    }
}