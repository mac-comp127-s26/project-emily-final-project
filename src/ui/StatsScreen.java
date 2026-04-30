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
    private boolean textExists = false;
    private double textSpacing;

    private List<GraphicsText> texts = new ArrayList<>();


    public StatsScreen(int size) {
        canvas = new CanvasWindow("Stats!", size, 190);
    }

    /**
     * Creates a text object slightly indented from the left of the screen
     */
    private GraphicsText createText(String text, double y) {
        GraphicsText txt = new GraphicsText(text, canvas.getWidth() / 130, y);
        texts.add(txt);
        return txt;
    }

    /**
     * Return the GraphicsText object in texts in list that has the longest width.
     */
    private GraphicsText getLongestText() {
        GraphicsText longest = null;
        for (GraphicsText t : texts) {
            if (t.getWidth() > longest.getWidth()) {
                longest = t;
                System.out.println("Found something longer!");
            }
        }
        return longest;
    }

    private double getNewScale() {
        double res = 1;
        if (getLongestText().getWidth() > canvas.getWidth()) {
            res = canvas.getWidth()/getLongestText().getWidth();
        }
        return res;
    }

    private void scaleAllText() {
        double scale = getNewScale();
        for (GraphicsText obj : texts) {
            obj.setScale(scale);
        }
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        textSpacing = canvas.getHeight() / 13;
        if (gameOn) {
            double y = textSpacing;
            canvas.removeAll();
            canvas.add(createText("Cards left in deck: " + game.getHand().numCardsRemaining(), y));
            if (selectedCard != null)
                canvas.add(new GraphicsText("Selected card:"), canvas.getWidth() - 110, y);
            y += textSpacing;
            if (selectedCard != null)
                canvas.add(new GraphicsText(selectedCard.getName(), canvas.getWidth() - 110, y));
            canvas.add(createText("Spaces left on board: " + game.getBoard().getOpenSpaces(), y));
            y += textSpacing * 2;
            canvas.add(createText("Population: " + game.getStats().get(0), y));
            y += textSpacing;
            canvas.add(createText("Economy: " + game.getStats().get(1), y));
            y += textSpacing;
            canvas.add(createText("Leisure: " + game.getStats().get(2), y));
            y += textSpacing * 2;
            if (previewedCard != null) {
                y = detailCard(previewedCard, y, textSpacing, "Previewed");
            } else if (selectedCard != null) {
                y = detailCard(selectedCard, y, textSpacing, "Selected");
            }
            if (game.testEndConditions() != EndCondition.NONE) {
                y = textSpacing;
                canvas.removeAll();
            }
            if (game.testEndConditions() == EndCondition.LOSE) {
                canvas.add(createText("Game over!", y));
                y += textSpacing * 2;
                canvas.add(createText("Your city fell into debt.", y));
                y += textSpacing * 2;
                canvas.add(createText("Your final scores:", y));
                y += textSpacing;
                canvas.add(createText("Population: " + game.getStats().get(0), y));
                y += textSpacing;
                canvas.add(createText("Economy: " + game.getStats().get(1), y));
                y += textSpacing;
                canvas.add(createText("Leisure: " + game.getStats().get(2), y));
                gameOn = false;
            } else if (game.testEndConditions() == EndCondition.WIN) {
                canvas.add(createText("Game over!", y));
                y += textSpacing * 2;
                canvas.add(createText("Your city is thriving.", y));
                y += textSpacing * 2;
                game.runEndAbilities();
                canvas.add(createText("Population: " + game.getStats().get(0), y));
                y += textSpacing;
                canvas.add(createText("Economy: " + game.getStats().get(1), y));
                y += textSpacing;
                canvas.add(createText("Leisure: " + game.getStats().get(2), y));
                y += textSpacing * 2;
                canvas.add(createText("Final score: " + game.getScoreTracker().finalScore(), y));
            }
        }
    }

    /**
     * Add the details of the selectedCard to the screen.
     */
    public double detailCard(Card card, double y, double textHeight, String typeOfPreview) {
        canvas.add(
            new GraphicsText(typeOfPreview + " card: " + card.getName() + " (" + card.getTypeName() + ")"),
            5, y);
        y += textHeight * 2;
        canvas.add(new GraphicsText("Ability: ", 5, y));
        y += textHeight * 2;
        canvas.add(new GraphicsText(card.getDescription(), 5, y));
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
