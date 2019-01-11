package com.example.ricar.tema4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaInicial extends AppCompatActivity implements View.OnClickListener {
    Button agregarUsuario;
    Button modificarDatos;
    Button agreagarProducto;
    Button modificarProducto;
    Button eliminarProducto;
    Button favoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);

        agregarUsuario = findViewById(R.id.agregarUsuarios);
        modificarDatos = findViewById(R.id.modificarDatos);
        agreagarProducto = findViewById(R.id.añadirProducto);
        modificarProducto = findViewById(R.id.modificarProducto);
        eliminarProducto = findViewById(R.id.eliminarProducto);


        favoritos = findViewById(R.id.misFavoritos); //examen

        agregarUsuario.setOnClickListener(this);
        modificarDatos.setOnClickListener(this);
        agreagarProducto.setOnClickListener(this);
        modificarProducto.setOnClickListener(this);
        eliminarProducto.setOnClickListener(this);

        favoritos.setOnClickListener(this); //examen
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.agregarUsuarios:
                Intent i1 = new Intent(getApplicationContext(), DatosUsuario.class);
                startActivity(i1);
                break;
            case R.id.modificarDatos:
                Intent i2 = new Intent(getApplicationContext(), ModificarDatos.class);
                startActivity(i2);
                break;
            case R.id.añadirProducto:
                Intent i3 = new Intent(getApplicationContext(), AgregarProducto.class);
                startActivity(i3);
                break;
            case R.id.modificarProducto:
                Intent i4 = new Intent(getApplicationContext(), MofificarProducto.class);
                startActivity(i4);
                break;
            case R.id.eliminarProducto:
                Intent i5 = new Intent(getApplicationContext(), EliminarProducto.class);
                startActivity(i5);

            case R.id.misFavoritos:
                Intent i6 = new Intent(getApplicationContext(), OpcionFavorito.class);
                startActivity(i6);
        }
    }
}
