package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.adapters.RaRanking;

public class TournamentListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));
    }
}