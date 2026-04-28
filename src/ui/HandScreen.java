package ui;

import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import game.Card;
import game.Hand;

/**
 * A screen that holds the hand and drawn cards.
 */
public class HandScreen {

    private double scale;
    private CanvasWindow handCanvas;
    private double cardSize;

    public HandScreen(int size) {
        scale = (0.00011111111 * size);
        cardSize = 1500 * scale;
        handCanvas = new CanvasWindow("Hand!", (int) cardSize * 6, (int) cardSize);
    }

    /**
     * Show given card on the hand at index position @param pos
     */
    public void addCardToHand(Hand hand, Card card, int pos) {
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
            addCardToHand(hand, cards.get(i), i);
        }
    }

    /**
     * Return the index of the card in the hand that the mouse is on.
     */
    public int getMouseIndex(double mouseX) {
        double index = mouseX / cardSize;
        return (int) index;
    }
}
