package com.upb.ti.easypaper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.viewHolder> {
    String[] dataSet;
    public DataAdapter(String[] dataSet){
        this.dataSet = dataSet;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.txtview_layout,parent,false);
        viewHolder vh = new viewHolder((TextView) v);

        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.txtView.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView txtView;

        public viewHolder(TextView itemView) {
            super(itemView);
            txtView = itemView;
        }

    }

}

