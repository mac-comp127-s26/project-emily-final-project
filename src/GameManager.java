import java.util.List;

public class GameManager {

    BoardScreen boardScreen;
    HandScreen handScreen;
    ScoreScreen scoreScreen;
    Board board;
    Hand hand;
    ScoreTracker scores;

    public GameManager(int screenSize, int boardSize) {
        Deck deck = new Deck();
        board = new Board(boardSize);
        hand = new Hand(deck);
        scores = new ScoreTracker(1);
        boardScreen = new BoardScreen(screenSize);
        handScreen = new HandScreen(screenSize);
        scoreScreen = new ScoreScreen();
    }

   public List<Card> drawCards(int n) {
        return hand.drawCards(n);
   }

   public int numCardsRemaining() {
        return hand.numCardsRemaining();
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

    public BoardScreen getBoardScreen() {
        return boardScreen;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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