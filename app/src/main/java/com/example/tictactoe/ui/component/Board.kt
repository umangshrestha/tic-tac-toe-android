package com.example.tictactoe.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.model.BoardType
import com.example.tictactoe.model.Player
import androidx.compose.ui.Modifier


@Composable
fun Board(
    board: BoardType,
    modifier: Modifier = Modifier,
    onSquareClick: (Int) -> Unit = {},
    isDisabled: (Int) -> Boolean = { false }
) {
    Column {
        for (row in 0 until 3) {
            Row {
                for (col in 0..2) {
                    val pos = row * 3 + col
                    BoardItem(
                        value = board[pos].toString(),
                        onClick = { onSquareClick(pos) },
                        enabled = !isDisabled(pos),
                        modifier = modifier
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    val board = remember {
        mutableStateListOf<Player>(
            Player.None, Player.None, Player.None,
            Player.None, Player.None, Player.None,
            Player.None, Player.None, Player.None
        )
    }

    fun onSquareClick(index: Int) {
        board[index] = when (board[index]) {
            Player.None -> Player.X
            Player.X -> Player.O
            Player.O -> Player.None
        }
    }
    Board(board = board.toTypedArray(), onSquareClick = ::onSquareClick)
}

