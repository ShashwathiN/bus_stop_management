package com.example.internship;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash1 extends AppCompatActivity {
    Animation anim;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash1);
        //this will bind your MainActivity.class file with activity_main.
        imageview = (ImageView) findViewById(R.id.imageview1);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.animator.animator);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(splash1.this, MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageview.startAnimation(anim);
    }
}