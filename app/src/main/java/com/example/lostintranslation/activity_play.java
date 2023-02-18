package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lostintranslation.model.GamePlay;
import com.example.lostintranslation.model.Word;

public class activity_play extends AppCompatActivity {
    ActionBar ab;
    private long timeleftinmillis = 60000;
    private CountDownTimer countDownTimer;
    private boolean timerrunning;
    private TextView timer;
    private EditText answer;
    private Button submit;
    private GamePlay game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_play);

        game = new GamePlay();

        answer = (EditText) findViewById(R.id.et_answer);
        submit = (Button) findViewById(R.id.bt_submit);
        Button key = findViewById(R.id.bt_viewKey);

        getWord();
        key.setOnClickListener(v-> openKey());
        submit.setOnClickListener(v->checkAnswer());
        timer = (TextView) findViewById(R.id.TimeCounter);
        Timer();
    }

    private void getWord() {
        Word word = new Word();
        System.out.println(word.getOriginal_word());
        word.getTransformed_word();
        TextView mystery = findViewById(R.id.tv_cipher);
        mystery.setText(word.getOriginal_word());
    }

    private void checkAnswer() {
        EditText answer = findViewById(R.id.et_answer);
        System.out.println(answer.getText().toString());
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

}