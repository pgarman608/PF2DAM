package com.example.proyectofinaldam1.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectofinaldam1.controlers.RankingActivity;
import com.example.proyectofinaldam1.controlers.RankingListActivity;
import com.example.proyectofinaldam1.controlers.UserActivity;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
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
        void onUsersObtenido(List<Usuario> users);
        void onTrnsObtenido(List<Torneo> torneos);
    }
    private static int solucion;
    public static int createUser(String nick, String password, String email){
        solucion =0;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // El usuario se registró exitosamente
                            DataBaseJSON.userFirebase = FirebaseAuth.getInstance().getCurrentUser();
                            if (DataBaseJSON.userFirebase != null) {
                                // Agregar el nick ingresado por el usuario a los datos de usuario en Firebase
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nick)
                                        .build();
                                DataBaseJSON.userFirebase.updateProfile(profileUpdates);
                            }
                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            // Create a new user with authentication_id and smash_points
                            Usuario user = new Usuario(userId,nick,100);

                            // Add the user to the "usuarios" collection with the user's ID as the document ID
                            db.getReference("usuarios")
                                    .child(userId)
                                    .setValue(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            solucion =1;
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            solucion = -1;
                                        }
                                    });
                            // Puedes iniciar sesión con el nuevo usuario aquí si lo deseas
                        } else {
                            // Si se produjo un error al registrar el usuario, muestra un mensaje de error
                            solucion=-1;
                        }
                    }
                });
        return solucion;
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
            dbFirebase.getReference("usuarios")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            for (DataSnapshot postSnapshot: task.getResult().getChildren()) {
                                HashMap<String, Object> childData = (HashMap<String, Object>) postSnapshot.getValue();
                                Gson gson = new Gson();
                                String json = gson.toJson(childData);
                                Usuario usuario = gson.fromJson(json, Usuario.class);
                                if (usuario.getPoints() <= tier){
                                    users.add(usuario);
                                }
                            }
                            callback.onUsersObtenido(users);
                        }
                    });
            return users;
        }
        @Override
        protected void onCancelled(List<Usuario> usuarios) {
            super.onCancelled(usuarios);
        }
    }
    public static int createTournament(Torneo torneo){
        solucion = 0;
        try{
            dbFirebase.getReference("Torneos")
                    .child(""+torneo.hashCode())
                    .setValue(torneo)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            solucion = 1;
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            solucion = -1;
                        }
                    });
        }catch (Exception ex){
            Log.e("error create tornament", "createTournament: " +ex.getMessage());
            solucion = -2;
        }
        return solucion;
    }
    public static class GetTrnsTask extends AsyncTask<Integer, Void, List<Torneo>> {
        private Context content;
        private UsuarioCallback callback;
        public GetTrnsTask( Context content, UsuarioCallback callback) {
            this.callback = callback;
            this.content = content;
        }
        @Override
        protected List<Torneo> doInBackground(Integer... voids) {
            solucion = 0;
            List<Torneo> torneos = new ArrayList<>();
            dbFirebase.getReference("Torneos").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    for (DataSnapshot tsk: task.getResult().getChildren()) {
                        HashMap<String, Object> childData = (HashMap<String, Object>) tsk.getValue();
                        Gson gson = new Gson();
                        String json = gson.toJson(childData);
                        Torneo trn = gson.fromJson(json, Torneo.class);
                        Log.e("error torneo", "onComplete: "+ trn);
                        torneos.add(trn);
                    }
                    callback.onTrnsObtenido(torneos);
                }
            });
            return torneos;
        }
        @Override
        protected void onCancelled(List<Torneo> tournaments) {
            super.onCancelled(tournaments);
            Log.e("error torneo 2", "onComplete: "+ tournaments.size());
        }
    }
}
