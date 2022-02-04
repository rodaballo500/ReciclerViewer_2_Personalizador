package com.example.reciclerviewer_2_personalizador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// con este adaptador alimentamos el item:list_personajes

//1- extendemos la clase adaptadorPersonajes a rcyclerView.Adapter.<aquí colocamos el nombre de esta clase con la clase holder con la que voy a trabajar>
//nos salen errores:
//  1e que cree la clase ViewHolderPersonajes
//  2e implementar los metodos automaticamente
//  3e nos dice que la clase ViewHolderPersonajes(abajo) debe extender de RecyclerView.ViewHolder
//  4e en clase ViewHolderPersonajes nos dice que debemos crer un constructor

//2- Debemos crear un arraylist para pasarle los elementos con los que trabajara. crearemos un list de tipo objeto que pueda contener
// todos los elementos que conforman nuestra estructura. Para ello creamo una clase java que se llamara personajes

public class AdaptadorPersonajes
        extends RecyclerView.Adapter <AdaptadorPersonajes.ViewHolderPersonajes>
        implements View.OnClickListener { // este implements nos obliga a implementar metodo onClick(mas abajo)
                                          // tambien tendremos que crear una propiedad private View.OnClickListener listener; *1


    ArrayList <PersonajeVo> listaPersonajes;
    // con esta lista creada genero constructor (boton derecho) que reciba esa lista

    //*1
    private View.OnClickListener listener;// este será nuestro escuchador. lo pndremos a trabajar en el ViewHolderPersonajes onCreateViewHolder *2

    public AdaptadorPersonajes(ArrayList<PersonajeVo> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @NonNull
    @Override
    /*2e*/ public ViewHolderPersonajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //aqui vamos a inflar con un view el item_list_personajes
        //esta linea nos indica que debe cargar el item_list_personajes. La vamos a comentar para cargarle el item_grid_personajes
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_personajes,null,false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_personajes,null,false);
        // ---AQUI EMPEZAMOS RECYCLER CON DOS OPCIONES SIMULTANEAS (BOTONES) AS DOS DE ARRIBA SERÍA EN NORMAL ELIGIENDO UNA --------
        // TIRAMOS DE IF PARA GENERAR LA LOGICA DE LOS BOTONES
        // TAMBIEN GENERAMOS ENTERO(INT) CON NOMBRE layout PARA GENERAR CONDICION
        int layout =0;
        if (Utilidades.visualizacion==Utilidades.LIST){
            layout = R.layout.item_list_personajes;
        } else {
            layout = R.layout.item_grid_personajes;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        //*2
        view.setOnClickListener(this); //asi podemos escuchar el elemento de seleccion *2
        return new ViewHolderPersonajes(view);
    }

    @Override
    /*2e*/ public void onBindViewHolder(@NonNull ViewHolderPersonajes holder, int position) {
        holder.etiNombre.setText(listaPersonajes.get(position).getNombre());

        //Este if es para incluir la info en caso de que se elija el boton list
        if (Utilidades.visualizacion==Utilidades.LIST){
            holder.etiInformacion.setText(listaPersonajes.get(position).getInfo());
        }
        //holder.etiInformacion.setText(listaPersonajes.get(position).getInfo()); ---COMENTADO PARA EL MODO GRID
        holder.foto.setImageResource(listaPersonajes.get(position).getFoto()); // usamos "setImageResource" porque traeremos las imagenes desde el drawable

    }

    @Override
    /*2e*/ public int getItemCount() {

        return listaPersonajes.size();
    }

    // crearemos metodo setOnClickListene que se encarga de escuchar como tal al evento onClick

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener; // se creo en *1

    }
    // metodo onClick que nos hace implementar con el implements
    @Override
    public void onClick(View view) {

        // hacemos validacion
        if (listener!=null) {
            listener.onClick(view); // le mando como parametro el view que le llega
        }

    }

    /*1e y 3e*/public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

        TextView etiNombre, etiInformacion;
        ImageView foto;
    /*4e*/    public ViewHolderPersonajes(@NonNull View itemView) {
            super(itemView);

            etiNombre = itemView.findViewById(R.id.idNombre);

        //Este if es para incluir la info en caso de que se elija el boton list
        if (Utilidades.visualizacion==Utilidades.LIST){
            etiInformacion = itemView.findViewById(R.id.idInfo);
        }
            //etiInformacion = itemView.findViewById(R.id.idInfo); ----COMENTADO PARA EL MODO GRID
            foto = itemView.findViewById(R.id.idImagen);
        }
    }
}

//