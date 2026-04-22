public class GameManager {

    public static void main(String[] args) {
        ScoreTracker scores = new ScoreTracker(1);
        Board board = new Board(4, scores);
        Deck deck = new Deck(board);

        Board newBoard = board.updateBoard();
    }

}

