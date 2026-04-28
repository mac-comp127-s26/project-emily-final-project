package storage;
import enums.Stat;

public class ChangeQueue {

    private Stat stat;
    private int change;
    
    public ChangeQueue(Stat stat, int change) {
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
