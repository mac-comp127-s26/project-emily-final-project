import java.util.ArrayList;
import java.util.List;

/**
 * Card object
 */
public class Card {

    private String name;
    private Type type;
    private String description;
    private List<Ability> abilities = new ArrayList<>();
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

    public void setDescription(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Stores an ability that triggers on @param trigger that adds/subtracts @param val to @param stat
     * per adjacent @param triggerType
     */
    public void addAbility(Trigger onTrigger, int change, Stat stat, Type adjacentType) {
        Ability effect = new Ability(onTrigger, change, stat, adjacentType);
        abilities.add(effect);
    }

    /**
     * Stores an ability that adds/subtracts @param val to @param stat on @param trigger.
     */
    public void addAbility(Trigger onTrigger, int change, Stat stat) {
        Ability effect = new Ability(onTrigger, change, stat);
        abilities.add(effect);
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

    /**
     * Return all abilities of this card with trigger @param trigger
     */
    public List<Ability> getAbility(Trigger trigger) {
        List<Ability> res = new ArrayList<>();
        for (Ability abi : abilities) {
            if (abi.getTrigger() == trigger) {
                res.add(abi);
            }
        }
        return res;
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

    /**
     * Return the number of @param typeGoal cards adjacent to this card on @param board
     */
    public int getAdjacentsOfType(Type typeGoal, Board board) {
        return countAdjacents(typeGoal, board.getAdjacentsOf(x, y));
    }
}
