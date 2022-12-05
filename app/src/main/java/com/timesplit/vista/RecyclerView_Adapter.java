package com.timesplit.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timesplit.R;
import com.timesplit.modelo.Contacto;

import java.util.List;


public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {

    // Recibirá una lista de perfiles a través de la consulta
    private List<Contacto> listaContactos;
    private Context context;

    //Constructor
    public RecyclerView_Adapter(List<Contacto> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creamos view y le pasamos el layout de la fila a través de LayoutInflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_perfil, parent, false);
        //Retornamos el view a través del método ViewHolder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Desde este método recibimos la posición actual del recyclerView. La usaremos para acceder a los datos de cada posición de la lista de contactos
        Contacto contacto = listaContactos.get(position);
        //A través del holder accedemos a los datos de cada contacto y los pasamos a cada textview del layout una vez hacemos el databinding en ViewHolder
        holder.nombre.setText(contacto.getNombre());
        holder.telefono.setText(contacto.getTelefono());
        holder.email.setText(contacto.getEmail());

    }

    @Override
    public int getItemCount() {
        //Para indicar el numero de filas utilizaremos el tamaño de la lista de contactos
        return listaContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView nombre;
       public TextView telefono;
       public TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Hacemos el databinding a través del itemView
            //Asignamos a los textView del layout fila_contacto
            nombre = itemView.findViewById(R.id.textView_nombre_contacto);
            telefono = itemView.findViewById(R.id.textView_telefono_contacto);
            email = itemView.findViewById(R.id.textView_email_contacto);

        }
    }
}
