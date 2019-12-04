package com.sisf.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sisf.R;

public class Quiz_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        int Chapt_ID=getIntent().getIntExtra("position",0);
        Toast.makeText(this, "Position"+Chapt_ID, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chapter NO:"+Chapt_ID);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void button_next(View view) {
        startActivity(new Intent(Quiz_screen.this,Result_Screen.class));
        finish();
    }
}
