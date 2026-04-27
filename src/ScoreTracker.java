import java.util.List;

/**
 * ScoreTracker to keep track of the three stats
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

    public void setStats(List<Integer> statsList) {
        econ = statsList.get(0);
        pop = statsList.get(1);
        leis = statsList.get(2);
    }

    public void changeStat(Stat stat, int change) {
        if (stat == Stat.ECONOMY)
            econ += change;
        if (stat == Stat.POPULATION)
            pop += change;
        if (stat == Stat.LEISURE)
            leis += change;
    }

    public boolean inDebt() {
        boolean debt1 = (econ < 0 && pop < 0);
        boolean debt2 = (pop < 0 && leis < 0);
        boolean debt3 = (econ < 0 && leis < 0);
        return (debt1 || debt2 || debt3);
    }

    public double finalScore() {
        List<Integer> finals = getStats();
        int lowestVal = getStats().get(0);
        for (int i : getStats()) {
            if (i < lowestVal) {
                lowestVal = i;
            }
        }
        finals.remove(lowestVal);
        int highAvg = ((finals.get(0) + finals.get(1)) / 2) * 10;
        return highAvg - lowestVal;
    }

}
