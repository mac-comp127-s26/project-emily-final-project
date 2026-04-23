

public class GameManager {

    public static void main(String[] args) {
    ScoreTracker scores = new ScoreTracker(1);
    BoardManager boardManager = new BoardManager();
    Board board = new Board(3, scores, boardManager);
    Deck deck = new Deck(board);
    Deck deck2 = new Deck(board);
    Deck deck3 = new Deck(board);
    Card bank = deck.getCard("Bank");
    Card bank2 = deck2.getCard("Bank");
    Card bank3 = deck3.getCard("Bank");
    Card park = deck.getCard("Park");
    Card park2 = deck2.getCard("Park");
    Card park3 = deck3.getCard("Park");
    Card complex1 = deck.getCard("Complex");
    Card complex2 = deck2.getCard("Complex");
    Card complex3 = deck3.getCard("Complex");

        board.addCard(0,0,bank2);
        board.addCard(0,1,bank3);
        board.addCard(0,2,park);
        board.addCard(0,3,complex1);
        board.addCard(1,0, park2);
        board.addCard(1,1, park3);
        board.addCard(1,2, park2);
        board.addCard(2,0, complex2);
        board.addCard(2, 1, bank);
        System.out.println(board.isFull());
        board.addCard(2,2, park2);
        System.out.println(board.isFull());
    }

    }


