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
    private double textHeight;
    private double scale;
    private double size;

    public StatsScreen(int size) {
        canvas = new CanvasWindow("Stats!", size, 190);
        this.size = size;
    }



    /**
     * Reading data from @param game, update the text on the screen to match the respective values.
     */
    public void update(GameManager game) {
        textHeight = canvas.getHeight()/12.67;
        scale = canvas.getHeight()/16.95;
        if (gameOn) {
            double y = textHeight;
            canvas.removeAll();
            canvas.add(new GraphicsText("Cards left in deck: " + game.getHand().numCardsRemaining(), 5, y));
            System.out.println(new GraphicsText("Cards left in deck: " + game.getHand().numCardsRemaining(), 5, y).getHeight());
            if (selectedCard != null) {
                canvas.add(new GraphicsText("Selected card:"), canvas.getWidth()-105, y);
            }
            y += textHeight;
            canvas.add(new GraphicsText("Spaces left on board: " + game.getBoard().getOpenSpaces(), 5, y));
            if (selectedCard != null) {
                canvas.add(new GraphicsText(selectedCard.getName()), canvas.getWidth()-105, y);
            }
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
