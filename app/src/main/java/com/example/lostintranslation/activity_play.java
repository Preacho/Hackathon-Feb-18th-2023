package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class activity_play extends AppCompatActivity {
    ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_play);
        EditText answer = findViewById(R.id.et_answer);
        Button submit = findViewById(R.id.bt_submit);
        Button key = findViewById(R.id.bt_viewKey);
        key.setOnClickListener(v-> openKey());

    }

    private void openKey() {
        Intent intent = new Intent(this, activity_view_key.class);
        startActivity(intent);

    }
}