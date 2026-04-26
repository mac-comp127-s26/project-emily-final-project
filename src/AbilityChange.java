public class AbilityChange {

    private Stat stat;
    private int change;
    
    public AbilityChange(Stat stat, int change) {
        this.stat = stat;
        this.change = change;
    }

    public Stat getStat() {
        return stat;
    }

    public int getChange() {
        return change;
    }

}
