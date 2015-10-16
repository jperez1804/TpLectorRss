/*
<one line to give the program's name and a brief idea of what it does.>
        Copyright (C) <year>  <name of author>

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.perez.jonatan.lectorrss;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    private Handler handler = null;
    private RecyclerView rv = null;
    private List<Noticia> listaNoticias;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText urlText = (EditText)this.findViewById(R.id.editText);
        Button button = (Button)this.findViewById(R.id.buttonPanel);



        //button.setOnClickListener(this);

        this.rv = (RecyclerView)findViewById(R.id.list);

        handler = new Handler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(linearLayoutManager);

        //http://192.168.2.57:8080/personas.xml
        //http://www.lslutnfra.com/alumnos/practicas/personas.csv
        //http://rss.cnn.com/rss/edition.rss
        //http://www.infobae.com/rss/hoy.xml
        ThreadConexion hiloConexion = new ThreadConexion("http://rss.cnn.com/rss/edition.rss", handler);

        Thread hilo = new Thread(hiloConexion);

        hilo.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {

        if ( msg.arg1 == 0 ) {



            listaNoticias = (List<Noticia>) msg.obj;
            this.adapter = new MyAdapter((List<Noticia>) msg.obj, handler);
            rv.setAdapter(this.adapter);
        }
        else
        {
            /*si viene bitmap*/
            Noticia n = this.listaNoticias.get(msg.arg2);
            n.setImg((Bitmap)msg.obj);
            /*Cambio mi ImageView*/
            adapter.notifyDataSetChanged();
        }

        return false;
    }


    @Override
    public void onClick(View v) {

    }
}
