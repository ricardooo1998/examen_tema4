package com.example.ricar.tema4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ricar.tema4.Model.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosFavoritos extends AppCompatActivity {
    DatabaseReference bbdd;
    ArrayList<Producto> llistado;
    ListView llistadoFavorito;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_favoritos);
        llistado = new ArrayList<Producto>();
        llistadoFavorito = findViewById(R.id.llistadoFavoritos);

        bbdd = FirebaseDatabase.getInstance().getReference("productos").child("favoritos");
        bbdd.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot : dataSnapshot.getChildren())
                {
                    Producto p = (Producto) datasnapshot.getValue();
                    String nombreDelCreador = p.getNombreUsuario();
                    String nombre = p.getNombre();
                    String descripcion = p.getDescripcion();
                    String categoria = p.getCategoria();
                    String precio = p.getPrecio();
                    Producto productoFavorito = new Producto(nombreDelCreador, nombre, descripcion, categoria, precio);

                    llistado.add(productoFavorito);
                    arrayAdapter.add(llistado);
                    llistadoFavorito.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
