package com.example.proyectofinaldam1.controlers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.adapters.RaRanking;
import com.example.proyectofinaldam1.adapters.RaTournaments;
import com.example.proyectofinaldam1.models.DataBaseJSON;
import com.example.proyectofinaldam1.models.Torneo;
import com.example.proyectofinaldam1.models.Usuario;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TournamentListActivity extends AppCompatActivity implements DataBaseJSON.UsuarioCallback{

    private Button btnCreateTrn;

    private RecyclerView rvTournaments;

    private List<Torneo> tournaments;

    private RaTournaments raTournaments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        btnCreateTrn = (Button) findViewById(R.id.btnAddTrn);
        rvTournaments = (RecyclerView) findViewById(R.id.rvTrns);

        tournaments = new ArrayList<>();

        LinearLayoutManager layout = new LinearLayoutManager(this);
        raTournaments = new RaTournaments(tournaments);

        rvTournaments.setAdapter(raTournaments);
        rvTournaments.setLayoutManager(layout);
        btnCreateTrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAddTrn:
                        Intent intentTrnCreate = new Intent(TournamentListActivity.this, TournamentCreateActivity.class);
                        startActivity(intentTrnCreate);
                        break;
                }
            }
        });
        Gson gson = new Gson();
        raTournaments.setOnItemClickListener(new RaTournaments.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                try {
                    Torneo posTrn = tournaments.get(position);
                    String strIntent = gson.toJson(posTrn);
                    Intent intentToTrn = new Intent(TournamentListActivity.this, TournamentActivity.class);
                    intentToTrn.putExtra("activity_anterior",strIntent);
                    startActivity(intentToTrn);
                }catch (Exception ex) {
                    Log.e("to trn", "onItemClick: " + ex.getMessage());
                }
            }
        });
        DataBaseJSON.GetTrnsTask getTournaments = new DataBaseJSON.GetTrnsTask(this, this);
        getTournaments.execute();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onUsuarioObtenido(Usuario usuario) {

    }

    @Override
    public void onUsersObtenido(List<Usuario> users) {

    }

    @Override
    public void onTrnsObtenido(List<Torneo> torneos) {
        tournaments = torneos;
        raTournaments.setTournaments(tournaments);
    }
}