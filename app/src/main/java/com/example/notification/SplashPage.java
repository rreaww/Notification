package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashPage extends AppCompatActivity {

    private static int SLEEP_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        runtime();
    }

    private void runtime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SLEEP_TIME);
    }

}
