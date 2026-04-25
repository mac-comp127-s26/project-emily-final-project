public class MainGame {

    Card selectedCard;

    public static void main(String[] args) {
        GameManager game = new GameManager(500, 3);
        Deck deck = new Deck();
        Card bank = deck.getCard("Bank");
        Card park = deck.getCard("Park");
        game.placeCard(bank);
        game.drawBoard(game.getBoard());
        game.placeCard(park, 4, 4);
        game.drawBoard(game.getBoard());

        game.getBoardScreen().getScreen().onClick(e -> {
            System.out.println("X :" + game.getBoardScreen().getMouseCoordinates(game.getBoard()).getX());
            System.out.println("Y :" + game.getBoardScreen().getMouseCoordinates(game.getBoard()).getY());
        });
    }


   
}

