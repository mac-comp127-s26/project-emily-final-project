package ui;

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

    public StatsScreen(int size) {
        canvas = new CanvasWindow("Stats!", size, 190);
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        if (gameOn) {
            int y = 15;
            canvas.removeAll();
            canvas.add(new GraphicsText("Cards left in deck: " + game.getHand().numCardsRemaining(), 5, y));
            if (selectedCard != null) {
                canvas.add(new GraphicsText("Selected card:"), canvas.getWidth()-105, y);
            }
            y += 15;
            canvas.add(new GraphicsText("Spaces left on board: " + game.getBoard().getOpenSpaces(), 5, y));
            if (selectedCard != null) {
                canvas.add(new GraphicsText(selectedCard.getName()), canvas.getWidth()-105, y);
            }
            y += 30;
            canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
            y += 15;
            canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
            y += 15;
            canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
            y += 30;
            if (previewedCard != null) {
                y = detailCard(previewedCard, y, "Previewed");
            } else if (selectedCard != null) {
                y = detailCard(selectedCard, y, "Selected");
            }
            if (game.testEndConditions() != EndCondition.NONE) {
                y = 15;
                canvas.removeAll();
            }
            if (game.testEndConditions() == EndCondition.LOSE) {
                canvas.add(new GraphicsText("Game over!"), 5, y);
                y += 30;
                canvas.add(new GraphicsText("Your city fell into debt."), 5, y);
                y += 30;
                canvas.add(new GraphicsText("Your final scores:"), 5, y);
                y += 30;
                canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
                y += 15;
                canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
                y += 15;
                canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
                gameOn = false;
            } else if (game.testEndConditions() == EndCondition.WIN) {
                canvas.add(new GraphicsText("Game over!"), 5, y);
                y += 30;
                canvas.add(new GraphicsText("Your city is thriving."), 5, y);
                y += 30;
                game.runEndAbilities();
                canvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
                y += 15;
                canvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
                y += 15;
                canvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
                y += 30;
                canvas.add(new GraphicsText("Final score: " + game.getScoreTracker().finalScore(), 5, y));
            }
        }
    }

    /**
     * Add the details of the selectedCard to the screen.
     */
    public int detailCard(Card card, int y, String typeOfPreview) {
        canvas.add(
            new GraphicsText(typeOfPreview + " card: " + card.getName() + " (" + card.getTypeName() + ")"),
            5, y);
        y += 30;
        canvas.add(new GraphicsText("Ability: ", 5, y));
        y += 15;
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
