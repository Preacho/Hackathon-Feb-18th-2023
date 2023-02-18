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
    }

    private void openRules() {
        Intent intent = new Intent();
    }
}