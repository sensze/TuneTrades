package com.ifkusyoba.tunetrades.ui.views.checkout

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.data.model.Music
import com.ifkusyoba.tunetrades.data.model.OrderMusic
import com.ifkusyoba.tunetrades.di.Injection
import com.ifkusyoba.tunetrades.ui.components.CheckOutButton
import com.ifkusyoba.tunetrades.ui.components.CheckoutItem
import com.ifkusyoba.tunetrades.ui.state.UiState
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme
import com.ifkusyoba.tunetrades.viewmodel.CheckoutViewModel
import com.ifkusyoba.tunetrades.viewmodel.ViewModelFactory

@Composable
fun CheckoutScreen(
    viewModel: CheckoutViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    onCheckoutButtonClicked: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getOrderedMusic()
            }
            is UiState.Success -> {
                CheckoutView(
                    state.data,
                    onProductCountChange = { id, count ->
                        viewModel.updateOrderedMusic(id, count)
                    },
                    onCheckoutButtonClicked = onCheckoutButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun CheckoutView(
    state: CheckoutState,
    onProductCountChange: (id: Long, count: Int) -> Unit,
    onCheckoutButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(id = R.string.checkout_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(state.orderMusic, key = { it.music.id }) { item ->
                    CheckoutItem(
                        id = item.music.id,
                        image = item.music.image,
                        title = item.music.title,
                        price = item.music.price * item.count,
                        count = item.count,
                        onProductCountChange = onProductCountChange,
                    )
                    Divider()
                }
            }
        }
        Box {
            Divider(
                thickness = 2.dp,
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.total),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = "Rp. " + state.totalPrice.toString(),
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier
                            .padding(end = 16.dp),
                        textAlign = TextAlign.End
                    )
                }
                CheckOutButton(
                    text = stringResource(id = R.string.checkout, state.totalPrice),
                    enabled = state.orderMusic.isNotEmpty(),
                    onClick = {
                        onCheckoutButtonClicked(
                            Toast.makeText(
                                context,
                                "Item purchased successfully.",
                                Toast.LENGTH_SHORT
                            ).show().toString()
                        )
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    TuneTradesTheme {
        CheckoutView(
            state = CheckoutState(
                orderMusic = listOf(
                    OrderMusic(
                        music = Music(
                            id = 1,
                            image = R.drawable.music_cello,
                            title = "String Section Cello",
                            price = 1500000,
                            description = R.string.description_cello
                        ),
                        count = 1
                    ),
                    OrderMusic(
                        music = Music(
                            id = 2,
                            image = R.drawable.music_violin,
                            title = "String Section Violin",
                            price = 2000000,
                            description = R.string.description_violin
                        ),
                        count = 1
                    )
                ),
                totalPrice = 3500000
            ),
            onProductCountChange = { id, count -> },
            onCheckoutButtonClicked = {}
        )
    }
}