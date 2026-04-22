import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck = new ArrayList<>();
    private Board board;

    public Deck(Board board) {
        this.board = board;
        for (String n : List.of("City Hall", "Museum")) {
            deck.add(cityhallMuseum(n));
        }
        for (String n : List.of("Neighborhood", "Mansion")) {
            deck.add(neighborhoodMansion(n));
        }
        for (String n : List.of("Complex", "Airport")) {
            deck.add(complexAirport(n));
        }
        for (String n : List.of("Business", "Bank")) {
            deck.add(businessBank(n));
        }
        for (String n : List.of("Park", "Theater")) {
            deck.add(parkTheater(n));
        }
        for (String n : List.of("Mall", "Stadium")) {
            deck.add(mallStadium(n));
        }
        for (String n : List.of("Landfill", "Station")) {
            deck.add(landfillStation(n));
        }
        for (String n : List.of("School", "University")) {
            deck.add(schoolUniversity(n));
        }
        for (String n : List.of("Coffeeshop", "Bookstore")) {
            deck.add(coffeeshopBookstore(n));
        }
        for (String n : List.of("Rec. Center", "Hospital")) {
            deck.add(reccenterHospital(n));
        }
    }


    /**
     * Returns a list of n random cards from deck
     */
    public List<Card> drawCards(int n) {
        List<Card> result = new ArrayList<>(deck);
        List<Card> cards = new ArrayList<>(deck);
        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            int rndm = rand.nextInt(cards.size());
            result.add(cards.get(rndm));
            cards.remove(rndm);
        }
        return result;
    }

    public List<Card> getCards() {
        return deck;
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public Card getCard(String cardName) {
        for (Card c : deck) {
            if (c.getName() == cardName) {
                return c;
            }
        }
        return null;
    }

    public int getSize() {
        return deck.size();
    }

    private Card cityhallMuseum(String name) {
        Card card = new Card(name, Type.COMMUNITY, board);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card neighborhoodMansion(String name) {
        Card card = new Card(name, Type.COMMUNITY, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card complexAirport(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, -1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card businessBank(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, -2, Stat.ECONOMY);
        card.addAbility(Trigger.ENDGAME, +3, Stat.ECONOMY);
        return card;
    }

    private Card parkTheater(String name) {
        Card card = new Card(name, Type.COMMUNITY, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.LEISURE);
        return card;
    }

    private Card mallStadium(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.ECONOMY, Type.COMMERCIAL);
        return card;
    }

    private Card landfillStation(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +2, Stat.ECONOMY);
        return card;
    }

    private Card schoolUniversity(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.POPULATION);
        return card;
    }

    private Card coffeeshopBookstore(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.LEISURE, Type.COMMERCIAL);
        return card;
    }

    private Card reccenterHospital(String name) {
        Card card = new Card(name, Type.COMMERCIAL, board);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.ECONOMY, Type.COMMUNITY);
        return card;
    }

}
