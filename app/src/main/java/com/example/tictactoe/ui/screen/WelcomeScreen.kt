package com.example.tictactoe.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.model.Player
import com.example.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun WelcomeScreen(
    onStartGame: (Player) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(20.dp))
        Button(
            onClick = { onStartGame(Player.None) },
            shape = RectangleShape,
            modifier = modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            Text(text = "Player with fiend")
        }

        Text(text = "Player with AI")
        Column {
            Button(
                onClick = { onStartGame(Player.O) },
                shape = RectangleShape,
                modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
            ) {
                Text(text = "Play as X")
            }
            Button(
                onClick = { onStartGame(Player.X) },
                shape = RectangleShape,
                modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
            ) {
                Text(text = "Play as O")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TicTacToeTheme {
        WelcomeScreen(onStartGame = {})
    }
}