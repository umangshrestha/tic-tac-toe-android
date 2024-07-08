package com.example.tictactoe.engine

import com.example.tictactoe.model.BoardType
import com.example.tictactoe.model.Player
import com.example.tictactoe.model.findWinner
import com.example.tictactoe.model.isBoardFull
import com.example.tictactoe.model.isFirstTurn
import com.example.tictactoe.model.toggle


fun findBestMove(board: BoardType, player: Player): Int {
    if (player == Player.None) return -1
    
    var bestScore = Int.MIN_VALUE
    var bestMove = -1

    // If the game just started, play in the center
    if (board.isFirstTurn())
        return 4

    for (i in board.indices) {
        if (board[i] == Player.None) {
            board[i] = player
            val score = minimax(board, 0, false, Int.MIN_VALUE, Int.MAX_VALUE, player)
            board[i] = Player.None
            if (score > bestScore) {
                bestScore = score
                bestMove = i
            }
        }
    }
    return bestMove
}


fun minimax(
    board: BoardType,
    depth: Int,
    isMaximizing: Boolean,
    alpha: Int,
    beta: Int,
    player: Player
): Int {
    val winner = board.findWinner()
    if (winner != Player.None) return if (winner == player) 10 - depth else depth - 10
    if (board.isBoardFull()) return 0

    var newAlpha = alpha
    var newBeta = beta
    if (isMaximizing) {
        var maxEval = Int.MIN_VALUE
        for (i in board.indices) {
            if (board[i] == Player.None) {
                board[i] = player
                val eval = minimax(board, depth + 1, false, newAlpha, newBeta, player)
                board[i] = Player.None
                maxEval = maxOf(maxEval, eval)
                newAlpha = maxOf(newAlpha, eval)
                if (newBeta <= newAlpha) break
            }
        }
        return maxEval
    } else {
        var minEval = Int.MAX_VALUE
        for (i in board.indices) {
            if (board[i] == Player.None) {
                board[i] = player.toggle()
                val eval = minimax(board, depth + 1, true, newAlpha, newBeta, player)
                board[i] = Player.None
                minEval = minOf(minEval, eval)
                newBeta = minOf(newBeta, eval)
                if (newBeta <= newAlpha) break
            }
        }
        return minEval
    }
}