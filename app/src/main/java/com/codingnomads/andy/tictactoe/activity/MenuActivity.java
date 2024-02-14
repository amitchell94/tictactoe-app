package com.codingnomads.andy.tictactoe.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.codingnomads.andy.tictactoe.game.GameMode;
import com.codingnomads.andy.tictactoe.R;
import com.codingnomads.andy.tictactoe.ui.SelectDifficulty;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void playComputer(View view) {
        SelectDifficulty selectDificulty = new SelectDifficulty();
        selectDificulty.show(getSupportFragmentManager(), "NoticeDialogFragmen");
    }

    public void playTwoPlayer(View view) {
        startActivity(new Intent(MenuActivity.this, GameActivity.class)
                .putExtra("gameMode",GameMode.TWO_PLAYERS));
    }
}
