package com.example.lostintranslation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.ImageView;

public class activity_view_key extends AppCompatActivity {
    ActionBar ab;
    private long sentovertimer;
    private boolean boltimer;
    private CountDownTimer timer;

    public static Intent getIntent(Context context, int timer){
        Intent intent = new Intent(context, activity_view_key.class);
        intent.putExtra("SENDOVERTIMER", timer);
        return intent;
    }
    private void getDataFromIntent(){
        Intent intent = getIntent();
        sentovertimer = intent.getIntExtra("SENDOVERTIMER", 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_key);
        getDataFromIntent();
        ActionBar backBar = getSupportActionBar();
        backBar.setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem holder){
        switch (holder.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(holder);

    }
    private void Timer(){
        timer = new CountDownTimer(sentovertimer,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sentovertimer = millisUntilFinished;

            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
        boltimer = true;
    }
}