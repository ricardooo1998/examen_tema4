package com.example.ricar.tema4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Usuarios;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatosUsuario extends AppCompatActivity {
    EditText email;
    EditText NombreUsuario;
    EditText Nombre;
    EditText Apellidos;
    EditText Direccion;
    Button enviar;

    DatabaseReference baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);
        baseDatos = FirebaseDatabase.getInstance().getReference("usuarios");

        email = findViewById(R.id.Email);
        NombreUsuario = findViewById(R.id.NombreUsuario);
        Nombre = findViewById(R.id.NombreReal);
        Apellidos = findViewById(R.id.Apellidos);
        Direccion = findViewById(R.id.Direcion);
        enviar = findViewById(R.id.enviarDatos);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtNombreUsuario = NombreUsuario.getText().toString();
                String txtNombre = Nombre.getText().toString();
                String txtApellidos = Apellidos.getText().toString();
                String txtDirreccion = Direccion.getText().toString();

                if (TextUtils.isEmpty(txtEmail))
                {
                    Toast.makeText(getApplicationContext(), "Error, Debes introducir algun email", Toast.LENGTH_LONG).show();
                        if (TextUtils.isEmpty(txtNombreUsuario))
                        {
                            Toast.makeText(getApplicationContext(), "Error, Debes introducir el nombre de usuario", Toast.LENGTH_LONG).show();
                            if (TextUtils.isEmpty(txtNombre))
                            {
                                Toast.makeText(getApplicationContext(), "Error, Debes introducir el nombre", Toast.LENGTH_LONG).show();
                                if (TextUtils.isEmpty(txtApellidos))
                                {
                                    Toast.makeText(getApplicationContext(), "Error, Debes introducir los apellidos", Toast.LENGTH_LONG).show();
                                    if (TextUtils.isEmpty(txtDirreccion));
                                    {
                                        Toast.makeText(getApplicationContext(), "Error, Debes introducir la direccion", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        Usuarios u = new Usuarios(txtEmail, txtNombreUsuario, txtNombre, txtApellidos, txtDirreccion);
                        String clave = baseDatos.push().getKey();
                        baseDatos.child(clave).setValue(u);
                        Toast.makeText(getApplicationContext(), "Se añadio un Nuevo usuario: [ "+NombreUsuario+" ]", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), PantallaInicial.class);
                        startActivity(i);
                    }



                }
        });
    }
}
