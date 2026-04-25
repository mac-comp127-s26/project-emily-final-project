import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class ScreenManager {

    private CanvasWindow canvas;
    private int size;
    private double scale;


    public ScreenManager(int size) {
        this.size = size;
        canvas = new CanvasWindow("CardCity!", size, size);
        scale = 0.00009523809*size;
    }

    public void addCardtoScreen(Card card, int x, int y) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        double adjW = icon.getWidth()*scale;
        double adjH = icon.getHeight()*scale;
        canvas.add(icon);
        icon.setCenter((x*adjW)+(adjW/2), (y*adjH)+(adjH/2));
    }

    public CanvasWindow getCanvas() {
        return canvas;
    }

    public void clear() {
        canvas.removeAll();
    }

    public void getNewScale(Board board) {
        double y;
        if (board.getArrayWidth() > board.getArrayHeight()) {
            y = board.getArrayWidth();
        } else {
            y = board.getArrayHeight();
        }
        scale = size / (1500 * y);
    }
}