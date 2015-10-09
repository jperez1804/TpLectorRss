package com.perez.jonatan.lectorrss;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
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
        myViewHolder.txtDescripcion.setText(noticia.getTxtDescripcion());
        myViewHolder.txtFecha.setText(noticia.getTxtFecha());

        //otra opcion: lanzo thread por esta etapa
        if ( noticia.getImg() == null && !noticia.getSeEstaBajandoLaImagen()) {
            ThreadImagenes thI = new ThreadImagenes(noticia.getUrl(), h, i);
            Thread t = new Thread(thI);
            noticia.setSeEstaBajandoLaImagen(true);
            t.start();
        }
        else
        {
            myViewHolder.img.setImageBitmap(noticia.getImg());
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
