package com.example.proyectofinaldam1.controlers;

import androidx.annotation.NonNull;
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
import com.example.proyectofinaldam1.models.DataBaseJSON;
import com.example.proyectofinaldam1.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RankingListActivity extends AppCompatActivity implements DataBaseJSON.UsuarioCallback {
    public Button btnSearch;
    public EditText etSearch;
    public RecyclerView rvRnk;
    private static List<Usuario> users;
    private RaRanking raRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        btnSearch = (Button) findViewById(R.id.btnSearchRnk);
        etSearch = (EditText) findViewById(R.id.edtNameUserSearch);
        rvRnk = (RecyclerView) findViewById(R.id.rvRnk);

        int tierSearch = getIntent().getIntExtra("Tier",-1);

        users = new ArrayList<>();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        raRanking = new RaRanking(users);
        rvRnk.setAdapter(raRanking);
        rvRnk.setLayoutManager(layout);

        DataBaseJSON.GetUsersTask getUsersTask = new DataBaseJSON.GetUsersTask(tierSearch,this,this);
        getUsersTask.execute();

        Gson gson = new Gson();
        raRanking.setOnItemClickListener(new RaRanking.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Usuario posUser = users.get(position);
                String strIntent = gson.toJson(posUser);
                Intent intentToUser = new Intent(RankingListActivity.this,UserActivity.class);
                intentToUser.putExtra("activity_anterior",strIntent);
                startActivity(intentToUser);
            }
        });
    }

    @Override
    public void onUsuarioObtenido(Usuario usuario) {

    }

    @Override
    public void onUsersObtendio(List<Usuario> usuarios) {
        users = usuarios;
        raRanking.setUsers(users);
    }
}