/**
 * Card object
 */
public class Card {

    private String name;
    private Type type;

    public Card(String name, Type type) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
