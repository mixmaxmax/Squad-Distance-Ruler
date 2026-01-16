import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame frame;
    private final Settings settings;
    private static Point lastWindowPosition = null;

    public MainWindow() {
        settings = new Settings();

        frame = new JFrame("Squad Distance Ruler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(220, 270);
        frame.setMinimumSize(new Dimension(220, 270));
        frame.setResizable(true);

        frame.setIconImage(AppIcon.loadIcon());

        if (lastWindowPosition != null) {
            frame.setLocation(lastWindowPosition);

            if (!isPositionOnScreen(lastWindowPosition)) {
                frame.setLocationRelativeTo(null);
            }
        } else {
            frame.setLocationRelativeTo(null);
        }

        JPanel panel = getJPanel();

        frame.add(panel);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                lastWindowPosition = frame.getLocation();
            }
        });
    }

    private JPanel getJPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton openRulerButton = new JButton("Open Ruler");
        openRulerButton.addActionListener(e -> openOverlayWindow());

        JButton resolutionButton = new JButton("Select Resolution");
        resolutionButton.addActionListener(e -> showResolutionDialog());

        JButton mapButton = new JButton("Select Map");
        mapButton.addActionListener(e -> showMapDialog());

        JLabel infoLabel = new JLabel(
                "<html><center>" +
                        "Resolution: " + settings.getResolution() + "<br>" +
                        "Map: " + settings.getGameMapObject().getDisplayName() + "<br>" +
                        "Zoom Levels: " + settings.getTotalZoomLevels() +
                        "</center></html>"
        );
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(openRulerButton);
        panel.add(resolutionButton);
        panel.add(mapButton);
        panel.add(infoLabel);
        return panel;
    }

    private boolean isPositionOnScreen(Point position) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            if (screenBounds.contains(position)) {
                return true;
            }
        }
        return false;
    }

    private void showMapDialog() {
        GameMap[] maps = GameMapManager.getAllMaps().toArray(new GameMap[0]);
        String[] displayNames = GameMapManager.getAllDisplayNames();

        String current = settings.getGameMap();
        int defaultIndex = 0;
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].getName().equals(current)) {
                defaultIndex = i;
                break;
            }
        }

        String selected = (String) JOptionPane.showInputDialog(
                frame,
                "Select Squad map:",
                "Map Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                displayNames,
                displayNames[defaultIndex]
        );

        if (selected != null) {
            for (GameMap map : maps) {
                if (map.getDisplayName().equals(selected)) {
                    settings.setGameMap(map.getName());
                    settings.save();

                    lastWindowPosition = frame.getLocation();
                    frame.dispose();
                    new MainWindow().show();
                    break;
                }
            }
        }
    }

    private void showResolutionDialog() {
        String[] options = {"1920x1080 (Full HD)", "2560x1440 (Quad HD)", "3840x2160 (4K Ultra HD) - WILL BE LATER"};

        String current = settings.getResolution();
        int defaultIndex = 1;
        if (current.equals("1920x1080")) defaultIndex = 0;
        else if (current.equals("3840x2160")) defaultIndex = 2;

        String selected = (String) JOptionPane.showInputDialog(
                frame,
                "Select your screen resolution:",
                "Resolution Settings",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[defaultIndex]
        );

        if (selected != null) {
            String resolution = selected.substring(0, selected.indexOf(" "));
            settings.setResolution(resolution);
            settings.save();
            lastWindowPosition = frame.getLocation();
            frame.dispose();
            new MainWindow().show();
        }
    }

    private void openOverlayWindow() {
        lastWindowPosition = frame.getLocation();
        frame.setState(JFrame.ICONIFIED);

        OverlayWindow overlayWindow = new OverlayWindow(settings);

        overlayWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                frame.setState(JFrame.NORMAL);
            }
        });

        overlayWindow.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().show();
        });
    }
}