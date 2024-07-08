package com.example.tictactoe.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.model.Player
import com.example.tictactoe.ui.theme.DarkBlue
import com.example.tictactoe.ui.theme.LightGray



@Composable
fun GameStatus(
    isXTurn: Boolean,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 60.sp,
) {
    val color = Color.White
    val backgroundColor = DarkBlue
    val disabledColor = LightGray
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(Color.Transparent)
    ) {
        Text(
            text = Player.X.toString(),
            fontSize = fontSize,
            color = color,
            modifier = modifier
                .background(if (isXTurn) backgroundColor else disabledColor),
            textAlign = TextAlign.Center

        )
        Text(
            text = Player.O.toString(),
            fontSize = fontSize,
            color = color,
            modifier = modifier
                .background(if (!isXTurn) backgroundColor else disabledColor),
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun StatusIconPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val color = remember {
            mutableStateOf(true)
        }

        GameStatus(color.value, modifier = Modifier.size(80.dp).padding(top=5.dp))
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            shape = RectangleShape,
            onClick = { color.value = !color.value }
        ) {
            Text(
                text = "Toggle",
                fontSize = 30.sp,
                modifier = Modifier
            )
        }
    }
}



