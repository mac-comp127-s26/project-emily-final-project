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
    private boolean gameOn = true;

    public StatsScreen(int size) {
        scoreCanvas = new CanvasWindow("Stats!", size, 190);
    }

    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        if (gameOn) {
            int y = 15;
            scoreCanvas.removeAll();
            scoreCanvas.add(new GraphicsText("Cards left in deck: " + game.getHand().numCardsRemaining(), 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText("Spaces left on board: " + game.getBoard().getOpenSpaces(), 5, y));
            y += 30;
            scoreCanvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
            y += 30;
            if (selectedCard != null) {
                y = detailSelectedCard(y);
            }
            if (game.testEndConditions() != EndCondition.NONE) {
                y = 15;
                scoreCanvas.removeAll();
            }
            if (game.testEndConditions() == EndCondition.LOSE) {
                scoreCanvas.add(new GraphicsText("Game over!"), 5, y);
                y += 30;
                scoreCanvas.add(new GraphicsText("Your city fell into debt."), 5, y);
                y += 30;
                scoreCanvas.add(new GraphicsText("Your final scores:"), 5, y);
                y += 30;
                scoreCanvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, y));
                y += 15;
                scoreCanvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, y));
                y += 15;
                scoreCanvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, y));
                gameOn = false;
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
    }

    /**
     * Add the details of the selectedCard to the screen.
     */
    public int detailSelectedCard(int y) {
        scoreCanvas.add(
            new GraphicsText("Selected card: " + selectedCard.getName() + " (" + selectedCard.getTypeName() + ")"),
            5, y);
        y += 30;
        scoreCanvas.add(new GraphicsText("Ability: ", 5, y));
        y += 15;
        scoreCanvas.add(new GraphicsText(selectedCard.getDescription(), 5, y));
        return y;
    }

    /**
     * Set @param card to the selected card.
     */
    public void selectCard(Card card) {
        selectedCard = card;
    }

}
