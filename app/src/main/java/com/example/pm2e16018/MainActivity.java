package com.example.pm2e16018;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnguardar;
    EditText txtnombre,txtnotas,txttelefono;
    Spinner txtpais;
    Button btncamara,btnVer;
    ImageView fotico;
    String RutaiMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnguardar=findViewById(R.id.btnGuardar);
        txtnombre=findViewById(R.id.txtnombre);
        txttelefono=findViewById(R.id.txttelefono);
        txtnotas=findViewById(R.id.txtnotas);
        txtpais=findViewById(R.id.txtpais2);
        btncamara=findViewById(R.id.Foto);
        fotico=findViewById(R.id.foto);
        btnVer=findViewById(R.id.btnVer);
        String[] categorias = {"Honduras (504)", "Costa Rica (502)"};
        txtpais.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, categorias));
        String Pais = (String) txtpais.getSelectedItem();

    //Camara
        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camara();
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sig=new Intent( MainActivity.this,MainActivity2.class);
                startActivity(Sig);
            }
        });

        //guardar
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conexion conexion=new Conexion(MainActivity.this);
                SQLiteDatabase db=conexion.getWritableDatabase();
                dbContactos Dbcontactos=new dbContactos(MainActivity.this);
               long id=Dbcontactos.Insertar(txtnombre.getText().toString(),txttelefono.getText().toString(),
                        Pais.toString(),txtnotas.getText().toString());

                if(txtnombre.getText().toString().isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Debe escribir un Nombre").setTitle("Agenda");
                    builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alerta=builder.create();
                    alerta.show();
                   // Toast.makeText(MainActivity.this,"Dbe escribir un nombre",Toast.LENGTH_LONG).show();
                }else {
                    if (txttelefono.getText().toString().isEmpty()) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Debe ingresar un numero").setTitle("Agenda");
                        builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alerta=builder.create();
                        alerta.show();

                       // Toast.makeText(MainActivity.this, "debe escribir un numero", Toast.LENGTH_LONG).show();
                    } else {
                        if (txtnotas.getText().toString().isEmpty()) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Debe Escribir una Nota").setTitle("Agenda");
                            builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog alerta=builder.create();
                            alerta.show();

                            //Toast.makeText(MainActivity.this, "debe escribir una nota", Toast.LENGTH_LONG).show();
                        }else{
                            if(fotico.getDrawable() == null){
                                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Debe Tomarse una foto").setTitle("Agenda");
                                builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                AlertDialog alerta=builder.create();
                                alerta.show();
                               // Toast.makeText(MainActivity.this, "debe tomarse una foto", Toast.LENGTH_LONG).show();
                            }else{
                                {if (id > 0) {
                                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Registro Guardado").setTitle("Agenda");
                                    builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    AlertDialog alerta=builder.create();
                                    alerta.show();

                                   // Toast.makeText(MainActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
                                    limpiar();


                                } else {
                                    Toast.makeText(MainActivity.this, "Registro NO guardado", Toast.LENGTH_LONG).show();
                                }

                                }

                            }
                        }
                    }

                }
            }
            private void limpiar(){
                txtnombre.setText("");
                txtnotas.setText("");
                txttelefono.setText("");
                fotico.setImageBitmap(null);
            }
        });
    }
    private void Camara(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File archivo=null;
        try {
            archivo=CImagen();

        }catch (Exception ex){
            Log.e("error", ex.toString());
        }

        if(archivo!=null){
            Uri fotoUri= FileProvider.getUriForFile(this,"com.example.pm2e16018.fileprovider",archivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intent,1);
        }


    }
    protected void onActivityResult(int resquestCode,int ResultCode,Intent data) {
        super.onActivityResult(resquestCode, ResultCode, data);
        if (resquestCode == 1 && ResultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            Bitmap imgBitmap = BitmapFactory.decodeFile(RutaiMG);
            fotico.setImageBitmap(imgBitmap);
        }
    }
 //obtengo la ruta de la imagen y ademas la creo.
    private File CImagen() throws IOException {
    String nombre="Foto_";
    File directorio=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File imagen=File.createTempFile(nombre,".jpg",directorio);
    RutaiMG=imagen.getAbsolutePath();
    return  imagen;
    }






}