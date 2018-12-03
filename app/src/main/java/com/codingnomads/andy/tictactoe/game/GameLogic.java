package com.codingnomads.andy.tictactoe.game;

import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_TWO_LETTER;

public class GameLogic {

    public static boolean checkWin(String[][] gameBoard, String player) {
        boolean winsHorizontally = false;

        //Check horizontal wins
        for (int i = 0; i < gameBoard.length; i++) {
            winsHorizontally = true;
            for (int j = 0; j < gameBoard[i].length; j++) {
                winsHorizontally = winsHorizontally && player.equals(gameBoard[i][j]);
            }
            if (winsHorizontally) {
                return true;
            }
        }

        boolean winsVertically = false;

        for (int i = 0; i < gameBoard.length; i++) {
            winsVertically = true;
            for (int j = 0; j < gameBoard[i].length; j++) {
                winsVertically = winsVertically && player.equals(gameBoard[j][i]);
            }
            if (winsVertically) {
                return true;
            }
        }

        boolean winsDiagonallyRight = true;

        for (int i = 0; i < gameBoard.length; i++) {
            winsDiagonallyRight = winsDiagonallyRight && player.equals(gameBoard[i][i]);
        }
        if (winsDiagonallyRight) {
            return true;
        }

        boolean winsDiagonallyLeft = true;

        int indexColumn = gameBoard.length - 1;
        int indexRow = 0;

        while (indexColumn >= 0) {
            winsDiagonallyLeft = winsDiagonallyLeft && player.equals(gameBoard[indexRow][indexColumn]);
            indexColumn--;
            indexRow++;
        }
        if (winsDiagonallyLeft) {
            return true;
        }
        return false;
    }

    public static boolean checkDraw(String[][] gameBoard) {
        boolean isDraw = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                isDraw = isDraw && (PLAYER_TWO_LETTER.equals(gameBoard[i][j]) || PLAYER_ONE_LETTER.equals(gameBoard[i][j]));
            }
        }
        return isDraw;
    }

}
