package com.example.ricar.tema4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button registrar;
    Button entrar;
    EditText login;
    EditText contrasenia;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrar = findViewById(R.id.registrar);
        entrar = findViewById(R.id.entrar);
        registrar.setOnClickListener(this);
        entrar.setOnClickListener(this);
        
        login = findViewById(R.id.email);
        contrasenia = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registrar:
                Intent i = new Intent(getApplicationContext(), ActivityRegistrar.class);
                startActivity(i);
                break;
                
            case R.id.entrar:
                String email = login.getText().toString();
                String password = contrasenia.getText().toString();
                loginUsuario(email, password);
                break;
        }
    }

    private void loginUsuario(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Accediendo."+user.getUid(), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), PantallaInicial.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Fallo en la Autentificación.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
