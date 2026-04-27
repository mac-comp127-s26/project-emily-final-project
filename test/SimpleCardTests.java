import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SimpleCardTests {

    ScoreTracker scores = new ScoreTracker(1);
    Board board = new Board(3);
    Deck deck = new Deck();
    Deck deck2 = new Deck();
    Deck deck3 = new Deck();
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
        board = board.refreshBoard();
        assertEquals(3, board.getBoardSize());
    }

    @Test
    public void newBoardDimensionsSame() {
        board = board.refreshBoard();
        assertEquals(5, board.getArrayHeight());
        assertEquals(5, board.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetToMax() {
        board.addCard(bank);
        board.addCard(4, 4, bank2);
        Board newBoard = board.refreshBoard();
        assertEquals(3, newBoard.getArrayHeight());
        assertEquals(3, newBoard.getArrayWidth());
    }

    @Test
    public void newBoardDimensionsSetSmall() {
        board.addCard(bank);
        board.addCard(3, 0, bank2);
        board = board.refreshBoard();
        assertEquals(3, board.getArrayHeight());
        assertEquals(4, board.getArrayWidth());
    }

    @Test
    public void moveTwoCards() {
        board.addCard(bank);
        board.addCard(3, 4, park);
        assertEquals("Bank", board.getCard(2, 2).getName());
        assertEquals("Park", board.getCard(3, 4).getName());
        board = board.refreshBoard();
        assertEquals("Park", board.getCard(2, 2).getName());
        assertEquals("Bank", board.getCard(1, 0).getName());
    }

    @Test
    public void newBoardRecursion() {
        board.addCard(bank);
        board.addCard(3, 4, park);
board = board.refreshBoard();
        assertEquals("Park", board.getCard(2, 2).getName());
        assertEquals("Bank", board.getCard(1, 0).getName());
    }

    @Test
    public void getPositionWorksOnMovedCard() {
        board.addCard(bank);
        board.addCard(3, 4, park);
        board = board.refreshBoard();
        assertEquals(2, board.getCard(2, 2).getPos().getX());
        assertEquals(2, board.getCard(2, 2).getPos().getY());
        assertEquals(1, board.getCard(1, 0).getPos().getX());
        assertEquals(0, board.getCard(1, 0).getPos().getY());
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

    // @Test
    // public void complexAbilityWorksOnPlacement() {
    //     Card card = deck.getCard("Complex");
    //     board.activateAbility(card, AbilityTrigger.PLACEMENT);
    //     assertEquals(1, board.getScoreboard().getEcon());
    //     assertEquals(3, board.getScoreboard().getPop());
    //     assertEquals(0, board.getScoreboard().getLeis());
    // }

    // @Test
    // public void zeroAbilityWorksOnEndgame() {
    //     Card complex = deck.getCard("Complex");
    //     board.addCard(complex);
    //     assertEquals(List.of(3,1,0), board.getScoreboard().getStats());
    //     board.activateAbility(complex, AbilityTrigger.ENDGAME);
    //     assertEquals(List.of(3,1,0), board.getScoreboard().getStats());
    // }

    // @Test
    // public void statsKeepTrackAcrossPlacement() {
    //     Card complex = deck.getCard("Complex");
    //     board.addCard(complex);
    //     assertEquals(List.of(3,1,0), board.getScoreboard().getStats());
    //     board.addCard(1, 2, park);
    //     assertEquals(List.of(3,0,1), board.getScoreboard().getStats());
    //     board.addCard(3, 2, park2);
    //     assertEquals(List.of(3,-1,2), board.getScoreboard().getStats());
    //     board.addCard(2, 3, bank);
    //     assertEquals(List.of(3,-3,2), board.getScoreboard().getStats());
    //     board.activateAbility(complex, AbilityTrigger.ENDGAME);
    //     assertEquals(List.of(1,-3,2), board.getScoreboard().getStats());
    // }

    // @Test
    // public void cardWithSingleAbilityDescription() {
    //     Card landfill = deck.getCard("Landfill");
    //     assertEquals("When placed, -1 Population and -1 Leisure.\nAt end of game, +2 Econ.", landfill.getDescription());
    // }

    // @Test
    // public void cardWithComplexDescription() {
    //     Card coffee = deck.getCard("Coffeeshop");
    //     assertEquals("When placed, -1 Econ and -1 Population and -1 Leisure.", coffee.getDescription());
    // }

    // @Test
    // public void mainGameMovesCards() {
    //     GameManager game = new GameManager(600, 3);
    //     assertEquals("Bank", game.getBoard().getCard(2,2).getName());
    //     // game.placeCard(park, 4,1);
    //     // assertEquals("Park", game.getBoard().getCard(4,1).getName());
    //     // assertEquals("Bank", game.getBoard().getCard(0,2).getName());
    // }

    @Test
    public void statsCanBeRead() {
        GameManager game = new GameManager(600, 3);
        assertEquals(1, game.getScoreTracker().getEcon());
        assertEquals(1, game.getScoreTracker().getPop());
        assertEquals(1, game.getScoreTracker().getLeis());
    }

    @Test
    public void finalScoreCalculatesCorrectly() {
        GameManager game = new GameManager(600, 3);
        game.getScoreTracker().changeStat(Stat.ECONOMY, -2);
        game.getScoreTracker().changeStat(Stat.POPULATION, +4);
        assertEquals(List.of(5,-1,1), game.getScoreTracker().getStats());
        assertEquals(29, game.getScoreTracker().finalScore());
    }

    @Test
    public void scoreCheckTwo() {
        GameManager game = new GameManager(600, 3);
        game.getScoreTracker().changeStat(Stat.ECONOMY, +1);
        game.getScoreTracker().changeStat(Stat.POPULATION, +1);
        assertEquals(List.of(2,2,1), game.getScoreTracker().getStats());
        assertEquals(21, game.getScoreTracker().finalScore());
    }
}