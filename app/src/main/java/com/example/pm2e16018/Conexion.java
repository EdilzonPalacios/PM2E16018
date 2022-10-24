package com.example.pm2e16018;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class Conexion extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="Agenda.db";
    static final String TABLE_CONTACTOS="Contactos";
    public Conexion(@Nullable Context context)
    {
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db) {
        // Creacion de las tablas de la db
        db.execSQL("CREATE TABLE "+TABLE_CONTACTOS+"(" +
                "id integer PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "telefono TEXT NOT NULL, "+
                "pais TEXT NOT NULL," +
                "nota TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Eliminamos la data y las tablas de la aplicacion
    db.execSQL("DROP TABLE "+TABLE_CONTACTOS);
        onCreate(db);

    }

}
