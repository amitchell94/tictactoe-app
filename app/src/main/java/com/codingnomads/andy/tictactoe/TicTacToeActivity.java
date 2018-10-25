package com.codingnomads.andy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TicTacToeActivity extends AppCompatActivity {
    String[][] gameBoard;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        gameBoard = new String[3][3];
        buttons = new Button[3][3];
        initialiseButtons();
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
        setWinningText("Let's Play!");
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
            }
        }
    }

    public void setWinningText(String text) {
        TextView tv = findViewById(R.id.winning_text);

        tv.setText(text);
    }

    public void topLeftEntry(View view) {
        setUserInput(0, 0, "X");
    }

    public void topCenterEntry(View view) {
        setUserInput(0, 1, "X");
    }

    public void topRightEntry(View view) {
        setUserInput(0, 2, "X");
    }

    public void middleLeftEntry(View view) {
        setUserInput(1, 0, "X");
    }

    public void middleCenterEntry(View view) {
        setUserInput(1, 1, "X");
    }

    public void middleRightEntry(View view) {
        setUserInput(1, 2, "X");
    }

    public void bottomLeftEntry(View view) {
        setUserInput(2, 0, "X");
    }

    public void bottomCenterEntry(View view) {
        setUserInput(2, 1, "X");
    }

    public void bottomRightEntry(View view) {
        setUserInput(2, 2, "X");
    }

    private void setUserInput(int xCoordinate, int yCoordinate, String letter) {
        disableButtons();
        if (!"X".equals(gameBoard[xCoordinate][yCoordinate]) &&
                !"O".equals(gameBoard[xCoordinate][yCoordinate])) {
            gameBoard[xCoordinate][yCoordinate] = letter;
        }
        setButtonTexts();

        if (checkDraw()) {
            disableButtons();
            setWinningText("It's a tie!");
            return;
        }
        if (!checkWin("X")) {
            computerMove();
            setButtonTexts();
            if (checkWin("O")) {
                disableButtons();
                setWinningText("You lost");
                return;
            }
            if (checkDraw()) {
                disableButtons();
                setWinningText("It's a tie!");
                return;
            }
        } else {
            disableButtons();
            setWinningText("You won!");
        }
    }

    private boolean checkWin(String letter) {
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

    private boolean checkDraw() {

        boolean isDraw = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                isDraw = isDraw && ("O".equals(gameBoard[i][j]) || "X".equals(gameBoard[i][j]));
            }
        }
        if (isDraw) {
            return true;
        } else {
            return false;
        }
    }

    private void computerMove() {
        while (true) {
            int randomColumn = (int) (Math.random() * gameBoard.length + 0);
            int randomrow = (int) (Math.random() * gameBoard.length + 0);

            if (!"X".equals(gameBoard[randomColumn][randomrow]) && !"O".equals(gameBoard[randomColumn][randomrow])) {
                gameBoard[randomColumn][randomrow] = "O";
                break;
            }
        }
        enableButtons();
    }

    public void newGame(View view) {
        initialiseButtons();
    }
}
