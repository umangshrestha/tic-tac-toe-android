package com.example.tictactoe.model



data class Game(
    val gameState: GameState = GameState.CONFIGURATION,
    val isXTurn: Boolean = true,
    var board: BoardType = Array(9) { Player.None },
    var winningPosition: Array<Int> = emptyArray(),
    var ai: Player = Player.X,
    var history: ArrayDeque<Int> = ArrayDeque(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (gameState != other.gameState) return false
        if (isXTurn != other.isXTurn) return false
        if (!board.contentEquals(other.board)) return false
        if (!winningPosition.contentEquals(other.winningPosition)) return false
        if (ai != other.ai) return false
        if (history != other.history) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gameState.hashCode()
        result = 31 * result + isXTurn.hashCode()
        result = 31 * result + board.contentHashCode()
        result = 31 * result + winningPosition.contentHashCode()
        result = 31 * result + ai.hashCode()
        result = 31 * result + history.hashCode()
        return result
    }
}