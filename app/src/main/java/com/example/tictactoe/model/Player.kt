package com.example.tictactoe.model

enum class Player {
    X,
    O,
    None;

    override fun toString(): String {
        return when (this) {
            X -> "X"
            O -> "O"
            None -> ""
        }
    }
}


fun Player.toggle(): Player = when (this) {
    Player.X -> Player.O
    Player.O -> Player.X
    Player.None -> Player.None
}