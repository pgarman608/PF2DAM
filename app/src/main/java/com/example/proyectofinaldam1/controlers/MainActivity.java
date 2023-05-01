package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectofinaldam1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logBtn;
    private Button tnrBtn;
    private Button rnkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logBtn = (Button) findViewById(R.id.btnLog);
        tnrBtn = (Button) findViewById(R.id.btnTnr);
        rnkBtn = (Button) findViewById(R.id.btnRnk);
        logBtn.setOnClickListener(this);
        tnrBtn.setOnClickListener(this);
        rnkBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intentHome = null;
        switch (view.getId()){
            case R.id.btnLog:
                intentHome = new Intent(MainActivity.this, LoggingActivity.class);
                startActivity(intentHome);
                break;

            case R.id.btnTnr:
                intentHome = new Intent(MainActivity.this, TournamentActivity.class);
                startActivity(intentHome);
                break;

            case R.id.btnRnk:
                intentHome = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intentHome);
                break;

        }
    }
}