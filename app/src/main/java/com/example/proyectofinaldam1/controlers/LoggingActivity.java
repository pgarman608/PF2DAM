package com.example.proyectofinaldam1.controlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.models.DataBaseJSON;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvToRegister;
    private EditText etGMAIL;
    private EditText etPW;
    private Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        tvToRegister = (TextView) findViewById(R.id.txtToRegister);
        etGMAIL = (EditText) findViewById(R.id.edtgmailLI);
        etPW = (EditText) findViewById(R.id.edtContrasenaLI);
        btnLog = (Button) findViewById(R.id.btnLogIn);

        tvToRegister.setOnClickListener(this);
        btnLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intentLog = null;
        switch (v.getId()) {
            case R.id.txtToRegister:
                intentLog = new Intent(LoggingActivity.this, RegisterActivity.class);
                startActivity(intentLog);
                break;
            case R.id.btnLogIn:
                initSesion(etGMAIL.getText().toString(),etPW.getText().toString());
                onBackPressed();
                break;
        }
    }
    private void initSesion(String email,String password){
        DataBaseJSON.fbAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("funciona", "signInWithEmail:success");
                            FirebaseUser user = DataBaseJSON.fbAuth.getCurrentUser();
                            DataBaseJSON.userFirebase = user;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Nop", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoggingActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}