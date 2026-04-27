public class MainGame {

    private Card selectedCard;
    private GameManager game;
    private boolean firstCardPlaced = false;
    private boolean readyToDraw = true;

    public MainGame(int screenSize, int boardSize) {
        game = new GameManager(screenSize, boardSize);
    }

    public void run() {
        game.drawStatsScreen();

        game.getHandScreen().getScreen().onClick(e -> { 
            drawCards(e.getPosition().getX()); 
            selectCardFromHand(e.getPosition().getX());
        });
        game.getBoardScreen().getScreen().onClick(e -> { 
            selectCardFromBoard(e.getPosition().getX(), e.getPosition().getY()); 
            placeCardOnBoard(e.getPosition().getX(), e.getPosition().getY());
        });
        
        game.getHandScreen().getScreen().onMouseMove(e -> { updateScreens(); });

        game.getBoardScreen().getScreen().onMouseMove(e -> { 
                updateScreens();
                previewCursor(game.getBoard(), e.getPosition().getX(), e.getPosition().getY()); }
        );
    }

    public void updateScreens() {
        game.getBoardScreen().removeCursor();
        game.getStatsScreen().selectCard(selectedCard);
        game.getHandScreen().update(game.getHand());
        game.drawBoard(game.getBoard());
        game.getStatsScreen().update(game);
    }

    public void drawCards(double mouseX) {
        if (readyToDraw && game.getHandScreen().getMouseIndex(mouseX) >= game.getHand().getCurrentHand().size()) {
                game.getHand().drawCards(2);
                game.getHandScreen().update(game.getHand());
                readyToDraw = false;
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
        if (inBounds(game.getBoard(), mouseXIndex, mouseYIndex)) {
            if (game.getBoard().hasCard(mouseXIndex, mouseYIndex)) {
            selectedCard = game.getBoard().getCard(mouseXIndex, mouseYIndex);
        }
        }
    }

    public void placeCardOnBoard(double mouseX, double mouseY) {
        int mouseXIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getX();
        int mouseYIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getY();
        if (inBounds(game.getBoard(), mouseXIndex, mouseYIndex)) {
            if (!game.getBoard().hasCard(selectedCard) && !game.getBoard().hasCard(mouseXIndex, mouseYIndex)) {
                if (!firstCardPlaced) {
                    firstCardPlaced = true;
                    game.placeCard(selectedCard);
                    readyToDraw = true;
                } else {
                    game.placeCard(selectedCard, mouseXIndex, mouseYIndex);
                    readyToDraw = true;
                }
            }
        }
        }

    public void previewCursor(Board board, double mouseX, double mouseY) {
        int mouseXIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getX();
        int mouseYIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getY();
            if (firstCardPlaced && mouseX < game.getBoardScreen().getScreen().getWidth() && mouseY < game.getBoardScreen().getScreen().getHeight() && inBounds(board, mouseXIndex, mouseYIndex)) {
                game.getBoardScreen().placeCursor(board, selectedCard, mouseXIndex, mouseYIndex);
            }
        }
    

    public boolean inBounds(Board board, int x, int y) {
        return (x < board.getArrayWidth() && y < board.getArrayHeight());
    }
}


