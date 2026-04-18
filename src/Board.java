import java.util.List;

/**
 * Board object.
 */
public class Board {

    private Card[][] boardArray;
    private static int boardSize;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Board(int boardSize) {
        Board.boardSize = boardSize;
        int doubleSize = (boardSize * 2) - 1;
        boardArray = new Card[doubleSize][doubleSize];
        initializeValues(doubleSize);
    }

    private void initializeValues(int doubleSize) {
        minX = doubleSize / 2;
        maxX = doubleSize / 2;
        minY = doubleSize / 2;
        maxY = doubleSize / 2;
    }

    public Board(int width, int height) {
        int w = (boardSize * 2) - width;
        int h = (boardSize * 2) - height;
        boardArray = new Card[w][h];
    }

    /**
     * Get a list of coordinates and margins/widths, used primarily for testing.
     */
    public List<Integer> getMargins() {
        int xWidth = (maxX + 1) - minX;
        int yHeight = (maxY + 1) - minY;
        List<Integer> result = List.of(minX, maxX, xWidth, minY, maxY, yHeight);
        return result;
    }

    /**
     * Make a new board with the new size and card locations
     */
    public Board updateBoard() {
        Board newBoard = new Board(getMargins().get(2), getMargins().get(5));
        newBoard.translateCardsFrom(this);
        return newBoard;
    }

    /**
     * Move all cards from oldBoard to their relative location on newBoard
     */
    private void translateCardsFrom(Board oldBoard) {
        int xDif = oldBoard.getArrayWidth() - getArrayWidth();
        int yDif = oldBoard.getArrayHeight() - getArrayHeight();
        for (int x = 0; x < oldBoard.getArrayWidth(); x++) {
            for (int y = 0; y < oldBoard.getArrayHeight(); y++) {
                int newX = x - xDif;
                int newY = y - yDif;
                Card card = oldBoard.getCard(x, y);
                if (card != null) {
                    addCard(newX, newY, card);
                }
            }
        }
    }

    /**
     * Place card at (x, y)
     */
    public void addCard(int x, int y, Card card) {
        boardArray[x][y] = card;
        if (x > maxX)
            maxX = x;
        else if (x < minX)
            minX = x;
        if (y > maxY)
            maxY = y;
        else if (y < minY)
            minY = y;
    }

    /**
     * Place card at center.
     */
    public void addCard(Card card) {
        boardArray[minX][minY] = card;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getArrayWidth() {
        return boardArray.length;
    }

    public int getArrayHeight() {
        return boardArray[0].length;
    }

    /**
     * Returns card at (x, y)
     */
    public Card getCard(int x, int y) {
        return boardArray[x][y];
    }
}
