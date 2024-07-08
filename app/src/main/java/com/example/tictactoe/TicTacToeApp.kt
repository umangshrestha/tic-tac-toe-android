package com.example.tictactoe

import android.app.AlertDialog
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.viewModel.GameViewModel
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tictactoe.model.GameState
import com.example.tictactoe.ui.component.Dialog
import com.example.tictactoe.ui.screen.GameScreen
import com.example.tictactoe.ui.theme.TicTacToeTheme

import com.example.tictactoe.ui.screen.WelcomeScreen

@Composable
fun TicTacToeApp(gameViewModel: GameViewModel) {
    val navController = rememberNavController()

    val gameState = gameViewModel.state.collectAsState().value
    val openAlertDialog = remember { mutableStateOf(false) }

    LaunchedEffect(gameState.gameState) {
        if (gameState.gameState == GameState.GAME_OVER) {
            openAlertDialog.value = true
        }

    }
    NavHost(navController = navController, startDestination = "welcomeScreen") {
        composable("welcomeScreen") {
            Surface {
                WelcomeScreen(onStartGame = { player ->
                    gameViewModel.setAI(player)
                    navController.navigate("gameScreen")
                    gameViewModel.reset()
                })
            }
        }
        composable("gameScreen") {
            Surface {
                GameScreen(gameViewModel)
                if (openAlertDialog.value) {
                    val winningPosition = gameState.winningPosition
                    val turn = gameState.isXTurn
                    val dialogTitle = if (winningPosition.isNotEmpty()) {
                        "${if (!turn) "X" else "O"} wins"
                    } else {
                        "Draw"
                    }
                    Dialog(
                        confirmText = "home page",
                        onConfirmation = {
                            openAlertDialog.value = false
                            navController.navigate("welcomeScreen")
                            gameViewModel.reset()
                        },
                        onDismissRequest = {
                            openAlertDialog.value = false
                        },
                        dialogTitle = dialogTitle,
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        TicTacToeApp(gameViewModel = GameViewModel)
    }
}