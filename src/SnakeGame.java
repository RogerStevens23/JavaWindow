import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends Thread {
    // Fields
    private WindowScreen gameScreen;
    private double TARGET_FPS = 120D, TARGET_UPS = 200D;
    private KeyboardInputs inputs;
    private Snake snake;
    private Apple apple;

    // Getters
    public KeyboardInputs getInputs() {return inputs;}
    public Snake getSnake() {return snake;}
    public Apple getApple() {return apple;}

    // Constructor
    public SnakeGame() {
        Random r = new Random();
        snake = new Snake(this,500, 500, Color.green);
        apple = new Apple();
        inputs = new KeyboardInputs(this);
        gameScreen = new WindowScreen(this);

        start();
    }

    // Methods
    @Override
    public void run() {
        long nsStartTime = System.nanoTime(); // Start time in nanoseconds.
        long msStartTime = System.currentTimeMillis(); // Start time in milliseconds.
        double nsPerFrame = 1000000000D / TARGET_FPS; // Amount of nanoseconds passed to render frame, calculated with the desired frame rate.
        double nsPerUpdate = 1000000000D / TARGET_UPS; // Amount of nanoseconds passed per update/tick, calculated with the desired update rate.

        int updates = 0; // UPS counter.
        int frames = 0; // FPS counter.

        double deltaTime = 0;  // The difference in time between the current and previous frame.
        double deltaFrame = 0; // The difference in time between the current and previous frame.

        while (true) {
            long now = System.nanoTime();
            deltaTime += (now - nsStartTime) / nsPerUpdate; // The difference in time between the current and previous frame, divided by desired update rate.
            deltaFrame += (now - nsStartTime) / nsPerFrame; // The difference in time between the current and previous frame, divided by desired frame rate.
            nsStartTime = now;                              // previous time check is set to current time.

            // Limit tick/update rate.
            while (deltaTime >= 1) {
                update(); // Game logic updating.
                updates++; // tick counter.
                deltaTime--;
            }

            // Limit frame rate.
            while (deltaFrame >= 1) {
                gameScreen.repaint(); // images refreshing.
                frames++; // frames counter.
                deltaFrame--;
            }

            // Print fps and ups to the console.
            // If the difference between the current time and start time is greater
            // than or equal to 1000ms, add 1000ms, print to console and reset counters.
            if (System.currentTimeMillis() - msStartTime >= 1000) {
                msStartTime += 1000;
                debugConsole(frames, updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void debugConsole(int frames, int updates) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println(frames + " frames || " + updates + " updates"); // Prints every second.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("segments: " + snake.getSegmentCount());
        System.out.println("arraySize: " + snake.getSegmentsSize());
        System.out.println("DeltaDirPoints: " + snake.getDeltaDirPointsSize());
        System.out.println(":Current head position:");
        System.out.println("X: "+snake.getSegments().get(0).xPos+" || Y:"+snake.getSegments().get(0).yPos);
        System.out.println("------------------------------------------------------------------------");

        /*ArrayList<Segment> temp = snake.getSegments();

        int i = 0;
        for (Segment seg : temp) {
            System.out.println(":Segment ["+i+"] position:");
            System.out.println("X: "+seg.xPos+" || Y:"+seg.yPos);
            i++;
        }*/

        /*System.out.println();
        System.out.println("------------------------------------------------------------------------");
        System.out.println("DeltaDirPoints: " + snake.getDeltaDirPointsSize());
        for (DeltaDirPoint dP : snake.getDeltaDirPoints()) {
            System.out.println("X: "+dP.x+" || Y: "+dP.y);
            System.out.println("XDir: "+dP.xDir+" || YDir: "+dP.yDir);
        }
        System.out.println("------------------------------------------------------------------------");*/
    }

    public void update() {
        snake.update();
    }
    public void render(Graphics g) {
        apple.draw(g);
        snake.draw(g);
    }
    /*public void drawTriangle(Graphics g) {
        int[] x = new int[] {500, 485, 515};
        int[] y = new int[] {500, 470, 470};
        g.setColor(Color.green);
        g.fillPolygon(x, y, 3);
    }*/
}