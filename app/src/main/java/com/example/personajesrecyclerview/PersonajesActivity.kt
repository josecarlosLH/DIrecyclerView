package com.example.personajesrecyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_personajes.*
import org.json.JSONObject

class PersonajesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personajes)

        val bundle: Bundle?  = intent.extras
        if (bundle!=null){
            val personajesString = bundle!!.getString("personaje")
            val personaje = JSONObject(personajesString)
            val name =  personaje["Nombre"]
            val dni = personaje["DNI"]
            val surname = personaje["Apellido"]
            val phone = personaje["Telefono"]
            val foto = personaje["Foto"]

            //Foto
            Glide.with(this).load(foto).into(imageView2)
            textView.text = "Personaje: "+name+" "+surname+"\n"+"DNI: "+dni+"\nTeléfono: "+phone

            //Contactar personaje
            var phone1:String?=""
            btCall.setOnClickListener {
                phone1 = phone.toString().trim()
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null))
                startActivity(intent)
            }
        }
    }
}