import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleCardTests {
    
    private Board board;

    @BeforeEach
    public void createBoard() {
        this.board = new Board(4);
    }

    @Test
    public void testAddCard() {
        board.addCard(0, 0, new Card("Bank", Type.COMMERCIAL));
        Card card = board.getCard(0,0);
        assertEquals("Bank", card.getName());
    }
}
