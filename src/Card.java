import java.util.ArrayList;
import java.util.List;

/**
 * Card object
 */
public class Card {

    private String name;
    private BuildingType type;
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

        public CardBuilder addAbility(Ability ability) {
            abilities.add(ability);
            return this;
        }

        public Card buildCard() {
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
        for (int i = 0; i < numAbilitiesWithTrigger(trigger); i++) {
            desc += starter;
            Ability ab = abilityWithTrigger(trigger);
            if (ab.getAdjacentType() == null) {
                if (ab.getNumChanges() > 1) {
                    for (int x = 0; x < ab.getNumChanges() - 1; x++) {
                        desc += posSignOf(ab.getChange(x)) + ab.getChange(x) + statToString(ab.getStat(x)) + " and ";
                    }
                    desc += posSignOf(ab.getChange(ab.getNumChanges() - 1)) + ab.getChange(ab.getNumChanges() - 1)
                        + statToString(ab.getStat(ab.getNumChanges() - 1)) + ". ";
                } else {
                    desc += posSignOf(ab.getChange(0)) + ab.getChange(0) + statToString(ab.getStat(0)) + ". ";
                }
            } else {
                if (ab.getNumChanges() > 1) {
                    for (int x = 0; x < ab.getNumChanges() - 1; x++) {
                        desc += posSignOf(ab.getChange(x)) + ab.getChange(x) + statToString(ab.getStat(x))
                            + " per adjacent " + ab.getAdjacentTypeName() + " building and ";
                    }
                    desc += posSignOf(ab.getChange(ab.getNumChanges() - 1)) + ab.getChange(ab.getNumChanges() - 1)
                        + statToString(ab.getStat(ab.getNumChanges() - 1))
                        + " per adjacent " + ab.getAdjacentTypeName() + " building.";
                } else {
                    desc += posSignOf(ab.getChange(0)) + ab.getChange(0) + statToString(ab.getStat(0))
                        + " per adjacent " + ab.getAdjacentTypeName() + " building.";
                }
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
     * Trigger the @param trigger ability of the card and change scores on the scoretracker @param
     * scores.
     */
    public void activateAbility(AbilityTrigger trigger, Board board) {
        for (Ability i : abilities) {
            if (i.getTrigger() == trigger) {
                if (i.getAdjacentType() != null) {
                    for (int a = 0; a < i.getNumChanges(); a++) {
                        int num = board.getAdjacentsOfType(i.getAdjacentType(), getPos().getX(), getPos().getY());
                        board.changeStat(i.getStat(a), i.getChange(a) * num);
                    }
                } else {
                    for (int b = 0; b < i.getNumChanges(); b++) {
                        board.changeStat(i.getStat(b), i.getChange(b));
                    }
                }

            }
        }
    }
}
