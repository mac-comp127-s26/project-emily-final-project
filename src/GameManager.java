

public class GameManager {

    public static void main(String[] args) {
        Board board = new Board(4);
        Deck deck = new Deck();
        System.out.println(board.getScoreboard().getStats());

        Card complex = deck.getCard("Complex");
        System.out.println(complex.getAbility(AbilityTrigger.PLACEMENT).get(0).getChange(1));
        board.addCard(complex);
        System.out.println(board.getScoreboard().getStats());
    }

    }


