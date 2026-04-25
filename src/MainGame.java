public class MainGame {

    ScreenManager screen;
    Board board;

    public MainGame(int screenSize, int boardSize) {
        screen = new ScreenManager(screenSize);
        board = new Board(boardSize);
    }

    public void placeCard(Card card, int x, int y) {
        board.addCard(x, y, card);
        board = board.refreshBoard();
    }

    public void placeCard(Card card) {
        board.addCard(card);
        board = board.refreshBoard();
    }

    public Board getBoard() {
        return board;
    }

}
