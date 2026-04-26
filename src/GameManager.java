import java.util.List;

public class GameManager {

    private BoardScreen boardScreen;
    private HandScreen handScreen;
    private ScoreScreen scoreScreen;
    private Board board;
    private Hand hand;

    private ScoreTracker scores = new ScoreTracker(1);

    public GameManager(int screenSize, int boardSize) {
        Deck deck = new Deck();
        board = new Board(boardSize);
        hand = new Hand(deck);
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
        activateAbility(card, AbilityTrigger.PLACEMENT);
        Board newBoard = board.refreshBoard();
        setBoard(newBoard);
    }

    public void placeCard(Card card) {
        board.addCard(card);
        activateAbility(card, AbilityTrigger.PLACEMENT);
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
                    boardScreen.addCardtoScreen(board.getCard(x, y), x, y);
                }
            }
        }
    }

    public void activateAbility(Card card, AbilityTrigger trigger) {
        board.activateAbility(card, trigger);
        List<AbilityChange> changes = board.getAbilityChanges();
        for (AbilityChange change : changes) {
            scores.changeStat(change.getStat(), change.getChange());
        }
    }

    public List<Integer> getStats() {
        return scores.getStats();
    }

    public void drawScoreScreen() {
        scoreScreen.update(this);
    }
}