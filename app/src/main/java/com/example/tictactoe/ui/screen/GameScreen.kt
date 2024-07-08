package com.example.tictactoe.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.component.Board
import com.example.tictactoe.ui.component.GameStatus
import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.viewModel.GameViewModel


@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
) {
    val value = gameViewModel.state.collectAsState().value

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            "Tic Tac Toe",
            fontSize = 50.sp,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        GameStatus(value.isXTurn, modifier = Modifier.size(70.dp))
        Board(
            value.board,
            onSquareClick = gameViewModel::onSquareClick,
            isDisabled = gameViewModel::isDisabled
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            Button(
                onClick = gameViewModel::undo,
                enabled = value.history.isNotEmpty(),
                shape = RectangleShape,
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                Text("Undo")
            }
            Button(
                onClick = gameViewModel::reset,
                enabled = value.history.isNotEmpty(),
                shape = RectangleShape,
                modifier = Modifier
            ) {
                Text("Reset")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        GameScreen(GameViewModel)
    }
}