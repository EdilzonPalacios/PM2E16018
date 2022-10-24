package com.example.pm2e16018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Button btnatras;


    RecyclerView listacontactos;
    ArrayList<Contactos> listaArrayContactos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnatras=findViewById(R.id.Btnatras);
        listacontactos=findViewById(R.id.listacontactos);
        listacontactos.setLayoutManager(new LinearLayoutManager(this));
        dbContactos DbContactos=new dbContactos(MainActivity2.this);
        listaArrayContactos=new ArrayList<>();
        listacontactosadapter adaptador=new listacontactosadapter(DbContactos.Mostrarcontactos());
        listacontactos.setAdapter(adaptador);



        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sig=new Intent( MainActivity2.this,MainActivity.class);
                startActivity(Sig);



            }
        });






    }
}