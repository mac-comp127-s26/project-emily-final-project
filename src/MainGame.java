public class MainGame {

    Card selectedCard;

    public static void main(String[] args) {
        GameManager game = new GameManager(500, 3);
        Card card1 = game.drawCards(1).get(0);
        System.out.println(game.numCardsRemaining());
        game.placeCard(card1);
        game.drawBoard(game.getBoard());

        game.getBoardScreen().getScreen().onClick(e -> {
            // game.placeCard(card2, 4, 4);
            game.drawBoard(game.getBoard());
        });
    }

   
}

