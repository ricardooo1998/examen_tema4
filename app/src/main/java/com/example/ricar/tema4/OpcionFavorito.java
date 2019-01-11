package com.example.ricar.tema4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpcionFavorito extends AppCompatActivity implements View.OnClickListener {
    Button agregarFavorito;
    Button misArticulosFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_favorito);

        agregarFavorito = findViewById(R.id.agregarFavorito);
        misArticulosFavoritos = findViewById(R.id.verFavoritos);

        agregarFavorito.setOnClickListener(this);
        misArticulosFavoritos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.agregarFavorito:
                Intent i1 = new Intent(getApplicationContext(), AgregarFavorito.class);
                startActivity(i1);
                break;

            case R.id.verFavoritos:
                Intent i2 = new Intent(getApplicationContext(), ProductosFavoritos.class);
                startActivity(i2);
                break;
        }

    }
}
