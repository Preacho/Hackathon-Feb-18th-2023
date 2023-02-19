package com.example.lostintranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rules = findViewById(R.id.bt_rules);
        Button settings = findViewById(R.id.bt_settings);
        Button play = findViewById(R.id.bt_play);


        rules.setOnClickListener(v-> openRules());
        settings.setOnClickListener(v -> openSettings());
        play.setOnClickListener(v -> openPlay());
    }

    private void openPlay() {
        //System.out.println(activity_settings.getDifficultyData(this));
        Intent intent = new Intent(this, activity_play.class);
        startActivity(intent);
    }

    private void openRules() {
        Intent intent = new Intent(this, activity_about.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
    }
}