package com.codingnomads.andy.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.PlayerLetters.PLAYER_TWO_LETTER;

public class UiLogic extends AppCompatActivity {

    public static void disableButtons(Button[][] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    public static void enableButtons(Button[][] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(true);
            }
        }
    }

    public static int[] getButtonCoordinate(Button[][] buttons,int id){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getId() == id) {
                    int[] coordinates = {i,j};
                    return coordinates;
                }
            }
        }
        return null;
    }

    public static void setButtonTexts(Button[][] buttons,String[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                buttons[i][j].setText(gameBoard[i][j]);
                if(PLAYER_ONE_LETTER.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.BLUE);
                } else if(PLAYER_TWO_LETTER.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.rgb(186,24,24));
                }
            }
        }
    }

    public static boolean checkWin(String player, String[][] gameBoard) {
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
