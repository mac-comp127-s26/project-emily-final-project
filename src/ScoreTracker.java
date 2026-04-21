
/**
 * GameManager for the game to run under
 */

public class ScoreTracker {

    private static int econ;
    private static int pop;
    private static int leis;

    public static void initializeValues(int i) {
        ScoreTracker.econ = i;
        ScoreTracker.pop = i;
        ScoreTracker.leis = i;
    }

    public static int getEcon() {
        return econ;
    }

    public static int getPop() {
        return pop;
    }

    public static int getLeis() {
        return leis;
    }

    public static void changeStat(Stat stat, int change) {
        if (stat == Stat.ECONOMY)
            econ += change;
        if (stat == Stat.POPULATION)
            pop += change;
        if (stat == Stat.LEISURE)
            leis += change;
    }
}
