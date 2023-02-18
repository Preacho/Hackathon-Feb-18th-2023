package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class activity_settings extends AppCompatActivity {
    ActionBar ab;
    int highScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);

        createDifficultyButtons();
        resetScore();
    }

    private void resetScore() {
        TextView score = findViewById(R.id.tv_high_score);
        Button button = findViewById(R.id.bt_reset);
        button.setOnClickListener( v -> {
            score.setText("0");
        });
    }

    private void createDifficultyButtons() {
        RadioGroup group = findViewById(R.id.rv_difficulty);
        String[] difficultyOptions = {"Easy", "Normal", "Hard"};

        for(int i = 0; i<difficultyOptions.length; i++){
            String difficulty = difficultyOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(difficulty);

            button.setOnClickListener( v->{
                Toast.makeText(this,"Difficulty "+difficulty,Toast.LENGTH_SHORT).show();

            });

            group.addView(button);
        }
    }
}