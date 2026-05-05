package ui;

import edu.macalester.graphics.CanvasWindow;
import game.*;

/**
 * A screen that holds the drawn cards and cursor.
 */
public class HandScreen {

    private CanvasWindow canvas;
    private double scale;

    public HandScreen(int size) {
        scale = 0.00011111111 * size;
        canvas = new CanvasWindow("Hand!", (int) (1500 * scale) * 6, (int) (1500 * scale));
    }

    /**
     * Using the data from @param hand, clear the handScreen and redraw all cards onto the hand.
     */
    public void update(Hand hand) {
        canvas.removeAll();
        scale = getNewScale(hand);
        for (int i = 0; i < hand.getCurrentHand().size(); i++) {
            addCardToScreen(hand.getCurrentHand().get(i), i);
        }
    }

    /**
     * Show given card on the hand at index position @param pos
     */
    public void addCardToScreen(Card card, int x) {
        ScreenUtils.addCardtoScreen(canvas, card, x, 0, scale);
    }

    public CanvasWindow getScreen() {
        return canvas;
    }

    /**
     * Place a rectangle at the position on the screen that corresponds to the (x) index on the screen
     */
    public void placeCursor(int x) {
        ScreenUtils.placeCursor(canvas, x, 0, scale);
    }

    /**
     * Readjust the scale so that the card icons will fit in the hand window
     */
    private double getNewScale(Hand hand) {
        double w = canvas.getWidth() / 9000.0;
        if (hand.getCurrentHand().size() < 6) {
            w = canvas.getWidth() / (1500.0 * (hand.getCurrentHand().size() + 0.5));
        }
        double h = 0.00066666666 * canvas.getHeight();
        if (w < h)
            return w;
        else
            return h;
    }

    public double getScale() {
        return scale;
    }
}
