import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Card object
 */
public class Card {

    private String name;
    private Type type;
    private Map<Trigger, Ability> abilities = new HashMap<>();
    private int x;
    private int y;

    /**
     * @param name Name of card
     * @param type Type of card
     */
    public Card(String name, Type type) {
        this.type = type;
        this.name = name;
    }

    /**
     * Stores an ability that triggers on @param trigger that adds/subtracts @param val to @param stat
     * per adjacent @param triggerType
     */
    public void addAbility(Trigger trigger, Type triggerType, int val, Stat stat) {
        Ability effect = new Ability(triggerType, val, stat);
        abilities.put(trigger, effect);
    }

    /**
     * Stores an ability that adds/subtracts @param val to @param stat on @param trigger.
     */
    public void addAbility(Trigger trigger, int val, Stat stat) {
        Ability effect = new Ability(val, stat);
        abilities.put(trigger, effect);
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

    public Position getPos() {
        return new Position(x, y);
    }

    public Ability getAbility(Trigger trigger) {
        return abilities.get(trigger);
    }

    /**
     * Return the number of @param typeGoal in @param listTypes
     */
    private int countAdjacents(Type typeGoal, List<Type> listTypes) {
        int res = 0;
        for (Type i : listTypes) {
            if (i == typeGoal)
                res += 1;
        }
        return res;
    }

    public int getAdjacentsOfType(Type typeGoal, Board board) {
        return countAdjacents(typeGoal, board.getAdjacentsOf(x, y));
    }

    public void whenPlaced() {
        if (abilities.containsKey(Trigger.PLACEMENT)) { 
        }
    }
}
