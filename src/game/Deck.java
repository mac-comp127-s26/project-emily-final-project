package game;

import java.util.ArrayList;
import java.util.List;

import enums.*;
import storage.Ability;

/**
 * A deck object that has many Cards.
 */
public class Deck {

    private List<Card> deck = new ArrayList<>();

    /**
     * Initializes every card in the game.
     */
    public Deck(IconPath path) {
        for (String n : List.of("City Hall", "Museum")) {
            deck.add(cityhallMuseum(n, path));
        }
        for (String n : List.of("Neighborhood", "Mansion")) {
            deck.add(neighborhoodMansion(n, path));
        }
        for (String n : List.of("Complex", "Airport")) {
            deck.add(complexAirport(n, path));
        }
        for (String n : List.of("Business", "Bank")) {
            deck.add(businessBank(n, path));
        }
        for (String n : List.of("Park", "Theater")) {
            deck.add(parkTheater(n, path));
        }
        for (String n : List.of("Mall", "Stadium")) {
            deck.add(mallStadium(n, path));
        }
        for (String n : List.of("Landfill", "Station")) {
            deck.add(landfillStation(n, path));
        }
        for (String n : List.of("School", "University")) {
            deck.add(schoolUniversity(n, path));
        }
        for (String n : List.of("Coffeeshop", "Bookstore")) {
            deck.add(coffeeshopBookstore(n, path));
        }
        for (String n : List.of("Rec. Center", "Hospital")) {
            deck.add(reccenterHospital(n, path));
        }
    }

    public List<Card> getCards() {
        return deck;
    }

    public Card getCard(String cardName) {
        for (Card c : deck) {
            if (c.getName() == cardName) {
                return c;
            }
        }
        return null;
    }

    /**
     * Removes the card at index i from the list of cards in the deck
     */
    public void removeCard(int i) {
        deck.remove(i);
    }

    public int getSize() {
        return deck.size();
    }

    /**
     * Stores the data for every card in the game.
     */
    private Card cityhallMuseum(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMUNITY)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(+2, Stat.ECONOMY).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(+1, Stat.POPULATION)
                .addAdjacentType(BuildingType.COMMERCIAL).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card neighborhoodMansion(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMUNITY)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.ECONOMY)
                .addChange(-1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+1, Stat.POPULATION)
                .addAdjacentType(BuildingType.COMMUNITY).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card complexAirport(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(+2, Stat.POPULATION)
                .addChange(-1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(-1, Stat.POPULATION)
                .addAdjacentType(BuildingType.COMMUNITY).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card businessBank(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-2, Stat.ECONOMY).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+3, Stat.ECONOMY).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card parkTheater(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMUNITY)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.ECONOMY)
                .addChange(+1, Stat.LEISURE).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card mallStadium(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(+2, Stat.ECONOMY)
                .addChange(+1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(-1, Stat.ECONOMY)
                .addAdjacentType(BuildingType.COMMERCIAL).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card landfillStation(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.POPULATION)
                .addChange(-1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+2, Stat.ECONOMY).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card schoolUniversity(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMUNITY)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.ECONOMY)
                .addChange(+2, Stat.LEISURE).buildAbility())
            .addAbility(
                new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+1, Stat.POPULATION).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card coffeeshopBookstore(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.ECONOMY)
                .addChange(-1, Stat.POPULATION).addChange(-1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+2, Stat.LEISURE)
                .addAdjacentType(BuildingType.COMMERCIAL).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

    private Card reccenterHospital(String name, IconPath path) {
        Card card = new Card.CardBuilder(name, BuildingType.COMMERCIAL)
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.PLACEMENT).addChange(-1, Stat.POPULATION)
                .addChange(-1, Stat.LEISURE).buildAbility())
            .addAbility(new Ability.AbilityBuilder(AbilityTrigger.ENDGAME).addChange(+1, Stat.ECONOMY)
                .addAdjacentType(BuildingType.COMMUNITY).buildAbility())
            .addIcon(name, path)
            .buildCard();
        return card;
    }

}
