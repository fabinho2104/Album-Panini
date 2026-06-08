package com.example.albumpanini

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("searchplayers.php")
    fun buscarJugador(
        @Query("p") nombre: String
    ): Call<PlayerResponse>
}