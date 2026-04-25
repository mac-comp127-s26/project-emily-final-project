public class MainGame {

    ScreenManager screen;
    Board board;

    public MainGame(int screenSize, int boardSize) {
        screen = new ScreenManager(screenSize);
        board = new Board(boardSize);
    }

    public void placeCard(Card card, int x, int y) {
        board.addCard(x, y, card);
        System.out.println(board.getCard(x,y).getName());
        Board newBoard = board.refreshBoard();
        setBoard(newBoard);
    }

    public void placeCard(Card card) {
        board.addCard(card);
        System.out.println(board.getCard(board.getMargins().get(0),board.getMargins().get(3)).getName());
        Board newBoard = board.refreshBoard();
        setBoard(newBoard);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void drawBoard(Board board) {
        screen.clear();
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x, y)) {
                    screen.addCardtoScreen(board.getCard(x,y), x, y);
                }
            }
        }
    }

}
