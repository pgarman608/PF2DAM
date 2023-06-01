package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.models.Torneo;
import com.google.gson.Gson;

public class TournamentActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvInfo;
    private TextView tvPoints;
    private TextView tvPrecio;
    private TextView tvPlayers;
    private TextView tvMPlayers;
    private Button btnJPS;
    private Button btnSets;
    private Button btnStats;
    private Button btnJoins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        tvTitle = (TextView) findViewById(R.id.tvTitleTrns);
        tvInfo = (TextView) findViewById(R.id.tvInfoTrns);
        tvPoints = (TextView) findViewById(R.id.tvPointTrn);
        tvPrecio = (TextView) findViewById(R.id.tvPriceTrn);
        tvPlayers = (TextView) findViewById(R.id.tvNJP);
        tvMPlayers = (TextView) findViewById(R.id.tvMJP);

        btnJPS = (Button) findViewById(R.id.btnSeePlayers);
        btnSets = (Button) findViewById(R.id.btnSeeSets);
        btnStats = (Button) findViewById(R.id.btnUnite);
        btnJoins = (Button) findViewById(R.id.btnStartTrns);

        Gson gson = new Gson();
        String strIntent = getIntent().getStringExtra("activity_anterior");

        Torneo torneo = gson.fromJson(strIntent,Torneo.class);

        tvTitle.setText(torneo.getName());
        tvInfo.setText(torneo.getInfo());
        if (torneo.getUsersList() == null){
            tvPlayers.setText(""+0);
        }else{
            tvPlayers.setText(torneo.getUsersList().size());
        }
        tvMPlayers.setText(""+torneo.getNumMaxUsers());
        tvPoints.setText("Puntos: " + torneo.getAllPoints());
        tvPrecio.setText("Precio: " + torneo.getPrecioEntrada());

        btnSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TournamentActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
    }
}