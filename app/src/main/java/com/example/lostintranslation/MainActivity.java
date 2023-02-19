package com.example.lostintranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private int[] imageid = {R.drawable.play, R.drawable.gear, R.drawable.question};
    private int[] imageviews = {R.id.playImageView,R.id.gearImageView, R.id.questionImageView};
    private int[] chartext = {R.id.chartext1, R.id.chartext2, R.id.chartext3, R.id.chartext4};
    private long timer = 100000000;
    private boolean timerun;
    private CountDownTimer countDownTimer;
    private TextView chartextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button rules = findViewById(R.id.bt_rules);
        Button settings = findViewById(R.id.bt_settings);
        Button play = findViewById(R.id.bt_play);


        rules.setOnClickListener(v-> openRules());
        settings.setOnClickListener(v -> openSettings());
        play.setOnClickListener(v -> openPlay());

        for(int i = 0; i < 3; i++){
            image = findViewById(imageviews[i]);
            image.setImageResource(imageid[i]);
        }
        Timer();



    }
    @Override
    protected void onResume(){
        super.onResume();
        Timer();
    }
    private void Timer(){
        countDownTimer = new CountDownTimer(timer, 1) {
            @Override
            public void onTick(long untilFinished) {
                timer = untilFinished;
                if(timer%97 == 0){
                    updatechartext();}

            }


            @Override
            public void onFinish() {

            }
        }.start();
        timerun = true;

    }

    private void openPlay() {
        //System.out.println(activity_settings.getDifficultyData(this));
        Intent intent = new Intent(this, activity_play.class);
        countDownTimer.cancel();
        startActivity(intent);
    }

    private void openRules() {
        Intent intent = new Intent(this, activity_about.class);
        countDownTimer.cancel();
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, activity_settings.class);
        countDownTimer.cancel();
        startActivity(intent);
    }
    private void updatechartext(){
        for(int i = 0; i < 4; i++){
            Random rand = new Random();
            int randomint = rand.nextInt(12) + 35;
            chartextview = findViewById(chartext[i]);
            chartextview.setText(Character.toString((char)randomint));

        }

    }
}