package com.codingnomads.andy.tictactoe.activity.strategy;

import android.widget.Button;

import com.codingnomads.andy.tictactoe.Player;

import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_TWO_LETTER;

public class TwoPlayerStrategy implements GameStrategy {
    @Override
    public String getStartingText(Player player) {
        if (player == Player.PLAYER_ONE) {
            return PLAYER_ONE_LETTER + " to start first";
        } else {
            return PLAYER_TWO_LETTER + " to start first";
        }
    }

    @Override
    public String[][] computerMove(String[][] gameBoard, String playerTwo,Button[][] buttons) {
        return gameBoard;
    }

    @Override
    public String getPlayerOneWinText() {
        return PLAYER_ONE_LETTER + " wins!";
    }

    @Override
    public String getPlayerTwoWinText() {
        return PLAYER_TWO_LETTER + " wins!";
    }

    @Override
    public Player switchPlayer(Player currentPlayer) {
        if (currentPlayer.equals(Player.PLAYER_ONE)) {
            return Player.PLAYER_TWO;
        }
        return Player.PLAYER_ONE;
    }

    @Override
    public String getNextPlayerText(Player currentPlayer) {
        if (currentPlayer == Player.PLAYER_ONE) {
            return PLAYER_ONE_LETTER + " to play";
        } else {
            return PLAYER_TWO_LETTER + " to play";
        }
    }
}
