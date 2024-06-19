

package Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21100298_lamichoacana.R;

import global.listas;

public class AdaptadorEliminar extends RecyclerView.Adapter<AdaptadorEliminar.Miactivity>{

    public Context context;
    @NonNull
    @Override
    public AdaptadorEliminar.Miactivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.elimanador,null);
        Miactivity obj = new Miactivity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEliminar.Miactivity holelimina, int i) {
        final int pos = i;
        holelimina.nombre.setText((listas.lista.get(i).getNombre()));
        holelimina.sabor.setText((listas.lista.get(i).getSabor()));
        holelimina.hora.setText((listas.lista.get(i).getHora()));
        holelimina.seleccion.setChecked(false);
        holelimina.seleccion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(((CheckBox)view).isChecked())
                    listas.listabajas.add(listas.lista.get(pos));
                else
                    listas.listabajas.remove(listas.lista.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listas.lista.size();
    }

    public class Miactivity extends RecyclerView.ViewHolder {
        TextView nombre, sabor, hora;
        CheckBox seleccion;
        public Miactivity(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.bajanombre);
            sabor = itemView.findViewById(R.id.bajsabor);
            hora = itemView.findViewById(R.id.bajshora);
            seleccion = itemView.findViewById(R.id.seleccion);
        }
    }
}