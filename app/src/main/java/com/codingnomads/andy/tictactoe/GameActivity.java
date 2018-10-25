package com.codingnomads.andy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import static com.codingnomads.andy.tictactoe.UiLogic.checkDraw;
import static com.codingnomads.andy.tictactoe.UiLogic.checkWin;
import static com.codingnomads.andy.tictactoe.UiLogic.disableButtons;
import static com.codingnomads.andy.tictactoe.UiLogic.enableButtons;
import static com.codingnomads.andy.tictactoe.UiLogic.getButtonCoordinate;
import static com.codingnomads.andy.tictactoe.UiLogic.setButtonTexts;

public class GameActivity extends AppCompatActivity {
    public static final String PLAYER_ONE = "X";
    public static final String PLAYER_TWO = "O";

    private Map<GameMode, GameStrategy > gameStrategyMap = new HashMap<>();
    private GameStrategy gameStrategy;
    private GameMode gameMode;
    private String currentPlayer;

    String[][] gameBoard;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        gameStrategyMap.put(GameMode.ONE_PLAYER, new OnePlayerStrategy());
        gameStrategyMap.put(GameMode.TWO_PLAYERS, new TwoPlayerStrategy());


        gameBoard = new String[3][3];
        buttons = new Button[3][3];

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            gameMode = (GameMode)extras.get("gameMode");
                gameStrategy = gameStrategyMap.get(gameMode);
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
        setText(gameStrategy.getStartingText(currentPlayer));
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
        disableButtons(buttons);

        if (!PLAYER_ONE.equals(gameBoard[rowNo][columnNo]) &&
                !PLAYER_TWO.equals(gameBoard[rowNo][columnNo])) {
            gameBoard[rowNo][columnNo] = letter;
        } else {
            enableButtons(buttons);
            return;
        }
        setButtonTexts(buttons,gameBoard);

        currentPlayer = gameStrategy.switchPlayer(currentPlayer);
        setText(gameStrategy.getNextPlayerText(currentPlayer));

        if (checkWin(PLAYER_ONE, gameBoard)) {
            setText(gameStrategy.getPlayerOneWinText(PLAYER_ONE));
            disableButtons(buttons);
            return;
        }

        if (checkDraw(gameBoard)) {
            setText("It's a draw!");
            disableButtons(buttons);
            return;
        }
        gameBoard = gameStrategy.computerMove(gameBoard, PLAYER_TWO, buttons);

        if (checkWin(PLAYER_TWO, gameBoard)) {
            setText(gameStrategy.getPlayerTwoWinText(PLAYER_TWO));
            disableButtons(buttons);
            return;
        }

        enableButtons(buttons);
    }

    public void newGame(View view) {
        initialiseButtons();
    }
}
