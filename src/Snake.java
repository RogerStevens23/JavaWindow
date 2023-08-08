import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Snake extends Entity {
    // Fields
    private final int segmentOffset = 2; // 2px spacing offset.
    private int segmentCount = 1;
    private SnakeGame game;
    private Segment head, tail;
    private ArrayList<Segment> segments;
    private ArrayList<DeltaDirPoint> deltaDirPoints;
    private Directions currentDirection = Directions.UP;

    // Getters
    public int getSegmentCount() {return segmentCount;}
    public int getSegmentsSize() {return segments.size();}
    public int getDeltaDirPointsSize() {return deltaDirPoints.size();}
    public ArrayList<DeltaDirPoint> getDeltaDirPoints() {return deltaDirPoints;}
    public ArrayList<Segment> getSegments() {return segments;}

    // Constructor
    public Snake(SnakeGame game, int xPos, int yPos, Color color) {
        super(xPos, yPos, 0, 0,new Dimension(10, 10), color);
        segments = new ArrayList<>();
        deltaDirPoints = new ArrayList<>();
        this.game = game;
        head = new Segment(this, new Point(this.xPos, this.yPos), xDir, yDir);
        segments.add(head);
        //addNewSegment();
    }

    // Methods
    public void addNewSegment() {
        tail = segments.get(segments.size()-1);
        Segment temp = new Segment(this, new Point(tail.xPos, tail.yPos), head.xDir, head.yDir);
        segments.add(temp);
        tail = segments.get(segments.size()-1);
        segmentCount++;
    }
    public void update() {

        // Head movement.
        if (head.xDir == 1 || head.xDir == -1) {
            head.xPos += head.xDir;
        }
        if (head.yDir == 1 || head.yDir == -1) {
            head.yPos += head.yDir;
        }

        int index = 0;
        // Segment movement.
        for (Segment seg : segments) {
            if (!seg.equals(head)) {
                seg.xDir = head.xDir;
                seg.yDir = head.yDir;
                if (deltaDirPoints.size() == 0) {
                    // Up
                    if (head.xDir == 0 && head.yDir == -1) {
                        seg.yPos = (head.yPos) + (12 * index);
                        seg.xPos = head.xPos;
                    }
                    // Left
                    if (head.xDir == -1 && head.yDir == 0) {
                        seg.xPos = (head.xPos) + (12 * index);
                        seg.yPos = head.yPos;
                    }
                    // Down
                    if (head.xDir == 0 && head.yDir == 1) {
                        seg.yPos = (head.yPos) - (12 * index);
                        seg.xPos = head.xPos;
                    }
                    // Right
                    if (head.xDir == 1 && head.yDir == 0) {
                        seg.xPos = (head.xPos) - (12 * index);
                        seg.yPos = head.yPos;
                    }
                } else {
                    for (DeltaDirPoint dP : deltaDirPoints) {
                        if (!seg.isAt(dP)) {
                            seg.xPos += seg.xDir;
                            seg.yPos += seg.yDir;
                        } else {
                            seg.xDir = head.xDir;
                            seg.yDir = head.yDir;
                        }
                    }
                }
                index++;
            }

            /*if(segments.get(i) != head) {
                segments.get(i).xPos = (head.xPos-12);
            }

            switch (currentDirection) {
                case UP:
                    seg.xPos = head.xPos;
                    seg.yPos = head.yPos - 12;
                    break;
                case LEFT:
                    break;
                case DOWN:
                    break;
                case RIGHT:
                    break;
            }*//*
        }*/
        /*for (DeltaDirPoint dP : deltaDirPoints) {
            for(Segment seg : segments) {
                if(!seg.equals(head)) {
                    if(seg.isAt(dP)) {
                        seg.xDir = dP.xDir;
                        seg.yDir = dP.yDir;
                    }
                    if(seg.equals(tail)) {
                        dP.setHitTail(true);
                    }
                }
            }

        }*/

            for (int i=0; i < deltaDirPoints.size()-1; i++) {
            if(deltaDirPoints.get(i).getHitTail())
                deltaDirPoints.remove(i);
            }
        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        for (Segment seg : segments) {
            g.fillRect((seg.xPos - centerPoint.x), (seg.yPos - centerPoint.y), width, height);
        }

        if(deltaDirPoints.size() > 0)
            for (Point point : deltaDirPoints) {
                g.setColor(Color.white);
                g.fillRect(point.x - centerPoint.x, point.y - centerPoint.y, width, height);
            }

    }

    // Booleans
    /*public boolean isTouchingApple(Apple apple) {
        if()
    }*/

    // Input
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                head.xDir = 0;
                head.yDir = 0;
                break;
            case KeyEvent.VK_W:
                if(head.xDir != 0 && head.yDir != -1)
                    deltaDirPoints.add(new DeltaDirPoint(head.xPos, head.yPos, 0, -1));
                head.xDir = 0;
                head.yDir = -1;
                currentDirection = Directions.UP;
                break;
            case KeyEvent.VK_A:
                if(head.xDir != -1 && head.yDir != 0)
                    deltaDirPoints.add(new DeltaDirPoint(head.xPos, head.yPos, -1, 0));
                head.xDir = -1;
                head.yDir = 0;
                currentDirection = Directions.LEFT;
                break;
            case KeyEvent.VK_S:
                if(head.xDir != 0 && head.yDir != 1)
                    deltaDirPoints.add(new DeltaDirPoint(head.xPos, head.yPos, 0, 1));
                head.xDir = 0;
                head.yDir = 1;
                currentDirection = Directions.DOWN;
                break;
            case KeyEvent.VK_D:
                if(head.xDir != 1 && head.yDir != 0)
                    deltaDirPoints.add(new DeltaDirPoint(head.xPos, head.yPos, 1, 0));
                head.xDir = 1;
                head.yDir = 0;
                currentDirection = Directions.RIGHT;
                break;
            case KeyEvent.VK_E:
                addNewSegment();
                break;
        }
    }
}