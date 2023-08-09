import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Uses the key event class to register keyboard presses.
public class KeyboardInputs implements KeyListener {
    // Fields
    private SnakeGame game;

    // Constructor
    public KeyboardInputs(SnakeGame game) {
        this.game = game;
    }

    // Methods

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        game.getSnake().keyReleased(e);
    }
}