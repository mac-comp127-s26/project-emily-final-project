package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import game.Board;
import game.Card;
import storage.Position;

/**
 * A screen that holds the board and cursor.
 */
public class BoardScreen {

    private CanvasWindow boardCanvas;
    private double scale;
    private Rectangle cursor;

    public BoardScreen(int size) {
        scale = 0.00009523809 * size;
        boardCanvas = new CanvasWindow("Board!", size, size);
    }

    /**
     * Using the data from @param board, clear the boardScreen and redraw all cards onto the board.
     */
    public void update(Board board) {
        boardCanvas.removeAll();
        scale = getNewScale(board);
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x, y)) {
                    addCardtoScreen(board.getCard(x, y), x, y);
                }
            }
        }
    }

    /**
     * Adds the icon of the given card at the position on the screen that corresponds to (x,y)
     */
    public void addCardtoScreen(Card card, int x, int y) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        double adj = 1500 * scale;
        boardCanvas.add(icon);
        icon.setCenter((x * adj) + (adj / 2), (y * adj) + (adj / 2));
    }

    public CanvasWindow getScreen() {
        return boardCanvas;
    }

    /**
     * Place a rectangle at the position on the screen that corresponds to (x,y) on the given board
     */
    public void placeCursor(int x, int y) {
        cursor = new Rectangle(x, y, 1500 * scale, 1500 * scale);
        boardCanvas.add(cursor);
        double adj = 1500 * scale;
        cursor.setCenter((x * adj) + (adj / 2), (y * adj) + (adj / 2));
    }

    /**
     * Readjust the scale variable so that the size of the card icons will fit on the screen, determined
     * by the smaller of the dimensions of the screen.
     */
    public double getNewScale(Board board) {
        double wScale = boardCanvas.getWidth() / (1500.0 * board.getArrayWidth());
        double hScale = boardCanvas.getHeight() / (1500.0 * board.getArrayHeight());
        if (wScale < hScale) {
            return wScale;
        } else {
            return hScale;
        }
    }

    /**
     * Return the (x,y) coordinate of the board the mouse is on.
     */
    public Position getMouseCoordinates(double mX, double mY) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }
}