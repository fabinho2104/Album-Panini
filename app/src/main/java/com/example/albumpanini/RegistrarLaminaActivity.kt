package com.example.albumpanini

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarLaminaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_lamina)

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val dbHelper = DatabaseHelper(this)

        btnGuardar.setOnClickListener {

            val numeroTexto = etNumero.text.toString()
            val nombre = etNombre.text.toString()

            if (numeroTexto.isEmpty() || nombre.isEmpty()) {

                Toast.makeText(
                    this,
                    "Complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val numero = numeroTexto.toInt()

            val laminaExistente =
                dbHelper.buscarLaminaPorNumero(numero)

            if (laminaExistente != null) {

                val nuevasRepetidas =
                    laminaExistente.repetidas + 1

                dbHelper.actualizarRepetidas(
                    numero,
                    nuevasRepetidas
                )

                Toast.makeText(
                    this,
                    "Lámina repetida registrada",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val nuevaLamina = Lamina(
                    numero,
                    nombre,
                    true,
                    0
                )

                val guardado =
                    dbHelper.insertarLamina(nuevaLamina)

                if (guardado) {

                    Toast.makeText(
                        this,
                        "Lámina guardada",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Toast.makeText(
                        this,
                        "La lámina ya existe",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Log.d(
                    "NUEVA",
                    nuevaLamina.toString()
                )
            }

            Log.d(
                "REPOSITORIO",
                RepositorioLaminas.laminas.toString()
            )
        }
    }
}