

public class GameManager {

    public static void main(String[] args) {
        Board board = new Board(4);
        Deck deck = new Deck();
        ScreenManager game = new ScreenManager(500, board.getBoardSize());
        Card bank = deck.getCard("Bank");
        Card park = deck.getCard("Park");
        board.addCard(bank);
        board.addCard(3, 4, park);
        game.drawCardAt(board, bank, 3, 3);
        game.drawCardAt(board, park, 3, 4);
    }

    }


