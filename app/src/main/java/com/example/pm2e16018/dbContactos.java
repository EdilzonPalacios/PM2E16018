package com.example.pm2e16018;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class dbContactos extends Conexion {
    Context context;

    public dbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long Insertar(String nombre, String telefono, String pais, String nota) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        long id = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("pais", pais);
            values.put("nota", nota);
            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    public ArrayList<Contactos> Mostrarcontactos() {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ArrayList<Contactos> lista = new ArrayList<Contactos>();
        Contactos contactos = null;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);

        if (cursor.moveToFirst()) {
            do {
                contactos = new Contactos();
                contactos.setId(cursor.getInt(0));
                contactos.setNombre(cursor.getString(1));
                contactos.setTelefono(cursor.getString(2));
                contactos.setPais(cursor.getString(3));
                contactos.setNota(cursor.getString(4));
                lista.add(contactos);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }


   
    public Contactos Vercontactos(int id) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();

        Contactos contactos = null;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + "WHERE id=" + id +"LIMIT 1", null);

        if (cursor.moveToFirst()) {
            contactos = new Contactos();
            contactos.setId(cursor.getInt(0));
            contactos.setNombre(cursor.getString(1));
            contactos.setTelefono(cursor.getString(2));
            contactos.setPais(cursor.getString(3));
            contactos.setNota(cursor.getString(4));

        }
        cursor.close();
        return contactos;
    }

    public long editar(int id,String nombre, String telefono, String pais, String nota) {
        Conexion conexion = new Conexion(context);
        SQLiteDatabase db = conexion.getWritableDatabase();
        boolean correcto=false;
        try {

            db.execSQL("UPDATE "+TABLE_CONTACTOS+" SET nombre='"+nombre+"'," +
                    "telefono='"+telefono+"',pais='"+pais+"',nota='"+nota+"'WHERE id='"+id+"'");
            correcto=true;
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }







}


