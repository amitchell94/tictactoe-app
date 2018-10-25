package com.codingnomads.andy.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void playComputer(View view) {
        startActivity(new Intent(MenuActivity.this,GameActivity.class)
                .putExtra("isTwoPlayer",false));
    }

    public void playTwoPlayer(View view) {
        startActivity(new Intent(MenuActivity.this, GameActivity.class)
                .putExtra("isTwoPlayer",true));
    }
}
