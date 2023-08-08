import javax.swing.*;
import java.awt.*;

public class WindowScreen extends JPanel {
    private static final String NAME = "Snake";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int SCALE = 10;
    private JFrame frame;
    private SnakeGame game;
    public WindowScreen(SnakeGame game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        frame = new JFrame(NAME);
        frame.add(this);
        frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(game.getInputs());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraphLines(g);
        game.render(g);
    }
    public void drawGraphLines(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(-500, 500, 1000, 500); // X line
        g.drawLine(500, -500, 500, 1000); // Y line
    }
}