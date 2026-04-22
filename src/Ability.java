public class Ability {

    private BuildingType type;
    private int val;
    private Stat stat;
    private AbilityTrigger trigger;

    public Ability(AbilityTrigger trigger, int change, Stat stat, BuildingType adjacentType) {
        this.type = adjacentType;
        this.val = change;
        this.stat = stat;
        this.trigger = trigger;
    }

    public Ability(AbilityTrigger trigger, int val, Stat stat) {
        this.val = val;
        this.stat = stat;
        this.trigger = trigger;
        type = null;
    }

    /**
     * Return the type of adjacent card that matters for the ability.
     * 
     * @return
     */
    public BuildingType getAdjacentType() {
        return type;
    }

    /**
     * Return the name of the adjacent card type that matters for the ability.
     * 
     * @return
     */
    public String getAdjacentTypeName() {
        if (type == BuildingType.COMMERCIAL)
            return "Commercial";
        else if (type == BuildingType.COMMUNITY)
            return "Community";
        return null;
    }

    /**
     * Return the value of change per adjacent card of the right type.
     * 
     * @return
     */
    public int getChange() {
        return val;
    }

    /**
     * Return the stat that changes per adjacent card of the right type.
     * 
     * @return
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Returns the trigger that activates the ability.
     */
    public AbilityTrigger getTrigger() {
        return trigger;
    }
}
