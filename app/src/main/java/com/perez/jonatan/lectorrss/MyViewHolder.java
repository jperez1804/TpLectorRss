package com.perez.jonatan.lectorrss;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alumno on 08/10/2015.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {


    public TextView txtFecha;
    public TextView txtTitulo;
    public TextView txtDescripcion;
    public ImageView img;

    public MyViewHolder(View itemView) {
        super(itemView);

        this.txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        this.txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        this.img = (ImageView) itemView.findViewById(R.id.imageView);
        this.txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);

    }
}
