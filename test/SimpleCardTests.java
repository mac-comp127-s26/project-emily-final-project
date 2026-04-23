import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SimpleCardTests {

    ScoreTracker scores = new ScoreTracker(1);
    BoardManager board = new BoardManager();
    Board b = new Board(3, scores, board);
    Deck deck = new Deck(board.getBoard());
    Deck deck2 = new Deck(board.getBoard());
    Deck deck3 = new Deck(board.getBoard());
    Card bank = deck.getCard("Bank");
    Card bank2 = deck2.getCard("Bank");
    Card bank3 = deck3.getCard("Bank");
    Card park = deck.getCard("Park");
    Card park2 = deck2.getCard("Park");
    Card park3 = deck3.getCard("Park");
    Card complex1 = deck.getCard("Complex");
    Card complex2 = deck2.getCard("Complex");
    Card complex3 = deck3.getCard("Complex");

    @Test
    public void testAddCard() {
        board.getBoard().addCard(bank);
        Card card = board.getBoard().getCard(2, 2);
        assertEquals("Bank", card.getName());
    }

    @Test
    public void testInitialMargins() {
        assertEquals(List.of(2, 2, 1, 2, 2, 1), board.getBoard().getMargins());
    }

    @Test
    public void testXMarginOnly() {
        board.getBoard().addCard(2, 2, bank);
        board.getBoard().addCard(3, 2, bank2);
        assertEquals(List.of(2, 3, 2, 2, 2, 1), board.getBoard().getMargins());
    }

    @Test
    public void testYMarginOnly() {
        board.getBoard().addCard(2, 2, bank);
        board.getBoard().addCard(2, 4, bank2);
        assertEquals(List.of(2, 2, 1, 2, 4, 3), board.getBoard().getMargins());
    }

    @Test
    public void testBothMargins() {
        board.getBoard().addCard(2, 2, bank);
        board.getBoard().addCard(1, 4, bank2);
        assertEquals(List.of(1, 2, 2, 2, 4, 3), board.getBoard().getMargins());
    }

    @Test
    public void testMarginOn0() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(0, 0, bank2);
        assertEquals(List.of(0, 2, 3, 0, 2, 3), board.getBoard().getMargins());
    }

    @Test
    public void testMarginOn0and1() {
        board.getBoard().addCard(2, 2, bank);
        board.getBoard().addCard(0, 0, bank2);
        assertEquals(List.of(0, 2, 3, 0, 2, 3), board.getBoard().getMargins());
    }

    @Test
    public void getMargins() {
        assertEquals(5, board.getBoard().getArrayWidth());
        assertEquals(5, board.getBoard().getArrayHeight());
    }

    @Test
    public void newBoardSizeSame() {
        board.setBoard(board.getBoard().updateBoard());
        assertEquals(3, board.getBoard().getBoardSize());
    }

    @Test
    public void newBoardDimensionsSame() {
        board.setBoard(board.getBoard().updateBoard());
        assertEquals(5, board.getBoard().getArrayHeight());
        assertEquals(5, board.getBoard().getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetToMax() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(4, 4, bank2);
        Board newBoard = board.getBoard().updateBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(3, newBoard.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetSmall() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(3, 0, bank2);
        board.setBoard(board.getBoard().updateBoard());
        assertEquals(3, board.getBoard().getArrayHeight());
        assertEquals(4, board.getBoard().getArrayWidth());
    }

    @Test
    public void moveTwoCards() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(3, 4, park);
        assertEquals("Bank", board.getBoard().getCard(2, 2).getName());
        assertEquals("Park", board.getBoard().getCard(3, 4).getName());
        board.setBoard(board.getBoard().updateBoard());
        assertEquals("Park", board.getBoard().getCard(2, 2).getName());
        assertEquals("Bank", board.getBoard().getCard(1, 0).getName());
    }

    @Test
    public void newBoardRecursion() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(3, 4, park);
        board.setBoard(board.getBoard().updateBoard());
        assertEquals("Park", board.getBoard().getCard(2, 2).getName());
        assertEquals("Bank", board.getBoard().getCard(1, 0).getName());
    }

    @Test
    public void getPositionWorksOnMovedCard() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(3, 4, park);
        board.setBoard(board.getBoard().updateBoard());
        assertEquals(2, board.getBoard().getCard(2, 2).getPos().getX());
        assertEquals(2, board.getBoard().getCard(2, 2).getPos().getY());
        assertEquals(1, board.getBoard().getCard(1, 0).getPos().getX());
        assertEquals(0, board.getBoard().getCard(1, 0).getPos().getY());
    }

    @Test
    public void neighborListIsEmpty() {
        board.getBoard().addCard(bank);
        assertEquals(List.of(), board.getBoard().getAdjacentsOf(bank.getPos().getX(), bank.getPos().getY()));
    }

    @Test
    public void neighborListIsCorrect() {
        board.getBoard().addCard(bank);
        board.getBoard().addCard(1, 2, bank2);
        board.getBoard().addCard(2, 1, bank3);
        board.getBoard().addCard(3, 1, park);
        board.getBoard().addCard(3, 2, park2);
        assertEquals(List.of(BuildingType.COMMERCIAL, BuildingType.COMMUNITY, BuildingType.COMMERCIAL), board.getBoard().getAdjacentsOf(2, 2));
    }

    @Test
    public void cardGainsTwoDifferentAbilities() {
        assertEquals(-2, bank.getAbility(AbilityTrigger.PLACEMENT).get(0).getChange(0));
        assertEquals(+3, bank.getAbility(AbilityTrigger.ENDGAME).get(0).getChange(0));
    }

    @Test
    public void cardGainsTwoAbilitiesWithSameTrigger() {
        assertEquals(Stat.ECONOMY, park.getAbility(AbilityTrigger.PLACEMENT).get(0).getStat(0));
        assertEquals(Stat.LEISURE, park.getAbility(AbilityTrigger.PLACEMENT).get(0).getStat(1));
    }

    @Test
    public void deckGenerates() {
        assertEquals(20, deck.getSize());
        assertEquals("City Hall", deck.getCard(0).getName());
    }

    @Test
    public void complexAbilityWorksOnPlacement() {
        Card card = deck.getCard("Complex");
        card.activateAbility(AbilityTrigger.PLACEMENT, scores);
        assertEquals(1, scores.getEcon());
        assertEquals(3, scores.getPop());
        assertEquals(0, scores.getLeis());
    }

    @Test
    public void zeroAbilityWorksOnEndgame() {
        Card complex = deck.getCard("Complex");
        board.getBoard().addCard(complex);
        complex.activateAbility(AbilityTrigger.ENDGAME, scores);
        assertEquals(1, scores.getEcon());
        assertEquals(3, scores.getPop());
        assertEquals(0, scores.getLeis());
    }

    @Test
    public void statsKeepTrackAcrossPlacement() {
        Card complex = deck.getCard("Complex");
        board.getBoard().addCard(complex);
        assertEquals(List.of(3,1,0), scores.getStats());
        board.getBoard().addCard(1, 2, park);
        assertEquals(List.of(3,0,1), scores.getStats());
        board.getBoard().addCard(3, 2, park2);
        assertEquals(List.of(3,-1,2), scores.getStats());
        board.getBoard().addCard(2, 3, bank);
        assertEquals(List.of(3,-3,2), scores.getStats());
        complex.activateAbility(AbilityTrigger.ENDGAME, scores);
        assertEquals(List.of(1,-3,2), scores.getStats());
    }

    @Test
    public void cardWithSingleAbilityDescription() {
        Card landfill = deck.getCard("Landfill");
        assertEquals("When placed, -1 Population and -1 Leisure. At end of game, +2 Econ.", landfill.getDescription());
    }

    @Test
    public void cardWithComplexDescription() {
        Card coffee = deck.getCard("Coffeeshop");
        assertEquals("When placed, -1 Econ and -1 Population and -1 Leisure. At end of game, +1 Leisure per adjacent Commercial building.", coffee.getDescription());
    }

    @Test
    public void checkBoardFull() {
        board.getBoard().addCard(0,0,bank2);
        board.getBoard().addCard(0,1,bank3);
        board.getBoard().addCard(0,2,park);
        board.getBoard().addCard(1,0, park2);
        board.getBoard().addCard(1,1, park3);
        board.getBoard().addCard(1,2, park2);
        board.getBoard().addCard(2,0, complex1);
        board.getBoard().addCard(2, 1, bank);
        assertFalse(board.getBoard().isFull());
        board.getBoard().addCard(2,2, park2);
        assertTrue(board.getBoard().isFull());
    }
    
}