import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {

    private Deck deck;
    private List<Card> handArray = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    /**
     * Adds n random cards from the deck to the list of cards while removing them from the deck.
     */
    public void drawCards(int n) {
        for (int i = 0; i < n; i++) {
            drawCard();
        }
    }

    /**
     * Returns a random card from the deck, and removes that card from the deck
     */
    private Card drawCard() {
        Random rand = new Random();
        int rndm = rand.nextInt(deck.getSize());
        Card card = deck.getCard(rndm);
        deck.removeCard(rndm);
        return card;
    }

    public List<Card> getCards() {
        return handArray;
    }

    public Deck getDeck() {
        return deck;
    }

}
