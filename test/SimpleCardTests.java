import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SimpleCardTests {

    ScoreTracker scores = new ScoreTracker(1);
    Board board = new Board(3, scores);
    Deck deck = new Deck(board);
    Deck deck2 = new Deck(board);
    Deck deck3 = new Deck(board);
    Card bank = deck.getCard("Bank");
    Card bank2 = deck2.getCard("Bank");
    Card bank3 = deck3.getCard("Bank");
    Card park = deck.getCard("Park");
    Card park2 = deck2.getCard("Park");

    @Test
    public void testAddCard() {
        board.addCard(bank);
        Card card = board.getCard(2, 2);
        assertEquals("Bank", card.getName());
    }

    @Test
    public void testInitialMargins() {
        assertEquals(List.of(2, 2, 1, 2, 2, 1), board.getMargins());
    }

    @Test
    public void testXMarginOnly() {
        board.addCard(2, 2, bank);
        board.addCard(3, 2, bank2);
        assertEquals(List.of(2, 3, 2, 2, 2, 1), board.getMargins());
    }

    @Test
    public void testYMarginOnly() {
        board.addCard(2, 2, bank);
        board.addCard(2, 4, bank2);
        assertEquals(List.of(2, 2, 1, 2, 4, 3), board.getMargins());
    }

    @Test
    public void testBothMargins() {
        board.addCard(2, 2, bank);
        board.addCard(1, 4, bank2);
        assertEquals(List.of(1, 2, 2, 2, 4, 3), board.getMargins());
    }

    @Test
    public void testMarginOn0() {
        board.addCard(bank);
        board.addCard(0, 0, bank2);
        assertEquals(List.of(0, 2, 3, 0, 2, 3), board.getMargins());
    }

    @Test
    public void testMarginOn0and1() {
        board.addCard(2, 2, bank);
        board.addCard(0, 0, bank2);
        assertEquals(List.of(0, 2, 3, 0, 2, 3), board.getMargins());
    }

    @Test
    public void getMargins() {
        assertEquals(5, board.getArrayWidth());
        assertEquals(5, board.getArrayHeight());
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
        board.addCard(4, 4, bank2);
        Board newBoard = board.updateBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(3, newBoard.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetSmall() {
        board.addCard(bank);
        board.addCard(3, 0, bank2);
        Board newBoard = board.updateBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(4, newBoard.getArrayWidth());
    }

    @Test
    public void moveTwoCards() {
        board.addCard(bank);
        board.addCard(3, 4, park);
        Board newBoard = board.updateBoard();
        assertEquals("Bank", board.getCard(2, 2).getName());
        assertEquals("Park", board.getCard(3, 4).getName());
        assertEquals("Park", newBoard.getCard(2, 2).getName());
        assertEquals("Bank", newBoard.getCard(1, 0).getName());
    }

    @Test
    public void newBoardRecursion() {
        board.addCard(bank);
        board.addCard(3, 4, park);
        board = board.updateBoard();
        assertEquals("Park", board.getCard(2, 2).getName());
        assertEquals("Bank", board.getCard(1, 0).getName());
    }

    @Test
    public void getPositionWorksOnMovedCard() {
        board.addCard(bank);
        board.addCard(3, 4, park);
        board = board.updateBoard();
        assertEquals(2, board.getCard(2, 2).getPos().getX());
        assertEquals(2, board.getCard(2, 2).getPos().getY());
        assertEquals(1, board.getCard(1, 0).getPos().getX());
        assertEquals(0, board.getCard(1, 0).getPos().getY());
    }

    @Test
    public void neighborListIsEmpty() {
        board.addCard(bank);
        assertEquals(List.of(), board.getAdjacentsOf(bank.getPos().getX(), bank.getPos().getY()));
    }

    @Test
    public void neighborListIsCorrect() {
        board.addCard(bank);
        board.addCard(1, 2, bank2);
        board.addCard(2, 1, bank3);
        board.addCard(3, 1, park);
        board.addCard(3, 2, park2);
        assertEquals(List.of(BuildingType.COMMERCIAL, BuildingType.COMMUNITY, BuildingType.COMMERCIAL), board.getAdjacentsOf(2, 2));
    }

    @Test
    public void cardGainsTwoDifferentAbilities() {
        assertEquals(-2, bank.getAbility(AbilityTrigger.PLACEMENT).get(0).getChange());
        assertEquals(+3, bank.getAbility(AbilityTrigger.ENDGAME).get(0).getChange());
    }

    @Test
    public void cardGainsTwoAbilitiesWithSameTrigger() {
        bank.addAbility(AbilityTrigger.PLACEMENT, -1, Stat.ECONOMY);
        assertEquals(Stat.ECONOMY, bank.getAbility(AbilityTrigger.PLACEMENT).get(0).getStat());
        assertEquals(Stat.ECONOMY, bank.getAbility(AbilityTrigger.PLACEMENT).get(1).getStat());
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
        board.addCard(complex);
        complex.activateAbility(AbilityTrigger.ENDGAME, scores);
        assertEquals(1, scores.getEcon());
        assertEquals(3, scores.getPop());
        assertEquals(0, scores.getLeis());
    }

    @Test
    public void statsKeepTrackAcrossPlacement() {
        Card complex = deck.getCard("Complex");
        board.addCard(complex);
        assertEquals(List.of(3,1,0), scores.getStats());
        board.addCard(1, 2, park);
        assertEquals(List.of(3,0,1), scores.getStats());
        board.addCard(3, 2, park2);
        assertEquals(List.of(3,-1,2), scores.getStats());
        board.addCard(2, 3, bank);
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

 // WRITE TEST TO CHECK BOARDFULL FUNCTION
    
}

// BUILDER PATTERN FOR CARD ABILITIES