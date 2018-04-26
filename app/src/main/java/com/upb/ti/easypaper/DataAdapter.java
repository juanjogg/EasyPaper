package com.upb.ti.easypaper;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.viewHolder> {
    private String[] dataSet;
    private ArrayList<Papeleria> papelerias;
    protected static String idPapeleria;

    public DataAdapter(String[] dataSet){
        this.dataSet = dataSet;
    }
    public DataAdapter(ArrayList<Papeleria> papelerias){
        this.papelerias = papelerias;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        viewHolder vh = new viewHolder((CardView) v);

        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        //holder.txtView.setText(dataSet[position]);
        final Papeleria papeleria = papelerias.get(position);
        holder.txtNombre.setText(papeleria.getNombre());
        holder.txtUbicacion.setText(papeleria.getUbicacion());
        holder.botonVm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idPapeleria = papeleria.getIdPapeleria();
                Intent intento = new Intent(view.getContext(),PapeleriaGralActivity.class);
                view.getContext().startActivity(intento);
            }
        });

    }

    @Override
    public int getItemCount() {
        return papelerias.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView txtNombre , txtUbicacion;
        private Button botonVm;
        public viewHolder(CardView itemView) {
            super(itemView);
            this.cardView = itemView;
            txtNombre = itemView.findViewById(R.id.txtNombrePapeleria);
            txtUbicacion = itemView.findViewById(R.id.txtUbicacion);
            botonVm = itemView.findViewById(R.id.btnVm);

        }

    }

}

