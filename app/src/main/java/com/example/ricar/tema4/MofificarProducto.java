package com.example.ricar.tema4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MofificarProducto extends AppCompatActivity {
    Spinner modificarProducto;
    ArrayAdapter<CharSequence> adapter;
    DatabaseReference bbdd;
    Button modificar;
    EditText nombre;
    EditText precio;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mofificar_producto);
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
        modificar = findViewById(R.id.modificarProducto);
        nombre = findViewById(R.id.modificarNombreProducto);
        precio = findViewById(R.id.modificarPrecioProducto);
        descripcion = findViewById(R.id.modificarDescripcionProducto);
        modificarProducto = findViewById(R.id.modificarCategoria);
        adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        modificarProducto.setAdapter(adapter);


        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txtNombre = nombre.getText().toString();
                final String txtPrecio = precio.getText().toString();
                final String txtDescripcion = descripcion.getText().toString();
                final String txtCategoria = modificarProducto.getSelectedItem().toString();
                modificarDatosProducto(txtNombre, txtDescripcion, txtCategoria, txtPrecio);
            }
        });
    }

    public void modificarDatosProducto(final String txtNombre, final String txtDescripcion, final String txtCategoria, final String txtPrecio)
    {
        if (!TextUtils.isEmpty(txtNombre))
        {
            Query q = bbdd.orderByChild(String.valueOf(getString(R.string.nombre_del_producto).equals(txtNombre)));
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren())
                    {
                        String clave = datasnapshot.getKey();
                        bbdd.child(clave).child("nombre").setValue(txtNombre);
                        bbdd.child(clave).child("descripcion").setValue(txtDescripcion);
                        bbdd.child(clave).child("categoria").setValue(txtCategoria);
                        bbdd.child(clave).child("precio").setValue(txtPrecio);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Toast.makeText(getApplicationContext(), "Se ha modificado la información del producto: "+txtNombre+".", Toast.LENGTH_SHORT);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Debes introducir el nombre de usuario", Toast.LENGTH_SHORT);
        }
    }
}
