package com.codingnomads.andy.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.codingnomads.andy.tictactoe.activity.GameActivity;
import com.codingnomads.andy.tictactoe.activity.MenuActivity;

import java.util.Arrays;

public class SelectDificulty extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] options = {(String)getText(R.string.easy),
                (String)getText(R.string.medium),
                (String)getText(R.string.hard), };

        builder.setTitle("Select difficulty")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                startActivity(new Intent(getActivity(),GameActivity.class)
                                        .putExtra("gameMode",GameMode.ONE_PLAYER)
                                        .putExtra("difficultyLevel",0));
                                break;
                            case 1:
                                startActivity(new Intent(getActivity(),GameActivity.class)
                                        .putExtra("gameMode",GameMode.ONE_PLAYER)
                                        .putExtra("difficultyLevel",1));
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(),GameActivity.class)
                                        .putExtra("gameMode",GameMode.ONE_PLAYER)
                                        .putExtra("difficultyLevel",2));
                                break;
                        }
                    }
                });

        return builder.create();
    }
}
