public class GameManager {

    public static void main(String[] args) {
        MainGame game = new MainGame(500, 3);
        Deck deck = new Deck();
        Card bank = deck.getCard("Bank");
        Card park = deck.getCard("Park");
        game.placeCard(bank);
        game.drawBoard(game.getBoard());
        game.placeCard(park, 4,4);
        game.drawBoard(game.getBoard());
    }

    }


