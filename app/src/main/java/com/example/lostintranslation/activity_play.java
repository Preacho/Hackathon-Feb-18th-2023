package com.example.lostintranslation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostintranslation.model.GamePlay;
import com.example.lostintranslation.model.Word;

import java.util.Locale;

public class activity_play extends AppCompatActivity {
    ActionBar ab;
    private long timeleftinmillis = 60000;
    private CountDownTimer countDownTimer;
    private boolean timerrunning;
    private TextView timer;
    private EditText answer;
    private TextView lives;
    private Button submit;
    private GamePlay game;
    private int difficulty;

    private String originalWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_play);
        difficulty = activity_settings.getDifficultyData(this);

        game = new GamePlay(difficulty);

        answer = (EditText) findViewById(R.id.et_answer);
        submit = (Button) findViewById(R.id.bt_submit);
        Button key = findViewById(R.id.bt_viewKey);

        getWord();
        key.setOnClickListener(v-> openKey());
        submit.setOnClickListener(v->checkAnswer());

        lives = findViewById(R.id.Life_Counter);
        lives.setText(String.valueOf(game.getAttempts()));

        timer = (TextView) findViewById(R.id.TimeCounter);
        Timer();

        ActionBar backbar = getSupportActionBar();
        backbar.setDisplayHomeAsUpEnabled(true);

    }

    private void getWord() {
        System.out.println("difficulty " + difficulty);
        Word word = new Word(difficulty);
        System.out.println(word.getOriginal_word());
        originalWord = word.getOriginal_word();
        TextView mystery = findViewById(R.id.tv_cipher);
        mystery.setText(word.getTransformed_word());
    }

    private void checkAnswer() {
        EditText answer = findViewById(R.id.et_answer);
        String response = answer.getText().toString().toLowerCase(Locale.ROOT);
        if(response.equals("")){

        }
        else if(response.equals(originalWord.toLowerCase(Locale.ROOT))){
            answer.setText("");
            game.addScore();
            Toast.makeText(this,"Score: "+game.getScore(), Toast.LENGTH_SHORT).show();

            pausetimer();
            timeleftinmillis += 5000;
            resumetimer();

            nextWord();
        }
        else{
            Toast.makeText(this,"incorrect "+originalWord, Toast.LENGTH_SHORT).show();
            game.reduceAttempts();

            if(game.getAttempts() < 0){

                Toast.makeText(this,"Game Over!",Toast.LENGTH_SHORT).show();
                activity_settings.saveHighScore(activity_play.this,game.getScore(),difficulty);
                pausetimer();
                finish();
            }else{
                lives.setText(String.valueOf(game.getAttempts()));
            }


        }
        System.out.println(answer.getText().toString());

    }

    private void nextWord() {
        getWord();
    }

    private void openKey() {
        Intent intent = new Intent(this, activity_view_key.class);
        pausetimer();
        startActivity(intent);
        resumetimer();


    }
    private void Timer(){
        countDownTimer = new CountDownTimer(timeleftinmillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinmillis = millisUntilFinished;
                updatetimer();

            }


            @Override
            public void onFinish() {
                Toast.makeText(activity_play.this,"Game Over!",Toast.LENGTH_SHORT).show();
                activity_settings.saveHighScore(activity_play.this,game.getScore(),difficulty);
                finish();
            }
        }.start();
        timerrunning = true;

    }
    private void updatetimer() {
        int seconds = (int) timeleftinmillis/1000;
        timer.setText(Integer.toString(seconds));
    }
    private void pausetimer(){
        countDownTimer.cancel();
    }
    private void resumetimer(){
        Timer();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem holder){
        switch (holder.getItemId()){
            case android.R.id.home:
                pausetimer();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(holder);
    }
    @Override
    public void onBackPressed(){
        pausetimer();
        finish();
    }

}