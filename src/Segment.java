import java.awt.*;
import java.util.ArrayList;

public class Segment extends Entity {
    // Fields
    private Snake snake;

    // Getters

    // Setters

    // Constructors
    public Segment(int xPos, int yPos, int xDir, int yDir) {
        super(xPos,yPos, xDir, yDir, new Dimension(10 , 10), Color.green);
    }
    public Segment(Point point, int xDir, int yDir, Color color) {
        super(point.x, point.y, xDir, yDir,new Dimension(10 ,10), color);
    }
    public Segment(Snake snake, Point point, int xDir, int yDir) {
        super(point.x, point.y, xDir, yDir,new Dimension(10 , 10), Color.green);
        this.snake = snake;
    }

    // Methods
    public boolean isAt(DeltaDirPoint dP) {
        return (this.xPos == dP.x && this.yPos == dP.y);
    }
    public void update() {
        if (xDir == 1 || xDir == -1) {
            xPos += xDir;
        }
        if (yDir == 1 || yDir == -1) {
            yPos += yDir;
        }
    }
}