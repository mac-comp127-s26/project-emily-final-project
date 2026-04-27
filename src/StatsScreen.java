import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class StatsScreen {

    private CanvasWindow scoreCanvas;
    private Card selectedCard;

    public StatsScreen(int size) {
        scoreCanvas = new CanvasWindow("Stats!", size, 175);
    }

    public StatsScreen update(GameManager game) {
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
            scoreCanvas.add(new GraphicsText("Selected card: " + selectedCard.getName()), 5, y);
            y += 30;
            scoreCanvas.add(new GraphicsText("Ability: ", 5, y));
            y += 15;
            scoreCanvas.add(new GraphicsText(selectedCard.getDescription(), 5, y));
        }
        return this;
    }

    public void selectCard(Card card) {
        selectedCard = card;
    }
}
