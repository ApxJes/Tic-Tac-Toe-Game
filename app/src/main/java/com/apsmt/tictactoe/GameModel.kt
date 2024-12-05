package com.apsmt.tictactoe

import kotlin.random.Random

data class GameModel(
    var fillPos: MutableList<String> = mutableListOf("","","","","","","","",""),
    var currentPlayer: String = (arrayOf("X","O"))[Random.nextInt(2)],
    var winner: String = "",
    var status: Status? = null
)