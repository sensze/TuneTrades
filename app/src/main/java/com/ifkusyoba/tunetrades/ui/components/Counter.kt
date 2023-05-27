package com.ifkusyoba.tunetrades.ui.components

import android.view.Surface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme
import com.ifkusyoba.tunetrades.R

@Composable
fun Counter(
    id: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.size(width = 40.dp, height = 80.dp).padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = Color.LightGray,
            contentColor = Color.DarkGray,
            modifier = Modifier.size(22.dp)
        ) {
            Text(
                text = "+",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased(id)
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
            modifier = Modifier.size(22.dp)
        ) {
            Text(
                text = "-",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased(id)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    TuneTradesTheme {
        Counter(id = 1, orderCount = 0, onProductIncreased = {}, onProductDecreased = {})
    }
}