import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {

    private List<Card> handArray = new ArrayList<>();

    public Hand(Deck deck) {
        handArray = deck.getCards();
    }

    /**
     * Adds n random cards from the deck to a list of cards while removing them from the deck.
     */
    public List<Card> drawCards(int n) {
        List<Card> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(drawCard());
        }
        return res;
    }

    /**
     * Returns a random card from the deck, and removes that card from the deck
     */
    private Card drawCard() {
        Random rand = new Random();
        int rndm = rand.nextInt(handArray.size());
        Card card = handArray.get(rndm);
        handArray.remove(rndm);
        return card;
    }

    public List<Card> getCards() {
        return handArray;
    }

    public int numCardsRemaining() {
        return handArray.size();
    }

}
