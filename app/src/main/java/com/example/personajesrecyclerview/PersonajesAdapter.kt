package com.example.personajesrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_personaje.view.*
import org.json.JSONArray
import org.json.JSONObject

class PersonajesAdapter(private val personajes: JSONArray)
    : RecyclerView.Adapter<PersonajesAdapter.ViewHolder>(){

    // Creamos el modelo para cada item del recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_personaje, parent, false)
        return ViewHolder(view)
    }

    // Llamamos a las vistas que tengamos en ese modelo
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val imageView: ImageView = view.imageView
    }

    //Enlazamos las vistas con los datos
    override fun onBindViewHolder(holder: PersonajesAdapter.ViewHolder, position: Int) {

        //Informacion del empleado en texto
        val personaje: JSONObject = personajes.getJSONObject(position)
        holder.nameTextView.text = personaje["Nombre"].toString() + " " + personaje["Apellido"].toString()+"\n"+ personaje["DNI"].toString()+"\n"+ personaje["Telefono"].toString()+"\n"+ personaje["Direccion"].toString()

        //La foto del empleado
        val uri: String = personaje["Foto"].toString()
        val name: String = personaje["Nombre"].toString()
        val num: String = personaje["Telefono"].toString()
        Glide.with(holder.itemView.context).load(uri).into(holder.imageView)

        //El toast con la info rápida
        holder.nameTextView.setOnClickListener{Toast.makeText(holder.itemView.context, "Personaje: "+name+"\nTeléfono: "+num+" ", Toast.LENGTH_SHORT).show()}

        //Para la actividad del empleado, metemos la informacion del empleado seleccionado en un intent
        holder.imageView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PersonajesActivity::class.java)
            intent.putExtra("personaje",personajes[position].toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    //Recorre la lista
    override fun getItemCount(): Int = personajes.length()
}

