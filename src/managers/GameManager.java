package managers;
import java.util.List;

import enums.*;
import game.Board;
import game.Card;
import game.Deck;
import game.Hand;
import game.ScoreTracker;
import storage.ChangesQueue;
import ui.*;

public class GameManager {

    private BoardScreen boardScreen;
    private HandScreen handScreen;
    private StatsScreen scoreScreen;
    private Board board;
    private Hand hand;
    private ScoreTracker scores = new ScoreTracker(1);
    private boolean gameFinished = false;

    public GameManager(int screenSize, int boardSize, IconPath iconType) {
        Deck deck = new Deck(iconType);
        board = new Board(boardSize);
        hand = new Hand(deck, 6);
        boardScreen = new BoardScreen(screenSize);
        handScreen = new HandScreen(screenSize);
        scoreScreen = new StatsScreen(screenSize);
    }

    public ScoreTracker getScoreTracker() {
        return scores;
    }

    public Hand getHand() {
        return hand;
    }

    public void placeCard(Card card, int x, int y) {
        hand.removeCardFromHand(card);
        board.addCard(x, y, card);
        activateAbility(card, AbilityTrigger.PLACEMENT);
        Board newBoard = board.refreshBoard();
        setBoard(newBoard);
    }

    public void placeCard(Card card) {
        hand.removeCardFromHand(card);
        board.addCard(card);
        activateAbility(card, AbilityTrigger.PLACEMENT);
        Board newBoard = board.refreshBoard();
        setBoard(newBoard);
    }

    public BoardScreen getBoardScreen() {
        return boardScreen;
    }

    public HandScreen getHandScreen() {
        return handScreen;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void drawBoard(Board board) {
        boardScreen.clear();
        boardScreen.getNewScale(board);
        for (int x = 0; x < board.getArrayWidth(); x++) {
            for (int y = 0; y < board.getArrayHeight(); y++) {
                if (board.hasCard(x, y)) {
                    boardScreen.addCardtoScreen(board.getCard(x, y), x, y);
                }
            }
        }
    }

    public void activateAbility(Card card, AbilityTrigger trigger) {
        board.activateAbility(card, trigger);
        List<ChangesQueue> changes = board.getAbilityChanges();
        for (ChangesQueue change : changes) {
            scores.changeStat(change.getStat(), change.getChange());
        }
    }

    public List<Integer> getStats() {
        return scores.getStats();
    }

    public StatsScreen getStatsScreen() {
        return scoreScreen;
    }

    public void drawStatsScreen() {
        scoreScreen.update(this);
    }

    public EndCondition testEndConditions() {
        if (board.isFull()) {
            return EndCondition.WIN;
        } else if (scores.inDebt()) {
            return EndCondition.LOSE;
        } else {
            return EndCondition.NONE;
        }
    }

    public void runEndAbilities() {
        if (!gameFinished) {
            gameFinished = true;
            for (int x = 0; x < board.getArrayWidth(); x++) {
                for (int y = 0; y < board.getArrayHeight(); y++) {
                    activateAbility(board.getCard(x,y), AbilityTrigger.ENDGAME);
                }
            }
        }
    }
}