package com.codingnomads.andy.tictactoe;

import android.widget.Button;

class TwoPlayerStrategy implements GameStrategy {
    @Override
    public String getStartingText(String player) {
        return player + " to start first";
    }

    @Override
    public String[][] computerMove(String[][] gameBoard, String playerTwo,Button[][] buttons) {
        return gameBoard;
    }

    @Override
    public String getPlayerOneWinText(String playerOne) {
        return playerOne + " wins!";
    }

    @Override
    public String getPlayerTwoWinText(String playerTwo) {
        return playerTwo + " wins!";
    }

    @Override
    public String switchPlayer(String currentPlayer) {
        if (currentPlayer.equals("X")) {
            return "O";
        }
        return "X";
    }

    @Override
    public String getNextPlayerText(String currentPlayer) {
        return currentPlayer + " to play";
    }
}
