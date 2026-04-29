package ui;

import storage.Position;

public class ScreenUtils {

     /**
     * Return the (x,y) coordinate of the position (x,y) on the screen.
     */
    public static Position getMouseCoordinates(double mX, double mY, double scale) {
        double mouseX = mX / (1500 * scale);
        double mouseY = mY / (1500 * scale);
        return new Position((int) mouseX, (int) mouseY);
    }
    
}
