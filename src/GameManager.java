public class GameManager {

    private Board board;
    private ScreenManager screen;
    
    public GameManager() {
        board = new Board(4);
        screen = new ScreenManager(750);
    }

    public void placeCard(Card card, int x, int y) {
        board.addCard(x, y, card);
        board.refreshBoard();
        screen.drawScreen(board);
    }

    public void placeCard(Card card) {
        board.addCard(card);
        board.refreshBoard();
        screen.drawScreen(board);
    }

    }


