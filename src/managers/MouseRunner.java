package managers;

import game.*;
import ui.ScreenUtils;
import enums.*;

/**
 * A large class that keeps track of mouse clicks and interactions.
 */
public class MouseRunner {

    private Card previewedCard;
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
         * When the hand screen is clicked, try to draw cards, select a card, and update all screens.
         */
        game.getHandScreen().getScreen().onClick(e -> {
            drawCards(e.getPosition().getX(), e.getPosition().getY());
            selectCard();
            updateScreens();
        });

        /**
         * When the board screen is clicked, try to place a card, select a card and update all screens.
         */
        game.getBoardScreen().getScreen().onClick(e -> {
            placeCardOnBoard(e.getPosition().getX(), e.getPosition().getY());
            selectedCard = null;
            selectCard();
            updateScreens();
        });

        /**
         * When the mouse moves over the hand screen, preview the card hovered over, update all the screen
         * and try to set the cursor.
         */
        game.getHandScreen().getScreen().onMouseMove(e -> {
            updateScreens();
            previewCardFromHand(e.getPosition().getX(), e.getPosition().getY());
            previewCursorHand(game.getHand(), e.getPosition().getX(), e.getPosition().getY());
        });

        /**
         * When the mouse moves over the board screen, update all the screens, preview the card hovered
         * over, and try to set the cursor.
         */
        game.getBoardScreen().getScreen().onMouseMove(e -> {
            updateScreens();
            previewCardFromBoard(e.getPosition().getX(), e.getPosition().getY());
            previewCursorBoard(game.getBoard(), e.getPosition().getX(), e.getPosition().getY());
        });
    }

    /**
     * Call all of the update functions for the screens.
     */
    public void updateScreens() {
        game.getStatsScreen().previewCard(previewedCard);
        game.getStatsScreen().selectCard(selectedCard);
        game.getHandScreen().update(game.getHand());
        game.getBoardScreen().update(game.getBoard());
        game.getStatsScreen().update(game);
    }

    /**
     * If the mouse position is not over a current card, draw 2 cards up to a maximum of 6.
     */
    public void drawCards(double mouseX, double mouseY) {
        if (readyToDraw
            && (ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getX() >= game
                .getHand()
                .getCurrentHand().size()
                || ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getY() > 0)) {
            game.getHand().drawCards(2);
            game.getHandScreen().update(game.getHand());
            readyToDraw = false;
        }
    }

    /**
     * If the mouse position is over a current card, select that card.
     */
    public void previewCardFromHand(double mouseX, double mouseY) {
        if (ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getX() < game.getHand()
            .getCurrentHand().size()
            && ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getY() < 1) {
            previewedCard = game.getHand()
                .getCardInHand(ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getX());
        } else {
            previewedCard = null;
        }
    }

    /**
     * If the mouse position is over a current card, select that card.
     */
    public void previewCardFromBoard(double mouseX, double mouseY) {
        int mouseXIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getX();
        int mouseYIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getY();
        if (inBounds(game.getBoard(), mouseXIndex, mouseYIndex)) {
            if (game.getBoard().hasCard(mouseXIndex, mouseYIndex)) {
                previewedCard = game.getBoard().getCard(mouseXIndex, mouseYIndex);
            } else {
                previewedCard = null;
            }
        }
    }

    public void selectCard() {
        if (previewedCard != null) {
            selectedCard = previewedCard;
        }
    }

    /**
     * If the mouse position is valid and not over a current card, place the selected card.
     */
    public void placeCardOnBoard(double mouseX, double mouseY) {
        int mouseXIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getX();
        int mouseYIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getY();
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
    public void previewCursorBoard(Board board, double mouseX, double mouseY) {
        int mouseXIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getX();
        int mouseYIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getBoardScreen().getScale()).getY();
        if (firstCardPlaced && mouseX < game.getBoardScreen().getScreen().getWidth()
            && mouseY < game.getBoardScreen().getScreen().getHeight() && inBounds(board, mouseXIndex, mouseYIndex)) {
            game.getBoardScreen().placeCursor(mouseXIndex, mouseYIndex);
        }
    }

    public void previewCursorHand(Hand hand, double mouseX, double mouseY) {
        int mouseXIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getX();
        int mouseYIndex = ScreenUtils.getMouseCoordinates(mouseX, mouseY, game.getHandScreen().getScale()).getY();
        if (mouseXIndex < hand.getCurrentHand().size() && mouseYIndex < 1) {
            game.getHandScreen().placeCursor(mouseXIndex);
        }
    }

    /**
     * Return if the mouse position is in bounds of the board array.
     */
    public boolean inBounds(Board board, int x, int y) {
        return (x < board.getArrayWidth() && y < board.getArrayHeight());
    }
}

