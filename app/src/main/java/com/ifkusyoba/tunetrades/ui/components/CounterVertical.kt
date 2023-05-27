package com.ifkusyoba.tunetrades.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme

@Composable
fun CounterVertical(
    id: Int,
    orderCount: Int,
    onProductIncreased: (Int) -> Unit,
    onProductDecreased: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.size(width = 110.dp, height = 40.dp).padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.LightGray,
            contentColor = Color.DarkGray,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "-",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased(id)
                    }
            )
        }
        Text(
            text = orderCount.toString() + "X",
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.LightGray,
            contentColor = Color.DarkGray,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "+",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased(id)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreviewVertical() {
    TuneTradesTheme {
        CounterVertical(id = 1, orderCount = 0, onProductIncreased = {}, onProductDecreased = {})
    }
}