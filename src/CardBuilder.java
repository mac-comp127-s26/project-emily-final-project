public class CardBuilder {

    Board board;
    
    public CardBuilder(Board board) {
        this.board = board;
    }

    public Card buildCard(String name, BuildingType type) {
        return new Card(name, type, board);
    }
    

}
