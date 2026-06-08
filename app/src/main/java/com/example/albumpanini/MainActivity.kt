package com.example.albumpanini

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.content.Intent
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {

            val intent = Intent(
                this,
                RegistrarLaminaActivity::class.java
            )

            startActivity(intent)
        }

        val btnObtenidas =
            findViewById<Button>(R.id.btnObtenidas)

        btnObtenidas.setOnClickListener {

            val intent =
                Intent(this, ObtenidasActivity::class.java)

            startActivity(intent)
        }

        val btnRepetidas =
            findViewById<Button>(R.id.btnRepetidas)

        btnRepetidas.setOnClickListener {

            val intent =
                Intent(this, RepetidasActivity::class.java)

            startActivity(intent)
        }

        val btnIntercambiar =
            findViewById<Button>(R.id.btnIntercambiar)

        btnIntercambiar.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    IntercambiarActivity::class.java
                )
            )
        }

        val btnPendientes =
            findViewById<Button>(R.id.btnPendientes)

        btnPendientes.setOnClickListener {

            val intent = Intent(
                this,
                PendientesActivity::class.java
            )

            startActivity(intent)
        }

        val btnBuscarJugador =
            findViewById<Button>(R.id.btnBuscarJugador)

        btnBuscarJugador.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    BuscarJugadorActivity::class.java
                )
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}