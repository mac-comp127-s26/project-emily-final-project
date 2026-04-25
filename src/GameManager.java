public class GameManager {

    public static void main(String[] args) {
        MainGame game = new MainGame(600, 3);
        Deck deck = new Deck();
        game.getBoard().addCard(deck.getCard("Bank"));
        game.getBoard().addCard(4,4,deck.getCard("Park"));
        game.getBoard().refreshBoard();
        // Deck deck = new Deck();
        // game.placeCard(deck.getCard("Bank"));
        // game.placeCard(deck.getCard("Park"), 3, 3);
    }

    }


