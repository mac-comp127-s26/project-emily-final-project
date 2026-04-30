import enums.IconPath;
import managers.MouseRunner;

public class CardCity {
    public static void main(String[] args) {
        MouseRunner game = new MouseRunner(650, 4, IconPath.Polished);
        game.run();
    }
}
