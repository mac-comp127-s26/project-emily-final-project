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

    private CanvasWindow scoreCanvas;
    private Card selectedCard;
    private Card previewedCard;

    public StatsScreen(int size) {
        scoreCanvas = new CanvasWindow("Stats!", size, 175);
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        int y = 15;
        scoreCanvas.removeAll();
        scoreCanvas.add(new GraphicsText("Cards left in deck: " + game.getHand().numCardsRemaining(), 5, y));
        y += 30;
        scoreCanvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
        y += 15;
        scoreCanvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
        y += 15;
        scoreCanvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
        y += 30;
        if (selectedCard != null) {
            y = showSelectedCard(y);
        } else {
            y = showPreviewedCard(y);
        }
        if (game.testEndConditions() != EndCondition.NONE) {
            y = 15;
            scoreCanvas.removeAll();
        }
        if (game.testEndConditions() == EndCondition.LOSE) {
            scoreCanvas.add(new GraphicsText("Game over!"), 5, y);
            y += 30;
            scoreCanvas.add(new GraphicsText("Your city fell into debt."), 5, y);
        } else if (game.testEndConditions() == EndCondition.WIN) {
            scoreCanvas.add(new GraphicsText("Game over!"), 5, y);
            y += 30;
            scoreCanvas.add(new GraphicsText("Your city is thriving."), 5, y);
            y += 30;
            game.runEndAbilities();
            scoreCanvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
            y += 30;
            scoreCanvas.add(new GraphicsText("Final score: " + game.getScoreTracker().finalScore(), 5, y));
        }
    }

    /**
     * Add details of selectedCard to the screen
     */
    public int showSelectedCard(int y) {
        scoreCanvas.add(
            new GraphicsText("Selected card: " + selectedCard.getName() + " (" + selectedCard.getTypeName() + ")"), 5,
            y);
        y += 30;
        scoreCanvas.add(new GraphicsText("Ability: ", 5, y));
        y += 15;
        scoreCanvas.add(new GraphicsText(selectedCard.getDescription(), 5, y));
        return y;
    }

    /**
     * Add details of previewedCard to the screen
     */
    public int showPreviewedCard(int y) {
        scoreCanvas.add(
            new GraphicsText("Previewed card: " + previewedCard.getName() + " (" + previewedCard.getTypeName() + ")"), 5,
            y);
        y += 30;
        scoreCanvas.add(new GraphicsText("Ability: ", 5, y));
        y += 15;
        scoreCanvas.add(new GraphicsText(previewedCard.getDescription(), 5, y));
        return y;
    }

/**
 * Set @param card to the previewed card.
 */
    public void previewCard(Card card) {
        previewedCard = card;
    }

    /**
     * Set @param card to the selected card.
     */
    public void selectCard(Card card) {
        selectedCard = card;
    }

}
