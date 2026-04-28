import enums.IconPath;
import managers.MouseRunner;

public class CardCity {

    public static void main(String[] args) {
        MouseRunner game = new MouseRunner(500, 4, IconPath.Basic);
        game.run();
    }
}
