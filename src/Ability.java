public class Ability {

    private Type type;
    private int val;
    private Stat stat;
    
    public Ability(Type type, int val, Stat stat) {
        this.type = type;
        this.val = val;
        this.stat = stat;
    }

    public Ability(int val, Stat stat) {
        this.val = val;
        this.stat = stat;
    }

    public Type getTriggerType() {
        return type;
    }

    public int getValue() {
        return val;
    }

    public Stat getStat() {
        return stat;
    }

}
