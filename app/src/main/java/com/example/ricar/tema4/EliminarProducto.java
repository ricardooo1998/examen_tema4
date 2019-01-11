package com.example.ricar.tema4;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EliminarProducto extends AppCompatActivity {
    DatabaseReference bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);
        final EditText nomET=findViewById(R.id.nombre);
        bbdd= FirebaseDatabase.getInstance().getReference("productos");


        final Button cancelar = findViewById(R.id.cancelar);
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                Intent i=new Intent();
                finish();
            }
        });

        final Button aceptar=findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom= String.valueOf(nomET.getText());
                if (nom.equals("")){
                    Toast.makeText(v.getContext(), "Introduzca todos los campos necesarios(Nombre a modificar)",Toast.LENGTH_SHORT).show();
                }else {
                    Query q=bbdd.orderByChild("nombre").equalTo(nom);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue()==null){
                                Toast.makeText(getApplicationContext(), "Error, compruebe que ha escrito correctamente el titulo",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    String key=datasnapshot.getKey();
                                    DatabaseReference dbr=bbdd.child(key);
                                    dbr.removeValue();
                                }
                                Toast.makeText(getApplicationContext(), "Completado con exito",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}

// String titulo = text_titulo.getText().toString();
//
//                if(!TextUtils.isEmpty(titulo)){
//                    Query q=bbdd.orderByChild(getString(R.string.campo_titulo)).equalTo(titulo);
//
//                    q.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
//                                String clave=datasnapshot.getKey();
//                                DatabaseReference ref = bbdd.child(clave);
//
//                                ref.removeValue();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//                    Toast.makeText(MainActivity.this, "El disco "+titulo+" se ha borrado con éxito", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
//                }
//
//            }
