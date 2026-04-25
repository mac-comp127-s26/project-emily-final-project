import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class HandScreen {

    private double scale;
    private CanvasWindow handCanvas;
    private double cardSize;
    
    public HandScreen(int size) {
        scale = (0.00011111111*size);
        cardSize = 1500*scale;
        System.out.println("Card size: " + cardSize);
        handCanvas = new CanvasWindow("Hand!", (int) cardSize*6, (int) cardSize);
    }

    public void addCardToHand(Card card, int pos) {
        System.out.println(getNewScale());
        scale = getNewScale();
        cardSize = scale*1500;
        GraphicsObject icon = card.getIcon();
        icon.setScale(scale);
        icon.setCenter((pos*cardSize)+(cardSize/2), 0+(cardSize/2));
        handCanvas.add(icon);
    }

    public CanvasWindow getScreen() {
        return handCanvas;
    }

    private double getNewScale() {
        double w = 0.00011111111 * handCanvas.getWidth();
        double h = 0.00066666666 * handCanvas.getHeight();
        if (w < h) return w;
        else return h;
    }

}
