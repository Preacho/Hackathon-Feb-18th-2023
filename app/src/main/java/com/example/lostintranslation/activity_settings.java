package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class activity_settings extends AppCompatActivity {
    ActionBar ab;
    private int highScore = 0;
    private SharedPreferences prefs;
    private int saveddifficulty;
    private ImageView image;
    private TextView highscoretext;
    private int[] imageid = {R.drawable.sunrise, R.drawable.sunset,R.drawable.night};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
        saveddifficulty = getDifficultyData(this);

        highscoretext = findViewById(R.id.tv_high_score);
        highscoretext.setText(Integer.toString(getHighScore(this,saveddifficulty)));

        image = findViewById(R.id.BackgroundImageView);
        image.setImageResource(imageid[saveddifficulty]);

        createDifficultyButtons();
        resetScore();
    }

    private void resetScore() {
        TextView score = findViewById(R.id.tv_high_score);
        Button button = findViewById(R.id.bt_reset);
        button.setOnClickListener( v -> {
            resetHighScoreData();
            highscoretext.setText("0");

        });
    }

    private void createDifficultyButtons() {
        RadioGroup group = findViewById(R.id.rv_difficulty);
        String[] difficultyOptions = {"Easy", "Normal", "Hard"};

        for(int i = 0; i<difficultyOptions.length; i++){
            String difficulty = difficultyOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(difficulty);
            final int index = i;
            button.setOnClickListener( v->{
                saveDifficultyData(index);
                highscoretext.setText(Integer.toString(getHighScore(this,saveddifficulty)));
                image.setImageResource(imageid[index]);
            });

            group.addView(button);

            if(i == saveddifficulty){
                button.setChecked(true);
            }
        }
    }
    private void resetHighScoreData(){
        for(int i = 0; i < 3; i++){
            prefs = this.getSharedPreferences("HighScoreData" + i, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("HighScores" + i, 0);
            editor.apply();
        }
    }
    public void saveDifficultyData(int position){
        prefs = this.getSharedPreferences("DifficultyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Difficulty Installed", position);
        editor.apply();
    }
    public static int getDifficultyData(Context context){
        SharedPreferences prefs = context.getSharedPreferences("DifficultyPref", MODE_PRIVATE);
        return prefs.getInt("Difficulty Installed", 0);

    }
    public static void saveHighScore(Context context, int score, int difficulty){
        SharedPreferences prefs = context.getSharedPreferences("HighScoreData" + difficulty, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("HighScores" + difficulty, score);
        editor.apply();
    }
    public static int getHighScore(Context context, int difficulty){
        SharedPreferences prefs = context.getSharedPreferences("HighScoreData" + difficulty, MODE_PRIVATE);
        return prefs.getInt("HighScores" + difficulty, 0);
    }
}