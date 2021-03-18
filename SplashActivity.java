package com.example.sonyvaio.funtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView iv1;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv1= (ImageView) findViewById(R.id.iv1);

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.custom);
        iv1.startAnimation(animation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

            }
        }).start();
    }
}


