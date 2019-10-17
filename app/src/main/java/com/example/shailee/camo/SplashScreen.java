package com.example.shailee.camo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView = (TextView) findViewById(R.id.textView);
        textView.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.splash, 0, 0);
        Animation myanimation = AnimationUtils.loadAnimation(this, R.anim.mytranstion);
        textView.startAnimation(myanimation);
        final Intent i = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    startActivity(i);
                    finish();

                }
            }
        };
        timer.start();
    }


}
