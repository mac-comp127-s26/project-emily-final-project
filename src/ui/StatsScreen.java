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
    private double textHeight;

    private List<GraphicsText> texts = new ArrayList<>();

    public StatsScreen(int size) {
        canvas = new CanvasWindow("Stats!", size, 190);
    }

    private GraphicsText createText(String text, double y) {
        GraphicsText txt = new GraphicsText(text, canvas.getWidth()/130, y);
        texts.add(txt);
        return txt;
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        textHeight = canvas.getHeight()/13;
        if (gameOn) {
            double y = textHeight;
            canvas.removeAll();
            canvas.add(createText("Cards left in deck: " + game.getHand().numCardsRemaining(), y));
            if (selectedCard != null) canvas.add(new GraphicsText("Selected card:"), canvas.getWidth()-110, y);
            y += textHeight;
            if (selectedCard != null) canvas.add(new GraphicsText(selectedCard.getName(), canvas.getWidth()-110, y));
            canvas.add(createText("Spaces left on board: " + game.getBoard().getOpenSpaces(), y));
            y += textHeight*2;
            canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
            y += textHeight;
            canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
            y += textHeight;
            canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
            y += textHeight*2;
            if (previewedCard != null) {
                y = detailCard(previewedCard, y, textHeight, "Previewed");
            } else if (selectedCard != null) {
                y = detailCard(selectedCard, y, textHeight, "Selected");
            }
            if (game.testEndConditions() != EndCondition.NONE) {
                y = textHeight;
                canvas.removeAll();
            }
            if (game.testEndConditions() == EndCondition.LOSE) {
                canvas.add(new GraphicsText("Game over!"), 5, y);
                y += textHeight*2;
                canvas.add(new GraphicsText("Your city fell into debt."), 5, y);
                y += textHeight*2;
                canvas.add(new GraphicsText("Your final scores:"), 5, y);
                y += textHeight;
                canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
                y += textHeight;
                canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
                y += textHeight;
                canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
                gameOn = false;
            } else if (game.testEndConditions() == EndCondition.WIN) {
                canvas.add(new GraphicsText("Game over!"), 5, y);
                y += textHeight*2;
                canvas.add(new GraphicsText("Your city is thriving."), 5, y);
                y += textHeight*2;
                game.runEndAbilities();
                canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
                y += textHeight;
                canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
                y += textHeight;
                canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
                y += textHeight*2;
                canvas.add(new GraphicsText("Final score: " + game.getScoreTracker().finalScore(), 5, y));
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
        y += textHeight*2;
        canvas.add(new GraphicsText("Ability: ", 5, y));
        y += textHeight*2;
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
