package com.example.conwaysgameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.conwaysgameoflife.views.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameofLifeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameofLifeView = (GameView) findViewById(R.id.game_of_life);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameofLifeView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameofLifeView.stop();
    }
}
