public class Card {

String name;
Type type;

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
