import java.util.List;

/**
 * GameManager for the game to run under
 */

public class ScoreTracker {

    private int econ;
    private int pop;
    private int leis;

    public ScoreTracker(int initial) {
        econ = initial;
        pop = initial;
        leis = initial;
    }

    public int getEcon() {
        return econ;
    }

    public int getPop() {
        return pop;
    }

    public int getLeis() {
        return leis;
    }

    /**
     * Returns a list of pop, econ, leisure
     * 
     * @return
     */
    public List<Integer> getStats() {
        return List.of(pop, econ, leis);
    }

    public void changeStat(Stat stat, int change) {
        System.out.println("Current " + stat + " " + getStats());
        if (stat == Stat.ECONOMY)
            econ += change;
        if (stat == Stat.POPULATION)
            pop += change;
        if (stat == Stat.LEISURE)
            leis += change;
        System.out.println("New " + stat + " " + getStats());
    }
    
}
