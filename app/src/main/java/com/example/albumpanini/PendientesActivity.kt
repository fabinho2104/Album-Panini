package com.example.albumpanini

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PendientesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendientes)

        val listView =
            findViewById<ListView>(R.id.listViewPendientes)

        val dbHelper = DatabaseHelper(this)

        val laminasObtenidas =
            dbHelper.obtenerTodasLasLaminas()

        val numerosObtenidos =
            laminasObtenidas.map { it.numero }

        val listaPendientes = mutableListOf<String>()

        for (i in 1..100) {

            if (!numerosObtenidos.contains(i)) {

                listaPendientes.add(
                    "Lámina $i"
                )
            }
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaPendientes
        )

        listView.adapter = adapter
    }
}