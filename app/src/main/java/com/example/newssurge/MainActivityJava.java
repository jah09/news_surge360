package com.example.newssurge;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivityJava extends AppCompatActivity {

    ImageView imageViewLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        imageViewLogo=findViewById(R.id.imageViewLogo);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_fade_in);
        imageViewLogo.startAnimation(animation);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), Homepage.class);
                startActivity(intent);
            }
        },1500);
    }
}