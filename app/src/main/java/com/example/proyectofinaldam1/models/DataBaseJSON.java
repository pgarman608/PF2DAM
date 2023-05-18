package com.example.proyectofinaldam1.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proyectofinaldam1.controlers.RankingActivity;
import com.example.proyectofinaldam1.controlers.RankingListActivity;
import com.example.proyectofinaldam1.controlers.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataBaseJSON {
    public static FirebaseDatabase dbFirebase = FirebaseDatabase.getInstance();
    public static FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    public static FirebaseUser userFirebase;
    private static DatabaseReference refDB = dbFirebase.getReference("usuarios");
    public static interface UsuarioCallback {
        void onUsuarioObtenido(Usuario usuario);
        void onUsersObtendio(List<Usuario> users);
    }
    public static void getUsuario(String uid,UsuarioCallback callback){
        List<Usuario> listaUsuarios = new ArrayList<>();
        refDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    int points = 0;
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        HashMap<String, Object> childData = (HashMap<String, Object>) userSnapshot.getValue();
                        Gson gson = new Gson();
                        String json = gson.toJson(childData);
                        Usuario usuario = gson.fromJson(json, Usuario.class);
                        callback.onUsuarioObtenido(usuario);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase", "Error getting data", error.toException());
            }
        });
    }
    public static class GetUsersTask extends AsyncTask<Integer, Void, List<Usuario>> {
        private Context content;
        private UsuarioCallback callback;
        private int tier;
        public GetUsersTask(int tier, Context content, UsuarioCallback callback) {
            this.callback = callback;
            this.content = content;
            this.tier = tier;
        }

        @Override
        protected List<Usuario> doInBackground(Integer... voids) {
            Log.d("Usuarios", "onDataChange: ");
            List<Usuario> users = new ArrayList<>();
            refDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int index = 0;
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        HashMap<String, Object> childData = (HashMap<String, Object>) postSnapshot.getValue();
                        Gson gson = new Gson();
                        String json = gson.toJson(childData);
                        Usuario usuario = gson.fromJson(json, Usuario.class);
                        if (usuario.getPoints() <= tier){
                            users.add(usuario);
                        }
                    }
                    callback.onUsersObtendio(users);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return users;
        }
        @Override
        protected void onCancelled(List<Usuario> usuarios) {
            super.onCancelled(usuarios);

        }
    }
}
