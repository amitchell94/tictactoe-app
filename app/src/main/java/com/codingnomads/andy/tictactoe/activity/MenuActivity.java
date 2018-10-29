package com.codingnomads.andy.tictactoe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.codingnomads.andy.tictactoe.GameMode;
import com.codingnomads.andy.tictactoe.R;
import com.codingnomads.andy.tictactoe.SelectDificulty;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void playComputer(View view) {
        SelectDificulty selectDificulty = new SelectDificulty();
        selectDificulty.show(getSupportFragmentManager(), "NoticeDialogFragmen");
    }

    public void playTwoPlayer(View view) {
        startActivity(new Intent(MenuActivity.this, GameActivity.class)
                .putExtra("gameMode",GameMode.TWO_PLAYERS));
    }
}
