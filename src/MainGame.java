public class MainGame {

    public static void main(String[] args) {
        GameManager game = new GameManager(500, 3);
        Deck deck = new Deck();
        Card bank = deck.getCard("Bank");
        Card park = deck.getCard("Park");
        Card complex = deck.getCard("Complex");
        game.placeCard(bank);
        game.drawBoard(game.getBoard());

        game.getBoardScreen().onClick(e -> {
            game.placeCard(park, 2, 0);
            game.drawBoard(game.getBoard());
        });

        game.getHandScreen().onClick(e -> {
            game.getHand().addCardToHand(complex);
        });
    }
}

