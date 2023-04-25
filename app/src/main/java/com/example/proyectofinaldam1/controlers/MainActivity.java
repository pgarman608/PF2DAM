package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.proyectofinaldam1.R;

public class MainActivity extends AppCompatActivity {
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

    }
}