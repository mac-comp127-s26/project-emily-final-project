package ui;

import java.util.List;

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

    private double scale;
    private CanvasWindow handCanvas;
    private double cardSize;
    private Rectangle cursor;

    public HandScreen(int size) {
        scale = (0.00011111111 * size);
        cardSize = 1500 * scale;
        handCanvas = new CanvasWindow("Hand!", (int) cardSize * 6, (int) cardSize);
    }

    /**
     * Show given card on the hand at index position @param pos
     */
    public void addCardToScreen(Hand hand, Card card, int pos) {
        scale = getNewScale(hand);
        cardSize = scale * 1500;
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        icon.setCenter((pos * cardSize) + (cardSize / 2), 0 + (cardSize / 2));
        handCanvas.add(icon);
    }

    public CanvasWindow getScreen() {
        return handCanvas;
    }

    /**
     * Readjust the scale so that the card icons will fit in the hand window
     */
    private double getNewScale(Hand hand) {
        double w = handCanvas.getWidth()/(1500.0*hand.getCurrentHand().size());
        double h = 0.00066666666 * handCanvas.getHeight();
        if (w < h)
            return w;
        else
            return h;
    }

    /**
     * Using the data from @param hand, clear the handScreen and redraw all cards onto the hand.
     */
    public void update(Hand hand) {
        handCanvas.removeAll();
        List<Card> cards = hand.getCurrentHand();
        for (int i = 0; i < cards.size(); i++) {
            addCardToScreen(hand, cards.get(i), i);
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

    /**
     * Place a rectangle at the position on the screen that corresponds to the (x) index on the screen
     */
    public void placeCursor(int x) {
        cursor = new Rectangle(x, 0, 1500 * scale, 1500 * scale);
        handCanvas.add(cursor);
        double adj = 1500 * scale;
        cursor.setCenter((x * adj) + (adj / 2), (adj / 2));
    }
}
