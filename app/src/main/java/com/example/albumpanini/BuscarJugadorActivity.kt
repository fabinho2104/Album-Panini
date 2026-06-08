package com.example.albumpanini

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarJugadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_jugador)

        val etNombreJugador =
            findViewById<EditText>(R.id.etNombreJugador)

        val btnBuscar =
            findViewById<Button>(R.id.btnBuscar)

        val imgJugador =
            findViewById<ImageView>(R.id.imgJugador)

        val tvNombre =
            findViewById<TextView>(R.id.tvNombre)

        val tvEquipo =
            findViewById<TextView>(R.id.tvEquipo)

        val tvNacionalidad =
            findViewById<TextView>(R.id.tvNacionalidad)

        btnBuscar.setOnClickListener {

            val nombreJugador =
                etNombreJugador.text.toString()

            if (nombreJugador.isEmpty()) {

                Toast.makeText(
                    this,
                    "Ingrese un nombre",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            RetrofitClient.api.buscarJugador(
                nombreJugador
            ).enqueue(object : Callback<PlayerResponse> {

                override fun onResponse(
                    call: Call<PlayerResponse>,
                    response: Response<PlayerResponse>
                ) {

                    val jugador =
                        response.body()?.player?.firstOrNull()

                    if (jugador != null) {

                        tvNombre.text =
                            "Nombre: ${jugador.strPlayer}"

                        tvEquipo.text =
                            "Equipo: ${jugador.strTeam}"

                        tvNacionalidad.text =
                            "Nacionalidad: ${jugador.strNationality}"

                        Glide.with(this@BuscarJugadorActivity)
                            .load(jugador.strThumb)
                            .into(imgJugador)

                    } else {

                        Toast.makeText(
                            this@BuscarJugadorActivity,
                            "Jugador no encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<PlayerResponse>,
                    t: Throwable
                ) {

                    Toast.makeText(
                        this@BuscarJugadorActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}