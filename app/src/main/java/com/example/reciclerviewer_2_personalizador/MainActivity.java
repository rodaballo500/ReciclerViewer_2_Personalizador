package com.example.reciclerviewer_2_personalizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList <PersonajeVo> listaPersonajes;
    RecyclerView recyclerPersonajes;
    TextView tvresul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /* COMENTAMOS ESTO PARA ADAPTARLO AL MODO DINAMICO Y PODER ELEGIR SEGUN EL BOTON QUE APIETES. lISTA O GRID
        //instanciamos listapersoinajes
        listaPersonajes = new ArrayList<>();
        recyclerPersonajes = findViewById(R.id.RecyclerId);
        //recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this)); lo comentamos para probar el gridLayout a priori lo volveremos a activar
        recyclerPersonajes.setLayoutManager(new GridLayoutManager(this,3));//gridlayout con cantidad de columnas
        llenarPersonajes();

        AdaptadorPersonajes adapter = new AdaptadorPersonajes(listaPersonajes);
        recyclerPersonajes.setAdapter(adapter);*/

        tvresul = findViewById(R.id.tvResultado);

        construirRecycler();


    }

    private void llenarPersonajes () {
        listaPersonajes.add(new PersonajeVo("Distrito 91 001","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discoprimeroo));
        listaPersonajes.add(new PersonajeVo("Distrito 91 002","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discosegundoo));
        listaPersonajes.add(new PersonajeVo("Distrito 91 003","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discoterceroo));
        listaPersonajes.add(new PersonajeVo("Distrito 91 004","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discocuartoo));
        listaPersonajes.add(new PersonajeVo("Hooded Rec 001","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discoquintoo));
        listaPersonajes.add(new PersonajeVo("Hooded Rec 002","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discosextoo));
        listaPersonajes.add(new PersonajeVo("Hooded Rec 003","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discoseptimoo));
        listaPersonajes.add(new PersonajeVo("Hooded Rec 004","BlaBlaBlaBlaInfoInfoInfo",R.drawable.discooctavoo));
        listaPersonajes.add(new PersonajeVo("Hooded Rec 005","BlaBlaBlaBlaInfoInfoInfo",R.drawable.disconovenoo));

    }

    // creamos el metodo onClick automaticamente desde el activity_main.xml, para incluir los botones en la jugada
    // generaremos el escuchador para cada uno

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnList: Utilidades.visualizacion=Utilidades.LIST;
                break;
            case R.id.btnLGrid: Utilidades.visualizacion=Utilidades.GRID;
                break;
        }
        construirRecycler();
    }

    private void construirRecycler() {
        //instanciamos listapersoinajes
        listaPersonajes = new ArrayList<>();
        recyclerPersonajes = findViewById(R.id.RecyclerId);

        if (Utilidades.visualizacion==Utilidades.LIST){
            recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerPersonajes.setLayoutManager(new GridLayoutManager(this,3));
        }
        /* COMENTAMOS ESTAS DOS LINEAS PORQUE SON DEL PRIMER RECYCLER SENCILLO.
         LO QUE HACEMOS ES METER LAS DOS EN EL IF PARA GENERAR LA LOGICA DE SELECCION DEPENDIENDO DE QUE BOTON PULSEMOS. lIST O GRID
        //recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this)); lo comentamos para probar el gridLayout a priori lo volveremos a activar
        //recyclerPersonajes.setLayoutManager(new GridLayoutManager(this,3));//gridlayout con cantidad de columnas*/
        llenarPersonajes();

        AdaptadorPersonajes adapter = new AdaptadorPersonajes(listaPersonajes);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Seleccion: "+
                        listaPersonajes.get(recyclerPersonajes.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
                tvresul.setText("");
            }
        });
        recyclerPersonajes.setAdapter(adapter);


    }
}