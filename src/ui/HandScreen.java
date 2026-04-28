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

    public void addCardToHand(Card card, int pos) {
        scale = getNewScale();
        cardSize = scale * 1500;
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        icon.setCenter((pos * cardSize) + (cardSize / 2), 0 + (cardSize / 2));
        handCanvas.add(icon);
    }

    public CanvasWindow getScreen() {
        return handCanvas;
    }

    private double getNewScale() {
        double w = 0.00011111111 * handCanvas.getWidth();
        double h = 0.00066666666 * handCanvas.getHeight();
        if (w < h)
            return w;
        else
            return h;
    }

    public void clear() {
        handCanvas.removeAll();
    }

    public void update(Hand hand) {
        handCanvas.removeAll();
        List<Card> cards = hand.getCurrentHand();
        for (int i = 0; i < cards.size(); i++) {
            addCardToHand(cards.get(i), i);
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
