package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lostintranslation.model.Word;

public class levels extends AppCompatActivity {
    ActionBar ab;
    private ListView listView;
    private Word word;
    private String[] levelNames = {"","","","",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_levels);
        word = new Word(1);
        for(int i = 0; i<5; i++){
            levelNames[i] = "Level "+(i+1);
        }
        listView = findViewById(R.id.lv_levels);
        fillList();
        listClick();


    }

    private void listClick() {
        listView.setOnItemClickListener(((parent, view, position, id) -> {
            Intent intent = new Intent(this,activity_play.class);
            intent.putExtra("position",position);
            startActivity(intent);
            finish();

        }));
    }

    private void fillList() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.list_levels,levelNames);

        listView.setAdapter(arrayAdapter);
    }
}