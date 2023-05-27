package com.ifkusyoba.tunetrades.ui.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ifkusyoba.tunetrades.data.model.OrderMusic
import com.ifkusyoba.tunetrades.di.Injection
import com.ifkusyoba.tunetrades.ui.components.ItemCard
import com.ifkusyoba.tunetrades.ui.components.SearchBar
import com.ifkusyoba.tunetrades.ui.state.UiState
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme
import com.ifkusyoba.tunetrades.viewmodel.HomeViewModel
import com.ifkusyoba.tunetrades.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onCartClicked: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    val searchQuery = remember { mutableStateOf("") }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getAllItem()
            }
            is UiState.Success -> {
                HomeView(
                    orderMusic = state.data,
                    navigateToDetail = navigateToDetail,
                    searchQuery = searchQuery.value,
                    onSearchQuery = { searchQuery.value = it },
                    onCartClicked = {onCartClicked()},
                    modifier = modifier
                )
            }
            is UiState.Error -> {
                // *Nothiing
            }
        }
    }
}

@Composable
fun HomeView(
    orderMusic: List<OrderMusic>,
    navigateToDetail: (Long) -> Unit,
    searchQuery: String,
    onSearchQuery: (String) -> Unit,
    onCartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // *Search
    val filteredOrderMusic = if (searchQuery.isNotBlank()) {
        orderMusic.filter { data ->
            data.music.title.contains(searchQuery, ignoreCase = true)
        }
    } else {
        orderMusic
    }

    Column(modifier = modifier) {
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQuery,
            onCartClicked = onCartClicked,
            modifier = modifier
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier.testTag("MusicList")
        ) {
            items(filteredOrderMusic) { data ->
                ItemCard(
                    title = data.music.title,
                    image = data.music.image,
                    price = data.music.price,
                    modifier = modifier.clickable {
                        navigateToDetail(data.music.id)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TuneTradesTheme {
        HomeScreen(navigateToDetail = {}, onCartClicked = {})
    }
}