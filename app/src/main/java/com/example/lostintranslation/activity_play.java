package com.example.lostintranslation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostintranslation.model.GamePlay;
import com.example.lostintranslation.model.Word;

import java.util.Locale;

public class activity_play extends AppCompatActivity {
    ActionBar ab;
    private long timeleftinmillis = 61000;
    private long animatetimer;
    private CountDownTimer countDownTimer;
    private CountDownTimer animationTimer;
    private boolean timerrunning;
    private boolean animaterunning;
    private TextView timer;
    private EditText answer;
    private TextView lives;
    private Button submit;
    private GamePlay game;
    private TextView score;
    private TextView best;
    private TextView animatescore;
    private int difficulty;
    private MediaPlayer incorrect;
    private MediaPlayer correct;
    private MediaPlayer gameOver;
    private int level;
    private int iterator;
    private String[] levelSet;

    private String originalWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_play);
        difficulty = activity_settings.getDifficultyData(this);


        Intent intent = getIntent();
        level = intent.getIntExtra("position",-1);

        game = new GamePlay(difficulty);
        if(level != -1){
            Word levelwords = new Word(5);
            levelSet = levelwords.getLevel(level);

            game.setSelectedLevel(levelwords.getLevel(level));
            iterator = 0;

        }
        answer = (EditText) findViewById(R.id.et_answer);
        submit = (Button) findViewById(R.id.bt_submit);
        score = findViewById(R.id.tv_score);
        Button key = findViewById(R.id.bt_viewKey);

        getWord();
        key.setOnClickListener(v-> openKey());
        submit.setOnClickListener(v->checkAnswer());

        lives = findViewById(R.id.Life_Counter);
        lives.setText(String.valueOf(game.getAttempts()));

        best = findViewById(R.id.tv_best);
        best.setText(String.valueOf(activity_settings.getHighScore(this,difficulty)));


        timer = (TextView) findViewById(R.id.TimeCounter);
        Timer();

        ActionBar backbar = getSupportActionBar();
        backbar.setDisplayHomeAsUpEnabled(true);

        animatescore = findViewById(R.id.addScore);



    }

    private void levelGame() {
    }

    private void getWord() {
        TextView mystery = findViewById(R.id.tv_cipher);
        if(level ==-1){
            Word word = new Word(difficulty);
            originalWord = word.getOriginal_word();

            mystery.setText(word.getTransformed_word(originalWord));
        }else{
            if(iterator == 5){
                Intent intent = new Intent(this,levels.class);
                startActivity(intent);
                finish();
            }else{
                originalWord = levelSet[iterator];
                mystery.setText(transformWord());
                iterator++;

            }


        }

    }
    private String transformWord(){
        String[] symbols = {"(! ","(@ ","(# ","($ ","(% ","(^ ","(& ","(* ","(- ","(+ ",
                "[! ","[@ ","[# ","[$ ","[% ","[^ ","[& ","[* ","[- ","[+ ",
                "{! ","{@ ","{# ","{$ ","{% ","{^ ","{& ","{* ","{- ","{+ "};

        String transformed_word = "";
        String original_Word = originalWord.toLowerCase(Locale.ROOT);
        for(int i = 0; i < original_Word.length(); i++){
            int ascii = original_Word.charAt(i)-97;
            transformed_word = transformed_word+symbols[ascii];
        }
        return transformed_word;
    }

    private void checkAnswer() {
        EditText answer = findViewById(R.id.et_answer);
        String response = answer.getText().toString().toLowerCase(Locale.ROOT);
        if(response.equals("")){

        }
        else if(response.equals(originalWord.toLowerCase(Locale.ROOT))){
            correct = MediaPlayer.create(this,R.raw.correct);
            correct.start();
            answer.setText("");
            game.addScore();

            animationTimer();
            animatescore.setText("+" + Integer.toString(50+difficulty*50));

            Animation animatefadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
            animatescore.setAnimation(animatefadein);

            score.setText(String.valueOf(game.getScore()));
            pausetimer();
            timeleftinmillis+=5000;
            resumetimer();

            nextWord();
        }
        else{
            Toast.makeText(this,"incorrect", Toast.LENGTH_SHORT).show();
            incorrect = MediaPlayer.create(this,R.raw.negative_beeps);
            incorrect.start();
            //Toast.makeText(this,"incorrect "+originalWord, Toast.LENGTH_SHORT).show();
            game.reduceAttempts();

            if(game.getAttempts() < 0){
                if(game.getScore() > Integer.parseInt(best.getText().toString())){
                    activity_settings.saveHighScore(activity_play.this,game.getScore(),difficulty);
                }
                pausetimer();
                gameOver();

            }
            else{
                lives.setText(String.valueOf(game.getAttempts()));
            }


        }
    }

    private void gameOver() {
        gameOver = MediaPlayer.create(this,R.raw.decide);
        gameOver.start();
        game_over game_over = new game_over(score.getText().toString());
        game_over.setCancelable(false);
        game_over.show(getSupportFragmentManager(),"example dialog");

    }


    private void nextWord() {
        getWord();
    }

    private void openKey() {
        pausetimer();
        Intent intent = activity_view_key.getIntent(this, (int)timeleftinmillis);
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
                gameOver();
            }
        }.start();
        timerrunning = true;

    }
    private void updatetimer() {
        int seconds = (int) timeleftinmillis/1000 - 1;
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

    private void animationTimer(){
        animatetimer = 1000;
        animationTimer = new CountDownTimer(animatetimer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                animatetimer = millisUntilFinished;

            }

            @Override
            public void onFinish() {
                Animation animatefadeout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                animatescore.setAnimation(animatefadeout);

            }
        }.start();
        animaterunning = true;
    }

}