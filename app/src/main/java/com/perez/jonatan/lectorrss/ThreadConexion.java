package com.perez.jonatan.lectorrss;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;

/**
 * Created by alumno on 08/10/2015.
 */
public class ThreadConexion implements Runnable {

    private String url;
    private Handler h;

    public ThreadConexion(String url, Handler h)
    {
        this.url = url;
        this.h = h;
    }
    @Override
    public void run() {
        HttpManager http =  new HttpManager(url);

        try {
            String resultado = http.getStrDataByGET();
            //List<Persona> personas = Persona.obtenerListaPersona(resultado);

            XMLParser parser = new XMLParser(resultado);

            List<Noticia> noticias = parser.parser();

//            for (Persona p: personas)
//            {
//                http =  new HttpManager(p.getUrl());
//                byte[] miArray = http.getBytesDataByGET();
//
//
//                //el bit map consume mucho mas memoria, porq son los pixeles generados en crudo.
//                Bitmap bitmapImg = BitmapFactory.decodeByteArray(miArray, 0, miArray.length);
//                p.setImg(bitmapImg);
//            }

            Message msg= new Message();
            msg.obj = noticias;
            h.sendMessage(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
