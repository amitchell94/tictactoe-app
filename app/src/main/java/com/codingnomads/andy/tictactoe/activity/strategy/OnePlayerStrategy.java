package com.codingnomads.andy.tictactoe.activity.strategy;

import android.widget.Button;

import com.codingnomads.andy.tictactoe.AiDecisionMaker;
import com.codingnomads.andy.tictactoe.AiMove;
import com.codingnomads.andy.tictactoe.Player;

import static com.codingnomads.andy.tictactoe.UiLogic.setButtonTexts;

public class OnePlayerStrategy implements GameStrategy {
    @Override
    public String getStartingText(Player player) {
        return "Let's play!";
    }

    @Override
    public String[][] computerMove(String[][] gameBoard, String player, Button[][] buttons) {
        AiDecisionMaker aiDecisionMaker = new AiDecisionMaker();
        AiMove bestMove = aiDecisionMaker.getBestMove(gameBoard, player);

        gameBoard[bestMove.getFirstIndex()][bestMove.getSecondIndex()] = player;
        setButtonTexts(buttons,gameBoard);
        return  gameBoard;
    }

    @Override
    public String getPlayerOneWinText() {
        return "You win!";
    }

    @Override
    public String getPlayerTwoWinText() {
        return "You lose";
    }

    @Override
    public Player switchPlayer(Player currentPlayer) {
        return currentPlayer;
    }

    @Override
    public String getNextPlayerText(Player currentPlayer) {
        return "Let's play!";
    }
}
