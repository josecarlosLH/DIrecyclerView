package com.example.personajesrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val cola = Volley.newRequestQueue(this)
        var url = "http://iesayala.ddns.net/josecarlosLH/Personajes.php"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            val personajes = response.getJSONArray("Personaje")
            Log.d("Json", personajes.toString())
            recyclerView.adapter = PersonajesAdapter(personajes)
        },
        Response.ErrorListener { error -> Log.d("JSON", error.toString()) })
        cola.add(jsonObjectRequest)
    }
}