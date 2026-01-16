import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class AppIcon {

    public static Image loadIcon() {
        Image fileIcon = tryLoadIcon("icon128x128.png");
        if (fileIcon != null) {
            return fileIcon;
        }
        return createDefaultIcon();
    }

    private static Image tryLoadIcon(String fileName) {
        try {
            URL iconUrl = AppIcon.class.getClassLoader().getResource(fileName);
            if (iconUrl != null) {
                System.out.println("Loaded icon: " + fileName);
                return new ImageIcon(iconUrl).getImage();
            }
        } catch (Exception e) {

        }
        return null;
    }

    private static Image createDefaultIcon() {
        int size = 64;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(new Color(255, 196, 11));
        g2d.fillRect(0, 0, size, size);

        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        String text = "S";
        int x = (size - fm.stringWidth(text)) / 2;
        int y = (size - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(text, x, y);

        g2d.dispose();
        return image;
    }
}