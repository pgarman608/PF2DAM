package com.example.proyectofinaldam1.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.models.DataBaseJSON;
import com.example.proyectofinaldam1.models.Torneo;

public class TournamentCreateActivity extends AppCompatActivity {
    private Button btnCreate;
    private EditText etTitle;
    private EditText etInfo;
    private EditText etMaxPlayer;
    private EditText etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_create);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        etTitle = (EditText) findViewById(R.id.edtTitleCreate);
        etInfo = (EditText) findViewById(R.id.edtCreateInfo);
        etMaxPlayer = (EditText) findViewById(R.id.edtMxJP);
        etPrecio = (EditText) findViewById(R.id.edtPrecioEntr);
        btnCreate = (Button) findViewById(R.id.btnCreateTrn);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTitle = etTitle.getText().toString();
                String strInfo = etInfo.getText().toString();
                String strMaxPlayer = etMaxPlayer.getText().toString();
                String strPrecio = etPrecio.getText().toString();

                if (!TextUtils.isEmpty(strTitle)){
                    if (!TextUtils.isEmpty(strInfo)){
                        if (!TextUtils.isEmpty(strMaxPlayer)){
                            if (!TextUtils.isEmpty(strPrecio)){
                                Torneo torneo = new Torneo("asd",DataBaseJSON.fbAuth.getUid(),strTitle,strInfo,Integer.parseInt(strMaxPlayer),Integer.parseInt(strPrecio));
                                int funciona = DataBaseJSON.createTournament(torneo);
                                if (funciona == -1){
                                    createToast("No funciona");
                                }else{
                                    if(funciona == 1){
                                        createToast("Funciona");
                                        onBackPressed();
                                    }else{
                                        if (funciona == -2) {
                                            createToast("No funciona da excepcion");
                                        }
                                    }
                                }
                            }else{
                                createToast("El torneo necesita un precio por entrada");
                            }
                        }else{
                            createToast("El torneo debe tener un limite de Jugadores");
                        }
                    }else{
                        createToast("El torneo debe tiener informacion" );
                    }
                }else{
                    createToast("El titulo está vacio");
                }
            }
        });
    }
    private void createToast(String info){
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }
}