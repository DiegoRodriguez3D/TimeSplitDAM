package com.timesplit.vista;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.timesplit.R;
import com.timesplit.modelo.Perfil;

import java.util.List;


public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {

    // Recibirá una lista de perfiles a través de la consulta
    private List<Perfil> listaPerfiles;
    private Context context;
    private OnRecyclerViewItemClickListener listener;

    //Constructor
    RecyclerView_Adapter(List<Perfil> listaPerfiles, Context context) {
        this.listaPerfiles = listaPerfiles;
        this.context = context;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener)
    {
        this.listener = listener;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Desde este método recibimos la posición actual del recyclerView. La usaremos para acceder a los datos de cada posición de la lista de perfiles
        Perfil perfil = listaPerfiles.get(position);
        //A través del holder accedemos a los datos de cada perfil y los pasamos a cada textview del layout una vez hacemos el databinding en ViewHolder
        holder.nombrePerfil.setText(perfil.getNombre_perfil());

        holder.parentView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                listener.onRecyclerViewItemClicked(position, -1);
            }
        });


    }

    @Override
    public int getItemCount() {
        //Para indicar el numero de filas utilizaremos el tamaño de la lista de contactos
        return listaPerfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView nombrePerfil;
        public CardView parentView;
//       public TextView tiempoTrabajo;
//       public TextView tiempoDescanso;
//       public TextView numeroRondas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Hacemos el databinding a través del itemView
            //Asignamos a los textView del layout fila_perfil
            nombrePerfil = itemView.findViewById(R.id.textView_nombre_perfil);
            parentView = (CardView) itemView.findViewById(R.id.cardView_contacto);
//            tiempoTrabajo = itemView.findViewById(R.id.textView_tiempoTrabajo);
//            tiempoDescanso = itemView.findViewById(R.id.textView_tiempoDescanso);
//            numeroRondas = itemView.findViewById(R.id.textView_numeroRondas);

        }
    }
}


