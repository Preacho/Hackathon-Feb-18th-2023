package com.example.lostintranslation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class game_over extends AppCompatDialogFragment {
    String score;
    public game_over(String score) {
        this.score = score;
        if(score.equals("")){
            this.score = "0";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Game Over!")
                .setMessage("Score: "+score)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        getActivity().finish();
                    }
                });
        return builder.create();
    }
}
