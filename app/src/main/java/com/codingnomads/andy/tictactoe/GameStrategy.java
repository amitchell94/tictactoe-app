package com.codingnomads.andy.tictactoe;

import android.widget.Button;

interface GameStrategy {
    String getStartingText(String player);

    String[][] computerMove(String[][] gameBoard, String playerTwo, Button[][] buttons);

    String getPlayerTwoWinText(String playerTwo);

    String switchPlayer(String currentPlayer);

    String getNextPlayerText(String currentPlayer);

    String getPlayerOneWinText(String playerOne);
    //MAKE MOVES
    //CHEECK WINS
}
