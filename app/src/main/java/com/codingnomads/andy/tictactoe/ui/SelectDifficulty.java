package com.codingnomads.andy.tictactoe.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.codingnomads.andy.tictactoe.game.GameMode;
import com.codingnomads.andy.tictactoe.R;
import com.codingnomads.andy.tictactoe.activity.GameActivity;

public class SelectDifficulty extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] options = {getString(R.string.easy),
                getString(R.string.medium),
                getString(R.string.hard) };

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
