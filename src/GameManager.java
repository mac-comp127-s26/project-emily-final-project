

public class GameManager {

    public static void main(String[] args) {
        Board board = new Board(4);
        Deck deck = new Deck();
        ScreenManager game = new ScreenManager(500, board.getBoardSize());
        Card bank = deck.getCard("Bank");
        Card park = deck.getCard("Park");
        Card complex = deck.getCard("Complex");
        board.addCard(0,0,bank);
        board.addCard(0,1, complex);
        board.addCard(1,1, park);
        game.drawCardAt(board, bank);
        game.drawCardAt(board, park);
        game.drawCardAt(board, complex);
    }

    }


