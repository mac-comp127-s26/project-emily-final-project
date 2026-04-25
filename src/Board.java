import java.util.ArrayList;
import java.util.List;

/**
 * Board object.
 */
public class Board {

    private Card[][] boardArray;
    private int boardSize;
    private ScoreTracker scores;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        int doubleSize = (boardSize * 2) - 1;
        scores = new ScoreTracker(1);
        boardArray = new Card[doubleSize][doubleSize];
        initializeValues(doubleSize);
    }

    private void initializeValues(int doubleSize) {
        minX = doubleSize / 2;
        maxX = doubleSize / 2;
        minY = doubleSize / 2;
        maxY = doubleSize / 2;
    }

    public Board(int width, int height, int boardSize, ScoreTracker scores) {
        this.scores = scores;
        this.boardSize = boardSize;
        int w = (boardSize * 2) - width;
        int h = (boardSize * 2) - height;
        boardArray = new Card[w][h];
    }

     /**
     * Make a new board with the new size and card locations
     */
    public Board refreshBoard() {
        Board newBoard = new Board(getMargins().get(2), getMargins().get(5), getBoardSize(), scores);
        newBoard.translateCardsFrom(this);
        return newBoard;
    }

    /**
     * Move all cards from oldBoard to their relative location on newBoard
     */
    private void translateCardsFrom(Board oldBoard) {
        System.out.println("Current width: " + oldBoard.getArrayWidth() + " New width: " + getArrayWidth());
        System.out.println("Current height: " + oldBoard.getArrayHeight() + " New height: " + getArrayHeight());
        int xDif = oldBoard.getArrayWidth() - getArrayWidth();
        int yDif = oldBoard.getArrayHeight() - getArrayHeight();
        System.out.println("xDif: " + xDif + " yDif: " + yDif);
        for (int x = 0; x < oldBoard.getArrayWidth(); x++) {
            for (int y = 0; y < oldBoard.getArrayHeight(); y++) {
                int newX = x - xDif;
                int newY = y - yDif;
                System.out.println("Checking position: " + x + "," + y);
                System.out.println("New position: " + newX + "," + newY);
                if (newX >= 0 && newY >= 0) {
                    Card card = oldBoard.getCard(x, y);
                    if (card != null) {
                        System.out.println("Moving card " + card.getName() + " from " + x + "," + y + " to " + newX + "," + newY);
                        addCard(newX, newY, card);
                    }
                }
            }
        }
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
     * If card is not already in the array, place card at (x, y)
     */
    public void addCard(int x, int y, Card card) {
            boardArray[x][y] = card;
            card.setPosition(x, y);
            activateAbility(card, AbilityTrigger.PLACEMENT);
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
        card.setPosition(minX, minY);
        activateAbility(card, AbilityTrigger.PLACEMENT);
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

    public Card[][] getCards() {
        return boardArray;
    }

    public String getCardNames() {
        String res = "";
        for (int x = 0; x < getArrayWidth(); x++) {
            for (int y = 0; y < getArrayHeight(); y++) {
                if (getCard(x, y) != null) {
                res += (getCard(x,y).getName());
            }
        }
    }
    return res;
}

    /**
     * Returns card at (x, y)
     */
    public Card getCard(int x, int y) {
        if (boardArray[x][y] != null) return boardArray[x][y];
        else return null;
    }

    public Position getMaxes() {
        return new Position(maxX, maxY);
    }

    /**
     * Returns a list of the Types of the cards adjacent to the card at x, y
     */
    private List<BuildingType> getAdjacentsOf(int x, int y) {
        List<BuildingType> adjacents = new ArrayList<>();
        if (typeOf(x - 1, y) != null)
            adjacents.add(typeOf(x - 1, y));
        if (typeOf(x + 1, y) != null)
            adjacents.add(typeOf(x + 1, y));
        if (typeOf(x, y - 1) != null)
            adjacents.add(typeOf(x, y - 1));
        if (typeOf(x, y + 1) != null)
            adjacents.add(typeOf(x, y + 1));
        return adjacents;
    }

     /**
     * Return the number of @param typeGoal in @param listTypes
     */
    private int countAdjacents(BuildingType typeGoal, List<BuildingType> listTypes) {
        int res = 0;
        for (BuildingType i : listTypes) {
            if (i == typeGoal)
                res += 1;
        }
        return res;
    }

      /**
     * Return the number of @param typeGoal cards adjacent to the card at (x, y)
     */
    public int getAdjacentsOfType(BuildingType typeGoal, int x, int y) {
        return countAdjacents(typeGoal, getAdjacentsOf(x, y));
    }

    public void changeStat(Stat stat, int change) {
        scores.changeStat(stat, change);
    }

    /**
     * Returns the type of the card at (x, y)
     */
    private BuildingType typeOf(int x, int y) {
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

    /**
     * Returns true if every space in the board is occupied, or otherwise false
     */
    public boolean isFull() {
        for (int x = 0; x < boardArray.length; x++) {
            for (int y = 0; y < boardArray[0].length; y++) {
                if (boardArray[x][y] == null) return false;
            }
        } return true;
    }

    public ScoreTracker getScoreboard() {
        return scores;
    }

    /**
     * Activate all abilities of @param trigger for @param card.
     */
    public void activateAbility(Card card, AbilityTrigger trigger) {
        for (Ability i : card.getAbility(trigger)) {
            if (i.getTrigger() == trigger) {
                if (i.getAdjacentType() != null) {
                    for (int a = 0; a < i.getNumChanges(); a++) {
                        int num = getAdjacentsOfType(i.getAdjacentType(), card.getPos().getX(), card.getPos().getY());
                        changeStat(i.getStat(a), i.getChange(a) * num);
                    }
                } else {
                    for (int b = 0; b < i.getNumChanges(); b++) {
                        changeStat(i.getStat(b), i.getChange(b));
                    }
                }

            }
        }
    }
}
