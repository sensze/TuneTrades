package com.ifkusyoba.tunetrades.ui.views.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme

@Composable
fun AccountScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.account),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 64.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            backgroundColor = MaterialTheme.colors.surface
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.nama),
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    TuneTradesTheme {
        AccountScreen(onBackClick = {})
    }
}