package com.ifkusyoba.tunetrades.ui.views.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.di.Injection
import com.ifkusyoba.tunetrades.ui.components.CheckOutButton
import com.ifkusyoba.tunetrades.ui.components.CounterVertical
import com.ifkusyoba.tunetrades.ui.state.UiState
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme
import com.ifkusyoba.tunetrades.viewmodel.DetailViewModel
import com.ifkusyoba.tunetrades.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getItemById(id)
            }
            is UiState.Success -> {
                val data = state.data
                DetailView(
                    data.music.image,
                    data.music.title,
                    data.music.price,
                    data.count,
                    data.music.description,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.music, count)
                        navigateToCart()
                    }
                )
            }
            is UiState.Error -> {/*Nothing*/
            }
        }
    }
}

@Composable
fun DetailView(
    @DrawableRes image: Int,
    title: String,
    price: Int,
    count: Int,
    @StringRes description: Int,
    onAddToCart: (count: Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var orderCount by rememberSaveable { mutableStateOf(0) }
    var totalPrice by rememberSaveable { mutableStateOf(count) }
    val context = LocalContext.current

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Box {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = context.getString(R.string.content_detail_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth()
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = context.getString(R.string.header),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                )
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold, color = Color.Black
                    )
                )
                Text(
                    text = stringResource(id = description),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CounterVertical(
                id = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            totalPrice = price * orderCount
            CheckOutButton(
                text = stringResource(id = R.string.checkout, totalPrice),
                enabled = orderCount > 0,
                onClick = { onAddToCart(orderCount) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailViewPreview() {
    TuneTradesTheme {
        DetailView(
            image = R.drawable.music_cello,
            title = "String Section Cello",
            price = 1500000,
            count = 1,
            description = R.string.description_cello,
            onAddToCart = {},
            onBackClick = {}
        )
    }
}