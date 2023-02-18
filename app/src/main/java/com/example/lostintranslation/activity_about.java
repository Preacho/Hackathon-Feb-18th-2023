package com.example.lostintranslation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class activity_about extends AppCompatActivity {
    ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_about);

        ImageView image = (ImageView) findViewById(R.id.TableImageView);
        image.setImageResource(R.drawable.alphabet_key);
    }
}