package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import game.Board;
import game.Card;
import storage.Position;

/**
 * A screen that holds the board and cursor.
 */
public class BoardScreen {

    private CanvasWindow boardCanvas;
    private double scale;
    private Ellipse cursor;

    public BoardScreen(int size) {
        boardCanvas = new CanvasWindow("Board!", size, size);
        scale = 0.00009523809 * size;
    }

    /**
     * Place an ellipse at the position on the screen that corresponds to (x,y) on the given board
     */
    public void placeCursor(Board board, Card card, int x, int y) {
        System.out.println("Trying to place!");
        // removeCursor();
        if (cursor == null) {
            cursor = new Ellipse(x, y, 1000 * scale, 1000 * scale);
            System.out.println("Trying to create!");
            boardCanvas.add(cursor);
        }
        double adjW = 1500.0 * scale;
        double adjH = 1500.0 * scale;
        System.out.println("Trying to center!");
        cursor.setCenter((x * adjW) + (adjW / 2), (y * adjH) + (adjH / 2));
    }

    /**
     * Adds the icon of the given card at the position on the screen that corresponds to (x,y)
     */
    public void addCardtoScreen(Card card, int x, int y) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        double adjW = 1500 * scale;
        double adjH = 1500 * scale;
        boardCanvas.add(icon);
        icon.setCenter((x * adjW) + (adjW / 2), (y * adjH) + (adjH / 2));
    }

    public CanvasWindow getScreen() {
        return boardCanvas;
    }

    public void clear() {
        boardCanvas.removeAll();
    }

    /**
     * Return the size of the smaller length of the screen.
     */
    private double getSmallestSize() {
        if (boardCanvas.getWidth() < boardCanvas.getHeight()) {
            return boardCanvas.getWidth();
        } else {
            return boardCanvas.getHeight();
        }
    }

    /**
     * Readjust the scale variable so that the size of the card icons will fit on the screen.
     */
    public void getNewScale(Board board) {
        double y;
        if (board.getArrayWidth() > board.getArrayHeight()) {
            y = board.getArrayWidth();
        } else {
            y = board.getArrayHeight();
        }
        scale = ((int) getSmallestSize()) / (1500 * y);
    }

    /**
     * Return the (x,y) coordinate of the board the mouse is on.
     */
    public Position getMouseCoordinates(Board board, double mX, double mY) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }

    /**
     * If the cursor exists, remove it.
     */
    public void removeCursor() {
        if (cursor != null) {
            boardCanvas.remove(cursor);
            cursor = null;
        }
    }
}