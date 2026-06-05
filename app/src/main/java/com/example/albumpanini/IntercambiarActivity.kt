package com.example.albumpanini

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IntercambiarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intercambiar)

        val etEntregada =
            findViewById<EditText>(R.id.etNumeroEntregada)

        val etRecibida =
            findViewById<EditText>(R.id.etNumeroRecibida)

        val etNombre =
            findViewById<EditText>(R.id.etNombreRecibida)

        val btnIntercambiar =
            findViewById<Button>(R.id.btnIntercambiar)

        btnIntercambiar.setOnClickListener {

            val numeroEntregado =
                etEntregada.text.toString().toInt()

            val numeroRecibido =
                etRecibida.text.toString().toInt()

            val nombreRecibido =
                etNombre.text.toString()

            val laminaEntregada =
                RepositorioLaminas.laminas.find {
                    it.numero == numeroEntregado
                }

            if (laminaEntregada == null ||
                laminaEntregada.repetidas <= 0) {

                Toast.makeText(
                    this,
                    "No tienes repetidas para intercambiar",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            laminaEntregada.repetidas--

            val existente =
                RepositorioLaminas.laminas.find {
                    it.numero == numeroRecibido
                }

            if (existente == null) {

                RepositorioLaminas.laminas.add(
                    Lamina(
                        numeroRecibido,
                        nombreRecibido,
                        true,
                        0
                    )
                )
            }

            Toast.makeText(
                this,
                "Intercambio realizado",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}