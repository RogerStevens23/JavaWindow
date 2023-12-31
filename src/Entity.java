import java.awt.*;

// Represents a rectangle in 2D space using the native Canvas class.
public class Entity extends Canvas {

    // Fields
    protected int xPos, yPos;
    protected final int width, height;
    protected Point centerPoint;
    protected int xDir = 0, yDir = 0;
    protected Color color;

    // Getters
    public int getXPos() {return xPos;}
    public int getYPos() {return yPos;}
    public int getXDir() {return xDir;}
    public int getYDir() {return yDir;}

    // Constructor
     public Entity(int xPos, int yPos, int xDir, int yDir, Dimension widthHeight, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xDir = xDir; // Used to change the direction of the rect.
        this.yDir = yDir;
        this.width = widthHeight.width;
        this.height = widthHeight.height;
        this.centerPoint = new Point((width/2), (width/2));
        this.color = color;
    }

    // Methods
    // Draws the object to the screen.
    public void draw(Graphics g) {
        g.setColor(color);
    }
}