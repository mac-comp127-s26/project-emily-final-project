public class Board {

    private Card[][] boardArray;
    private int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.boardArray = new Card[(boardSize * 2) - 1][(boardSize * 2) - 1];
    }

    /**
     * Placed card at (x, y)
     */
    public void addCard(int x, int y, Card card) {
        boardArray[x][y] = card;
    }

    public int getSize() {
        return boardSize;
    }

    /**
     * Returns card at (x, y)
     */
    public Card getCard(int x, int y) {
        return boardArray[x][y];
    }
}
