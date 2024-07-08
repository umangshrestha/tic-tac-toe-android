package com.example.tictactoe.model

import com.example.tictactoe.engine.minimax

typealias BoardType = Array<Player>


private val WINNING_COMBINATIONS = arrayOf(
    intArrayOf(0, 1, 2),
    intArrayOf(3, 4, 5),
    intArrayOf(6, 7, 8),
    intArrayOf(0, 3, 6),
    intArrayOf(1, 4, 7),
    intArrayOf(2, 5, 8),
    intArrayOf(0, 4, 8),
    intArrayOf(2, 4, 6)
)


fun BoardType.isBoardFull(): Boolean {
    return none { it == Player.None }
}


fun BoardType.isFirstTurn(): Boolean {
    return all { it == Player.None }
}


fun BoardType.findWinner(): Player {
    for ((x, y, z) in WINNING_COMBINATIONS)
        if (this[x] != Player.None && this[x] == this[y] && this[y] == this[z]) {
            return this[x]
        }
    return Player.None
}


fun BoardType.getWinningPosition(): Array<Int> {
    for ((x, y, z) in WINNING_COMBINATIONS)
        if (this[x] != Player.None && this[x] == this[y] && this[y] == this[z]) {
            return arrayOf(x, y, z)
        }
    return emptyArray()
}
