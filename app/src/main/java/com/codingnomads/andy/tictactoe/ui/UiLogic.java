package com.codingnomads.andy.tictactoe.ui;

import android.graphics.Color;
import android.widget.Button;

import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_ONE_LETTER;
import static com.codingnomads.andy.tictactoe.players.PlayerLetters.PLAYER_TWO_LETTER;

public class UiLogic {

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

}
