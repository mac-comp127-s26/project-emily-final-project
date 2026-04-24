import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class ScreenManager {
    
    // private GraphicsGroup handDisplay = new GraphicsGroup();
    private GraphicsGroup boardDisplay = new GraphicsGroup();
    private CanvasWindow canvas;
    double width;
    double boardHeight;

    public ScreenManager(int size) {
        double width = size;
        this.width = width;
        double boardHeight = size/1.5;
        this.boardHeight = boardHeight;
        boardDisplay.setScale(0.075);

        canvas = new CanvasWindow("CardCity", size, size);
        canvas.add(boardDisplay);
    }

    private void drawCardAt(Board board, Card card) {
        GraphicsObject icon = card.getIcon();
        icon.setCenter(
            (card.getPos().getX() * icon.getWidth()) + icon.getWidth()/2, 
            (card.getPos().getY() * icon.getHeight()) + icon.getHeight()/2
        );
        boardDisplay.add(card.getIcon());
    }

    public void drawScreen(Board board) {
        boardDisplay.removeAll();
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.getCard(x, y) != null) {
                    drawCardAt(board, board.getCard(x,y));
                }
            }
        }
    }

}
