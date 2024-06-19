package Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a21100298_lamichoacana.R;
import com.example.a21100298_lamichoacana.Card;

import global.listas;

public class AdaptadorVer extends RecyclerView.Adapter<AdaptadorVer.Miactivity>{

    public Context context;
    @NonNull
    @Override
    public AdaptadorVer.Miactivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.lista,null);
        Miactivity obj =new Miactivity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorVer.Miactivity holder, int i) {
        final int pos = i;
        holder.nombre.setText((listas.lista.get(i).getNombre()));
        holder.sabor.setText((listas.lista.get(i).getSabor()));
        holder.hora.setText((listas.lista.get(i).getHora()));
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent card = new Intent(context,com.example.a21100298_lamichoacana.Card.class);
                card.putExtra("pos",pos);
                context.startActivity(card);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listas.lista.size();
    }

    public class Miactivity extends RecyclerView.ViewHolder {
        TextView nombre, sabor, hora;
        public Miactivity(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.lisnombre);
            sabor = itemView.findViewById(R.id.lissabor);
            hora = itemView.findViewById(R.id.lishora);
        }
    }
}