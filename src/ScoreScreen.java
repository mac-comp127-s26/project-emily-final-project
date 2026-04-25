import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class ScoreScreen {

    CanvasWindow scoreCanvas;
    
    public ScoreScreen() {
        scoreCanvas = new CanvasWindow("Scores!", 155, 80);
    }

    public ScoreScreen update(ScoreTracker scores, Deck deck) {
        scoreCanvas.removeAll();
        scoreCanvas.add(new GraphicsText("Cards left in deck: " + deck.getSize(),5,15));
        scoreCanvas.add(new GraphicsText("Population: " + scores.getPop(), 5, 45));
        scoreCanvas.add(new GraphicsText("Economy: " + scores.getEcon(), 5, 60));
        scoreCanvas.add(new GraphicsText("Leisure: " + scores.getLeis(), 5, 75));
        return this;
    }
}
