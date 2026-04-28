import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

import enums.*;
import game.*;
import managers.GameManager;

public class SimpleCardTests {

    ScoreTracker scores = new ScoreTracker(1);
    Board board = new Board(3);
    Deck deck = new Deck(IconPath.Basic);
    Deck deck2 = new Deck(IconPath.Basic);
    Deck deck3 = new Deck(IconPath.Basic);
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
    public void finalScoreCalculatesCorrectly() {
        GameManager game = new GameManager(600, 3, IconPath.Basic);
        game.getScoreTracker().changeStat(Stat.ECONOMY, -2);
        game.getScoreTracker().changeStat(Stat.POPULATION, +4);
        assertEquals(List.of(5,-1,1), game.getScoreTracker().getStats());
        assertEquals(29, game.getScoreTracker().finalScore());
    }

    @Test
    public void secondScoreCheck() {
        GameManager game = new GameManager(600, 3, IconPath.Basic);
        game.getScoreTracker().changeStat(Stat.ECONOMY, +1);
        game.getScoreTracker().changeStat(Stat.POPULATION, +1);
        assertEquals(List.of(2,2,1), game.getScoreTracker().getStats());
        assertEquals(21, game.getScoreTracker().finalScore());
    }
}