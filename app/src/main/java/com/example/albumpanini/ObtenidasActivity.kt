package com.example.albumpanini

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ObtenidasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obtenidas)

        val listView =
            findViewById<ListView>(R.id.listViewObtenidas)

        val dbHelper = DatabaseHelper(this)

        val laminas =
            dbHelper.obtenerTodasLasLaminas()

        val listaTexto = mutableListOf<String>()

        for (lamina in laminas) {

            listaTexto.add(
                "${lamina.numero} - ${lamina.nombre}"
            )
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.item_lamina,
            R.id.tvLamina,
            listaTexto
        )

        listView.adapter = adapter
    }
}