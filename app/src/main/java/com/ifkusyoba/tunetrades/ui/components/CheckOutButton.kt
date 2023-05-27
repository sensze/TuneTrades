package com.ifkusyoba.tunetrades.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme

@Composable
fun CheckOutButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Surface(shape = RoundedCornerShape(size = 5.dp)) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
                .semantics(mergeDescendants = true) {
                    contentDescription = context.getString(R.string.content_order_button)
                }
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckOutButtonPreview() {
    TuneTradesTheme {
        CheckOutButton(text = "Checkout", onClick = {})
    }
}