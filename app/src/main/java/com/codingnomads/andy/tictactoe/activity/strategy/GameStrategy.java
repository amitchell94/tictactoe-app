package com.codingnomads.andy.tictactoe.activity.strategy;

import android.widget.Button;

import com.codingnomads.andy.tictactoe.Player;

public interface GameStrategy {
    String getStartingText(Player player);

    String[][] computerMove(String[][] gameBoard, String playerTwo, Button[][] buttons, int difficultyLevel);

    String getPlayerOneWinText();

    String getPlayerTwoWinText();

    Player switchPlayer(Player currentPlayer);

    String getNextPlayerText(Player currentPlayer);
    //MAKE MOVES
    //CHEECK WINS
}
