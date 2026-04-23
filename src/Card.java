import java.util.ArrayList;
import java.util.List;

/**
 * Card object
 */
public class Card {

    private String name;
    private BuildingType type;
    private Board board;
    private List<Ability> abilities = new ArrayList<>();
    private int x;
    private int y;

    /**
     * @param name Name of card
     * @param type Type of card
     */

public static class CardBuilder {
    private final String name;
    private final BuildingType type;
    private List<Ability> abilities = new ArrayList<>();

    public CardBuilder(String name, BuildingType type) {
      this.name = name;
      this.type = type;
    }

    public CardBuilder ability(Ability ability) {
      abilities.add(ability);
      return this;
    }

    public Card build() {
      return new Card(this);
    }
  }

    private Card(CardBuilder cardBuilder) {
        this.type = cardBuilder.type;
        this.name = cardBuilder.name;
        this.abilities = cardBuilder.abilities;
    }

    public String getDescription() {
        String desc = "";
        desc += getDescriptionFor(AbilityTrigger.PLACEMENT);
        desc += getDescriptionFor(AbilityTrigger.ENDGAME);
        return desc.trim();
    }

    private String getDescriptionFor(AbilityTrigger trigger) {
        String starter = "";
        if (trigger == AbilityTrigger.PLACEMENT)
            starter = "When placed, ";
        if (trigger == AbilityTrigger.ENDGAME)
            starter = "At end of game, ";
        String desc = "";
        int num = numAbilitiesWithTrigger(trigger);
        if (num == 1) {
            Ability ab = abilityWithTrigger(trigger);
            if (ab.getAdjacentType() == null) {
                desc += starter + posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat()) + ". ";
            } else {
                desc += starter + posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat())
                    + " per adjacent " + ab.getAdjacentTypeName() + " building. ";
            }
        } else if (num > 1) {
            desc += starter;
            for (int p = 0; p < num - 1; p++) {
                Ability ab = abilities.get(p);
                if (ab.getAdjacentType() == null) {
                    desc += posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat()) + " and ";
                } else {
                    desc += posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat())
                        + " per adjacent " + ab.getAdjacentTypeName() + " building and ";
                }
            }
            Ability ab = abilities.get(num - 1);
            if (ab.getAdjacentType() == null) {
                desc += posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat()) + ". ";
            } else {
                desc += posSignOf(ab.getChange()) + ab.getChange() + statToString(ab.getStat())
                    + " per adjacent " + ab.getAdjacentTypeName() + " building. ";
            }
        }
        return desc;
    }

    /**
     * Returns the string version of the stat @param stat
     */
    private String statToString(Stat test) {
        if (test == Stat.ECONOMY)
            return " Econ";
        if (test == Stat.POPULATION)
            return " Population";
        if (test == Stat.LEISURE)
            return " Leisure";
        return "";
    }

    /**
     * Returns the first ability of card with trigger @param trig
     */
    private Ability abilityWithTrigger(AbilityTrigger trig) {
        for (Ability ab : abilities) {
            if (ab.getTrigger() == trig) {
                return ab;
            }
        }
        return null;
    }


    /**
     * @return "+" if the number is positive
     */
    private String posSignOf(int n) {
        if (n > 0)
            return "+";
        else
            return "";
    }

    private int numAbilitiesWithTrigger(AbilityTrigger trigger) {
        int t = 0;
        for (Ability ab : abilities) {
            if (ab.getTrigger() == trigger)
                t += 1;
        }
        return t;
    }

    /**
     * Stores an ability that triggers on @param trigger that adds/subtracts @param val to @param stat
     * per adjacent @param triggerType
     */
    public void addAbility(AbilityTrigger onTrigger, int change, Stat stat, BuildingType adjacentType) {
        Ability effect = new Ability.AbilityBuilder(onTrigger, change, stat).adjacentType(adjacentType).build();
        abilities.add(effect);
    }

    /**
     * Stores an ability that adds/subtracts @param val to @param stat on @param trigger.
     */
    public void addAbility(AbilityTrigger onTrigger, int change, Stat stat) {
        Ability effect = new Ability.AbilityBuilder(onTrigger, change, stat).build();
        abilities.add(effect);
    }

    public BuildingType getType() {
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
    public List<Ability> getAbility(AbilityTrigger trigger) {
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
    private int countAdjacents(BuildingType typeGoal, List<BuildingType> listTypes) {
        int res = 0;
        for (BuildingType i : listTypes) {
            if (i == typeGoal)
                res += 1;
        }
        return res;
    }

    /**
     * Return the number of @param typeGoal cards adjacent to this card
     */
    public int getAdjacentsOfType(BuildingType typeGoal) {
        return countAdjacents(typeGoal, board.getAdjacentsOf(x, y));
    }

    /**
     * Trigger the @param trigger ability of the card and change scores on the scoretracker @param
     * scores.
     */
    public void activateAbility(AbilityTrigger trigger, ScoreTracker scoreTracker) {
        for (Ability i : abilities) {
            if (i.getTrigger() == trigger) {
                if (i.getAdjacentType() != null) {
                    int num = getAdjacentsOfType(i.getAdjacentType());
                    scoreTracker.changeStat(i.getStat(), i.getChange() * num);
                } else {
                    scoreTracker.changeStat(i.getStat(), i.getChange());
                }

            }
        }
    }
}
