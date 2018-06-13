package com.topstermidster.mepague;

import android.widget.LinearLayout;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.List;

public class devedorAdapter extends RecyclerView.Adapter<devedorAdapter.MyViewHolder> {

    private List<devedor> devedorsList;
    public LinearLayout ll;
    public ImageButton btnDelete;
    public ImageButton btnEdit;

    WeakReference<Context> mContextWeakReference;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, value, descricao;

        public MyViewHolder(View view, final Context context) {
            super(view);
            name = (TextView) view.findViewById(R.id.nomeDevedor);
            value = (TextView) view.findViewById(R.id.valor);
            date = (TextView) view.findViewById(R.id.data);
            descricao = (TextView) view.findViewById(R.id.descricao);
            //ll = (LinearLayout) itemView.findViewById(R.id.ll_layout);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnExcluirDevedor);
            btnEdit = (ImageButton) itemView.findViewById(R.id.edit);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((telaPrincipal) context).userItemClick(getAdapterPosition());
                }
            });
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((telaPrincipal) context).userItemClickData(getAdapterPosition());
                }
            });
        }


    }


    public devedorAdapter(List<devedor> devedorsList, Context context) {
        this.devedorsList = devedorsList;
        this.mContextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = mContextWeakReference.get();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.devedores_lista, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Context context = mContextWeakReference.get();

        if (context == null) {
            return;
        }

        devedor devedor = devedorsList.get(position);
        holder.name.setText(devedor.getName());
        holder.value.setText(devedor.getValue());
        holder.date.setText(devedor.getDate());
        holder.descricao.setText(devedor.getDesc());
    }

    @Override
    public int getItemCount() {
        return devedorsList.size();
    }
}