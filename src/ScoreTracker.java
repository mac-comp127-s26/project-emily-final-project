
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
}
