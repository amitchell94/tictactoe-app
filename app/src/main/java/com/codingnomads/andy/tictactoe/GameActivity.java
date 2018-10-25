package com.codingnomads.andy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.codingnomads.andy.tictactoe.GameLogic.checkDraw;
import static com.codingnomads.andy.tictactoe.GameLogic.checkWin;
import static com.codingnomads.andy.tictactoe.GameLogic.disableButtons;
import static com.codingnomads.andy.tictactoe.GameLogic.enableButtons;
import static com.codingnomads.andy.tictactoe.GameLogic.getButtonCoordinate;
import static com.codingnomads.andy.tictactoe.GameLogic.setButtonTexts;

public class GameActivity extends AppCompatActivity {
    public static final String PLAYER_ONE = "X";
    public static final String PLAYER_TWO = "O";

    private boolean isTwoPlayer;
    private String currentPlayer;

    String[][] gameBoard;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        gameBoard = new String[3][3];
        buttons = new Button[3][3];

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            isTwoPlayer = extras.getBoolean("isTwoPlayer");
        }

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
        currentPlayer = PLAYER_ONE;
        if (isTwoPlayer){
            setText(currentPlayer + " to play first");
        } else {
            setText("Let's Play!");
        }
    }

    public void setText(String text) {
        TextView tv = findViewById(R.id.winning_text);

        tv.setText(text);
    }

    public void userEntry(View view) {

        int buttonId = view.getId();
        int[] coordinates = getButtonCoordinate(buttons,buttonId);

        setUserInput(coordinates[0], coordinates[1], currentPlayer);
    }

    private void setUserInput(int rowNo, int columnNo, String letter) {
        if (!isTwoPlayer) {
            disableButtons(buttons);
        }
        if (!PLAYER_ONE.equals(gameBoard[rowNo][columnNo]) &&
                !PLAYER_TWO.equals(gameBoard[rowNo][columnNo])) {
            gameBoard[rowNo][columnNo] = letter;
        } else {
            enableButtons(buttons);
            return;
        }
        setButtonTexts(buttons,gameBoard);

        if (isTwoPlayer) {
            if (currentPlayer.equals(PLAYER_ONE)) {
                currentPlayer = PLAYER_TWO;
            } else {
                currentPlayer = PLAYER_ONE;
            }
            setText(currentPlayer + " to play");
        }

        if (checkWinners()) {
            return;
        }

        if (!isTwoPlayer) {
            computerMove();
            setButtonTexts(buttons, gameBoard);
            checkWinners();
        }
    }

    private boolean checkWinners() {

        if (checkDraw(gameBoard)) {
            disableButtons(buttons);
            setText("It's a tie!");
            return true;
        }

        if (checkWin(PLAYER_ONE, gameBoard)) {
            disableButtons(buttons);
            if (isTwoPlayer) {
                setText(PLAYER_ONE + " wins!");
            } else {
                setText("You win!");
            }
            return true;
        }

        if (checkWin(PLAYER_TWO,gameBoard)) {
            disableButtons(buttons);
            if (isTwoPlayer) {
                setText(PLAYER_TWO + " wins!");
            } else {
                setText("You lose");
            }
            return true;
        }
        return false;
    }

    private void computerMove() {
        AiDecisionMaker aiDecisionMaker = new AiDecisionMaker();
        AiMove bestMove = aiDecisionMaker.getBestMove(gameBoard, PLAYER_TWO);

        gameBoard[bestMove.getFirstIndex()][bestMove.getSecondIndex()] = PLAYER_TWO;
        enableButtons(buttons);
    }

    public void newGame(View view) {
        initialiseButtons();
    }
}
