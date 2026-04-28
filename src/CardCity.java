import enums.IconPath;
import managers.GameRunner;

public class CardCity {

    public static void main(String[] args) {
        GameRunner game = new GameRunner(500, 4, IconPath.Basic);
        game.run();
    }
}
