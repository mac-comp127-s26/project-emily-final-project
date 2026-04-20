import java.util.ArrayList;
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
                if (newX >= 0 && newY >= 0) {
                    System.out.println("X " + x + " to " + newX + " and Y " + y + " to " + newY);
                    Card card = oldBoard.getCard(x, y);
                    if (card != null) {
                        addCard(newX, newY, card);
                    }
                }
            }
        }
    }

    /**
     * If card is not already in the array, place card at (x, y)
     */
    public void addCard(int x, int y, Card card) {
        if (!contains(card)) {
            boardArray[x][y] = card;
            card.setPosition(x, y);
            if (x > maxX)
                maxX = x;
            else if (x < minX)
                minX = x;
            if (y > maxY)
                maxY = y;
            else if (y < minY)
                minY = y;
        }
    }

    /**
     * Place card at center.
     */
    public void addCard(Card card) {
        boardArray[minX][minY] = card;
        card.setPosition(minX, minY);
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

    /**
     * Returns a list of the Types of the cards adjacent to the card at x, y
     */
    public List<Type> getAdjacentsOf(int x, int y) {
        List<Type> adjacents = new ArrayList<>();
        addTypesFrom(x, y, adjacents);
        return adjacents;
    }

    /**
     * Adds the types of the cards adjacent to (x, y) to list.
     */
    private void addTypesFrom(int x, int y, List<Type> list) {
        if (typeOf(x - 1, y) != null)
            list.add(typeOf(x - 1, y));
        if (typeOf(x + 1, y) != null)
            list.add(typeOf(x + 1, y));
        if (typeOf(x, y - 1) != null)
            list.add(typeOf(x, y - 1));
        if (typeOf(x, y + 1) != null)
            list.add(typeOf(x, y + 1));
    }

    /**
     * Returns a list of the Types of the cards adjacent to the card at x, y
     */
    public List<Type> getAdjacentsOf(double x, double y) {
        int newX = (int) x;
        int newY = (int) y;
        return getAdjacentsOf(newX, newY);

    }

    /**
     * Returns the type of the card at (x, y)
     */
    private Type typeOf(int x, int y) {
        if (inBounds(x, y) && getCard(x, y) != null) {
            return getCard(x, y).getType();
        } else
            return null;
    }

    /**
     * Return if (x, y) is in bounds of the array.
     */
    private boolean inBounds(int x, int y) {
        if (x >= 0 && x < boardArray.length && y >= 0 && y < boardArray[0].length)
            return true;
        else
            return false;
    }

    /**
     * Returns whether or not @param card is in the array.
     */
    private boolean contains(Card card) {
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                if (boardArray[i][j] == card)
                    return true;
            }
        }
        return false;
    }
}
