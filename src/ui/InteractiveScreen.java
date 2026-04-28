package ui;

import edu.macalester.graphics.CanvasWindow;
import game.*;
import storage.Position;

public interface InteractiveScreen {
    
    public void update(Board board);
    public void update(Hand hand);

    public void addCardToScreen(Card card, int x, int y);
    public void addCardToScreen(Card card, int x);

    public CanvasWindow getScreen();

    public void placeCursor(int x, int y);
    public void placeCursor(int x);

    public double getNewScale(Board board);
    public double getNewScale(Hand hand);

    public Position getMouseCoordinates(double mX, double mY);
    //Both have the same code, so can I use this as an abstract class?
}
