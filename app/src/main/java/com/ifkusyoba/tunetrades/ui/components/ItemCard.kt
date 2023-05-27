@file:Suppress("NAME_SHADOWING")

package com.ifkusyoba.tunetrades.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
fun ItemCard(
    title: String,
    image: Int,
    price: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(modifier = modifier.shadow(elevation = 2.dp, shape = Shapes.small, clip = false)) {
        Column(modifier = modifier.padding(8.dp)) {
            val context = LocalContext.current
            Image(
                painter = painterResource(id = image),
                contentDescription = context.getString(R.string.content_image_product),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(Shapes.large)
            )
            Spacer(modifier = Modifier.height(8.dp))
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(id = R.string.price, price),
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    TuneTradesTheme {
        ItemCard(
            title = "String Section Cello",
            image = R.drawable.music_cello,
            price = 1500000
        )
    }
}