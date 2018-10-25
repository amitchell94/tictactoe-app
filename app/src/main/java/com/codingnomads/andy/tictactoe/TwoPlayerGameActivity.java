package com.codingnomads.andy.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwoPlayerGameActivity extends AppCompatActivity {
    public static final String PLAYER_ONE = "X";
    public static final String PLAYER_TWO = "O";
    public String currentPlayer;

    String[][] gameBoard;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        gameBoard = new String[3][3];
        buttons = new Button[3][3];
        initialiseButtons();
        currentPlayer = PLAYER_ONE;
    }

    private void initialiseButtons() {
        buttons[0][0] = findViewById(R.id.top_left);
        buttons[0][1] = findViewById(R.id.top_center);
        buttons[0][2] = findViewById(R.id.top_right);
        buttons[1][0] = findViewById(R.id.middle_left);
        buttons[1][1] = findViewById(R.id.middle_center);
        buttons[1][2] = findViewById(R.id.middle_right);
        buttons[2][0] = findViewById(R.id.bottom_left);
        buttons[2][1] = findViewById(R.id.bottom_center);
        buttons[2][2] = findViewById(R.id.bottom_right);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                gameBoard[i][j] = "";
                buttons[i][j].setText("");
                buttons[i][j].setClickable(true);
            }
        }
        setText(PLAYER_ONE + " to play first!");
    }

    public void disableButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    public void enableButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(true);
            }
        }
    }

    public void setButtonTexts() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                buttons[i][j].setText(gameBoard[i][j]);
                if(PLAYER_ONE.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.BLUE);
                } else if(PLAYER_TWO.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.rgb(186,24,24));
                }
            }
        }
    }

    public void setText(String text) {
        TextView tv = findViewById(R.id.winning_text);

        tv.setText(text);
    }

    public void topLeftEntry(View view) {
        setUserInput(0, 0, currentPlayer);
    }

    public void topCenterEntry(View view) {
        setUserInput(0, 1, currentPlayer);
    }

    public void topRightEntry(View view) {
        setUserInput(0, 2, currentPlayer);
    }

    public void middleLeftEntry(View view) {
        setUserInput(1, 0, currentPlayer);
    }

    public void middleCenterEntry(View view) {
        setUserInput(1, 1, currentPlayer);
    }

    public void middleRightEntry(View view) {
        setUserInput(1, 2, currentPlayer);
    }

    public void bottomLeftEntry(View view) {
        setUserInput(2, 0, currentPlayer);
    }

    public void bottomCenterEntry(View view) {
        setUserInput(2, 1, currentPlayer);
    }

    public void bottomRightEntry(View view) {
        setUserInput(2, 2, currentPlayer);
    }

    private void setUserInput(int xCoordinate, int yCoordinate, String letter) {
        disableButtons();
        if (!PLAYER_ONE.equals(gameBoard[xCoordinate][yCoordinate]) &&
                !PLAYER_TWO.equals(gameBoard[xCoordinate][yCoordinate])) {
            gameBoard[xCoordinate][yCoordinate] = letter;
            if (currentPlayer.equals(PLAYER_ONE)) {
                currentPlayer = PLAYER_TWO;
            } else {
                currentPlayer = PLAYER_ONE;
            }
        } else {
            enableButtons();
            return;
        }
        setButtonTexts();

        if (checkDraw()) {
            disableButtons();
            setText("It's a tie!");
            return;
        }
        if (checkWin(PLAYER_ONE)) {
            disableButtons();
            setText("X won!");
            return;
        }
        if (checkWin(PLAYER_TWO)) {
            disableButtons();
            setText("O won!");
            return;
        }
        enableButtons();
        setText(currentPlayer + " to play");
    }

    private boolean checkWin(String player) {
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

    private boolean checkDraw() {

        boolean isDraw = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                isDraw = isDraw && (PLAYER_TWO.equals(gameBoard[i][j]) || PLAYER_ONE.equals(gameBoard[i][j]));
            }
        }
        return isDraw;
    }

    public void newGame(View view) {
        initialiseButtons();
    }

}
