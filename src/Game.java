public class Game {
    
    public static void main(String[] args) {
        GameManager game = new GameManager();
        Deck deck = new Deck();
        game.placeCard(deck.getCard("Bank"));
        game.placeCard(deck.getCard("Complex"), 6, 6);
    }

}