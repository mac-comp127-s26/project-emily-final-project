import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import java.awt.Point;
import java.awt.MouseInfo;

public class BoardScreen {

    private CanvasWindow boardCanvas;
    private double scale;


    public BoardScreen(int size) {
        boardCanvas = new CanvasWindow("Board!", size, size);
        scale = 0.00009523809*size;
    }

    public void addCardtoScreen(Card card, int x, int y) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        double adjW = icon.getWidth()*scale;
        double adjH = icon.getHeight()*scale;
        boardCanvas.add(icon);
        icon.setCenter((x*adjW)+(adjW/2), (y*adjH)+(adjH/2));
    }

    public CanvasWindow getScreen() {
        return boardCanvas;
    }

    public void clear() {
        boardCanvas.removeAll();
    }

    private int getSmallestSize() {
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
        scale = getSmallestSize() / (1500 * y);
    }

    private Point getMousePos() {
        return MouseInfo.getPointerInfo().getLocation();
    }

/**
 * Return the (x,y) coordinate of the board the mouse is on.
 */
    public Position getMouseCoordinates(Board board) {
        double modX = boardCanvas.getWidth() / board.getArrayWidth();
        double mouseX = getMousePos().getX() / modX;
        double modY = boardCanvas.getHeight() / board.getArrayHeight();
        double mouseY = getMousePos().getY() / modY;
        return new Position((int) mouseX, (int) mouseY);
    }
}