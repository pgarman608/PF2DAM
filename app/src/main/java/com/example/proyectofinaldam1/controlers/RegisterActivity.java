package com.example.proyectofinaldam1.controlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinaldam1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_IMAGE_PICK = 1;
    private FirebaseAuth mAuth;

    private EditText edGMAIL;
    private EditText edPW;
    private Button btnRegister;
    private EditText edNick;
    private TextView tvTolog;
    private Button btnSelectImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(Color.parseColor("#000000"));

        mAuth = FirebaseAuth.getInstance();

        edGMAIL = (EditText) findViewById(R.id.edtgmailR);
        edPW = (EditText) findViewById(R.id.edtPWR);
        edNick = (EditText) findViewById(R.id.edtNickR);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvTolog = (TextView) findViewById(R.id.tvToLogin);

        tvTolog.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentRegister = null;
        switch (v.getId()){
            case R.id.btnRegister:
                register();
                intentRegister = new Intent(RegisterActivity.this, MainActivity.class);
                intentRegister.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            case R.id.tvToLogin:
                intentRegister = new Intent(RegisterActivity.this, LoggingActivity.class);
                break;
        }
        startActivity(intentRegister);
        finish();
    }

    private void register(){
        String email = edGMAIL.getText().toString().trim();
        String password = edPW.getText().toString().trim();
        String nick = edNick.getText().toString().trim();

        // Validar las credenciales de inicio de sesión y el nick
        if (TextUtils.isEmpty(email)) {
            edGMAIL.setError("Ingrese su dirección de correo electrónico");
        }else{
            if (TextUtils.isEmpty(password)) {
                edPW.setError("Ingrese su contraseña");
            }else{
                if (password.length() < 6) {
                    edPW.setError("La contraseña debe tener al menos 6 caracteres");
                }else{
                    if (TextUtils.isEmpty(nick)) {
                        edNick.setError("Ingrese un nick para su cuenta");
                    }else{
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // El usuario se registró exitosamente
                                        MainActivity.userFirebase = FirebaseAuth.getInstance().getCurrentUser();
                                        if (MainActivity.userFirebase != null) {
                                            // Agregar el nick ingresado por el usuario a los datos de usuario en Firebase
                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(nick)
                                                    .build();
                                            MainActivity.userFirebase.updateProfile(profileUpdates);
                                        }
                                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        // Create a new user with authentication_id and smash_points
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("authentication_id", userId);
                                        user.put("nick",nick);
                                        user.put("smash_points", 100);
                                        user.put("usuarios_jugados", new HashMap<String, Integer>());
                                        user.put("torneos_jugados", new ArrayList<String>());

                                        // Add the user to the "usuarios" collection with the user's ID as the document ID
                                        db.getReference("usuarios").child(userId)
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d("funciona basededatos", "User created successfully");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.e("funciona basededatos", "Error creating user: " + e.getMessage());
                                                    }
                                                });
                                        // Puedes iniciar sesión con el nuevo usuario aquí si lo deseas
                                    } else {
                                        // Si se produjo un error al registrar el usuario, muestra un mensaje de error
                                        Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    }
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}