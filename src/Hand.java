import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hand {

    private List<Card> remainingCardList = new ArrayList<>();
    private List<Card> currentHand = new ArrayList<>();

    public Hand(Deck deck) {
        remainingCardList = deck.getCards();
    }

    /**
     * Adds n random cards to hand while removing them from the remaining cards.
     */
    public void drawCards(int n) {
        for (int i = 0; i < n; i++) {
            currentHand.add(drawCard());
        }
    }

    /**
     * Returns a random card from the deck, and removes that card from the deck
     */
    private Card drawCard() {
        Random rand = new Random();
        int rndm = rand.nextInt(remainingCardList.size());
        Card card = remainingCardList.get(rndm);
        remainingCardList.remove(rndm);
        return card;
    }

    public List<Card> getRemainingCards() {
        return remainingCardList;
    }

    public List<Card> getCurrentHand() {
        return currentHand;
    }

    public int numCardsRemaining() {
        return remainingCardList.size();
    }

}
