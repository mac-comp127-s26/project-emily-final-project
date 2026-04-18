/**
 * Card object
 */
public class Card {

    private String name;
    private String description;
    private Type type;
    
    public Card(String name, Type type, String description) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
