package game;
import java.util.ArrayList;
import java.util.List;

import enums.AbilityTrigger;
import enums.BuildingType;
import enums.Stat;
import storage.Ability;
import storage.ChangeQueue;
import storage.Position;

/**
 * A Board object that holds cards.
 */
public class Board {

    private Card[][] boardArray;
    private int boardSize;
    private ScoreTracker scores;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private List<ChangeQueue> abilityChanges = new ArrayList<>();

    /**
     * Creates a board array that is big enough to hold any orientation of boardSizeXboardSize cards
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
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

    public Board(int width, int height, int boardSize) {
        this.boardSize = boardSize;
        int w = (boardSize * 2) - width;
        int h = (boardSize * 2) - height;
        boardArray = new Card[w][h];
        int doubleSize = (boardSize * 2) - 1;
        initializeValues(doubleSize);
    }

    /**
     * Make a new board with the new size and card locations
     */
    public Board refreshBoard() {
        Board newBoard = new Board(getMargins().get(2), getMargins().get(5), getBoardSize());
        newBoard.translateCards(this);
        return newBoard;
    }

    /**
     * Move all cards from oldBoard to their relative location on newBoard
     */
    private void translateCards(Board board) {
        int xDif = board.getArrayWidth() - getArrayWidth();
        int yDif = board.getArrayHeight() - getArrayHeight();
        boolean changeX = (getMargins().get(1) < board.getMargins().get(1));
        boolean changeY = (getMargins().get(4) < board.getMargins().get(4));
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x,y)) {
                    int destX = x;
                    int destY = y;
                    if (changeX) destX -= xDif;
                    if (changeY) destY -= yDif;
                    addCard(destX, destY, board.getCard(x,y));
                }
            }
        }
    }

    /**
     * Return if there is a card at (x,y) on the board
     */
    public boolean hasCard(int x, int y) {
        return (getCard(x, y) != null);
    }

    /**
     * Return if the given card exists on the board
     */
    public boolean hasCard(Card card) {
        for (int x = 0; x < boardArray.length; x++) {
            for (int y = 0; y < boardArray[0].length; y++) {
                if (boardArray[x][y] == card) {
                    return true;
                }
            }
        } return false;
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
        if (x > maxX) {
            maxX = x;
        } else if (x < minX)
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

    /**
     * Returns card at (x, y)
     */
    public Card getCard(int x, int y) {
        if (boardArray[x][y] != null)
            return boardArray[x][y];
        else
            return null;
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

    public void queueStat(Stat stat, int change) {
        abilityChanges.add(new ChangeQueue(stat, change));
    }

    /**
     * Return a list of AbilityChanges that will affect stats.
     */
    public List<ChangeQueue> getAbilityChanges() {
        return abilityChanges;
    }

    /**
     * Returns the type of the card at (x, y)
     */
    private BuildingType typeOf(int x, int y) {
        if (inBounds(x, y) && hasCard(x, y)) {
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
     * Returns true if every space in the board is occupied, or otherwise false
     */
    public boolean isFull() {
        for (int x = 0; x < boardArray.length; x++) {
            for (int y = 0; y < boardArray[0].length; y++) {
                if (boardArray[x][y] == null)
                    return false;
            }
        }
        return true;
    }

    public ScoreTracker getScoreboard() {
        return scores;
    }

    /**
     * Activate all abilities of @param trigger for @param card.
     */
    public void activateAbility(Card card, AbilityTrigger trigger) {
        abilityChanges.clear();
        for (Ability i : card.getAbility(trigger)) {
            if (i.getTrigger() == trigger) {
                if (i.getAdjacentType() != null) {
                    for (int a = 0; a < i.getNumChanges(); a++) {
                        int num = getAdjacentsOfType(i.getAdjacentType(), card.getPos().getX(), card.getPos().getY());
                        queueStat(i.getStat(a), i.getChange(a) * num);
                    }
                } else {
                    for (int b = 0; b < i.getNumChanges(); b++) {
                        queueStat(i.getStat(b), i.getChange(b));
                    }
                }
            }
        }
    }
}