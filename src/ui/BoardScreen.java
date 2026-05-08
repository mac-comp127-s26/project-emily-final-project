package ui;

import edu.macalester.graphics.CanvasWindow;
import game.*;

/**
 * A screen that holds the board and cursor.
 */
public class BoardScreen extends InteractiveScreen {

    private CanvasWindow canvas;
    private double scale;

    public BoardScreen(int size) {
        scale = 0.00009523809 * size;
        canvas = new CanvasWindow("Board!", size, size);
    }

    /**
     * Using the data from @param board, clear the boardScreen and redraw all cards onto the board.
     */
    public void update(Board board) {
        canvas.removeAll();
        scale = getNewScale(board);
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x, y)) {
                    addCardtoScreen(board.getCard(x, y), x, y);
                }
            }
        }
    }

    public CanvasWindow getScreen() {
        return canvas;
    }

    /**
     * Readjust the scale variable so that the size of the card icons will fit on the screen, determined
     * by the smaller of the dimensions of the screen.
     */
    public double getNewScale(Board board) {
        double wScale = canvas.getWidth() / (1500.0 * board.getArrayWidth());
        double hScale = canvas.getHeight() / (1500.0 * board.getArrayHeight());
        if (wScale < hScale) {
            return wScale;
        } else {
            return hScale;
        }
    }

    public double getScale() {
        return scale;
    }
}