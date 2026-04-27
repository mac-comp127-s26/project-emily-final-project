public class ChangesQueue {

    private Stat stat;
    private int change;
    
    public ChangesQueue(Stat stat, int change) {
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
