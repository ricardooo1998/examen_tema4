package com.example.ricar.tema4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarProducto extends AppCompatActivity {
    Spinner categoria;
    ArrayAdapter<CharSequence> adapter;
    DatabaseReference bbdd;
    FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    EditText nombreProducto;
    EditText descripcion;
    EditText precio;
    Button añadirProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);


        nombreProducto = findViewById(R.id.nombreProducto);
        descripcion = findViewById(R.id.descripcionProducto);
        precio = findViewById(R.id.precioProducto);
        añadirProducto = findViewById(R.id.registrarProducto);


        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_productos));
        categoria = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        categoria.setAdapter(adapter);


        añadirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNombreProducto = nombreProducto.getText().toString();
                String txtDescripcion = descripcion.getText().toString();
                String opcion = categoria.getSelectedItem().toString();
                String txtPrecio = precio.getText().toString();
                String usuarioActual = user.getEmail();
                if (!TextUtils.isEmpty(txtNombreProducto))
                {
                    Producto p = new Producto(usuarioActual, txtNombreProducto, txtDescripcion, opcion, txtPrecio);
                    String clave = bbdd.push().getKey();
                    bbdd.child(clave).setValue(p);
                    Toast.makeText(getApplicationContext(), "Se ha añadido un nuevo producto: "+txtNombreProducto+".", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fallo en el registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
