package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.adapters.RaPlayersVS;
import com.example.proyectofinaldam1.adapters.RaTournamentPlayed;
import com.example.proyectofinaldam1.models.DataBaseJSON;
import com.example.proyectofinaldam1.models.Torneo;
import com.example.proyectofinaldam1.models.Usuario;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

        tvUser = (TextView) findViewById(R.id.txtUserName);
        tvPoints = (TextView) findViewById(R.id.txtPointsUser);
        ivTier = (ImageView) findViewById(R.id.ivLvlUser);
        rvPlayersVS = (RecyclerView) findViewById(R.id.rvJE);
        rvTournamentPlayed = (RecyclerView) findViewById(R.id.rvTJ);

        Gson gson = new Gson();
        String strIntent = getIntent().getStringExtra("activity_anterior");

        if (strIntent.equals("PrimerActivity")){
            tvUser.setText(DataBaseJSON.userFirebase.getDisplayName());
            tvPoints.setText("Cargando");
            DataBaseJSON.getUsuario(DataBaseJSON.userFirebase.getUid(), new DataBaseJSON.UsuarioCallback() {
                @Override
                public void onUsuarioObtenido(Usuario usuario) {
                    if (usuario != null){
                        tvPoints.setText("Puntos: " + usuario.getPoints());
                    }
                }
                @Override
                public void onUsersObtendio(List<Usuario> users) {

                }
            });
        }else{
            Usuario usr = gson.fromJson(strIntent,Usuario.class);
            tvUser.setText(usr.getNick());
            tvPoints.setText("Point: " + usr.getPoints());
        }
    }
}