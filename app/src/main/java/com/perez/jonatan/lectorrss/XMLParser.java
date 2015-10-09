package com.perez.jonatan.lectorrss;

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

        while (event != XmlPullParser.END_DOCUMENT) {
            Noticia noticia = null;
            boolean estoyEnUnItem=false;
            switch (event) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:



                    String tag = parser.getName();

                    if (tag.equals("item"))
                    {
                        estoyEnUnItem = true;

                    }
                    if (tag.equals("title") && estoyEnUnItem) {


                        noticia = new Noticia();
                        try {
                            noticia.setTxtTitulo(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("link") && estoyEnUnItem) {

                        noticia = new Noticia();
                        try {
                            noticia.setLink(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("description") && estoyEnUnItem) {

                        noticia = new Noticia();
                        try {
                            noticia.setTxtDescripcion(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("pubDate") && estoyEnUnItem) {

                        noticia = new Noticia();
                        try {
                            noticia.setTxtFecha(parser.nextText());
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (tag.equals("media:thumbnail") && estoyEnUnItem) {

                        noticia = new Noticia();
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

        return lista;
    }
}
