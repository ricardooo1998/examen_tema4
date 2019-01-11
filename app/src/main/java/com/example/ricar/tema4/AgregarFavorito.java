package com.example.ricar.tema4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Producto;
import com.example.ricar.tema4.Model.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AgregarFavorito extends AppCompatActivity {
    DatabaseReference bbdd;
    EditText nombreDelArticulo;
    Button añadir;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_favorito);

        añadir = findViewById(R.id.añadirFavorito);
        bbdd = FirebaseDatabase.getInstance().getReference("productos");
        nombreDelArticulo = findViewById(R.id.nombreProductoFavorito);
        final String articulo = nombreDelArticulo.getText().toString();


        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articulo=="")
                {
                    Toast.makeText(getApplicationContext(), "Debes introducir algún articulo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth = FirebaseAuth.getInstance();
                    Query q=bbdd.orderByChild("nombre").equalTo(articulo);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue()==null)
                            {
                                Toast.makeText(getApplicationContext(), "Comprueba de que has introducido correctamente el nombre del articulo que quieres añadir", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if (dataSnapshot.getChildrenCount()>0){
                                    String clave = bbdd.push().getKey();

                                    Producto p = (Producto) dataSnapshot.getValue();
                                    String nombreUsuario = mAuth.getCurrentUser().toString();
                                    String nombreArticulo = p.getNombre();
                                    String descripcion = p.getDescripcion();
                                    String categoria = p.getCategoria();
                                    String precio = p.getPrecio();

                                    Producto p2 = new Producto(nombreUsuario, nombreArticulo, descripcion, categoria, precio);
                                    Map newPost = new HashMap();
                                    newPost.put("nombre del creador", nombreUsuario);
                                    newPost.put("nombre del articulo", nombreArticulo);
                                    newPost.put("descripcion", descripcion);
                                    newPost.put("categoria", categoria);
                                    newPost.put("precio", precio);
                                    bbdd.child(clave).setValue(newPost);
                                    Toast.makeText(getApplicationContext(), "Se ha insertado el articulo: "+nombreArticulo+" a favoritos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
