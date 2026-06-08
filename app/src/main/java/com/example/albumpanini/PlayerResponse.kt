package com.example.albumpanini

data class PlayerResponse(
    val player: List<Player>?
)

data class Player(
    val strPlayer: String?,
    val strNationality: String?,
    val strTeam: String?,
    val strThumb: String?
)