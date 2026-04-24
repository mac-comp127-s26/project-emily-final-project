import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class ScreenManager {
    
    private GraphicsGroup handDisplay = new GraphicsGroup();
    private GraphicsGroup boardDisplay = new GraphicsGroup();
    private CanvasWindow canvas;
    double width;
    double boardHeight;

    public ScreenManager(int size, int boardSize) {
        double width = size;
        this.width = width;
        double boardHeight = size/1.5;
        this.boardHeight = boardHeight;
        double handHeight = size/3;
        boardDisplay.setScale(0.075);

        canvas = new CanvasWindow("CardCity", size, size);
        canvas.add(boardDisplay);
        canvas.add(handDisplay, 0, boardHeight);

        Rectangle boardOutline = new Rectangle(0,0,width,boardHeight);
        boardOutline.setStrokeWidth(3);
        boardDisplay.add(boardOutline);

        Rectangle handOutline = new Rectangle(0,0,width,handHeight);
        handOutline.setStrokeWidth(3);
        handDisplay.add(handOutline);
    }

    public void drawCardAt(Board board, Card card) {
        GraphicsObject icon = card.getIcon();
        icon.setCenter(
            (card.getPos().getX() * icon.getWidth()) + icon.getWidth()/2, 
            (card.getPos().getY() * icon.getHeight()) + icon.getHeight()/2
        );
        boardDisplay.add(card.getIcon());
    }

}
