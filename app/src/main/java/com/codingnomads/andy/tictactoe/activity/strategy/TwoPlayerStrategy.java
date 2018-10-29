package com.codingnomads.andy.tictactoe.activity.strategy;

import android.content.Context;
import android.widget.Button;

import com.codingnomads.andy.tictactoe.Player;
import com.codingnomads.andy.tictactoe.R;

import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_TWO_LETTER;

public class TwoPlayerStrategy implements GameStrategy {

    private Context context;

    public TwoPlayerStrategy(Context context) {
        this.context = context;
    }

    @Override
    public String getStartingText(Player player) {
        if (player == Player.PLAYER_ONE) {
            return PLAYER_ONE_LETTER + context.getText(R.string.to_start_first);
        } else {
            return PLAYER_TWO_LETTER + context.getText(R.string.to_start_first);
        }

    }

    @Override
    public String[][] computerMove(String[][] gameBoard, String playerTwo,Button[][] buttons, int difficultyLevel) {
        return gameBoard;
    }

    @Override
    public String getPlayerOneWinText() {
        return PLAYER_ONE_LETTER + context.getText(R.string.two_player_wins_text);
    }

    @Override
    public String getPlayerTwoWinText() {
        return PLAYER_TWO_LETTER + context.getText(R.string.two_player_wins_text);
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
            return PLAYER_ONE_LETTER + context.getText(R.string.to_play);
        } else {
            return PLAYER_TWO_LETTER + context.getText(R.string.to_play);
        }
    }
}
