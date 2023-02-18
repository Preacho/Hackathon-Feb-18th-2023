package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lostintranslation.model.Word;

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

        getWord();
        key.setOnClickListener(v-> openKey());
        submit.setOnClickListener(v->checkAnswer());

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
        startActivity(intent);

    }
}