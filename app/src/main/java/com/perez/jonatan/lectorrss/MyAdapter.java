package com.perez.jonatan.lectorrss;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alumno on 08/10/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<Noticia> myList;
    private Handler h;

    public MyAdapter(List<Noticia> myList, Handler h)
    {
        this.myList = myList;
        this.h = h;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.layoutitem, null);

        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Noticia noticia = myList.get(i);
        myViewHolder.txtTitulo.setText(noticia.getTxtTitulo());
        myViewHolder.txtFecha.setText(noticia.getTxtFecha());

        String descripcion = noticia.getTxtDescripcion();
        descripcion = descripcion.replaceAll("<(.*?)\\>"," ");//Removes all items in brackets
        descripcion = descripcion.replaceAll("<(.*?)\\\n"," ");//Must be undeneath
        descripcion = descripcion.replaceFirst("(.*?)\\>", " ");//Removes any connected item to the last bracket
        descripcion = descripcion.replaceAll("&nbsp;", " ");
        descripcion = descripcion.replaceAll("&amp;"," ");

        myViewHolder.txtDescripcion.setText(descripcion);

        //otra opcion: lanzo thread por esta etapa
        if ( noticia.getImg() == null && !noticia.getSeEstaBajandoLaImagen() && noticia.getUrl() != null) {
            ThreadImagenes thI = new ThreadImagenes(noticia.getUrl(), h, i);
            //Log.d("my adapter","url:" + noticia.getUrl());
            Thread t = new Thread(thI);
            noticia.setSeEstaBajandoLaImagen(true);
            t.start();
        }
        else
        {
            //myViewHolder.img.setImageResource(R.drawable.);
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
