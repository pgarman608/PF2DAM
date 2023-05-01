package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.adapters.RaPlayersVS;
import com.example.proyectofinaldam1.adapters.RaTournamentPlayed;
import com.example.proyectofinaldam1.models.Torneo;
import com.example.proyectofinaldam1.models.Usuario;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private TextView tvUser;
    private TextView tvPoints;
    private ImageView ivTier;
    private RecyclerView rvPlayersVS;
    private RecyclerView rvTournamentPlayed;

    private RaPlayersVS raPlayersVS;

    private RaTournamentPlayed raTournamentPlayed;

    private ArrayList<Torneo> tournaments;

    private ArrayList<Usuario> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        getActionBar().setTitle("Usuario");

        tvUser = (TextView) findViewById(R.id.txtPointsUser);
        tvPoints = (TextView) findViewById(R.id.txtPointsUser);
        ivTier = (ImageView) findViewById(R.id.ivLvlUser);
        rvPlayersVS = (RecyclerView) findViewById(R.id.rvJE);
        rvTournamentPlayed = (RecyclerView) findViewById(R.id.rvTJ);

        if (getIntent().getStringExtra("activity_anterior").equals("PrimerActivity")){

        }else{

        }
    }

}