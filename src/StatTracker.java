import java.util.List;

public class StatTracker {

    private static int economy;
    private static int population;
    private static int leisure;

    /**
     * Initialize stats to start at val
     */
    public StatTracker(int val) {
        economy = val;
        population = val;
        leisure = val;
    }

    public static void changeEcon(int i) {
        economy += i;
    }

    public static void changePop(int i) {
        population += i;
    }

    public static void changeLeis(int i) {
        leisure += i;
    }

    public static List<Integer> getStats() {
        return List.of(economy, population, leisure);
    }

}
