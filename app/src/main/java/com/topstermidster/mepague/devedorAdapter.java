package com.topstermidster.mepague;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class devedorAdapter extends RecyclerView.Adapter<devedorAdapter.MyViewHolder> {

    private List<devedor> devedorsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, value;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nomeDevedor);
            value = (TextView) view.findViewById(R.id.valor);
            date = (TextView) view.findViewById(R.id.data);
        }
    }


    public devedorAdapter(List<devedor> devedorsList) {
        this.devedorsList = devedorsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.devedores_lista, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        devedor devedor = devedorsList.get(position);
        holder.name.setText(devedor.getName());
        holder.value.setText(devedor.getValue());
        holder.date.setText(devedor.getDate());
    }

    @Override
    public int getItemCount() {
        return devedorsList.size();
    }
}