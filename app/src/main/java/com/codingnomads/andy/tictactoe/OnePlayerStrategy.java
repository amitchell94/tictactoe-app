package com.codingnomads.andy.tictactoe;

import android.widget.Button;

import static com.codingnomads.andy.tictactoe.UiLogic.checkWin;
import static com.codingnomads.andy.tictactoe.UiLogic.disableButtons;
import static com.codingnomads.andy.tictactoe.UiLogic.enableButtons;
import static com.codingnomads.andy.tictactoe.UiLogic.setButtonTexts;

class OnePlayerStrategy implements GameStrategy {
    @Override
    public String getStartingText(String player) {
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
    public String getPlayerOneWinText(String playerOne) {
        return "You win!";
    }

    @Override
    public String getPlayerTwoWinText(String playerTwo) {
        return "You lose";
    }

    @Override
    public String switchPlayer(String currentPlayer) {
        return currentPlayer;
    }

    @Override
    public String getNextPlayerText(String currentPlayer) {
        return "Let's play!";
    }
}
