package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.adapters.RaRanking;

public class TournamentListActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCreateTrn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        btnCreateTrn = (Button) findViewById(R.id.btnAddTrn);

        btnCreateTrn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTrn:
                Intent intentTrnCreate = new Intent(TournamentListActivity.this, TournamentCreateActivity.class);
                startActivity(intentTrnCreate);
                break;
        }
    }
}