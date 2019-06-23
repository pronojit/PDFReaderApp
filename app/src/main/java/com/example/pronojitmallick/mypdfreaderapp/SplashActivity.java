package com.example.pronojitmallick.mypdfreaderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t = new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    //Toast.makeText(getApplicationContext(),"Taking you to the next Activity", Toast.LENGTH_LONG).show();
                }

            }
        };
        t.start();
    }
}
