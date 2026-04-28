package ui;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import game.*;
import storage.Position;

public abstract class InteractiveScreen {

    private CanvasWindow canvas; 
    private double scale;
    private Rectangle cursor;
    
    public abstract void update(Board board);
    public abstract void update(Hand hand);

    public abstract void addCardToScreen(Card card, int x, int y);
    public abstract void addCardToScreen(Card card, int x);

    public abstract CanvasWindow getScreen();

    public abstract void placeCursor(int x, int y);
    public abstract void placeCursor(int x);

    public abstract double getNewScale(Board board);
    public abstract double getNewScale(Hand hand);

    /**
     * Return the (x,y) coordinate of the position (x,y) on the screen.
     */
    public Position getMouseCoordinates(double mX, double mY) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }
}
