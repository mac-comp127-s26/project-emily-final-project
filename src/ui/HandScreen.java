package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import game.Card;
import game.Hand;
import storage.Position;

/**
 * A screen that holds the hand and drawn cards.
 */
public class HandScreen {

    private CanvasWindow handCanvas;
    private double scale;
    private Rectangle cursor;

    public HandScreen(int size) {
        scale = 0.00011111111 * size;
        handCanvas = new CanvasWindow("Hand!", (int) (1500 * scale) * 6, (int) (1500 * scale));
    }

    /**
     * Using the data from @param hand, clear the handScreen and redraw all cards onto the hand.
     */
    public void update(Hand hand) {
        handCanvas.removeAll();
        scale = getNewScale(hand);
        for (int i = 0; i < hand.getCurrentHand().size(); i++) {
            addCardToScreen(hand.getCurrentHand().get(i), i);
        }
    }

    /**
     * Show given card on the hand at index position @param pos
     */
    public void addCardToScreen(Card card, int x) {
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        icon.setCenter((x * scale * 1500) + (scale * 1500 / 2), 0 + (scale * 1500 / 2));
        handCanvas.add(icon);
    }

    public CanvasWindow getScreen() {
        return handCanvas;
    }

    /**
     * Place a rectangle at the position on the screen that corresponds to the (x) index on the screen
     */
    public void placeCursor(int x) {
        cursor = new Rectangle(x, 0, 1500 * scale, 1500 * scale);
        handCanvas.add(cursor);
        double adj = 1500 * scale;
        cursor.setCenter((x * adj) + (adj / 2), (adj / 2));
    }

    /**
     * Readjust the scale so that the card icons will fit in the hand window
     */
    private double getNewScale(Hand hand) {
        double w = handCanvas.getWidth() / 9000.0;
        if (hand.getCurrentHand().size() < 6) {
            w = handCanvas.getWidth() / (1500.0 * (hand.getCurrentHand().size() + 0.5));
        }
        double h = 0.00066666666 * handCanvas.getHeight();
        if (w < h)
            return w;
        else
            return h;
    }

    /**
     * Return the (x,y) coordinate of the position (x,y) on the screen.
     */
    public Position getMouseCoordinates(double mX, double mY) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }
}
