package game;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import enums.*;
import storage.*;

/**
 * Card object
 */
public class Card {

    private String name;
    private BuildingType type;
    private List<Ability> abilities = new ArrayList<>();
    private GraphicsObject icon;
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
        private GraphicsObject icon;

        public CardBuilder(String name, BuildingType type) {
            this.name = name;
            this.type = type;
        }

        public CardBuilder addAbility(Ability ability) {
            abilities.add(ability);
            return this;
        }

        public CardBuilder addIcon(String cardName, IconPath path) {
            String pathToImage = "Cards" + path + "/" + cardName + ".png";
            icon = new Image(pathToImage);
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
        this.icon = cardBuilder.icon;
    }

    public String getDescription() {
        String res = "";
        for (int p = 0; p < numAbilitiesWithTrigger(AbilityTrigger.PLACEMENT); p++) {
            res+= getDescriptionFor(AbilityTrigger.PLACEMENT, p) + "\n";
        }
        for (int e = 0; e < numAbilitiesWithTrigger(AbilityTrigger.ENDGAME); e++) {
            res+= getDescriptionFor(AbilityTrigger.ENDGAME, e) + "\n";
        }
        return res;
    }

    /**
     * Returns the description of the i ability with trigger trigger.
     * @param trigger
     * @param i
     * @return
     */
    private String getDescriptionFor(AbilityTrigger trigger, int i) {
        String starter = "";
        if (trigger == AbilityTrigger.PLACEMENT)
            starter = "When placed, ";
        if (trigger == AbilityTrigger.ENDGAME)
            starter = "At end of game, ";
        String desc = "";
        if (abilityWithTrigger(trigger, i) != null) {
            desc += starter;
            Ability ab = abilityWithTrigger(trigger, i);
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
     * Returns the i ability of card with trigger @param trig
     */
    private Ability abilityWithTrigger(AbilityTrigger trig, int i) {
        int n = -1;
        for (Ability ab : abilities) {
            if (ab.getTrigger() == trig) {
                n+= 1;
            }
            if (n == i) {
                return ab;
            }
        } return null;
    }

    public int numAbilitiesWithTrigger(AbilityTrigger trig) {
        int n = 0;
        for (Ability ab : abilities) {
            if (ab.getTrigger() == trig) {
                n += 1;
            }
        }
        return n;
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

    public BuildingType getType() {
        return type;
    }

    public String getTypeName() {
        if (type == BuildingType.COMMERCIAL) return "Commercial";
        else return "Community";
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getPos() {
        return new Position(x, y);
    }

    public GraphicsObject getIcon() {
        return icon;
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
}
