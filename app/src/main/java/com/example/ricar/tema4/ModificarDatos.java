package com.example.ricar.tema4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Usuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModificarDatos extends AppCompatActivity implements View.OnClickListener {
    EditText nombreUsuario;
    EditText nombre;
    EditText apellidos;
    EditText direccion;
    Button modificar;

    DatabaseReference ddbb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);

        nombreUsuario = findViewById(R.id.modificarNombreUsuario);
        nombre = findViewById(R.id.modificarNombre);
        apellidos = findViewById(R.id.modificarApellido);
        direccion = findViewById(R.id.modificarDireccion);

        modificar = findViewById(R.id.modificar);

        ddbb = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usuarios));
        modificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.modificar:
                String txtNombreUsuario = nombreUsuario.getText().toString();
                String txtNombre = nombre.getText().toString();
                String txtApellido = apellidos.getText().toString();
                String txtDireccion = direccion.getText().toString();
                modificarDatosUsuario(txtNombreUsuario, txtNombre, txtApellido, txtDireccion);
                break;

        }
    }

    private void modificarDatosUsuario(final String txtNombreUsuario, final String txtNombre, final String txtApellido, final String txtDireccion) {
        if (!TextUtils.isEmpty(txtNombreUsuario))
        {
            Query q = ddbb.orderByChild(String.valueOf(getString(R.string.nombre_de_usuario).equals(txtNombreUsuario)));
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren())
                    {
                        String clave = datasnapshot.getKey();
                        ddbb.child(clave).child("nombre").setValue(txtNombre);
                        ddbb.child(clave).child("apellido").setValue(txtApellido);
                        ddbb.child(clave).child("direccion").setValue(txtDireccion);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Toast.makeText(getApplicationContext(), "Se ha modificado la información del usuario: "+nombreUsuario+".", Toast.LENGTH_SHORT);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Debes introducir el nombre de usuario", Toast.LENGTH_SHORT);
        }
    }
}
