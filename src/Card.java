import java.util.List;

import edu.macalester.graphics.Point;

/**
 * Card object
 */
public class Card {

    private String name;
    private Type type;
    private int x;
    private int y;

    public Card(String name, Type type) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Point getPos() {
        return new Point(x, y);
    }

/**
 * Return the number of @param typeGoal in @param listTypes
 */
    public int countAdjacents(Type typeGoal, List<Type> listTypes) {
        int res = 0;
        for (Type i : listTypes) {
            if (i == typeGoal) res += 1;
        }
        return res;
    }
}
