package com.ifkusyoba.tunetrades.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onCartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        TextField(
            value = query, onValueChange = onQueryChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = context.getString(R.string.content_icon_search)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = context.getString(R.string.search_bar),
                    style = MaterialTheme.typography.body1
                )
            },
            modifier = modifier
                .padding(8.dp)
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
        IconButton(
            onClick = { onCartClicked() },
            modifier = Modifier.padding(start = 8.dp).size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = context.getString(R.string.cart),
                tint = MaterialTheme.colors.surface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    TuneTradesTheme {
        SearchBar("", {}, {})
    }
}