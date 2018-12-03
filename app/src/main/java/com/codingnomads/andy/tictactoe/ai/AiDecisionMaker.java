package com.codingnomads.andy.tictactoe.ai;

import java.util.ArrayList;
import java.util.List;

import static com.codingnomads.andy.tictactoe.game.GameLogic.checkWin;
import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_TWO_LETTER;

public class AiDecisionMaker {
    public AiMove getBestMove(String[][] gameboard, String player) {
        AiMove bestMove = new AiMove();

        List<AiMove> potentialMoves = getPotentialMoves(gameboard);

        if (checkWin(gameboard, PLAYER_TWO_LETTER)) {
            bestMove.setScore(-10);
            return bestMove;
        } else if (checkWin(gameboard, PLAYER_ONE_LETTER)) {
            bestMove.setScore(10);
            return bestMove;
        } else if (potentialMoves.size() == 0) {
            bestMove.setScore(0);
            return bestMove;
        }

        List<AiMove> moves = new ArrayList<>();

        for (AiMove potentialMove : potentialMoves) {

            gameboard[potentialMove.getFirstIndex()][potentialMove.getSecondIndex()] = player;

            if(player.equals(PLAYER_ONE_LETTER)) {
                potentialMove.setScore(getBestMove(gameboard, PLAYER_TWO_LETTER).getScore());
            } else {
                potentialMove.setScore(getBestMove(gameboard, PLAYER_ONE_LETTER).getScore());
            }

            gameboard[potentialMove.getFirstIndex()][potentialMove.getSecondIndex()] = "";

            moves.add(potentialMove);
        }

        if(player.equals(PLAYER_ONE_LETTER)) {
            int bestScore = -1000;
            for (AiMove move : moves) {
                if (move.getScore() > bestScore) {
                    bestScore = move.getScore();
                    bestMove = move;
                }
            }
        } else {
            int bestScore = 1000;
            for (AiMove move : moves) {
                if (move.getScore() < bestScore) {
                    bestScore = move.getScore();
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }

    public List<AiMove> getPotentialMoves (String[][] gameboard) {
        List<AiMove> potentialMoveList = new ArrayList<>();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                if(!PLAYER_ONE_LETTER.equals(gameboard[i][j]) &&
                        !PLAYER_TWO_LETTER.equals(gameboard[i][j])) {
                    AiMove aiMove = new AiMove();
                    aiMove.setFirstIndex(i);
                    aiMove.setSecondIndex(j);
                    potentialMoveList.add(aiMove);
                }
            }
        }
        return potentialMoveList;
    }
}
