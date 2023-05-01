package com.example.proyectofinaldam1.controlers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.proyectofinaldam1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logBtn;
    private Button tnrBtn;
    private Button rnkBtn;
    private FirebaseAuth fAuth;
    public static FirebaseUser userFirebase;

    private androidx.appcompat.view.ActionMode mLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        ActionBar barra = getSupportActionBar();
        barra.setTitle(Html.fromHtml("<font color='#006ca0'>Crear Imagen</font>"));
        getSupportActionBar().setTitle("hoal");


        logBtn = (Button) findViewById(R.id.btnLog);
        tnrBtn = (Button) findViewById(R.id.btnTnr);
        rnkBtn = (Button) findViewById(R.id.btnRnk);
        logBtn.setOnClickListener(this);
        tnrBtn.setOnClickListener(this);
        rnkBtn.setOnClickListener(this);
        logBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean active = false;
                if (mLog == null && userFirebase != null){
                    mLog = startSupportActionMode(mLogCallback);
                    active = true;
                }
                return active;
            }
        });
        fAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null){
            userFirebase = currentUser;
            logBtn.setText(userFirebase.getDisplayName().substring(0,3));
            Log.i("Cargar user", "onStart: No cargar pero funciona" + currentUser.getDisplayName());
        }else{
            registerForContextMenu(logBtn);
            Log.i("Cargar user", "onStart: No cargar pero funciona");
        }
    }

    @Override
    public void onClick(View view) {
        Intent intentHome = null;
        switch (view.getId()){
            case R.id.btnLog:
                if (userFirebase == null){
                    intentHome = new Intent(MainActivity.this, LoggingActivity.class);
                }else{
                    intentHome = new Intent(MainActivity.this, UserActivity.class);
                    intentHome.putExtra("activity_anterior", "PrimerActivity");
                }
                startActivity(intentHome);
                break;

            case R.id.btnTnr:
                intentHome = new Intent(MainActivity.this, TournamentActivity.class);
                startActivity(intentHome);
                break;

            case R.id.btnRnk:
                intentHome = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intentHome);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(userFirebase != null){
            logBtn.setText(userFirebase.getDisplayName().substring(0,3));
        }
    }

    private ActionMode.Callback mLogCallback = new ActionMode.Callback() {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_btn_main,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            switch(itemId){
                case R.id.close_user:
                    userFirebase = null;
                    fAuth.signOut();
                    logBtn.setText("LOG");
                    break;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mLog = null;
        }
    };
}