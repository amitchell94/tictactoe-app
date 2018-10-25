package com.codingnomads.andy.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class AiDecisionMaker {
    private static final String AI_PLAYER = "O";
    private static final String HUMAN_PLAYER = "X";


    public AiMove getBestMove(String[][] gameboard, String player) {
        AiMove bestMove = new AiMove();

        List<AiMove> potentialMoves = getPotentialMoves(gameboard);

        if (checkWin(gameboard, HUMAN_PLAYER)) {
            bestMove.setScore(-10);
            return bestMove;
        } else if (checkWin(gameboard, AI_PLAYER)) {
            bestMove.setScore(10);
            return bestMove;
        } else if (potentialMoves.size() == 0) {
            bestMove.setScore(0);
            return bestMove;
        }

        List<AiMove> moves = new ArrayList<>();

        for (AiMove potentialMove : potentialMoves) {

            gameboard[potentialMove.getFirstIndex()][potentialMove.getSecondIndex()] = player;

            if(player.equals(AI_PLAYER)) {
                potentialMove.setScore(getBestMove(gameboard, HUMAN_PLAYER).getScore());
            } else {
                potentialMove.setScore(getBestMove(gameboard, AI_PLAYER).getScore());
            }

            gameboard[potentialMove.getFirstIndex()][potentialMove.getSecondIndex()] = "";

            moves.add(potentialMove);
        }

        if(player.equals(AI_PLAYER)) {
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
                if(!"X".equals(gameboard[i][j]) && !"O".equals(gameboard[i][j])) {
                    AiMove aiMove = new AiMove();
                    aiMove.setFirstIndex(i);
                    aiMove.setSecondIndex(j);
                    potentialMoveList.add(aiMove);
                }
            }
        }
        return potentialMoveList;
    }
    private boolean checkWin(String[][] gameBoard, String letter) {
        boolean winsHorizontally = false;

        //Check horizontal wins
        for (int i = 0; i < gameBoard.length; i++) {
            winsHorizontally = true;
            for (int j = 0; j < gameBoard[i].length; j++) {
                winsHorizontally = winsHorizontally && letter.equals(gameBoard[i][j]);
            }
            if (winsHorizontally) {
                return true;
            }
        }

        boolean winsVertically = false;

        for (int i = 0; i < gameBoard.length; i++) {
            winsVertically = true;
            for (int j = 0; j < gameBoard[i].length; j++) {
                winsVertically = winsVertically && letter.equals(gameBoard[j][i]);
            }
            if (winsVertically) {
                return true;
            }
        }

        boolean winsDiagonallyRight = true;

        for (int i = 0; i < gameBoard.length; i++) {
            winsDiagonallyRight = winsDiagonallyRight && letter.equals(gameBoard[i][i]);
        }
        if (winsDiagonallyRight) {
            return true;
        }

        boolean winsDiagonallyLeft = true;

        int indexColumn = gameBoard.length - 1;
        int indexRow = 0;

        while (indexColumn >= 0) {
            winsDiagonallyLeft = winsDiagonallyLeft && letter.equals(gameBoard[indexRow][indexColumn]);
            indexColumn--;
            indexRow++;
        }
        if (winsDiagonallyLeft) {
            return true;
        }
        return false;

    }
}
