import managers.MainGame;

public class GameRunner {
    
    public static void main(String[] args) {
        MainGame game = new MainGame(500, 4);
        game.run();
    }
}
