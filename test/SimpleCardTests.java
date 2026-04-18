import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleCardTests {
    
    private Board board;

    @BeforeEach
    public void createBoard() {
        this.board = new Board(3);
    }
    Card bank = new Card("Bank", Type.COMMERCIAL);
    Card park = new Card("Park", Type.COMMUNITY);

    @Test
    public void testAddCard() {
        board.addCard(bank);
        Card card = board.getCard(2,2);
        assertEquals("Bank", card.getName());
    }

    @Test
    public void testInitialMargins() {
        assertEquals(List.of(2,2,1,2,2,1), board.getMargins());
    }

    @Test
    public void testXMarginOnly() {
        board.addCard(2, 2, bank);
        board.addCard(3, 2, bank);
        assertEquals(List.of(2,3,2,2,2,1), board.getMargins());
    }

    @Test
    public void testYMarginOnly() {
        board.addCard(2, 2, bank);
        board.addCard(2, 4, bank);
        assertEquals(List.of(2,2,1,2,4,3), board.getMargins());
    }

    @Test
    public void testBothMargins() {
        board.addCard(2, 2, bank);
        board.addCard(1, 4, bank);
        assertEquals(List.of(1,2,2,2,4,3), board.getMargins());
    }

    @Test
    public void testMarginOn0() {
        board.addCard(bank);
        board.addCard(0, 0, bank);
        assertEquals(List.of(0,2,3,0,2,3), board.getMargins());
    }

    @Test
    public void testMarginOn0and1() {
        board.addCard(2, 2, bank);
        board.addCard(0, 0, bank);
        assertEquals(List.of(0,2,3,0,2,3), board.getMargins());
    }

    @Test
    public void newBoardSizeSame() {
        Board newBoard = board.updateBoard();
        assertEquals(3, newBoard.getBoardSize());
    }

    @Test
    public void newBoardDimensionsSame() {
        Board newBoard = board.updateBoard();
        assertEquals(5, newBoard.getArrayHeight());
        assertEquals(5, newBoard.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetToMax() {
        board.addCard(bank);
        board.addCard(4,4,bank);
        Board newBoard = board.updateBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(3, newBoard.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetSmall() {
        board.addCard(bank);
        board.addCard(3,0,bank);
        Board newBoard = board.updateBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(4, newBoard.getArrayWidth());
    }

    @Test
    public void moveTwoCards() {
        board.addCard(bank);
        board.addCard(3,4,park);
        Board newBoard = board.updateBoard();
        assertEquals("Bank", board.getCard(2,2).getName());
        assertEquals("Park", board.getCard(3,4).getName());
        assertEquals("Park", newBoard.getCard(2,2).getName());
        assertEquals("Bank", newBoard.getCard(1,0).getName());
    }

    @Test
    public void newBoardRecursion() {
        board.addCard(bank);
        board.addCard(3,4,park);
        board = board.updateBoard();
        assertEquals("Park", board.getCard(2,2).getName());
        assertEquals("Bank", board.getCard(1,0).getName());
    }
}
