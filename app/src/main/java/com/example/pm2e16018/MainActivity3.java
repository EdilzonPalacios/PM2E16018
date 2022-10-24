package com.example.pm2e16018;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    EditText nombre,telefono,nota,pais;
    Button btnguardar;
    Contactos contactos;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        nombre.findViewById(R.id.txtnombre);
        telefono.findViewById(R.id.txttelefono);
        nota.findViewById(R.id.txtnotas);
        pais.findViewById(R.id.txtpais2);

        if(savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");

            }
        }else{
            id=(int) savedInstanceState.getSerializable("ID");
        }
        dbContactos Dbcontactos=new dbContactos(MainActivity3.this);
        contactos=Dbcontactos.Vercontactos(id);
        if(contactos !=null){
            nombre.setText(contactos.getNombre());
            telefono.setText(contactos.getTelefono());
            pais.setText(contactos.getPais());
            nota.setText(contactos.getNota());




        }




    }
}