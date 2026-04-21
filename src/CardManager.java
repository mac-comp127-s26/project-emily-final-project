import java.util.ArrayList;
import java.util.List;

public class CardManager {

    private List<Card> deck = new ArrayList<>();

    public void generateCards() {
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

    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card c : deck) {
            cardNames.add(c.getName());
        }
        return cardNames;
    }


    private Card cityhallMuseum(String name) {
        Card card = new Card(name, Type.COMMUNITY);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card neighborhoodMansion(String name) {
        Card card = new Card(name, Type.COMMUNITY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card complexAirport(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, -1, Stat.POPULATION, Type.COMMUNITY);
        return card;
    }

    private Card businessBank(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, -2, Stat.ECONOMY);
        card.addAbility(Trigger.ENDGAME, +3, Stat.ECONOMY);
        return card;
    }

    private Card parkTheater(String name) {
        Card card = new Card(name, Type.COMMUNITY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.LEISURE);
        return card;
    }

    private Card mallStadium(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.ECONOMY, Type.COMMERCIAL);
        return card;
    }

    private Card landfillStation(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +2, Stat.ECONOMY);
        return card;
    }

    private Card schoolUniversity(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, +2, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.POPULATION);
        return card;
    }

    private Card coffeeshopBookstore(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.ECONOMY);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.LEISURE, Type.COMMERCIAL);
        return card;
    }

    private Card reccenterHospital(String name) {
        Card card = new Card(name, Type.COMMERCIAL);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.POPULATION);
        card.addAbility(Trigger.PLACEMENT, -1, Stat.LEISURE);
        card.addAbility(Trigger.ENDGAME, +1, Stat.ECONOMY, Type.COMMUNITY);
        return card;
    }

}
