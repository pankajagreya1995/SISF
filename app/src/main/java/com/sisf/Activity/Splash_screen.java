package com.sisf.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sisf.R;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ImageView img = (ImageView)findViewById(R.id.image);
        Animation Rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.cloclwise);
        img.startAnimation(Rotate);



        //stop screen 5 sec
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_screen.this,Home.class));
                finish();
            }
        };
        handler.postDelayed(runnable,5000);
    }
}
