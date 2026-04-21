public class GameManager {

    public static void main(String[] args) {
        CardManager deck = new CardManager();
        deck.generateCards();
        System.out.println(deck.getCardNames());
    }

}

