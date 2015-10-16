package com.perez.jonatan.lectorrss;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 08/10/2015.
 */
public class XMLParser {


    private String xml;

    public XMLParser(String xml)
    {
        this.xml = xml;

    }

    public List<Noticia> parser(){
        XmlPullParser parser = Xml.newPullParser();
        List<Noticia> lista = new ArrayList<Noticia>();

        try {
            parser.setInput(new StringReader(this.xml));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        int event = 0;
        try {
            event = parser.getEventType();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        Noticia noticia = null;
        boolean estoyEnUnItem=false;
        while (event != XmlPullParser.END_DOCUMENT) {


            switch (event) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:



                    String tag = parser.getName();

                    Log.d("parser", "encontre el tag:" + tag);

                    if (tag.equals("item"))
                    {
                        Log.d("parser", "empieza el item");
                        estoyEnUnItem = true;

                    }
                    if (tag.equals("title") && estoyEnUnItem) {

                        noticia = new Noticia();
                        try {
                            Log.d("parser", "estoy seteando el tittle");
                            noticia.setTxtTitulo(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("link") && estoyEnUnItem) {

                        try {
                            noticia.setLink(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("description") && estoyEnUnItem) {


                        try {
                            noticia.setTxtDescripcion(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("pubDate") && estoyEnUnItem) {


                        try {
                            noticia.setTxtFecha(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("media:thumbnail") && estoyEnUnItem) {


                        try {
                            noticia.setTxtTitulo(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

break;
        case XmlPullParser.END_TAG:

        if(parser.getName().equals("item"))
        {
        lista.add(noticia);
            noticia = null;
        estoyEnUnItem = false;
        }

        break;
        }

        try {
        event = parser.next();
        } catch (XmlPullParserException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }

        Log.d("xmlparser", "toy retornando la lista");

        return lista;
    }
}
