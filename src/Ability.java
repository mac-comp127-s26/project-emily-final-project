public class Ability {

    private Type type;
    private int val;
    private Stat stat;
    private Trigger trigger;

    public Ability(Trigger trigger, int change, Stat stat, Type adjacentType) {
        this.type = adjacentType;
        this.val = change;
        this.stat = stat;
        this.trigger = trigger;
    }

    public Ability(Trigger trigger, int val, Stat stat) {
        this.val = val;
        this.stat = stat;
        this.trigger = trigger;
    }

    /**
     * Return the type of adjacent card that matters for the ability.
     * 
     * @return
     */
    public Type getAdjacentType() {
        return type;
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
    public Trigger getTrigger() {
        return trigger;
    }
}
