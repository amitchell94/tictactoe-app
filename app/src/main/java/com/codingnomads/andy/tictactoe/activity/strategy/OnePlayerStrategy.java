package com.codingnomads.andy.tictactoe.activity.strategy;

import android.content.Context;
import android.widget.Button;

import com.codingnomads.andy.tictactoe.ai.AiDecisionMaker;
import com.codingnomads.andy.tictactoe.ai.AiMove;
import com.codingnomads.andy.tictactoe.players.Player;
import com.codingnomads.andy.tictactoe.players.PlayerLetters;
import com.codingnomads.andy.tictactoe.R;

import java.util.Random;

import static com.codingnomads.andy.tictactoe.ui.UiLogic.setButtonTexts;

public class OnePlayerStrategy implements GameStrategy {

    private Context context;

    public OnePlayerStrategy(Context context) {
        this.context = context;
    }

    @Override
    public String getStartingText(Player player) {
        return context.getString(R.string.starting_text);
    }

    @Override
    public String[][] computerMove(String[][] gameBoard, String player, Button[][] buttons, int difficultyLevel) {
        int columnPosition = 0;
        int rowPosition = 0;
        boolean doBestMove = false;

        Random rand;

        if (difficultyLevel == 1) {
            rand = new Random();
            doBestMove = (rand.nextInt(4) + 1) != 4;
        }

        if (doBestMove || difficultyLevel == 2) {
            AiDecisionMaker aiDecisionMaker = new AiDecisionMaker();
            AiMove bestMove = aiDecisionMaker.getBestMove(gameBoard, player);

            columnPosition = bestMove.getFirstIndex();
            rowPosition = bestMove.getSecondIndex();
        } else {
            rand = new Random();
            while(true) {

                columnPosition = (rand.nextInt(gameBoard.length) + 0);
                rowPosition = (rand.nextInt(gameBoard[0].length) + 0);

                if (!PlayerLetters.PLAYER_ONE_LETTER.equals(gameBoard[columnPosition][rowPosition]) &&
                        !PlayerLetters.PLAYER_TWO_LETTER.equals(gameBoard[columnPosition][rowPosition])) {
                    break;
                }
            }
        }

        gameBoard[columnPosition][rowPosition] = player;
        setButtonTexts(buttons, gameBoard);

        return  gameBoard;
    }

    @Override
    public String getPlayerOneWinText() {
        return context.getString(R.string.player_wins_text);
    }

    @Override
    public String getPlayerTwoWinText() {
        return context.getString(R.string.player_loses_text);
    }

    @Override
    public Player switchPlayer(Player currentPlayer) {
        return currentPlayer;
    }

    @Override
    public String getNextPlayerText(Player currentPlayer) {
        return context.getString(R.string.starting_text);
    }
}
