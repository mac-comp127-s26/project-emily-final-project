import edu.macalester.graphics.CanvasWindow;

public class GameManager {

    BoardScreen boardScreen;
    HandScreen handScreen;
    Board board;
    Hand hand;

    public GameManager(int screenSize, int boardSize) {
        boardScreen = new BoardScreen(screenSize);
        handScreen = new HandScreen(screenSize);
        board = new Board(boardSize);
        hand = new Hand(new Deck());
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

    public BoardScreen getBoardScreen() {
        return boardScreen;
    }

    public HandScreen getHandScreen() {
        return handScreen;
    }

    public void drawBoard(Board board) {
        boardScreen.clear();
        boardScreen.getNewScale(board);
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x, y)) {
                    boardScreen.addCardtoScreen(board.getCard(x,y), x, y);
                }
            }
        }
    }

}
