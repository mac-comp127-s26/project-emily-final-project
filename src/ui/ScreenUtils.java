package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import game.Card;
import storage.Position;

public class ScreenUtils {

    /**
     * Return the (x,y) coordinate of the position (x,y) on the screen.
     */
    public static Position getMouseCoordinates(double mX, double mY, double scale) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }

    /**
     * Adds the icon of the given card at the position on the screen that corresponds to (x,y)
     */
    public static void addCardtoScreen(CanvasWindow canvas, Card card, int x, int y, double scale) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        canvas.add(icon);
        icon.setCenter((x * 1500 * scale) + (1500 * scale / 2), (y * 1500 * scale) + (1500 * scale / 2));
    }

    /**
     * Place a rectangle at the position on the screen that corresponds to (x,y) on the given board
     */
    public static void placeCursor(CanvasWindow canvas, int x, int y, double scale) {
        Rectangle cursor = new Rectangle(x, y, 1500 * scale, 1500 * scale);
        canvas.add(cursor);
        cursor.setCenter((x * 1500 * scale) + (1500 * scale / 2), (y * 1500 * scale) + (1500 * scale / 2));
    }

}
