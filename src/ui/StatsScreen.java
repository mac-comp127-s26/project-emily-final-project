package ui;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import enums.EndCondition;
import game.Card;
import managers.GameManager;

/**
 * A screen that updates with current scores, messages, and details.
 */
public class StatsScreen {

    private CanvasWindow canvas;
    private Card previewedCard;
    private Card selectedCard;
    private boolean gameOn = true;
    private double textSpacing;
    private double scale;

    private List<GraphicsText> texts = new ArrayList<>();

    public StatsScreen(int size) {
        canvas = new CanvasWindow("Stats!", size, 190);
    }

    /**
     * Creates a text object slightly indented from the left of the screen
     */
    private GraphicsText createText(String text, double y, double scale) {
        return createText(text, canvas.getWidth() / 130, y, scale);
    }

    /**
     * Creates a text object at position (x,y)
     */
    private GraphicsText createText(String text, double x, double y, double scale) {
        GraphicsText txt = new GraphicsText(text, x, y);
        txt.setScale(scale);
        txt.setCenter((txt.getPosition().getX() * scale) + ((txt.getWidth() * scale) / 2), txt.getPosition().getY());
        texts.add(txt);
        return txt;
    }

    /**
     * Return the GraphicsText object in texts in list that has the longest width.
     */
    private GraphicsText getLongestText() {
        GraphicsText longest = texts.get(0);
        for (GraphicsText t : texts) {
            if (t.getWidth() > longest.getWidth()) {
                longest = t;
            }
        }
        return longest;
    }

    private double getNewScale() {
        double res = 1.0;
        if (getLongestText().getWidth() > canvas.getWidth()) {
            res = canvas.getWidth() / getLongestText().getWidth();
        }
        return res;
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        if (gameOn) {
            textSpacing = canvas.getHeight() / 13;
            double y = textSpacing;
            canvas.removeAll();
            canvas.add(createText("Cards left in deck: " + game.getHand().numCardsRemaining(), y, scale));
            if (selectedCard != null && scale > 0.7) {
                canvas.add(createText("Selected card:", canvas.getWidth()-110, y, scale));
                y += textSpacing;
                canvas.add(createText(selectedCard.getName(), canvas.getWidth()-110, y, scale));
            } else {
                y += textSpacing;
            }
            canvas.add(createText("Spaces left on board: " + game.getBoard().getOpenSpaces(), y, scale));
            y += textSpacing * 2;
            canvas.add(createText("Population: " + game.getStats().get(0), y, scale));
            y += textSpacing;
            canvas.add(createText("Economy: " + game.getStats().get(1), y, scale));
            y += textSpacing;
            canvas.add(createText("Leisure: " + game.getStats().get(2), y, scale));
            y += textSpacing * 2;
            if (previewedCard != null) {
                y = detailCard(previewedCard, y, textSpacing, scale, "Previewed");
            } else if (selectedCard != null) {
                y = detailCard(selectedCard, y, textSpacing, scale, "Selected");
            }
            if (game.testEndConditions() != EndCondition.NONE) {
                y = textSpacing;
                canvas.removeAll();
            }
            if (game.testEndConditions() == EndCondition.LOSE) {
                canvas.add(createText("Game over!", y, scale));
                y += textSpacing * 2;
                canvas.add(createText("Your city fell into debt.", y, scale));
                y += textSpacing * 2;
                canvas.add(createText("Your final scores:", y, scale));
                y += textSpacing;
                canvas.add(createText("Population: " + game.getStats().get(0), y, scale));
                y += textSpacing;
                canvas.add(createText("Economy: " + game.getStats().get(1), y, scale));
                y += textSpacing;
                canvas.add(createText("Leisure: " + game.getStats().get(2), y, scale));
                gameOn = false;
            } else if (game.testEndConditions() == EndCondition.WIN) {
                canvas.add(createText("Game over!", y, scale));
                y += textSpacing * 2;
                canvas.add(createText("Your city is thriving.", y, scale));
                y += textSpacing * 2;
                game.runEndAbilities();
                canvas.add(createText("Population: " + game.getStats().get(0), y, scale));
                y += textSpacing;
                canvas.add(createText("Economy: " + game.getStats().get(1), y, scale));
                y += textSpacing;
                canvas.add(createText("Leisure: " + game.getStats().get(2), y, scale));
                y += textSpacing * 2;
                canvas.add(createText("Final score: " + game.getScoreTracker().finalScore(), y, scale));
            }
            scale = getNewScale();
        }
    }

    /**
     * Add the details of the selectedCard to the screen.
     */
    public double detailCard(Card card, double y, double textHeight, double scale, String typeOfPreview) {
        canvas.add(
            createText(typeOfPreview + " card: " + card.getName() + " (" + card.getTypeName() + ")", y, scale));
        y += textHeight * 2;
        canvas.add(createText("Ability: ", y, scale));
        y += textHeight * 2;
        canvas.add(createText(card.getDescription(), y, scale));
        return y;
    }

    /**
     * Set @param card to the selected card.
     */
    public void previewCard(Card card) {
        previewedCard = card;
    }

    public void selectCard(Card card) {
        selectedCard = card;
    }
}
