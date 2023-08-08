import java.awt.Point;


// Store a point in 2D space when a change in direction occurs. Also stores the directional change.
public class DeltaDirPoint extends Point {
    // Fields
    private int segCount = 0;
    public int xDir, yDir;
    private boolean hitTail = false;

    // Getters
    public int getSegCount() {return segCount;}
    public boolean getHitTail() {
        return hitTail;
    }

    // Setters
    public void setHitTail(boolean hitTail) {this.hitTail = hitTail;}

    // Constructor
    public DeltaDirPoint(int x, int y, int xDir, int yDir) {
        super(x, y);
        this.xDir = xDir;
        this.yDir = yDir;
    }

    // methods
}
