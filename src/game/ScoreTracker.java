package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Stat;

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
        List<Double> finals = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            finals.add((double) getStats().get(i));
        }
        System.out.println("CALCULATING>>>");
        Collections.sort(finals);
        double average = (finals.get(1) + finals.get(2))/2;
        System.out.println("Averaging " + finals.get(1) + " and " + finals.get(2) + " to get " + average);
        double highAvg = average*10;
        System.out.println("Multiplying by 10 to get " + highAvg);
        double total = highAvg+finals.get(0);
        System.out.println("Adding " + finals.get(0));
        return total;
    }

}
