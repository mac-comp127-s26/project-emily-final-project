public class MainGame {

    private Card selectedCard;
    private GameManager game;

    public MainGame(int screenSize, int boardSize) {
        game = new GameManager(screenSize, boardSize);
    }

    public void run() {
        game.drawStatsScreen();

        game.getHandScreen().getScreen().onClick(e -> { drawCards(e.getPosition().getX()); });
        game.getHandScreen().getScreen().onClick(e -> { selectCardFromHand(e.getPosition().getX()); });
        game.getBoardScreen().getScreen().onClick(e -> { selectCardFromBoard(e.getPosition().getX(), e.getPosition().getY()); });

        game.getHandScreen().getScreen().onClick(e -> {
            updateScreens();
        });

        game.getBoardScreen().getScreen().onClick(e -> {
            updateScreens();
        });
    }

    public void updateScreens() {
        game.getHandScreen().update(game.getHand());
        game.getStatsScreen().selectCard(selectedCard);
        game.getStatsScreen().update(game);
    }

    public void drawCards(double mouseX) {
        if (game.getHandScreen().getMouseIndex(mouseX) > game.getHand().getCurrentHand().size()) {
            game.getHand().drawCards(2);
            game.getHandScreen().update(game.getHand());
        }
    }

    public void selectCardFromHand(double mouseX) {
        if (game.getHandScreen().getMouseIndex(mouseX) < game.getHand().getCurrentHand().size()) {
            selectedCard = game.getHand().getCardInHand(game.getHandScreen().getMouseIndex(mouseX));
        }
    }

    public void selectCardFromBoard(double mouseX, double mouseY) {
        int mouseXIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getX();
        int mouseYIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getY();
        if (game.getBoard().hasCard(mouseXIndex, mouseYIndex)) {
            selectedCard = game.getBoard().getCard(mouseXIndex, mouseYIndex);
        }
    }


}

