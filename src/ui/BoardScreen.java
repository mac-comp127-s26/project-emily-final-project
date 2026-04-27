package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import game.Board;
import game.Card;
import storage.Position;

public class BoardScreen {

    private CanvasWindow boardCanvas;
    private double scale;
    private Ellipse cursor;

    public BoardScreen(int size) {
        boardCanvas = new CanvasWindow("Board!", size, size);
        scale = 0.00009523809 * size;
    }

    public void placeCursor(Board board, Card card, int x, int y) {
        removeCursor();
            if (cursor == null) {
                cursor = new Ellipse(x, y, 750 * scale, 750 * scale);
                boardCanvas.add(cursor);
            }
            double adjW = card.getIcon().getWidth() * scale;
            double adjH = card.getIcon().getHeight() * scale;
            cursor.setCenter((x * adjW) + (adjW / 2), (y * adjH) + (adjH / 2));
        }

    public void addCardtoScreen(Card card, int x, int y) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        double adjW = icon.getWidth() * scale;
        double adjH = icon.getHeight() * scale;
        boardCanvas.add(icon);
        icon.setCenter((x * adjW) + (adjW / 2), (y * adjH) + (adjH / 2));
    }

    public CanvasWindow getScreen() {
        return boardCanvas;
    }

    public void clear() {
        boardCanvas.removeAll();
    }

    private double getSmallestSize() {
        if (boardCanvas.getWidth() < boardCanvas.getHeight()) {
            return boardCanvas.getWidth();
        } else {
            return boardCanvas.getHeight();
        }
    }

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
        double mouseX = mX / (1500*scale);
        double mouseY = mY / (1500*scale);
        return new Position((int) mouseX, (int) mouseY);
    }

    public void removeCursor() {
        if (cursor != null) {
            boardCanvas.remove(cursor);
            cursor = null;
        }
    }
}