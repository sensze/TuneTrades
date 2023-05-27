@file:Suppress("NAME_SHADOWING")

package com.ifkusyoba.tunetrades.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.ui.theme.Shapes
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme

@Composable
fun CheckoutItem(
    id: Long,
    title: String,
    image: Int,
    price: Int,
    count: Int,
    onProductCountChange: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        val context = LocalContext.current
        Image(
            painter = painterResource(id = image),
            contentDescription = context.getString(R.string.content_cart_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .padding(2.dp)
                .clip(Shapes.medium)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, start = 4.dp)
                .weight(1.0f)
        ) {
            val context = LocalContext.current
            Text(
                text = context.getString(R.string.header),
                maxLines = 2,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.primary
                )
            )
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(id = R.string.price, price),
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle2
            )
        }
        Counter(
            id = id,
            orderCount = count,
            onProductIncreased = { onProductCountChange(id, count + 1) },
            onProductDecreased = { onProductCountChange(id, count - 1) },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    TuneTradesTheme {
        CheckoutItem(
            id = 1,
            title = "String Section Violin",
            image = R.drawable.music_violin,
            price = 2000000,
            count = 0,
            onProductCountChange = { id, count -> })
    }
}