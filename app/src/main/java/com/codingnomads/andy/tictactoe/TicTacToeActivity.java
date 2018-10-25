package com.codingnomads.andy.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TicTacToeActivity extends AppCompatActivity {


    public static final String HUMAN = "X";
    public static final String AI = "O";
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
                if(HUMAN.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.BLUE);
                } else if(AI.equals(gameBoard[i][j])) {
                    buttons[i][j].setTextColor(Color.rgb(186,24,24));
                }
            }
        }
    }

    public void setWinningText(String text) {
        TextView tv = findViewById(R.id.winning_text);

        tv.setText(text);
    }

    public void topLeftEntry(View view) {
        setUserInput(0, 0, HUMAN);
    }

    public void topCenterEntry(View view) {
        setUserInput(0, 1, HUMAN);
    }

    public void topRightEntry(View view) {
        setUserInput(0, 2, HUMAN);
    }

    public void middleLeftEntry(View view) {
        setUserInput(1, 0, HUMAN);
    }

    public void middleCenterEntry(View view) {
        setUserInput(1, 1, HUMAN);
    }

    public void middleRightEntry(View view) {
        setUserInput(1, 2, HUMAN);
    }

    public void bottomLeftEntry(View view) {
        setUserInput(2, 0, HUMAN);
    }

    public void bottomCenterEntry(View view) {
        setUserInput(2, 1, HUMAN);
    }

    public void bottomRightEntry(View view) {
        setUserInput(2, 2, HUMAN);
    }

    private void setUserInput(int xCoordinate, int yCoordinate, String letter) {
        disableButtons();
        if (!HUMAN.equals(gameBoard[xCoordinate][yCoordinate]) &&
                !AI.equals(gameBoard[xCoordinate][yCoordinate])) {
            gameBoard[xCoordinate][yCoordinate] = letter;
        } else {
            enableButtons();
            return;
        }
        setButtonTexts();

        if (checkDraw()) {
            disableButtons();
            setWinningText("It's a tie!");
            return;
        }
        if (checkWin(HUMAN)) {
            disableButtons();
            setWinningText("You won!");
        }

        computerMove();
        setButtonTexts();

        if (checkWin(AI)) {
            disableButtons();
            setWinningText("You lost");
            return;
        }
        if (checkDraw()) {
            disableButtons();
            setWinningText("It's a tie!");
            return;
        }

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
                isDraw = isDraw && (AI.equals(gameBoard[i][j]) || HUMAN.equals(gameBoard[i][j]));
            }
        }
        return isDraw;
    }

    private void computerMove() {
        AiDecisionMaker aiDecisionMaker = new AiDecisionMaker();
        AiMove bestMove = aiDecisionMaker.getBestMove(gameBoard, AI);

        gameBoard[bestMove.getFirstIndex()][bestMove.getSecondIndex()] = AI;
        enableButtons();
    }

    public void newGame(View view) {
        initialiseButtons();
    }
}
