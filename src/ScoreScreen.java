import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class ScoreScreen {

    CanvasWindow scoreCanvas;
    
    public ScoreScreen() {
        scoreCanvas = new CanvasWindow("Scores!", 155, 80);
    }

    public ScoreScreen update(GameManager game) {
        scoreCanvas.removeAll();
        scoreCanvas.add(new GraphicsText("Cards left in deck: ", 5,15));
        scoreCanvas.add(new GraphicsText("Population: ", 5, 45));
        scoreCanvas.add(new GraphicsText("Economy: ", 5, 60));
        scoreCanvas.add(new GraphicsText("Leisure: ", 5, 75));
        return this;
    }
}
