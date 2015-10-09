package com.perez.jonatan.lectorrss;

import android.graphics.Bitmap;

/**
 * Created by alumno on 08/10/2015.
 */
public class Noticia {

    private  String txtTitulo;
    private String txtDescripcion;
    private String txtFecha;
    private String link;
    private String url;
    private Bitmap img;
    private boolean seEstaBajandoLaImagen;

    public Noticia(){}

    public Noticia(String txtTitulo,String txtDescripcion, String txtFecha,String url,Bitmap img)
    {
        this.txtTitulo=txtTitulo;
        this.txtDescripcion=txtDescripcion;
        this.img=img;
        this.url=url;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getTxtDescripcion() {
        return txtDescripcion;
    }

    public String getTxtTitulo() {
        return txtTitulo;
    }

    public String getTxtFecha() {
        return txtFecha;
    }

    public String getUrl() {
        return url;
    }

    public boolean getSeEstaBajandoLaImagen() {
        return seEstaBajandoLaImagen;
    }


    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void setSeEstaBajandoLaImagen(boolean seEstaBajandoLaImagen) {
        this.seEstaBajandoLaImagen = seEstaBajandoLaImagen;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public void setTxtFecha(String txtFecha) {
        this.txtFecha = txtFecha;
    }

    public void setTxtTitulo(String txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

