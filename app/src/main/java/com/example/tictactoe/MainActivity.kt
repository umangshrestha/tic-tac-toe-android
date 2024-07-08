package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import com.example.tictactoe.ui.theme.TicTacToeTheme
import com.example.tictactoe.viewModel.GameViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val gameViewModel: GameViewModel by viewModels()
            TicTacToeTheme {
                TicTacToeApp(
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}
