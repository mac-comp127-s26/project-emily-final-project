package managers;

import game.*;
import enums.*;

/**
 * A large class that keeps track of mouse clicks and interactions.
 */
public class MouseRunner {

    private Card selectedCard;
    private GameManager game;
    private boolean firstCardPlaced = false;
    private boolean readyToDraw = true;

    public MouseRunner(int screenSize, int boardSize, IconPath iconType) {
        game = new GameManager(screenSize, boardSize, iconType);
    }

    public void run() {
        game.drawStatsScreen();

        /**
         * When the hand screen is clicked, try to draw cards and select a card and update all the screens.
         */
        game.getHandScreen().getScreen().onClick(e -> {
            drawCards(e.getPosition().getX());
            selectCardFromHand(e.getPosition().getX());
            updateScreens();
        });

        /**
         * When the board screen is clicked, try to place a card and select a card and update all the screens.
         */
        game.getBoardScreen().getScreen().onClick(e -> {
            selectCardFromBoard(e.getPosition().getX(), e.getPosition().getY());
            placeCardOnBoard(e.getPosition().getX(), e.getPosition().getY());
            updateScreens();
        });

        /**
         * When the mouse moves over the hand screen, update all the screens.
         */
        game.getHandScreen().getScreen().onMouseMove(e -> { updateScreens(); });

        /**
         * When the mouse moves over the board screen, update all the screens and try to set the cursor.
         */
        game.getBoardScreen().getScreen().onMouseMove(e -> {
            updateScreens();
            previewCursor(game.getBoard(), e.getPosition().getX(), e.getPosition().getY());
            updateScreens();
        });
    }

    /**
     * Call all of the update functions for the screens.
     */
    public void updateScreens() {
        game.getBoardScreen().removeCursor();
        game.getStatsScreen().selectCard(selectedCard);
        game.getHandScreen().update(game.getHand());
        game.drawBoard(game.getBoard());
        game.getStatsScreen().update(game);
    }

    /**
     * If the mouse position is not over a current card, draw 2 cards up to a maximum of 6.
     */
    public void drawCards(double mouseX) {
        if (readyToDraw && game.getHandScreen().getMouseIndex(mouseX) >= game.getHand().getCurrentHand().size()) {
            game.getHand().drawCards(2);
            game.getHandScreen().update(game.getHand());
            readyToDraw = false;
        }
    }

    /**
     * If the mouse position is over a current card, select that card.
     */
    public void selectCardFromHand(double mouseX) {
        if (game.getHandScreen().getMouseIndex(mouseX) < game.getHand().getCurrentHand().size()) {
            selectedCard = game.getHand().getCardInHand(game.getHandScreen().getMouseIndex(mouseX));
        }
    }

    /**
     * If the mouse position is over a current card, select that card.
     */
    public void selectCardFromBoard(double mouseX, double mouseY) {
        int mouseXIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getX();
        int mouseYIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getY();
        if (inBounds(game.getBoard(), mouseXIndex, mouseYIndex)) {
            if (game.getBoard().hasCard(mouseXIndex, mouseYIndex)) {
                selectedCard = game.getBoard().getCard(mouseXIndex, mouseYIndex);
            }
        }
    }

    /**
     * If the mouse position is valid and not over a current card, place the selected card.
     */
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

    /**
     * If the mouse position is valid, place the cursor at the equivalant (x,y) position of the board.
     */
    public void previewCursor(Board board, double mouseX, double mouseY) {
        int mouseXIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getX();
        int mouseYIndex = game.getBoardScreen().getMouseCoordinates(game.getBoard(), mouseX, mouseY).getY();
        if (firstCardPlaced && mouseX < game.getBoardScreen().getScreen().getWidth()
            && mouseY < game.getBoardScreen().getScreen().getHeight() && inBounds(board, mouseXIndex, mouseYIndex)) {
            game.getBoardScreen().placeCursor(board, mouseXIndex, mouseYIndex);
        }
    }

    /**
     * Return if the mouse position is in bounds of the board array.
     */
    public boolean inBounds(Board board, int x, int y) {
        return (x < board.getArrayWidth() && y < board.getArrayHeight());
    }
}

