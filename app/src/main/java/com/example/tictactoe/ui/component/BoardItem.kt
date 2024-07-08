package com.example.tictactoe.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.model.Player


@Composable
fun BoardItem(
    value: String,
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    fontSize: TextUnit = 80.sp,
    border: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.inversePrimary,
    borderColor: Color = MaterialTheme.colorScheme.inversePrimary,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier
            .width(width)
            .height(width)
            .border(border, borderColor)
    ) {
        Text(
            value,
            fontSize = fontSize,
            color = (if (enabled) color else Color.Gray)
        )
    }
}


@Preview
@Composable
fun BoardItemPreview() {
    Column {
        Row {
            BoardItem(Player.X.toString(), enabled = false, onClick = {})
            BoardItem(Player.O.toString(), enabled = false, onClick = {})
            BoardItem(Player.None.toString(), enabled = false, onClick = {})
        }
        Row {
            BoardItem(Player.X.toString(), enabled = true, onClick = {})
            BoardItem(Player.O.toString(), enabled = true, onClick = {})
            BoardItem(Player.None.toString(), enabled = true, onClick = {})
        }
    }
}
