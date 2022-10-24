package com.example.pm2e16018;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listacontactosadapter extends RecyclerView.Adapter<listacontactosadapter.ContactoViewHolder> {
    ArrayList<Contactos>listacontactos;
    public listacontactosadapter(    ArrayList<Contactos>listacontactos){
        this.listacontactos=listacontactos;
    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hola,null,false);
        return  new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
    holder.viewnombre.setText(listacontactos.get(position).getNombre());
        holder.viewtelefono.setText(listacontactos.get(position).getTelefono());
        holder.viewpais.setText(listacontactos.get(position).getPais());
        holder.viewnotas.setText(listacontactos.get(position).getNota());

    }

    @Override
    public int getItemCount() {
 return  listacontactos.size();

    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewnombre,viewtelefono,viewpais,viewnotas;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewnombre=itemView.findViewById(R.id.name);
            viewtelefono=itemView.findViewById(R.id.phone);
            viewpais=itemView.findViewById(R.id.pais);
            viewnotas=itemView.findViewById(R.id.notas);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context= view.getContext();
                    Intent intent =new Intent(context,MainActivity3.class);
                    intent.putExtra("ID",listacontactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
