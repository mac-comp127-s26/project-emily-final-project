import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class ScoreScreen {

    CanvasWindow scoreCanvas;
    
    public ScoreScreen() {
        scoreCanvas = new CanvasWindow("Stats!", 155, 80);
    }

    public ScoreScreen update(GameManager game) {
        scoreCanvas.removeAll();
        scoreCanvas.add(new GraphicsText("Cards left in deck: " + game.numCardsRemaining(), 5,15));
        scoreCanvas.add(new GraphicsText("Population: " + game.getStats().get(0), 5, 45));
        scoreCanvas.add(new GraphicsText("Economy: " + game.getStats().get(1), 5, 60));
        scoreCanvas.add(new GraphicsText("Leisure: " + game.getStats().get(2), 5, 75));
        return this;
    }
}
