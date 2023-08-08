import java.awt.*;

public class Apple extends Entity {
    // Fields

    // Constructor
    public Apple() {
        super(300, 100, 0, 0, new Dimension(10, 10), Color.RED);
    }

    // Methods
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(xPos, yPos, width, height);
    }
}