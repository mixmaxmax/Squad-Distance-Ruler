import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OverlayWindow extends JFrame {

    private static Point lastOverlayPosition = null;

    private static final int WINDOW_SIZE_FHD = 900;
    private static final int CONTROL_PANEL_HEIGHT_FHD = 55;
    private static final int FONT_SIZE_FHD = 12;
    private static final int TEXT_SHIFT_FHD = 35;

    private static final int WINDOW_SIZE_QHD = 1150;
    private static final int CONTROL_PANEL_HEIGHT_QHD = 70;
    private static final int FONT_SIZE_QHD = 16;
    private static final int TEXT_SHIFT_QHD = 45;

    private static final int WINDOW_SIZE_4K = 1880;
    private static final int CONTROL_PANEL_HEIGHT_4K = 85;
    private static final int FONT_SIZE_4K = 20;
    private static final int TEXT_SHIFT_4k = 55;

    private static final int POINT_RADIUS = 5;
    private static final int POINT_DIAMETER = POINT_RADIUS * 2;
    private static final int LINE_THICKNESS = 4;
    private static final int BORDER_THICKNESS = 1;
    private static final int FONT_SIZE = 24;
    private static final String FONT_NAME = "Arial";
    private static final int TEXT_MARGIN = 40;

    private static final Color TEXT_OUTLINE_COLOR = Color.BLACK;
    private static final int TEXT_OUTLINE_THICKNESS = 1;

    private static final Color POINT_COLOR = Color.RED;
    private static final Color LINE_COLOR = Color.RED;
    private static final Color TEXT_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255, 50);
    private static final Color BORDER_COLOR = Color.GRAY;
    private static final Color CONTROL_PANEL_COLOR = new Color(50, 50, 50);

    private static final Color ZOOM_BUTTON_COLOR = new Color(70, 70, 70);
    private static final Color ZOOM_BUTTON_SELECTED_COLOR = Color.GREEN;
    private static final Color ZOOM_BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color ZOOM_BUTTON_SELECTED_TEXT_COLOR = Color.BLACK;
    private static final Color MAP_BUTTON_COLOR = new Color(80, 80, 120);
    private static final Color CLOSE_BUTTON_COLOR = new Color(133, 51, 51);
    private static final Color RESET_BUTTON_COLOR = new Color(205, 179, 69);

    private static final int SCALE_LINE_SPACING = 30;
    private static final int SCALE_LINE_BASE_Y_OFFSET = 140;



    private Point point1 = null;
    private Point point2 = null;
    private final JPanel drawingPanel;
    private final Settings settings;
    private JButton[] zoomButtons;
    private JLabel scaleInfoLabel;
    private JButton mapTypeButton;

    private final ApproximateScaleLine approximateLine;
    private final ScaleLine scaleLine33;
    private final ScaleLine scaleLine100;
    private final ScaleLine scaleLine300;
    private final ScaleLine scaleLine900;

    private int getWindowSize() {
        return switch (settings.getResolution()) {
            case "1920x1080" -> WINDOW_SIZE_FHD;
            case "3840x2160" -> WINDOW_SIZE_4K;
            default -> WINDOW_SIZE_QHD;
        };
    }

    private int getFontSize() {
        return switch (settings.getResolution()) {
            case "1920x1080" -> FONT_SIZE_FHD;
            case "3840x2160" -> FONT_SIZE_4K;
            default -> FONT_SIZE_QHD;
        };
    }

    private int getTextShift() {
        return switch (settings.getResolution()) {
            case "1920x1080" -> TEXT_SHIFT_FHD;
            case "3840x2160" -> TEXT_SHIFT_4k;
            default ->  TEXT_SHIFT_QHD;
        };
    }
    private int getControlPanelHeight() {
        return switch (settings.getResolution()) {
            case "1920x1080" -> CONTROL_PANEL_HEIGHT_FHD;
            case "3840x2160" -> CONTROL_PANEL_HEIGHT_4K;
            default -> CONTROL_PANEL_HEIGHT_QHD;
        };
    }

    public OverlayWindow(Settings settings) {
        this.settings = settings;
        int windowSize = getWindowSize();
        int startX = windowSize - 25;
        int fontSize = getFontSize();
        int textShift = getTextShift();

        this.approximateLine = new ApproximateScaleLine(startX, windowSize - 110, textShift + 50, fontSize);
        this.scaleLine33 = new ScaleLine(startX, 0, 33, "33m", textShift - 9, fontSize);
        this.scaleLine100 = new ScaleLine(startX, 0, 100, "100m", textShift, fontSize);
        this.scaleLine300 = new ScaleLine(startX, 0, 300, "300m", textShift, fontSize);
        this.scaleLine900 = new ScaleLine(startX, 0, 900, "900m", textShift, fontSize);

        setSize(getWindowSize(), getWindowSize());

        if (lastOverlayPosition != null) {
            setLocation(lastOverlayPosition);

            if (!isPositionVisible(lastOverlayPosition)) {
                setLocationRelativeTo(null);
            }
        } else {
            setLocationRelativeTo(null);
        }

        setIconImage(AppIcon.loadIcon());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        System.out.println("Overlay has been opened.\nResolution: " + settings.getResolution() +
                "\nWindow size:" + windowSize + "\nMap: " + settings.getGameMap());

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(BACKGROUND_COLOR);
                g.fillRect(0, 0, getWidth(), getHeight());

                if (point1 != null && point2 != null) {
                    g.setColor(LINE_COLOR);
                    ((Graphics2D)g).setStroke(new BasicStroke(LINE_THICKNESS));
                    g.drawLine(point1.x, point1.y, point2.x, point2.y);

                    g.setColor(POINT_COLOR);
                    g.fillOval(point1.x - POINT_RADIUS, point1.y - POINT_RADIUS,
                            POINT_DIAMETER, POINT_DIAMETER);
                    g.fillOval(point2.x - POINT_RADIUS, point2.y - POINT_RADIUS,
                            POINT_DIAMETER, POINT_DIAMETER);

                    double distanceInPixels = Math.sqrt(
                            Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2)
                    );
                    double distanceInMeters = settings.pixelsToMeters(distanceInPixels);

                    String text = String.format("~ %.1f m", distanceInMeters);
                    g.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
                    FontMetrics fm = g.getFontMetrics();

                    int centerX = (point1.x + point2.x) / 2;
                    int centerY = (point1.y + point2.y) / 2;
                    Point textPosition = calculateTextPosition(centerX, centerY,
                            fm.stringWidth(text), fm.getHeight());

                    drawTextWithOutline(g, text, textPosition.x, textPosition.y);
                    drawApproximateLine(g, distanceInPixels, text);

                } else if (point1 != null) {
                    g.setColor(POINT_COLOR);
                    g.fillOval(point1.x - POINT_RADIUS, point1.y - POINT_RADIUS,
                            POINT_DIAMETER, POINT_DIAMETER);
                }

                if ("JENSENSRANGE".equals(settings.getGameMap())) drawScaleLinesTypeOne(g);
                if ("ALBASRAH".equals(settings.getGameMap())) drawScaleLinesTypeOne(g);
                if ("ANVIL".equals(settings.getGameMap())) drawScaleLinesTypeTwo(g);
                if ("BLACKCOAST".equals(settings.getGameMap())) drawScaleLinesTypeFour(g);
                if ("FALLUJAH".equals(settings.getGameMap())) drawScaleLinesTypeTwo(g);
                if("FOOLSROAD".equals(settings.getGameMap())) drawScaleLineForFoolsRoad(g);
                if ("GOOSEBAY".equals(settings.getGameMap())) drawScaleLinesTypeOne(g);
                if("GORODOK".equals(settings.getGameMap())) drawScaleLinesTypeOne(g);
                if("MANICOUGAN".equals(settings.getGameMap())) drawScaleLinesTypeThree(g);
                if ("MUTAHA".equals(settings.getGameMap())) drawScaleLinesTypeThree(g);
                if ("NARVA".equals(settings.getGameMap())) drawScaleLinesTypeThree(g);
                if ("SANXIANISLANDS".equals(settings.getGameMap())) drawScaleLinesTypeFour(g);
                if ("YEGORYEVKA".equals(settings.getGameMap())) drawScaleLinesForYegoryevka(g);
            }

            private void drawApproximateLine(Graphics g, double distanceInPixels, String name) {
                approximateLine.setEndX(approximateLine.startX - (int) distanceInPixels);
                approximateLine.setName(name);
                approximateLine.drawScaleLine(g);
            }

            private void drawScaleLineWithPosition(Graphics g, ScaleLine scaleLine, double coeff, int yPos) {
                scaleLine.setYPosition(yPos);
                double pixels = scaleLine.lineLengthInMeters / coeff;
                int endX = scaleLine.startX - (int) Math.round(pixels);
                scaleLine.setEndX(endX);
                scaleLine.drawScaleLine(g);
            }

            private void drawScaleLinesTypeOne(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 5) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 5) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private void drawScaleLinesTypeTwo(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom <= 1) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom > 0) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom <= 1) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private void drawScaleLinesTypeThree(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom <= 1) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom <= 1) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom <= 1) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 4) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private void drawScaleLinesTypeFour(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 5) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 5) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 5) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 5) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private void drawScaleLineForFoolsRoad(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom == 0) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 2) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 0) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom == 0) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom == 0) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 0) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 2) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private void drawScaleLinesForYegoryevka(Graphics g) {
                String mapType = settings.getMapType();
                int zoom = settings.getZoomLevel();
                double coeff = settings.getScaleCoefficient();

                int currentY = getWindowSize() - SCALE_LINE_BASE_Y_OFFSET;

                if ("MAP_ON_DEFAULT_M".equals(mapType)) {
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1 && zoom <= 6) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 5) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_CAPSLOCK".equals(mapType)) {
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1 && zoom <= 6) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 4) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 5) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }

                if ("MAP_ON_DEFAULT_ENTER".equals(mapType)) {
                    if (zoom <= 3) {
                        drawScaleLineWithPosition(g, scaleLine900, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 1 && zoom <= 6) {
                        drawScaleLineWithPosition(g, scaleLine300, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 3) {
                        drawScaleLineWithPosition(g, scaleLine100, coeff, currentY);
                        currentY -= SCALE_LINE_SPACING;
                    }
                    if (zoom >= 5) {
                        drawScaleLineWithPosition(g, scaleLine33, coeff, currentY);
                    }
                }
            }

            private Point calculateTextPosition(int centerX, int centerY, int textWidth, int textHeight) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                Point position = new Point();

                boolean isTopHalf = centerY < panelHeight / 2;
                boolean isBottomHalf = centerY > panelHeight / 2;
                boolean isLeftHalf = centerX < panelWidth / 2;
                boolean isRightHalf = centerX > panelWidth / 2;

                if (isTopHalf) {
                    position.y = centerY + TEXT_MARGIN + textHeight;
                } else if (isBottomHalf) {
                    position.y = centerY - TEXT_MARGIN;
                } else {
                    position.y = centerY + textHeight / 2;
                }

                if (isLeftHalf) {
                    position.x = centerX + TEXT_MARGIN;
                } else if (isRightHalf) {
                    position.x = centerX - textWidth - TEXT_MARGIN;
                } else {
                    position.x = centerX - textWidth / 2;
                }

                int outlineOffset = TEXT_OUTLINE_THICKNESS;
                if (position.x + textWidth + outlineOffset > panelWidth - 5) {
                    position.x = panelWidth - textWidth - outlineOffset - 5;
                }
                if (position.x < 5 + outlineOffset) {
                    position.x = 5 + outlineOffset;
                }
                if (position.y + outlineOffset > panelHeight - 5) {
                    position.y = panelHeight - outlineOffset - 5;
                }
                if (position.y < textHeight + 5 + outlineOffset) {
                    position.y = textHeight + 5 + outlineOffset;
                }

                return position;
            }

            private void drawTextWithOutline(Graphics g, String text, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                Font originalFont = g2d.getFont();

                g2d.setColor(TEXT_OUTLINE_COLOR);
                for (int offsetX = -TEXT_OUTLINE_THICKNESS; offsetX <= TEXT_OUTLINE_THICKNESS; offsetX++) {
                    for (int offsetY = -TEXT_OUTLINE_THICKNESS; offsetY <= TEXT_OUTLINE_THICKNESS; offsetY++) {
                        if (offsetX != 0 || offsetY != 0) {
                            g2d.drawString(text, x + offsetX, y + offsetY);
                        }
                    }
                }

                g2d.setColor(TEXT_COLOR);
                g2d.drawString(text, x, y);
                g2d.setFont(originalFont);
            }
        };

        drawingPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));
        drawingPanel.setOpaque(false);

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (point1 == null) {
                    point1 = e.getPoint();
                } else if (point2 == null) {
                    point2 = e.getPoint();
                } else {
                    point1 = e.getPoint();
                    point2 = null;
                }
                drawingPanel.repaint();
            }
        });

        MouseAdapter windowMover = new MouseAdapter() {
            private Point dragStart;
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point current = getLocation();
                setLocation(current.x + e.getX() - dragStart.x,
                        current.y + e.getY() - dragStart.y);
            }
        };

        drawingPanel.addMouseListener(windowMover);
        drawingPanel.addMouseMotionListener(windowMover);

        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        getRootPane().getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastOverlayPosition = getLocation();
                dispose();
            }
        });

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "close");
        getRootPane().getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastOverlayPosition = getLocation();
                dispose();
            }
        });

        setContentPane(mainPanel);
        setBackground(new Color(0, 0, 0, 0));

        initZoomButtons();
        updateScaleInfo();
        updateMapButtonText();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                lastOverlayPosition = getLocation();
            }
        });
    }

    private void initZoomButtons() {
        int currentZoom = settings.getZoomLevel();
        int maxZoom = settings.getTotalZoomLevels();

        for (int i = 0; i < maxZoom; i++) {
            if (zoomButtons != null && i < zoomButtons.length) {
                if (i == currentZoom) {
                    zoomButtons[i].setBackground(ZOOM_BUTTON_SELECTED_COLOR);
                    zoomButtons[i].setForeground(ZOOM_BUTTON_SELECTED_TEXT_COLOR);
                } else {
                    zoomButtons[i].setBackground(ZOOM_BUTTON_COLOR);
                    zoomButtons[i].setForeground(ZOOM_BUTTON_TEXT_COLOR);
                }
            }
        }
    }

    private void updateScaleInfo() {
        double coeff = settings.getScaleCoefficient();
        String mapTypeName = Settings.getMapTypeDisplayName(settings.getMapType());
        String mapName = settings.getGameMapObject().getDisplayName();
        int maxZoom = settings.getTotalZoomLevels();
        int currentZoom = settings.getZoomLevel();

        if (scaleInfoLabel != null) {
            scaleInfoLabel.setText(String.format("1px = %.2fm | Zoom %d/%d | %s | %s",
                    coeff, currentZoom, maxZoom - 1, mapTypeName, mapName));
        }
    }

    private void updateMapButtonText() {
        String displayName = Settings.getMapTypeDisplayName(settings.getMapType());
        if (mapTypeButton != null) {
            mapTypeButton.setText(displayName);
        }
    }

    private void showMapTypeDialog() {
        String[] options = Settings.getAllMapTypesDisplayNames();

        String currentDisplayName = Settings.getMapTypeDisplayName(settings.getMapType());

        int defaultIndex = 0;
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(currentDisplayName)) {
                defaultIndex = i;
                break;
            }
        }

        String selected = (String) JOptionPane.showInputDialog(
                this, "Select map type:", "Map type",
                JOptionPane.QUESTION_MESSAGE, null, options, options[defaultIndex]
        );

        if (selected != null) {
            String mapType = Settings.getMapTypeFromDisplayName(selected);
            settings.setMapType(mapType);
            settings.save();
            updateScaleInfo();
            updateMapButtonText();
            drawingPanel.repaint();
        }
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CONTROL_PANEL_COLOR);
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(getWidth(), getControlPanelHeight()));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(CONTROL_PANEL_COLOR);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        mapTypeButton = new JButton();
        mapTypeButton.setBackground(MAP_BUTTON_COLOR);
        mapTypeButton.setForeground(Color.WHITE);
        mapTypeButton.setFocusPainted(false);
        mapTypeButton.addActionListener(e -> showMapTypeDialog());

        Dimension buttonSize = new Dimension(100, 30);
        mapTypeButton.setPreferredSize(buttonSize);
        mapTypeButton.setMinimumSize(buttonSize);
        mapTypeButton.setMaximumSize(buttonSize);

        leftPanel.add(mapTypeButton, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(CONTROL_PANEL_COLOR);

        JPanel zoomPanel = new JPanel(new GridLayout(1, settings.getTotalZoomLevels(), 2, 0));
        zoomPanel.setBackground(CONTROL_PANEL_COLOR);
        zoomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        int maxZoom = settings.getTotalZoomLevels();
        zoomButtons = new JButton[maxZoom];

        for (int i = 0; i < maxZoom; i++) {
            final int zoomLevel = i;
            zoomButtons[i] = new JButton("Z" + i);
            zoomButtons[i].setBackground(ZOOM_BUTTON_COLOR);
            zoomButtons[i].setForeground(ZOOM_BUTTON_TEXT_COLOR);
            zoomButtons[i].setFocusPainted(false);
            zoomButtons[i].setFont(new Font("Arial", Font.BOLD, 12));

            Dimension zoomButtonSize = new Dimension(50, 30);
            zoomButtons[i].setPreferredSize(zoomButtonSize);
            zoomButtons[i].setMinimumSize(zoomButtonSize);
            zoomButtons[i].setMaximumSize(zoomButtonSize);

            zoomButtons[i].addActionListener(e -> {
                settings.setZoomLevel(zoomLevel);
                settings.save();

                for (int j = 0; j < maxZoom; j++) {
                    if (j == zoomLevel) {
                        zoomButtons[j].setBackground(ZOOM_BUTTON_SELECTED_COLOR);
                        zoomButtons[j].setForeground(ZOOM_BUTTON_SELECTED_TEXT_COLOR);
                    } else {
                        zoomButtons[j].setBackground(ZOOM_BUTTON_COLOR);
                        zoomButtons[j].setForeground(ZOOM_BUTTON_TEXT_COLOR);
                    }
                }
                updateScaleInfo();
                drawingPanel.repaint();
            });

            zoomPanel.add(zoomButtons[i]);
        }

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3));
        infoPanel.setBackground(CONTROL_PANEL_COLOR);
        scaleInfoLabel = new JLabel();
        scaleInfoLabel.setForeground(Color.WHITE);
        scaleInfoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoPanel.add(scaleInfoLabel);

        centerPanel.add(zoomPanel, BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        rightPanel.setBackground(CONTROL_PANEL_COLOR);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton resetButton = new JButton("Reset");

        resetButton.setPreferredSize(buttonSize);
        resetButton.setMinimumSize(buttonSize);
        resetButton.setMaximumSize(buttonSize);

        resetButton.setBackground(RESET_BUTTON_COLOR);
        resetButton.setForeground(ZOOM_BUTTON_TEXT_COLOR);
        resetButton.setFocusPainted(false);

        resetButton.addActionListener(e -> {
            point1 = null;
            point2 = null;
            drawingPanel.repaint();
        });

        JButton closeButton = new JButton("Close");

        closeButton.setPreferredSize(buttonSize);
        closeButton.setMinimumSize(buttonSize);
        closeButton.setMaximumSize(buttonSize);

        closeButton.setBackground(CLOSE_BUTTON_COLOR);
        closeButton.setForeground(ZOOM_BUTTON_TEXT_COLOR);
        closeButton.setFocusPainted(false);

        closeButton.addActionListener(e -> {
            lastOverlayPosition = getLocation();
            dispose();
        });

        rightPanel.add(resetButton);
        rightPanel.add(closeButton);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        int currentZoom = settings.getZoomLevel();
        for (int i = 0; i < maxZoom; i++) {
            if (i == currentZoom) {
                zoomButtons[i].setBackground(ZOOM_BUTTON_SELECTED_COLOR);
                zoomButtons[i].setForeground(ZOOM_BUTTON_SELECTED_TEXT_COLOR);
            } else {
                zoomButtons[i].setBackground(ZOOM_BUTTON_COLOR);
                zoomButtons[i].setForeground(ZOOM_BUTTON_TEXT_COLOR);
            }
        }

        MouseAdapter panelMover = new MouseAdapter() {
            private Point dragStart;

            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point current = getLocation();
                setLocation(current.x + e.getX() - dragStart.x,
                        current.y + e.getY() - dragStart.y);
            }
        };

        panel.addMouseListener(panelMover);
        panel.addMouseMotionListener(panelMover);
        return panel;
    }

    private boolean isPositionVisible(Point position) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            Rectangle windowRect = new Rectangle(position.x, position.y,
                    getWindowSize(), getWindowSize());

            if (screenBounds.contains(windowRect)) {
                return true;
            }
        }
        return false;
    }
}