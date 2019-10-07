package com.app.histomath;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Mako on 2/9/2017.
 */

public class FormAdapter extends RecyclerView.Adapter <FormAdapter.ViewHolder> {
    private List<Matematicos> dataset;
    Context context;
    private OnItemClickListener mListener;


    public FormAdapter(List<Matematicos> dataset, Context activity) {
        this.dataset =dataset;
        this.context=activity;

    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.matematico_item, parent, false);
        return new FormAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(dataset.get(position).getNombre());
        holder.fNacimiento.setText(dataset.get(position).getfNacimiento());
        holder.fMuerte.setText(dataset.get(position).getfMuerte());
        Picasso.with(context)
                .load(dataset.get(position).getImagen())
                .fit()
                .centerCrop()
                .into(holder.imagen);


    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public List<Matematicos> getDataset() {
        return dataset;
    }

    public void setDataset(List<Matematicos> dataset) {
        this.dataset = dataset;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombre,fNacimiento,fMuerte;
        private ImageView imagen;

        public ViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.textView_nombre_item);
            fNacimiento=(TextView)view.findViewById(R.id.textView_fNacimiento_item);
            fMuerte=(TextView)view.findViewById(R.id.textView_fMuerte_item);
            imagen= (ImageView) view.findViewById(R.id.image_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           //mListener.onItemClick();

        }
    }
}
