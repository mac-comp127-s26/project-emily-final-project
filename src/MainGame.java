public class MainGame {

    Card selectedCard;

    public static void main(String[] args) {
        GameManager game = new GameManager(500, 3);
        game.getHand().drawCards(1);
        Card card1 = game.getHand().getCurrentHand().get(0);
        game.placeCard(card1);
        game.visualizeBoard(game.getBoard());
        game.drawStatsScreen();

        game.getBoardScreen().getScreen().onClick(e -> {
            game.getHand().drawCards(1);
            Card card2 = game.getHand().getCurrentHand().get(1);
            game.getStatsScreen().selectCard(card2);
            game.placeCard(card2, 4, 4);
            game.visualizeBoard(game.getBoard());
            game.drawStatsScreen();
        });
    }

   
}

