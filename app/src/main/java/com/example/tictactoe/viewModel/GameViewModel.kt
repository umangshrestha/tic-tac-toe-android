package com.example.tictactoe.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tictactoe.engine.findBestMove
import com.example.tictactoe.model.Game
import com.example.tictactoe.model.GameState
import com.example.tictactoe.model.Player
import com.example.tictactoe.model.getWinningPosition
import com.example.tictactoe.model.isBoardFull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object GameViewModel : ViewModel() {
    private const val TAG = "GameViewModel"
    private val _state = MutableStateFlow(Game())
    val state: StateFlow<Game> = _state.asStateFlow()

    fun reset() {
        _state.value = Game(gameState = GameState.IN_PROGRESS, ai = state.value.ai)
        makeAIMove()
    }


    fun setAI(player: Player) {
        Log.d(TAG, "setAI: $player")
        _state.value = _state.value.copy(
            ai = player,
            gameState = GameState.IN_PROGRESS)
    }

    fun isDisabled(pos: Int): Boolean {
        if (_state.value.gameState == GameState.GAME_OVER) {
            return !_state.value.winningPosition.contains(pos)
        }
        return _state.value.board[pos] != Player.None
    }

    private val isAITurn: Boolean
        get() {
            val isXTurn = state.value.isXTurn
            val ai = state.value.ai
            return (isXTurn && ai == Player.X)
                    || (!isXTurn && ai == Player.O)
        }


    private fun revertToLastState() {
        if (state.value.history.isEmpty()) return

        _state.update { currState ->
            val history = currState.history
            val board = currState.board.copyOf()
            val pos = history.removeLast()
            board[pos] = Player.None
            currState.copy(
                board = board,
                isXTurn = !currState.isXTurn,
                gameState = GameState.IN_PROGRESS,
                history = history,
            )
        }
    }

    fun undo() {
        if (state.value.history.isEmpty()) return
        revertToLastState()
        if (state.value.ai != Player.None) {
            revertToLastState()
        }
        processIfWin()
    }


    private fun makeAIMove() {
        Log.d(TAG, "makeAIMove: ==> ${!isAITurn}")

        if (!isAITurn) return

        _state.update { currState ->
            Log.d(TAG, "makeAIMove: ")
            val board = currState.board.copyOf()
            val bestMove = findBestMove(currState.board, currState.ai)
            Log.d(TAG, "makeAIMove: $bestMove")

            if (bestMove != -1) {
                state.value.history += bestMove
                board[bestMove] = state.value.ai
                currState.copy(
                    board = board,
                    isXTurn = !currState.isXTurn,
                )
            } else
                currState
        }
    }


    fun onSquareClick(pos: Int) {
        Log.d(TAG, "onSquareClick: $pos ${state.value}")
        if (state.value.gameState == GameState.GAME_OVER) return
        if (state.value.board[pos] != Player.None) return
        Log.d(
            TAG, "Is AI turn: $isAITurn")
        makeMove(pos)
        processIfWin()
        makeAIMove()
        processIfWin()
    }

    private fun makeMove(pos: Int) {
        Log.d(TAG, "makeMove: $pos ==> $isAITurn")
        if (isAITurn) return
        _state.update { currState ->
            Log.d(TAG, "makeMove: $pos")
            currState.history += pos
            val board = currState.board.copyOf()
            board[pos] = if (currState.isXTurn) Player.X else Player.O
            currState.copy(
                board = board,
                isXTurn = !currState.isXTurn
            )
        }
    }

    private fun processIfWin() {
        _state.update { currState ->
            val winningPosition = currState.board.getWinningPosition()

            currState.copy(
                gameState = if (winningPosition.isEmpty() && _state.value.history.size != 9)
                    GameState.IN_PROGRESS else GameState.GAME_OVER,
                winningPosition = winningPosition
            )
        }
    }
}